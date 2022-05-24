package com.example.ssbeproject.entities;

import com.example.ssbeproject.serializer.NoObjectIdDeSerializer;
import com.example.ssbeproject.serializer.NoObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Date;

@Entity(value = "customers", noClassnameStored = true)
public class Customer implements Serializable {

    @Id
    private Object id;
    private String name;
    private String mobileNumber;
    private String email;
    private String address;
    private Date createdOn;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
