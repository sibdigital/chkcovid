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
    final private String CANT_BE_EMPTY="не может быть пустой";
    final private String SYMBOLS_10_12="может содержать только 10 или 12 символов";

    OrganizationRepo organizationRepo;

    public Validator(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    public UploadProtocol validate(List<Map> excelRows, UploadProtocol uploadProtocol){
        uploadProtocol.setParsedData(excelRows);
        int uploadedCount = 0;
        int errorsCount = 0;
        int alreadyExistsCount = 0;
        for(int i = 0 ; i < excelRows.size(); i++) {
            StringBuilder statusBuilder = new StringBuilder();
            Map map = excelRows.get(i);

            List<Boolean> fieldsAreValid = new ArrayList<>(5){{
                add(false); // ИНН
                add(false); // Организация
                add(false); // Фамилия
                add(false); // Имя
                add(true);  //Отчество
            }};



            String inn = ((String) map.get("inn")).trim();

            if(inn.length() == 0){
                statusBuilder.append(String.format("ИНН: %s\n",CANT_BE_EMPTY));
            } else if(inn.length() != 10 && inn.length() != 12) {
                statusBuilder.append( String.format("ИНН: %s\n",SYMBOLS_10_12));
            } else {
                fieldsAreValid.set(0, true);
            }

            String organizationName = (String) map.get("organizationName");
            String lastname  = (String) map.get("lastname");
            String firstname = (String) map.get("firstname");

            if(organizationName.trim().length() == 0){
                statusBuilder.append( String.format("Наименование организации: %s\n",CANT_BE_EMPTY));
            }else {
                fieldsAreValid.set(1, true);
            }
            if(lastname.trim().length() == 0){
                statusBuilder.append( String.format("Фамилия: %s\n",CANT_BE_EMPTY));
            }else {
                fieldsAreValid.set(2, true);
            }
            if(firstname.trim().length() == 0){
                statusBuilder.append( String.format("Имя: %s\n",CANT_BE_EMPTY));
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
                        statusBuilder.append( ALREDY_EXISTS);
                        alreadyExistsCount++;
                        log.info("Alredy in a base: " + organization.toString());
                    } else {
                        Organization organizationRecord = organizationRepo.saveAndFlush(organization);
                        statusBuilder.append( "OK");
                        log.info(organizationRecord.toString());
                        uploadedCount++;
                    }
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    statusBuilder.append(ex.getMessage()+"\n");
                    errorsCount++;
                }

            } else {
                errorsCount++;
            }
            map.put("status", statusBuilder.toString());
        }
        uploadProtocol.setErrors(errorsCount);
        uploadProtocol.setUploaded(uploadedCount);
        uploadProtocol.setAlreadyExists(alreadyExistsCount);
        return uploadProtocol;
    }

}