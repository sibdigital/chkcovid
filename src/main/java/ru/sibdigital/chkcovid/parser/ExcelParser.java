package ru.sibdigital.chkcovid.parser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class ExcelParser {
    String[] columnsNamePattern = {"ИНН организации".trim(), "Наименование организации".trim(), "Фамилия".trim(), "Имя".trim(), "Отчество".trim()};
    public List<Map> parseFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String name = file.getOriginalFilename();
        return parseFile(name, inputStream);
    }


    public List<Map> parseFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        String name = file.getName();
        return parseFile(name, inputStream);

    }

    private List<Map> parseFile(String name, InputStream inputStream) throws IOException {
        String[] split = name.split("\\.");
        String ext = split[split.length-1];
        if(ext.equals("xls")){
            return readXLSFile(inputStream);
        } else if(ext.equals("xlsx")) {
            return readXLSXFile(inputStream);
        } else {
            throw new IOException("Cant read the file with extension ." + ext);
        }
    }


    private List<Map> readXLSFile(InputStream inputStream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        return readRows(sheet);
    }

    private List<Map> readXLSXFile(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        return readRows(sheet);
    }

    private List<Map> readRows(Sheet sheet) throws IOException {
        Iterator<Row> rowIterator = sheet.iterator();
        final List<Map> fileData = new ArrayList<>();
        final List<String> columnName = new ArrayList<>(){{
            add("itn");
            add("organizationName");
            add("lastname");
            add("firstname");
            add("patronymic");
        }};
        Row row;
        Map<String,String > record;
        DataFormatter fmt = new DataFormatter();
        if(rowIterator.hasNext()) {


            Row names = rowIterator.next();
            for(int column=0; column<columnName.size(); column++) {
                // If the cell is missing from the file, generate a blank one
                // (Works by specifying a MissingCellPolicy)
                Cell cell = names.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                // Print the cell for debugging
                String name = fmt.formatCellValue(cell).trim();
                if(!name.equals(columnsNamePattern[column])){
                    throw new IOException("Файл не соответствует шаблону. Он должен содержать следующие колонки: " + columnsNamePattern);
                }

            }

        }
        boolean shouldAddRecord = true;
        while (rowIterator.hasNext()) {
            record = new HashMap<>(5);
            shouldAddRecord = true;

            row = rowIterator.next();
            int column = 0;
            for(column=0; column<columnName.size(); column++) {
                // If the cell is missing from the file, generate a blank one
                // (Works by specifying a MissingCellPolicy)
                Cell cell = row.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                // Print the cell for debugging
                String text = fmt.formatCellValue(cell);
                if(text.trim().isBlank() && column != columnName.size()-1) {
                    shouldAddRecord = false;
                    break;
                }
                record.put(columnName.get(column), text);
            }

            if(shouldAddRecord) {
                fileData.add(record);
            }



        }
        return fileData;
    }
}
