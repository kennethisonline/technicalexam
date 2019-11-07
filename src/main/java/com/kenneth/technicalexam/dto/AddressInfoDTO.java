package com.kenneth.technicalexam.dto;

import com.kenneth.technicalexam.model.AddressInfo;
import com.kenneth.technicalexam.model.ContactInfo;
import com.kenneth.technicalexam.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressInfoDTO {

    private Integer addressInfoID;

    private String address1;
    private String address2;
    private Boolean isPrimary;

    public AddressInfoDTO() { }

    public AddressInfoDTO(AddressInfo addressInfo) {
        this.addressInfoID = addressInfo.getAddressInfoID();
        this.address1 = addressInfo.getAddress1();
        this.address2 = addressInfo.getAddress2();
        this.isPrimary = addressInfo.getPrimary();
    }

    public Integer getAddressInfoID() {
        return addressInfoID;
    }

    public void setAddressInfoID(Integer addressInfoID) {
        this.addressInfoID = addressInfoID;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public static List<AddressInfo> generateAddressEntities(Map<String,String> otherFields, Employee employee) {
        List<AddressInfo> addressInfoList = new ArrayList<>();

        List<Integer> addressInfoIDs = otherFields.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("addressInfo_address1_"))
                .map(entry -> Integer.parseInt(entry.getKey().substring(21, entry.getKey().length())))
                .collect(Collectors.toList());

        Integer primaryAddress = -1;
        String primaryAddressStr = otherFields.get("primaryAddress");
        if (primaryAddressStr != null && !primaryAddressStr.trim().isEmpty()) {
            try {
                primaryAddress = Integer.parseInt(primaryAddressStr);
            }
            catch(NumberFormatException nfex) {
                primaryAddress = -1;
            }
        }

        for (Integer addressInfoID : addressInfoIDs) {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setAddressInfoID(addressInfoID);
            addressInfo.setEmployee(employee);
            addressInfo.setAddress1(otherFields.get("addressInfo_address1_" + addressInfoID));
            addressInfo.setAddress2(otherFields.get("addressInfo_address2_" + addressInfoID));
            addressInfo.setPrimary(primaryAddress.equals(addressInfoID));

            addressInfoList.add(addressInfo);
        }

        List<Integer> newAddressInfoIDs = otherFields.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("addressInfoNewRow_address1_"))
                .map(entry -> Integer.parseInt(entry.getKey().substring(27, entry.getKey().length())))
                .collect(Collectors.toList());

        for (Integer newAddressInfoID : newAddressInfoIDs) {
            String address1 = otherFields.get("addressInfoNewRow_address1_" + newAddressInfoID);
            String address2 = otherFields.get("addressInfoNewRow_address2_" + newAddressInfoID);
            if (address1 != null && !address1.trim().isEmpty() && address2 != null && !address2.trim().isEmpty()) {
                AddressInfo addressInfo = new AddressInfo();
                addressInfo.setEmployee(employee);
                addressInfo.setAddress1(address1);
                addressInfo.setAddress2(address2);
                addressInfo.setPrimary(primaryAddressStr != null && primaryAddressStr.equals("addressInfoNewRow_" + newAddressInfoID));

                addressInfoList.add(addressInfo);
            }
        }

        return addressInfoList;
    }
}
