package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/filter")
    public @ResponseBody List<DocPerson> filter(@RequestParam("inn") String inn,
                                                @RequestParam("lastname") String lastname,
                                                @RequestParam("firstname") String firstname,
                                                @RequestParam("patronymic") String patronymic) {
        List<DocPerson> people = null;

        if (patronymic.isBlank()) {
            people = personRepository.findAllByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(inn, lastname, firstname);
        } else {
            people = personRepository.findAllByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(inn, lastname, firstname, patronymic);
        }

        return people;
    }

//    @PostMapping("/")
//    public @ResponseBody List<DocPerson> filter(@RequestBody DocPerson person) {
//        List<DocPerson> people = null;
//
//        if (person.getPatronymic().equals("")) {
//            people = personRepository.findAllByInnAndLastnameAndFirstname(person.getInn(), person.getLastname(), person.getFirstname());
//        } else {
//            people = personRepository.findAllByInnAndLastnameAndFirstnameAndPatronymic(person.getInn(), person.getLastname(), person.getFirstname(), person.getPatronymic());
//        }
//
//        return people;
//    }
}