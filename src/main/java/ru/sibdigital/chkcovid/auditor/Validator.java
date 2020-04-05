package ru.sibdigital.chkcovid.auditor;

import lombok.extern.log4j.Log4j2;
import ru.sibdigital.chkcovid.domain.Organization;
import ru.sibdigital.chkcovid.repository.OrganizationRepo;
import ru.sibdigital.chkcovid.utils.MapToOrganizationConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class Validator {

    final private String ALREDY_EXISTS="Уже существует в Базе Данных";
    final private String CANT_BE_EMPTY="Не может быть пустой";
    final private String SYMBOLS_10_12="Может содержать 10 или 12 символов";

    OrganizationRepo organizationRepo;

    public Validator(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    public UploadProtocol validate(List<Map> excelRows, UploadProtocol uploadProtocol){
        List<List> errors = new ArrayList<>(excelRows.size());
        uploadProtocol.setStatuses(errors);
        uploadProtocol.setParsedData(excelRows);
        excelRows.forEach(map -> {
            List<String> errorsInRow = new ArrayList<>(5){{
                add("OK");
                add("OK");
                add("OK");
                add("OK");
                add("OK");
            }};

            List<Boolean> fieldsAreValid = new ArrayList<>(5){{
                add(false);
                add(false);
                add(false);
                add(false);
                add(true);
            }};





            errors.add(errorsInRow);

            String itn = ((String) map.get("itn")).trim();

            if(itn.equals("")){
                errorsInRow.set(0, CANT_BE_EMPTY);
            } else if(itn.length() != 10 && itn.length() != 12) {
                errorsInRow.set(0, SYMBOLS_10_12);
            } else {
                fieldsAreValid.set(0, true);
            }

            String organizationName = (String) map.get("organizationName");
            String lastname  = (String) map.get("lastname");
            String firstname = (String) map.get("firstname");

            if(organizationName.trim().equals("")){
                errorsInRow.set(1, CANT_BE_EMPTY);
            }else {
                fieldsAreValid.set(1, true);
            }
            if(lastname.trim().equals("")){
                errorsInRow.set(2, CANT_BE_EMPTY);
            }else {
                fieldsAreValid.set(2, true);
            }
            if(firstname.trim().equals("")){
                errorsInRow.set(3, CANT_BE_EMPTY);
            }else {
                fieldsAreValid.set(3, true);
            }

            boolean allFieldsAreValid = true;


            for (boolean field : fieldsAreValid){
                allFieldsAreValid &= field;
            }

            if(allFieldsAreValid) {
                try{
                    Organization organization = MapToOrganizationConverter.convert(map);

                    Organization organizationRecord = organizationRepo.saveAndFlush(organization);
                    log.info(organizationRecord.toString());
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    errorsInRow.set(0,ALREDY_EXISTS);
                }


            }
        });
        return uploadProtocol;
    }

}