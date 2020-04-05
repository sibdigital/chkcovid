package ru.sibdigital.chkcovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sibdigital.chkcovid.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping
    public String greeting(Map<String, Object> model) {
        List<Organization> organisations = new ArrayList<>(){{
            add(Organization.builder().firstname("Иван").lastname("Иванович").patronymic("Иванов").itn("123456789").organizationName("Водоснабжение").build());
            add(Organization.builder().firstname("Петр").lastname("Петров").patronymic("Петрович").itn("987654321").organizationName("Электросети").build());
            add(Organization.builder().firstname("Зигимунд").lastname("Кржижановский").patronymic("Доминикович").itn("4445").organizationName("Союз писателей").build());
        }};
        model.put("organisations", organisations);
        return "index";
    }

    @PostMapping("/filter")
    public String filter(Organization organisation) {
        return "index";
    }
}