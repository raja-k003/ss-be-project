package com.example.ssbeproject.service;

import com.example.ssbeproject.controller.CustomerController;
import com.example.ssbeproject.entities.Customer;
import com.example.ssbeproject.repository.CustomerRepository;
import com.example.ssbeproject.rest.ResponseCodes;
import com.example.ssbeproject.rest.RestResponse;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;


   public RestResponse addCustomer(Customer customer){
       customer.setCreatedOn(new Date());
       customerRepository.addCustomer(customer);
       RestResponse response = new RestResponse();
       response.setCode(ResponseCodes.CREATED);
       response.setStatus(true);
       response.setMessage("Customer created successfully");
       return response;
    }

    public RestResponse  updateCustomer(String id,Customer customer){
        customerRepository.updateCustomer(new ObjectId(id), customer);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("Customer updated successfully");
        return response;

    }
    public RestResponse deleteCustomer(String id){
        customerRepository.deleteCustomer(new ObjectId(id));
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodes.CREATED);
        response.setStatus(true);
        response.setMessage("Customer deleted successfully");
        return response;
    }
    public RestResponse fetchCustomers(String name, int from, int perPage){
        List<Customer> customerList = customerRepository.fetchCustomers(name, from, perPage + 1);
        JSONObject data = new JSONObject();
        if (customerList.size() > 0) {
            boolean more = false;
            if(customerList.size() == perPage + 1){
                customerList.remove(perPage);
                more = true;
            }
            data.put("customers", customerList);
            data.put("more", more);
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(data);
            response.setMessage("customers Listed");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("customers not found");
            return response;
        }

    }
    public RestResponse fetchSpecficCustomerSer(String id){
        Customer customer = customerRepository.fetchCustomerRep(new ObjectId(id));
        if(customer != null) {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.CREATED);
            response.setStatus(true);
            response.setData(customer);
            response.setMessage("customer details");
            return response;
        } else {
            RestResponse response = new RestResponse();
            response.setCode(ResponseCodes.FAILED);
            response.setStatus(false);
            response.setMessage("customer not found");
            return response;
        }
    }

}
