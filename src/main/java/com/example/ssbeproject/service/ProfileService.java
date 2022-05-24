package com.example.ssbeproject.service;

import com.example.ssbeproject.entities.Profiles;
import com.example.ssbeproject.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public void  addProfile(Profiles profile){
        profileRepository.addProfile(profile);
    }
}
