package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.chkcovid.domain.DocPerson;
import ru.sibdigital.chkcovid.domain.RegStatistic;
import ru.sibdigital.chkcovid.repository.DocPersonRepository;
import ru.sibdigital.chkcovid.repository.RegStatisticRepository;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private DocPersonRepository personRepository;
    private RegStatisticRepository statisticRepository;

    public MainController(DocPersonRepository personRepository,
                          RegStatisticRepository statisticRepository) {
        this.personRepository = personRepository;
        this.statisticRepository = statisticRepository;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "index";
    }

    @PostMapping("/")
    public @ResponseBody
    ResponseEntity filter(@RequestBody DocPerson person) {
        List<DocPerson> people = null;

        if(person.getFirstname() == null) return new ResponseEntity<String>("Firstname required", HttpStatus.BAD_REQUEST);
        if(person.getLastname() == null) return new ResponseEntity<String>("Lastname required", HttpStatus.BAD_REQUEST);
        if(person.getInn() == null) return new ResponseEntity<String>("inn required", HttpStatus.BAD_REQUEST);
        if(person.getFirstname().isBlank()) return new ResponseEntity<String>("Firstname can't be empty", HttpStatus.BAD_REQUEST);
        if(person.getLastname().isBlank()) return new ResponseEntity<String>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
        if(person.getInn().isBlank()) return new ResponseEntity<String>("inn  can't be empty", HttpStatus.BAD_REQUEST);

        try {
            if (person.getPatronymic().equals("")) {
                people = personRepository.findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(person.getInn(), person.getLastname(), person.getFirstname());
            } else {
                people = personRepository.findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(person.getInn(), person.getLastname(), person.getFirstname(), person.getPatronymic());
            }
        } catch (Exception e) {
            logger.error(e.toString());
            this.saveStatistic(person, people);
            return new ResponseEntity<List<DocPerson>>(people, HttpStatus.OK);
        }
        this.saveStatistic(person, people);
        return new ResponseEntity<List<DocPerson>>(people, HttpStatus.OK);
    }

    private void saveStatistic(DocPerson person, List<DocPerson> people) {
        try {
            RegStatistic regStatistic = new RegStatistic(person.getLastname(), person.getFirstname(), person.getPatronymic(), person.getInn(), people.size());
            statisticRepository.save(regStatistic);
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
    }
}