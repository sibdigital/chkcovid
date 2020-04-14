package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.chkcovid.domain.ClsOrganization;
import ru.sibdigital.chkcovid.domain.DocDacha;
import ru.sibdigital.chkcovid.domain.DocDachaAddr;
import ru.sibdigital.chkcovid.domain.DocRequest;
import ru.sibdigital.chkcovid.repository.DocDachaAddrRepository;
import ru.sibdigital.chkcovid.repository.DocDachaRepository;

import java.util.List;
import java.util.Map;

@Controller
public class DachaController {
    private static final Logger logger = Logger.getLogger(MainController.class);
    private DocDachaRepository docDachaRepository;
    private DocDachaAddrRepository docDachaAddrRepository;

    public DachaController(DocDachaRepository docDachaRepository,
                           DocDachaAddrRepository docDachaAddrRepository){
        this.docDachaRepository = docDachaRepository;
        this.docDachaAddrRepository = docDachaAddrRepository;
    }

    @GetMapping("/dacha_check")
    public String filterForm(){
        return "index_dacha";
    }

    @PostMapping("/dacha_check/")
    public @ResponseBody
    ResponseEntity filter(@RequestBody Map<String, String> customQuery) {
        List<DocDacha> dachas = null;
        if(customQuery.get("firstname") == null) return new ResponseEntity<String>("Firstname required", HttpStatus.BAD_REQUEST);
        if(customQuery.get("lastname") == null) return new ResponseEntity<String>("Lastname required", HttpStatus.BAD_REQUEST);
        if(customQuery.get("firstname").isBlank()) return new ResponseEntity<String>("Firstname can't be empty", HttpStatus.BAD_REQUEST);
        if(customQuery.get("lastname").isBlank()) return new ResponseEntity<String>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
        if(customQuery.get("district") == null) return new ResponseEntity<String>("District required", HttpStatus.BAD_REQUEST);
        if(customQuery.get("address") == null) return new ResponseEntity<String>("Address required", HttpStatus.BAD_REQUEST);
        if(customQuery.get("district").isBlank()) return new ResponseEntity<String>("District can't be empty", HttpStatus.BAD_REQUEST);
        if(customQuery.get("address").isBlank()) return new ResponseEntity<String>("Address can't be empty", HttpStatus.BAD_REQUEST);
        try {
            if (customQuery.get("patronymic").equals("")) {
                dachas = docDachaRepository.findByFirstnameAndLastnameAndDocDachaAddrs(
                        customQuery.get("firstname"),
                        customQuery.get("lastname"),
                        customQuery.get("district"),
                        customQuery.get("address").trim().toLowerCase());
            } else {
                dachas = docDachaRepository.findByFirstnameAndLastnameAAndPatronymicAndDocDachaAddrs(
                        customQuery.get("firstname"),
                        customQuery.get("lastname"),
                        customQuery.get("patronymic"),
                        customQuery.get("district"),
                        customQuery.get("address").trim().toLowerCase());
            }
//            dachas = docDachaRepository.findAll();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<List<DocDacha>>(dachas, HttpStatus.OK);
        }
        return new ResponseEntity<List<DocDacha>>(dachas, HttpStatus.OK);
    }
}
