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
        int uploadedCount = 0;
        int errorsCount = 0;
        int alreadyExistsCount = 0;
        for(int i = 0 ; i < excelRows.size(); i++) {

            Map map = excelRows.get(i);

            List<String> errorsInRow = new ArrayList<>(5){{
                add(""); // ИНН
                add("");  // Организация
                add("");  // Фамилия
                add("");  // Имя
                add("");  //Отчество
            }};

            List<Boolean> fieldsAreValid = new ArrayList<>(5){{
                add(false); // ИНН
                add(false); // Организация
                add(false); // Фамилия
                add(false); // Имя
                add(true);  //Отчество
            }};





            errors.add(errorsInRow);

            String inn = ((String) map.get("inn")).trim();

            if(inn.length() == 0){
                errorsInRow.set(0, CANT_BE_EMPTY);
            } else if(inn.length() != 10 && inn.length() != 12) {
                errorsInRow.set(0, SYMBOLS_10_12);
            } else {
                fieldsAreValid.set(0, true);
            }

            String organizationName = (String) map.get("organizationName");
            String lastname  = (String) map.get("lastname");
            String firstname = (String) map.get("firstname");

            if(organizationName.trim().length() == 0){
                errorsInRow.set(1, CANT_BE_EMPTY);
            }else {
                fieldsAreValid.set(1, true);
            }
            if(lastname.trim().length() == 0){
                errorsInRow.set(2, CANT_BE_EMPTY);
            }else {
                fieldsAreValid.set(2, true);
            }
            if(firstname.trim().length() == 0){
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
                    if(organizationRepo.existsByInnAndOrganizationNameAndFirstnameAndLastnameAndPatronymic(
                            organization.getInn(),
                            organization.getOrganizationName(),
                            organization.getFirstname(),
                            organization.getLastname(),
                            organization.getPatronymic()
                    )){
                        map.put("status", "Уже в базе");
                        alreadyExistsCount++;
                        log.info("Alredy in a base: " + organization.toString());
                    } else {
                        Organization organizationRecord = organizationRepo.saveAndFlush(organization);
                        map.put("status", "OK");
                        log.info(organizationRecord.toString());
                        uploadedCount++;
                    }
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    map.put("status", ex.getMessage());
                    errorsCount++;
                }
            } else {
                map.put("status", "ERROR");
                errorsCount++;
            }
        }
        uploadProtocol.setErrors(errorsCount);
        uploadProtocol.setUploaded(uploadedCount);
        uploadProtocol.setAlreadyExists(alreadyExistsCount);
        return uploadProtocol;
    }

}