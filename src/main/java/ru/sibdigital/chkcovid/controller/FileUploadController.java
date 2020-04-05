package ru.sibdigital.chkcovid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.sibdigital.chkcovid.auditor.UploadAuditor;
import ru.sibdigital.chkcovid.auditor.UploadProtocol;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    UploadAuditor uploadAuditor;

    @RequestMapping("/upload")
    public String forwardUpload(Model model) {
        model.addAttribute("files", new ArrayList<File>());
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles, Model model) throws IOException {
        List<UploadProtocol> protocols = new ArrayList<>(uploadedFiles.length);
        for(MultipartFile f : uploadedFiles) {
            protocols.add(uploadAuditor.auditFile(f));
        }
        model.addAttribute("protocols", protocols);
        return "upload_result";
    }

}
