package com.kenneth.technicalexam.dto;

import com.kenneth.technicalexam.model.AddressInfo;
import com.kenneth.technicalexam.model.ContactInfo;
import com.kenneth.technicalexam.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDetailDTO {

    private Integer employeeID;

    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String gender;
    private String maritalStatus;
    private String position;
    private String dateHired;

    private List<ContactInfoDTO> contactInfoDTOList;

    private List<AddressInfoDTO> addressInfoDTOList;

    public EmployeeDetailDTO() { }

    public EmployeeDetailDTO(Employee employee) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.employeeID = employee.getEmployeeID();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.middleName = employee.getMiddleName();
        if (employee.getBirthDate() != null) {
            this.birthDate = employee.getBirthDate().format(formatter);
        }
        else {
            this.birthDate = "";
        }
        this.gender = employee.getGender();
        this.maritalStatus = employee.getMaritalStatus();
        this.position = employee.getPosition();
        if (employee.getDateHired() != null) {
            this.dateHired = employee.getDateHired().format(formatter);
        }
        else {
            this.dateHired = "";
        }

        Set<ContactInfo> contactInfos = employee.getContactInfos();
        if (contactInfos != null && !contactInfos.isEmpty()) {
            this.contactInfoDTOList = contactInfos
                    .stream()
                    .map(c -> new ContactInfoDTO(c))
                    .collect(Collectors.toList());
        }

        Set<AddressInfo> addressInfos = employee.getAddressInfos();
        if (addressInfos != null && !addressInfos.isEmpty()) {
            this.addressInfoDTOList = addressInfos
                    .stream()
                    .map(a -> new AddressInfoDTO(a))
                    .collect(Collectors.toList());
        }
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateHired() {
        return dateHired;
    }

    public void setDateHired(String dateHired) {
        this.dateHired = dateHired;
    }

    public List<ContactInfoDTO> getContactInfoDTOList() {
        return contactInfoDTOList;
    }

    public void setContactInfoDTOList(List<ContactInfoDTO> contactInfoDTOList) {
        this.contactInfoDTOList = contactInfoDTOList;
    }

    public List<AddressInfoDTO> getAddressInfoDTOList() {
        return addressInfoDTOList;
    }

    public void setAddressInfoDTOList(List<AddressInfoDTO> addressInfoDTOList) {
        this.addressInfoDTOList = addressInfoDTOList;
    }

    public void updateEmployeeModel(Employee employee) {
        employee.setEmployeeID(this.getEmployeeID());
        employee.setFirstName(this.getFirstName());
        employee.setLastName(this.getLastName());
        employee.setMiddleName(this.getMiddleName());
        employee.setGender(this.getGender());
        employee.setMaritalStatus(this.getMaritalStatus());
        employee.setPosition(this.getPosition());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        employee.setBirthDate(LocalDate.parse(this.getBirthDate(), formatter));
        employee.setDateHired(LocalDate.parse(this.getDateHired(), formatter));
    }
}
