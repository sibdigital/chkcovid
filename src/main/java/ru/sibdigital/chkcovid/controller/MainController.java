package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sibdigital.chkcovid.domain.Organization;
import ru.sibdigital.chkcovid.repository.OrganizationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private OrganizationRepo organizationRepo;
    private static final Logger logger = Logger.getLogger(MainController.class);

    private List<Organization> organizations = new ArrayList<>(){{
        add(Organization.builder().firstname("Иван").lastname("Иванович").patronymic("Иванов").inn("123456789").organizationName("Водоснабжение").build());
        add(Organization.builder().firstname("Петр").lastname("Петров").patronymic("Петрович").inn("987654321").organizationName("Электросети").build());
        add(Organization.builder().firstname("Зигимунд").lastname("Кржижановский").patronymic("Доминикович").inn("4445").organizationName("Союз писателей").build());
    }};

    public MainController(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
//        List<Organization> all = organizationRepo.findAll();
        model.put("organizations", organizations);
        return "index";
    }

    @PostMapping("/filter")
    public String filter(Organization organisation, Map<String, Object> model) {
        return "index";
    }
}