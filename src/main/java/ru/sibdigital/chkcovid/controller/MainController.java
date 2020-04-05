package ru.sibdigital.chkcovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sibdigital.chkcovid.domain.Organisation;

import java.util.*;

@Controller
public class MainController {

    @GetMapping
    public String greeting(Map<String, Object> model) {
        List<Organisation> organisations = new ArrayList<>(){{
            add(Organisation.builder().firstname("Иван").lastname("Иванович").patronymic("Иванов").itn("123456789").organizationName("Водоснабжение").build());
            add(Organisation.builder().firstname("Петр").lastname("Петров").patronymic("Петрович").itn("987654321").organizationName("Электросети").build());
            add(Organisation.builder().firstname("Зигимунд").lastname("Кржижановский").patronymic("Доминикович").itn("4445").organizationName("Союз писателей").build());
        }};
        model.put("organisations", organisations);
        return "index";
    }

    @PostMapping("/filter")
    public String filter(Organisation organisation) {
        return "index";
    }
}