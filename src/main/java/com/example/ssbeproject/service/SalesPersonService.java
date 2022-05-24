package com.example.ssbeproject.service;

import com.example.ssbeproject.entities.Profiles;
import com.example.ssbeproject.entities.SalesPerson;
import com.example.ssbeproject.enums.Roles;
import com.example.ssbeproject.enums.Status;
import com.example.ssbeproject.repository.SalesPersonRepository;
import com.example.ssbeproject.rest.ResponseCodes;
import com.example.ssbeproject.rest.RestResponse;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesPersonService {
    @Autowired
    private SalesPersonRepository SalesPersonRepository;

    public RestResponse addSalesPersonSer(SalesPerson salesPerson){
        salesPerson.setCreatedOn(new Date());
        salesPerson.setStatus(Status.ACTIVE);
        salesPerson.setRole(Roles.SALESPERSON);
        SalesPersonRepository.addSalesPersonRep(salesPerson);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("salesPerson created successfully");
        return response;
    }
    public RestResponse  updateSalesPerson(String id, SalesPerson salesPerson){
        SalesPersonRepository.updateSalesPerson(new ObjectId(id), salesPerson);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("salesPerson updated successfully");
        return response;

    }
    public RestResponse deleteSalesPerson(String id){
        SalesPersonRepository.deleteSalesPerson(new ObjectId(id));
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("salesPerson deleted successfully");
        return response;
    }
    public RestResponse fetchSalesPersons(String name, int from, int perPage){
        List<SalesPerson> salesPersonList = SalesPersonRepository.fetchSalesPersons(name, from, perPage + 1);
        JSONObject data = new JSONObject();
        if (salesPersonList.size() > 0) {
            boolean more = false;
            if(salesPersonList.size() == perPage + 1){
                salesPersonList.remove(perPage);
                more = true;
            }
            data.put("salesPerson", salesPersonList);
            data.put("more", more);
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(data);
            response.setMessage("salesPerson Listed");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("salesPerson not found");
            return response;
        }

    }
    public RestResponse fetchSpecficSalesPerson(String id){
        SalesPerson salesPerson = SalesPersonRepository.fetchSpecficSalesPerson(new ObjectId(id));
        if(salesPerson != null) {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(salesPerson);
            response.setMessage("salesPerson details");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("salesPerson not found");
            return response;
        }
    }
}
