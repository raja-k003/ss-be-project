package com.example.ssbeproject.controller;

import com.example.ssbeproject.entities.Customer;
import com.example.ssbeproject.rest.RestResponse;
import com.example.ssbeproject.service.CustomerService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    // ADD CUSTOMER ==> START
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RestResponse> addCustomer(@RequestBody Customer customer) throws Exception {
        RestResponse response = customerService.addCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // ADD CUSTOMER ==> END

    // UPDATE CUSTOMER ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> updateCustomer(@PathVariable("id") String id,
                                                      @RequestBody Customer customer) throws Exception {
        RestResponse response = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE CUSTOMER ==> END

    // DELETE CUSTOMER ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> deleteCustomer(@PathVariable("id") String id) throws Exception {
        RestResponse response = customerService.deleteCustomer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // DELETE CUSTOMER ==> END

    // ALL CUSTOMER  LIST==> START
     @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchAllCustomers(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                         @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                                         @RequestParam(value = "perPage", required = false, defaultValue = "5") int perPage) {
        RestResponse response = customerService.fetchCustomers(name, from, perPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // ALL CUSTOMER  LIST==> END
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSpecficCustomer(@PathVariable("id") String id) throws Exception {

        RestResponse response = customerService.fetchSpecficCustomerSer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
