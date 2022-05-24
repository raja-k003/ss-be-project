package com.example.ssbeproject.entities;

import com.example.ssbeproject.enums.Roles;
import com.example.ssbeproject.enums.Status;
import dev.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Entity(value = "profiles", noClassnameStored = true)
public class Profiles {
    private Object _id;
    private String name;
    private String email;
    private String password;
    private Date createdOn;
    private Roles role;
   private Status status;
    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
