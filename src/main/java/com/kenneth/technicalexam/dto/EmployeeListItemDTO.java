package com.kenneth.technicalexam.dto;

import com.kenneth.technicalexam.model.AddressInfo;
import com.kenneth.technicalexam.model.ContactInfo;
import com.kenneth.technicalexam.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Set;

public class EmployeeListItemDTO {
    private Employee employee;

    public EmployeeListItemDTO(Employee employee) {
        this.employee = employee;
    }

    public Integer getEmployeeID() {
        return this.employee.getEmployeeID();
    }

    public String getName() {
        return this.employee.getFirstName() + " " + this.employee.getLastName();
    }

    public String getPrimaryAddress() {
        Set<AddressInfo> addressInfos = this.employee.getAddressInfos();
        if (addressInfos != null && !addressInfos.isEmpty()) {
            Optional<AddressInfo> optAddressInfo = addressInfos.stream().filter(a -> a.getPrimary()).findFirst();
            if (optAddressInfo.isPresent()) {
                AddressInfo addressInfo = optAddressInfo.get();
                return addressInfo.getAddress1() + ", " + addressInfo.getAddress2();
            }
            else {
                Optional<AddressInfo> optAddressInfo2 = addressInfos.stream().findFirst();
                if (optAddressInfo2.isPresent()) {
                    AddressInfo addressInfo = optAddressInfo2.get();
                    return addressInfo.getAddress1() + ", " + addressInfo.getAddress2();
                }
                else {
                    return "";
                }
            }
        }
        else {
            return "";
        }
    }

    public String getPrimaryContact() {
        Set<ContactInfo> contactInfos = this.employee.getContactInfos();
        if (contactInfos != null && !contactInfos.isEmpty()) {
            Optional<ContactInfo> optContactInfo = contactInfos.stream().filter(c -> c.getPrimary()).findFirst();
            if (optContactInfo.isPresent()) {
                ContactInfo contactInfo = optContactInfo.get();
                return contactInfo.getValue();
            }
            else {
                Optional<ContactInfo> optContactInfo2 = contactInfos.stream().findFirst();
                if (optContactInfo2.isPresent()) {
                    ContactInfo contactInfo = optContactInfo2.get();
                    return contactInfo.getValue();
                }
                else {
                    return "";
                }
            }
        }
        else {
            return "";
        }
    }

    public Integer getAge() {
        LocalDate birthDate = this.employee.getBirthDate();
        if (birthDate != null) {
            Period period = Period.between(birthDate, LocalDate.now());
            return period.getYears();
        }
        else {
            return null;
        }
    }

    public String getYearsInTheCompany() {
        LocalDate dateHired = this.employee.getDateHired();
        if (dateHired != null) {
            Period period = Period.between(dateHired, LocalDate.now());
            int years = period.getYears();
            int months = period.getMonths();
            if (months != 0 && years != 0) {
                return years + "y " + months + "m";
            }
            else if (months != 0 && years == 0) {
                return months + "m";
            }
            else {
                return years + "y";
            }
        }
        else {
            return "";
        }
    }
}
