package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.chkcovid.domain.Organization;
import ru.sibdigital.chkcovid.repository.OrganizationRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private OrganizationRepo organizationRepo;


    public MainController(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "index";
    }

    @PostMapping("/")
    public @ResponseBody List<Organization> filter(@RequestBody Organization organization) {
        List<Organization> organizations = null;

        if (organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByInnAndLastnameAndFirstname(organization.getInn(), organization.getLastname(), organization.getFirstname());
        } else {
            organizations = organizationRepo.findAllByInnAndLastnameAndFirstnameAndPatronymic(organization.getInn(), organization.getLastname(), organization.getFirstname(), organization.getPatronymic());

        }

        return organizations;
    }
}