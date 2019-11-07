package com.kenneth.technicalexam.controllers;

import com.kenneth.technicalexam.repositories.AddressInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.Map;

@RestController
public class AddressInfoRestController {

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @GetMapping("/api/addressInfo/delete/{id}")
    public Map<String, String> deleteAddressInfo(@PathVariable Integer id) {
        addressInfoRepository.deleteById(id);
        return Collections.singletonMap("result", "success");
    }
}
