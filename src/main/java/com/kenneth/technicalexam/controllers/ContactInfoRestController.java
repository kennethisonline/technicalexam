package com.kenneth.technicalexam.controllers;

import com.kenneth.technicalexam.model.Employee;
import com.kenneth.technicalexam.repositories.ContactInfoRepository;
import com.kenneth.technicalexam.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ContactInfoRestController {

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @Autowired
    private ContactInfoRepository contactInfoRepository;

//    @GetMapping("/api/employees")
//    public List<Employee> getEmployees() {
//        return employeeRepository.findAll();
//    }

    @GetMapping("/api/contactInfo/delete/{id}")
    public Map<String, String> deleteContactInfo(@PathVariable Integer id) {
        contactInfoRepository.deleteById(id);
        return Collections.singletonMap("result", "success");
    }
}
