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

            // ИНН
        if (!organization.getInn().equals("") && organization.getFirstname().equals("")
                && organization.getLastname().equals("") && organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByInn(organization.getInn());

            //   // ИНН + фамилия + имя
        } else if (!organization.getInn().equals("") && !organization.getFirstname().equals("")
                && !organization.getLastname().equals("") && organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByInnAndLastnameAndFirstname(organization.getInn(), organization.getLastname(), organization.getFirstname());

            // ИНН + фамилия + имя + отчество
        } else if (!organization.getInn().equals("") && !organization.getFirstname().equals("")
                && !organization.getLastname().equals("") && !organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByInnAndLastnameAndFirstnameAndPatronymic(organization.getInn(), organization.getLastname(), organization.getFirstname(), organization.getPatronymic());

            // фамилия + имя + отчество
        } else if (organization.getInn().equals("") && !organization.getFirstname().equals("")
                && !organization.getLastname().equals("") && !organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByLastnameAndFirstnameAndPatronymic(organization.getLastname(), organization.getFirstname(), organization.getPatronymic());

            // имя + отчество
        } else if (organization.getInn().equals("") && !organization.getFirstname().equals("")
                && !organization.getLastname().equals("") && organization.getPatronymic().equals("")) {
            organizations = organizationRepo.findAllByLastnameAndFirstname(organization.getLastname(), organization.getFirstname());
        }

        return organizations;
    }
}