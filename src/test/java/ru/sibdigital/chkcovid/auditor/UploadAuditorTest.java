package ru.sibdigital.chkcovid.auditor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
@SpringBootTest
class UploadAuditorTest {

    @Autowired
    UploadAuditor uploadAuditor;

    @Test
    void auditFileXLS() {
        File file = new File("F:\\JavaProjects\\chkcovid\\src\\main\\resources\\example.xls");

        try {
            uploadAuditor.auditFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    void auditFileXLSX() {
        File file = new File("F:\\JavaProjects\\chkcovid\\src\\main\\resources\\example.xlsx");

        try {
            uploadAuditor.auditFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}