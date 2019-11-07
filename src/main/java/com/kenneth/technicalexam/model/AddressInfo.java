package com.kenneth.technicalexam.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressInfoID;

    private String address1;
    private String address2;
    private Boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    public AddressInfo() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
