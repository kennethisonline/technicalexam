package com.kenneth.technicalexam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class EmployeeController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        System.out.println("from hello controller");
        model.addAttribute("datetimenow", LocalDateTime.now());
        return "hellotest";
    }
}
