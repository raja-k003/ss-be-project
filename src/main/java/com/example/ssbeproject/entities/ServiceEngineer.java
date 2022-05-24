package com.example.ssbeproject.entities;

import com.example.ssbeproject.enums.Roles;
import com.example.ssbeproject.enums.Status;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.Date;

@Entity(value = "serviceEngineer", noClassnameStored = true)
public class ServiceEngineer {
    @Id
    private  Object id;
    private String name;
    private String email;
    private String dateOfBirth;
    private String mobileNumber;
    private String adharNumber;
    private Roles role;
    private String password;
    private Status status;
    private Date createdOn;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
