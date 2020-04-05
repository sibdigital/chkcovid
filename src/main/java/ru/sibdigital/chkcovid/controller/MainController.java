package ru.sibdigital.chkcovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sibdigital.chkcovid.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping
    public String greeting(Map<String, Object> model) {
        List<Organization> organizations = new ArrayList<>(){{
            add(Organization.builder().firstname("Вавсилий").lastname("Петров").patronymic("").itn("123456789").organizationName("Пупа").build());
            add(Organization.builder().firstname("Вавсилий").lastname("Петров").patronymic("Никитович").itn("123456789").organizationName("Пупа").build());
            add(Organization.builder().firstname("Артем").lastname("Нежданов").patronymic("Василиевич").itn("123456789").organizationName("Пупа").build());
        }};
        model.put("organisations", organizations);
        return "index";
    }

}