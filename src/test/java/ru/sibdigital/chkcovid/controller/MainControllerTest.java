package ru.sibdigital.chkcovid.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sibdigital.chkcovid.domain.DocPerson;
import ru.sibdigital.chkcovid.domain.DocRequest;
import ru.sibdigital.chkcovid.repository.DocPersonRepository;
import ru.sibdigital.chkcovid.repository.DocRequestRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MainControllerTest {
    String inn = "0303006604";
    String firstname = "Евгений";
    String lastname = "Петров";
    String patronymic = "Даниилович";

//    String inn = "0326004032";
//    String firstname = "Анна";
//    String lastname = "Маняшина";
//    String patronymic = "Егоровна";

    @Autowired
    private DocPersonRepository personRepository;

    @Autowired
    private DocRequestRepository docRequestRepo;

    //@Test
    void filter() {
        List<DocPerson> people = null;

//        people = personRepository.findAllByInnAndLastnameStartsWithIgnoreCaseAndFirstnameStartsWithIgnoreCaseAndPatronymicStartsWithIgnoreCase(inn,
//                                                                                    lastname,
//                                                                                    firstname,
//                                                                                    patronymic);
//
//        people = personRepository.findFirstByInnAndLastnameAndFirstnameAndPatronymic(inn,
//                lastname,
//                firstname,
//                patronymic);
//
//       // List<DocPerson> peopleIt = personRepository.findAll();
//        log.info("people size:" + people.size());
    }

    @Test
    void testFindTop100ByInnOrShortName() {

        String innOrShortName = "Бест";
        List<DocRequest> requests = docRequestRepo.findTop100ByInnOrShortName(innOrShortName.trim().toLowerCase());
        assertNotNull(requests);

        log.info("requests size:" + requests.size());
        if (requests.size() > 0){
            DocRequest req = requests.get(0);

            log.info("organization: " + req.getOrganization().getName());

            log.info("dep: " + req.getDepartment().getName());

        }

    }
}