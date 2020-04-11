package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.chkcovid.domain.ClsOrganization;
import ru.sibdigital.chkcovid.domain.DocPerson;
import ru.sibdigital.chkcovid.domain.DocRequest;
import ru.sibdigital.chkcovid.domain.RegStatistic;
import ru.sibdigital.chkcovid.repository.DocPersonRepository;
import ru.sibdigital.chkcovid.repository.DocRequestRepository;
import ru.sibdigital.chkcovid.repository.RegStatisticRepository;

import java.util.List;
import java.util.Map;

@Controller
public class OrgController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private DocPersonRepository personRepository;
    private RegStatisticRepository statisticRepository;
    private DocRequestRepository docRequestRepository;

    public OrgController(DocPersonRepository personRepository,
                          RegStatisticRepository statisticRepository,
                          DocRequestRepository docRequestRepository) {
        this.personRepository = personRepository;
        this.statisticRepository = statisticRepository;
        this.docRequestRepository = docRequestRepository;
    }

    @GetMapping("/org_check")
    public String greeting(Map<String, Object> model) {
        return "index_org";
    }

    @PostMapping("/org_check/")
    public @ResponseBody
    ResponseEntity filter(@RequestBody ClsOrganization clsOrganization) {
        List<DocRequest> docRequests = null;
        if(clsOrganization.getInn() == null) return new ResponseEntity<String>("inn required", HttpStatus.BAD_REQUEST);
        try {
            docRequests = docRequestRepository.findTop100ByInnOrShortName(clsOrganization.getInn().trim().toLowerCase());
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<List<DocRequest>>(docRequests, HttpStatus.OK);
        }
        return new ResponseEntity<List<DocRequest>>(docRequests, HttpStatus.OK);
    }
}