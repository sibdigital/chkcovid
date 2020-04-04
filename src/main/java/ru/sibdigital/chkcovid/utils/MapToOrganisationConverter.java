package ru.sibdigital.chkcovid.utils;

import ru.sibdigital.chkcovid.domain.Organisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapToOrganisationConverter {
    static List<Organisation> convert(List<Map> dataToConvert){
        List<Organisation> organisations = new ArrayList<>(dataToConvert.size());
        for (Map row : dataToConvert){
            Organisation organisation = Organisation.builder()
                    .itn("itn")
                    .firstname("firstname")
                    .lastname("lastname")
                    .organizationName("organizationName")
                    .patronymic("patronymic")
                    .build();
            organisations.add(organisation);
        }
        return organisations;
    }
}
