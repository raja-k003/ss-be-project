package com.example.ssbeproject.controller;

import com.example.ssbeproject.entities.SalesPerson;
import com.example.ssbeproject.rest.RestResponse;
import com.example.ssbeproject.service.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/salesperson")
public class SalesPersonController {
    @Autowired
    private SalesPersonService salesPersonService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RestResponse> addSalesPerson(@RequestBody SalesPerson salesPerson) throws Exception {
        RestResponse response = salesPersonService.addSalesPersonSer(salesPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE SalesPerson ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> updateSalesPerson(@PathVariable("id") String id,
                                                              @RequestBody SalesPerson salesPerson) throws Exception {
        RestResponse response = salesPersonService.updateSalesPerson(id, salesPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE SalesPerson ==> END

    // DELETE SalesPerson ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> deleteSalesPerson(@PathVariable("id") String id) throws Exception {
        RestResponse response = salesPersonService.deleteSalesPerson(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // DELETE SalesPerson ==> END

    // ALL SalesPerson  LIST==> START
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSalesPersons(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                              @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                                              @RequestParam(value = "perPage", required = false, defaultValue = "5") int perPage) {
        RestResponse response = salesPersonService.fetchSalesPersons(name, from, perPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // ALL SalesPerson  LIST==> END
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSpecficSalesPerson(@PathVariable("id") String id) throws Exception {

        RestResponse response = salesPersonService.fetchSpecficSalesPerson(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
