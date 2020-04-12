package ru.sibdigital.chkcovid.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sibdigital.chkcovid.repository.DocDachaAddrRepository;
import ru.sibdigital.chkcovid.repository.DocDachaRepository;

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
}
