package ru.sibdigital.chkcovid.parser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelParser {
    public List<Map> parseFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        String[] split = file.getName().split("\\.");
        String ext = split[split.length-1];
        if(ext.equals("xls")){
            return readXLSFile(inputStream);
        } else if(ext.equals("xlsx")) {
            return readXLSXFile(inputStream);
        } else {
            throw new IOException("Cant read the file with extension ." + ext);
        }

    }

    private List<Map> readXLSFile(FileInputStream inputStream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        return readRows(sheet);
    }

    private List<Map> readXLSXFile(FileInputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        return readRows(sheet);
    }

    private List<Map> readRows(Sheet sheet) {
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
        if(rowIterator.hasNext()) rowIterator.next();

        while (rowIterator.hasNext()) {
            record = new HashMap<>(5);
            fileData.add(record);

            row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            int column = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                record.put(columnName.get(column), fmt.formatCellValue(cell));
                column++;
            }
        }
        return fileData;
    }
}
