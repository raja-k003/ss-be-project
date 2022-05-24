package com.example.ssbeproject.service;

import com.example.ssbeproject.entities.Profiles;
import com.example.ssbeproject.entities.ServiceEngineer;
import com.example.ssbeproject.enums.Roles;
import com.example.ssbeproject.enums.Status;
import com.example.ssbeproject.repository.ServiceEngineerRepository;
import com.example.ssbeproject.rest.ResponseCodes;
import com.example.ssbeproject.rest.RestResponse;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceEngineerService {
    @Autowired
    private ServiceEngineerRepository serviceEngineerRepository;

    @Autowired
    private ProfileService profileService;

   public RestResponse addserviceEngineerSer(ServiceEngineer serviceEngineer){
       serviceEngineer.setCreatedOn(new Date());
       serviceEngineer.setStatus(Status.ACTIVE);
       serviceEngineer.setRole(Roles.SERVICE_ENGINEER);
       serviceEngineerRepository.addServiceEngineerRep(serviceEngineer);
       Profiles profile = new Profiles();
       profile.setEmail(serviceEngineer.getEmail());
       profile.setName(serviceEngineer.getName());
       profile.setCreatedOn(serviceEngineer.getCreatedOn());
       profile.setRole(serviceEngineer.getRole());
       profile.set_id(serviceEngineer.getId());
       profile.setPassword(serviceEngineer.getPassword());
       profile.setStatus(serviceEngineer.getStatus());
       profileService.addProfile(profile);
       RestResponse response = new RestResponse();
       response.setCode(ResponseCodes.CREATED);
       response.setStatus(true);
       response.setMessage("serviceEngineer created successfully");
       return response;
   }
    public RestResponse  updateServiceEngineer(String id, ServiceEngineer serviceEngineer){
        serviceEngineerRepository.updateServiceEngineer(new ObjectId(id), serviceEngineer);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("ServiceEngineer updated successfully");
        return response;

    }
    public RestResponse deleteServiceEngineer(String id){
        serviceEngineerRepository.deleteServiceEngineer(new ObjectId(id));
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("serviceEngineer deleted successfully");
        return response;
    }
    public RestResponse fetchServiceEngineers(String name, int from, int perPage){
        List<ServiceEngineer> serviceEngineerList = serviceEngineerRepository.fetchServiceEngineers(name, from, perPage + 1);
        JSONObject data = new JSONObject();
        if (serviceEngineerList.size() > 0) {
            boolean more = false;
            if(serviceEngineerList.size() == perPage + 1){
                serviceEngineerList.remove(perPage);
                more = true;
            }
            data.put("serviceEngineer", serviceEngineerList);
            data.put("more", more);
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(data);
            response.setMessage("serviceEngineer Listed");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("serviceEngineer not found");
            return response;
        }

    }
    public RestResponse fetchSpecficServiceEngineer(String id){
        ServiceEngineer serviceEngineer = serviceEngineerRepository.fetchSpecficServiceEngineer(new ObjectId(id));
        if(serviceEngineer != null) {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(serviceEngineer);
            response.setMessage("serviceEngineer details");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("serviceEngineer not found");
            return response;
        }
    }
}
