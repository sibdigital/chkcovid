package ru.sibdigital.chkcovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sibdigital.chkcovid.domain.Organisation;

import java.util.*;

@Controller
public class MainController {

    @GetMapping
    public String greeting(Map<String, Object> model) {
        List<Organisation> organisations = new ArrayList<>(){{
            add(Organisation.builder().firstname("Вавсилий").lastname("Петров").patronymic("").itn("123456789").organizationName("Пупа").build());
            add(Organisation.builder().firstname("Вавсилий").lastname("Петров").patronymic("Никитович").itn("123456789").organizationName("Пупа").build());
            add(Organisation.builder().firstname("Артем").lastname("Нежданов").patronymic("Василиевич").itn("123456789").organizationName("Пупа").build());


        }};
        model.put("organisations", organisations);
        return "index";
    }

}