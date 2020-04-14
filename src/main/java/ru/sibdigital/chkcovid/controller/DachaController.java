package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.chkcovid.domain.DocDacha;
import ru.sibdigital.chkcovid.repository.DocDachaPersonRepository;
import ru.sibdigital.chkcovid.repository.DocDachaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DachaController {
    private static final Logger logger = Logger.getLogger(MainController.class);
    private DocDachaRepository docDachaRepository;
    private DocDachaPersonRepository docDachaPersonRepository;

    public DachaController(DocDachaRepository docDachaRepository,
                           DocDachaPersonRepository docDachaPersonRepository){
        this.docDachaRepository = docDachaRepository;
        this.docDachaPersonRepository = docDachaPersonRepository;
    }

    @GetMapping("/dacha_check")
    public String filterForm(){
        return "index_dacha";
    }

    @PostMapping("/dacha_check")
    public @ResponseBody
    ResponseEntity filter(@RequestBody Map<String, String> customQuery) {
        List<DocDacha> dachas = new ArrayList<>();
        ResponseEntity badResponse = checkQuery(customQuery);
        if (badResponse != null){
            return badResponse;
        }
        try {
            if (customQuery.get("patronymic").equals("")) {
                LocalDate now = LocalDate.now();
                dachas = docDachaRepository.findByFirstnameAndLastnameAndValidDate(
                        customQuery.get("firstname"),
                        customQuery.get("lastname"),
                        now);

            } else {
                LocalDate now = LocalDate.now();
                dachas = docDachaRepository.findByFirstnameAndLastnameAndPatronymicAndValidDate(
                        customQuery.get("firstname"),
                        customQuery.get("lastname"),
                        customQuery.get("patronymic"),
                        now);
            }
//            dachas = docDachaRepository.findAll();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(dachas, HttpStatus.OK);
        }
        return new ResponseEntity<>(dachas, HttpStatus.OK);
    }

    private ResponseEntity checkQuery(Map<String, String> customQuery){
        ResponseEntity re = null;
        if(customQuery.get("firstname") == null){
            re = new ResponseEntity<String>("Firstname required", HttpStatus.BAD_REQUEST);
        }
        if(customQuery.get("lastname") == null){
            re = new ResponseEntity<String>("Lastname required", HttpStatus.BAD_REQUEST);
        }
        if(customQuery.get("firstname").isBlank()){
            re = new ResponseEntity<String>("Firstname can't be empty", HttpStatus.BAD_REQUEST);
        }
        if(customQuery.get("lastname").isBlank()){
            re = new ResponseEntity<String>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
        }
//        if(customQuery.get("district") == null){
//            re = new ResponseEntity<String>("District required", HttpStatus.BAD_REQUEST);
//        }
//        if(customQuery.get("address") == null) {
//            re = new ResponseEntity<String>("Address required", HttpStatus.BAD_REQUEST);
//        }
//        if(customQuery.get("district").isBlank()){
//            re = new ResponseEntity<String>("District can't be empty", HttpStatus.BAD_REQUEST);
//        }
//        if(customQuery.get("address").isBlank()){
//            re = new ResponseEntity<String>("Address can't be empty", HttpStatus.BAD_REQUEST);
//        }
        return re;
    }
}
