package com.kenneth.technicalexam.controllers;

import com.kenneth.technicalexam.dto.AddressInfoDTO;
import com.kenneth.technicalexam.dto.ContactInfoDTO;
import com.kenneth.technicalexam.dto.EmployeeDetailDTO;
import com.kenneth.technicalexam.dto.EmployeeListItemDTO;
import com.kenneth.technicalexam.model.AddressInfo;
import com.kenneth.technicalexam.model.ContactInfo;
import com.kenneth.technicalexam.model.Employee;
import com.kenneth.technicalexam.repositories.AddressInfoRepository;
import com.kenneth.technicalexam.repositories.ContactInfoRepository;
import com.kenneth.technicalexam.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @GetMapping("/hello")
    public String sayHello(Model model) {
        System.out.println("from hello controller");
        model.addAttribute("datetimenow", LocalDateTime.now());
        return "hellotest";
    }

    @GetMapping("/employees")
    public String showEmployees(Model model) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeListItemDTO> employeeListItemDTOList = employeeList
                .stream()
                .map(e -> new EmployeeListItemDTO(e))
                .collect(Collectors.toList());
        model.addAttribute("employees", employeeListItemDTOList);
        return "employeeList";
    }

    @GetMapping("/employee/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDetailDTO());
        return "employeeDetail";
    }

    @GetMapping("/employee/{id}")
    public String showEmployeeDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", new EmployeeDetailDTO(employeeRepository.findById(id).orElse(new Employee())));
        return "employeeDetail";
    }

    @GetMapping("/employee/delete/{id}")
    public RedirectView deleteEmployee(@PathVariable Integer id, Model model) {
        employeeRepository.deleteById(id);
        return new RedirectView("/employees");
    }

    @PostMapping("/employee/save")
    public RedirectView saveEmployee(EmployeeDetailDTO employeeDTO, Model model, @RequestParam Map<String,String> otherFields) {
        if (otherFields != null) {
            otherFields.entrySet().stream()
                    .forEach(e -> System.out.println("Key: " + e.getKey() + ", Val: " + e.getValue()));
        }

        Employee employee = new Employee();
        employeeDTO.updateEmployeeModel(employee);

        employeeRepository.save(employee);

        List<ContactInfo> contactInfoList = ContactInfoDTO.generateContactEntities(otherFields, employee);
        for (ContactInfo contactInfo : contactInfoList) {
            contactInfoRepository.save(contactInfo);
        }

        List<AddressInfo> addressInfoList = AddressInfoDTO.generateAddressEntities(otherFields, employee);
        for (AddressInfo addressInfo : addressInfoList) {
            addressInfoRepository.save(addressInfo);
        }

        model.addAttribute("status", "Employee Details Saved");
        model.addAttribute("employee", new EmployeeDetailDTO(employee));

        return new RedirectView("/employee/" + employee.getEmployeeID());
    }
}
