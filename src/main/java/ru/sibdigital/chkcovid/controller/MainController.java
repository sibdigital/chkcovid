package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.chkcovid.domain.DocPerson;
import ru.sibdigital.chkcovid.repository.DocPersonRepository;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private DocPersonRepository personRepository;

    public MainController(DocPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "index";
    }

    @PostMapping("/")
    public @ResponseBody
    List<DocPerson> filter(@RequestBody DocPerson person) {
        List<DocPerson> people = null;

        if (person.getPatronymic().equals("")) {
            people = personRepository.findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(person.getInn(), person.getLastname(), person.getFirstname());
        } else {
            people = personRepository.findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(person.getInn(), person.getLastname(), person.getFirstname(), person.getPatronymic());
        }

        return people;
    }
}