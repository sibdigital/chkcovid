package ru.sibdigital.logistic.parser;

import org.junit.jupiter.api.Test;
import ru.sibdigital.chkcovid.parser.ExcelParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class ExcelParserTest {

    @Test
    void parseFile() {
        ExcelParser excelParser = new ExcelParser();
        try {
            List<Map> maps = excelParser.parseFile(new File("F:\\JavaProjects\\logistic\\src\\main\\resources\\example.xlsx"));

            maps.forEach(map -> {
                map.entrySet().forEach(o -> {
                    System.out.print(o+" ");
                });
                System.out.println();
            });



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}