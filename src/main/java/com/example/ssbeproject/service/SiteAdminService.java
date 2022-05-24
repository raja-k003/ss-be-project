package com.example.ssbeproject.service;

import com.example.ssbeproject.entities.Profiles;
import com.example.ssbeproject.entities.SiteAdmin;
import com.example.ssbeproject.enums.Roles;
import com.example.ssbeproject.enums.Status;
import com.example.ssbeproject.repository.SiteAdminRepository;
import com.example.ssbeproject.rest.ResponseCodes;
import com.example.ssbeproject.rest.RestResponse;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SiteAdminService {
    @Autowired
    private SiteAdminRepository siteAdminRepository;

    @Autowired
    private ProfileService profileService;

    public RestResponse addSiteAdminSer(SiteAdmin siteAdmin){
        siteAdmin.setCreatedOn(new Date());
        siteAdmin.setStatus(Status.ACTIVE);
        siteAdmin.setRole(Roles.SITE_ADMIN);
        siteAdminRepository.addSiteAdminRep(siteAdmin);
        Profiles profile = new Profiles();
        profile.setEmail(siteAdmin.getEmail());
        profile.setName(siteAdmin.getName());
        profile.setCreatedOn(siteAdmin.getCreatedOn());
        profile.setRole(siteAdmin.getRole());
        profile.set_id(siteAdmin.getId());
        profile.setPassword(siteAdmin.getPassword());
        profile.setStatus(siteAdmin.getStatus());
        profileService.addProfile(profile);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("SiteAdmin created successfully");
        return response;
    }
    public RestResponse  updateSiteAdmin(String id, SiteAdmin siteAdmin){
        siteAdminRepository.updateSiteAdmin(new ObjectId(id), siteAdmin);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("SiteAdmin updated successfully");
        return response;

    }
    public RestResponse deleteSiteAdmin(String id){
        siteAdminRepository.deleteSiteAdmin(new ObjectId(id));
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("SiteAdmin deleted successfully");
        return response;
    }
    public RestResponse fetchSiteAdmins(String name, int from, int perPage){
        List<SiteAdmin> siteAdminList = siteAdminRepository.fetchSiteAdmins(name, from, perPage + 1);
        JSONObject data = new JSONObject();
        if (siteAdminList.size() > 0) {
            boolean more = false;
            if(siteAdminList.size() == perPage + 1){
                siteAdminList.remove(perPage);
                more = true;
            }
            data.put("siteAdmin", siteAdminList);
            data.put("more", more);
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(data);
            response.setMessage("SiteAdmin Listed");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("SiteAdmin not found");
            return response;
        }

    }
    public RestResponse fetchSpecficSiteAdmin(String id){
        SiteAdmin siteAdmin = siteAdminRepository.fetchSpecficSiteAdmin(new ObjectId(id));
        if(siteAdmin != null) {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(siteAdmin);
            response.setMessage("SiteAdmin details");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("SiteAdmin not found");
            return response;
        }
    }
}
