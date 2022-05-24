package com.example.ssbeproject.repository;

import com.example.ssbeproject.entities.Profiles;
import com.example.ssbeproject.entities.SalesPerson;
import dev.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {

    @Autowired
    private Datastore datastore;

    public  void addProfile(Profiles profile){
        datastore.save(profile);
    }
}
