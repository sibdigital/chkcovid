package ru.sibdigital.chkcovid.auditor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sibdigital.chkcovid.parser.ExcelParser;
import ru.sibdigital.chkcovid.repository.OrganizationRepo;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class UploadAuditor {


    @Autowired
    ExcelParser excelParser;
    @Autowired
    OrganizationRepo organizationRepo;

    @PostConstruct
    public void initValidator(){
        this.validator = new Validator(organizationRepo);
    }


    private Validator validator;

    public UploadProtocol auditFile(MultipartFile file) {
        UploadProtocol uploadProtocol = new UploadProtocol(file.getOriginalFilename());

        try {
            List<Map> excelRows = excelParser.parseFile(file);

            uploadProtocol = validator.validate(excelRows, uploadProtocol);
        } catch (IOException ex) {
            uploadProtocol.setGlobalMessage(ex.getMessage());
            log.error(ex.getMessage());
        }
        return uploadProtocol;



    };

    public UploadProtocol auditFile(File file) throws IOException {
        UploadProtocol uploadProtocol = new UploadProtocol(file.getName());

        List<Map> excelRows = excelParser.parseFile(file);

        uploadProtocol = validator.validate(excelRows, uploadProtocol);

        return uploadProtocol;



    };
}
