package ru.sibdigital.chkcovid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="User") String name, Map<String, Object> model) {
        return "index";
    }

    @PostMapping("/")
    public String addSort(@RequestParam String arr, Map<String, Object> model) {
        return "index";
    }
}