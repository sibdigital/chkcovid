package ru.sibdigital.chkcovid.utils;

import org.springframework.stereotype.Component;
import ru.sibdigital.chkcovid.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MapToOrganizationConverter {
    public static List<Organization> convert(List<Map> dataToConvert){
        List<Organization> organizations = new ArrayList<>(dataToConvert.size());
        for (Map row : dataToConvert){
            Organization organization = Organization.builder()
                    .inn((String) row.get("inn"))
                    .firstname((String) row.get("firstname"))
                    .lastname((String) row.get("lastname"))
                    .organizationName((String) row.get("organizationName"))
                    .patronymic((String) row.get("patronymic"))
                    .build();
            organizations.add(organization);
        }
        return organizations;
    };

    public static Organization convert(Map dataToConvert){

        Organization organization = Organization.builder()
                .inn((String) dataToConvert.get("inn"))
                .firstname((String) dataToConvert.get("firstname"))
                .lastname((String) dataToConvert.get("lastname"))
                .organizationName((String) dataToConvert.get("organizationName"))
                .patronymic((String) dataToConvert.get("patronymic"))
                .build();


        return organization;
    }
}
