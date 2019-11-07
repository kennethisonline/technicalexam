package com.kenneth.technicalexam.dto;

import com.kenneth.technicalexam.model.ContactInfo;
import com.kenneth.technicalexam.model.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactInfoDTO {

    private Integer contactInfoID;
    private String value;
    private Boolean isPrimary;

    public ContactInfoDTO() { }

    public ContactInfoDTO(ContactInfo contactInfo) {
        this.contactInfoID = contactInfo.getContactInfoID();
        this.value = contactInfo.getValue();
        this.isPrimary = contactInfo.getPrimary();
    }

    public Integer getContactInfoID() {
        return contactInfoID;
    }

    public void setContactInfoID(Integer contactInfoID) {
        this.contactInfoID = contactInfoID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public static List<ContactInfo> generateContactEntities(Map<String,String> otherFields, Employee employee) {
        List<ContactInfo> contactInfoList = new ArrayList<>();

        List<Integer> contactIDs = otherFields.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("contactInfo_"))
                .map(entry -> Integer.parseInt(entry.getKey().substring(12, entry.getKey().length())))
                .collect(Collectors.toList());

        Integer primaryContact = -1;
        String primaryContactStr = otherFields.get("primaryContact");
        if (primaryContactStr != null && !primaryContactStr.trim().isEmpty()) {
            try {
                primaryContact = Integer.parseInt(primaryContactStr);
            }
            catch(NumberFormatException nfex) {
                primaryContact = -1;
            }
        }

        for (Integer contactInfoID : contactIDs) {
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoID(contactInfoID);
            contactInfo.setValue(otherFields.get("contactInfo_" + contactInfoID));
            contactInfo.setEmployee(employee);
            contactInfo.setPrimary(primaryContact.equals(contactInfoID));

            contactInfoList.add(contactInfo);
        }

        List<Integer> newContactIDs = otherFields.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("contactInfoNew_"))
                .map(entry -> Integer.parseInt(entry.getKey().substring(15, entry.getKey().length())))
                .collect(Collectors.toList());

        for (Integer newContactID : newContactIDs) {
            String value = otherFields.get("contactInfoNew_" + newContactID);
            if (value != null && !value.trim().isEmpty()) {
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setValue(value);
                contactInfo.setEmployee(employee);
                contactInfo.setPrimary(primaryContactStr != null && primaryContactStr.equals("contactInfoNew_" + newContactID));

                contactInfoList.add(contactInfo);
            }
        }

        return contactInfoList;
    }
}
