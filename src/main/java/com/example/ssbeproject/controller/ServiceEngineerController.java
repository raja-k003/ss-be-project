package com.example.ssbeproject.controller;

import com.example.ssbeproject.entities.ServiceEngineer;
import com.example.ssbeproject.rest.RestResponse;
import com.example.ssbeproject.service.ServiceEngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/serviceEngineer")
public class ServiceEngineerController {

    @Autowired
    private ServiceEngineerService serviceEngineerService;
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RestResponse> addServiceEngineer(@RequestBody ServiceEngineer serviceEngineer) throws Exception {
        RestResponse response = serviceEngineerService.addserviceEngineerSer(serviceEngineer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE ServiceEngineer ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> updateServiceEngineer(@PathVariable("id") String id,
                                                       @RequestBody ServiceEngineer serviceEngineer) throws Exception {
        RestResponse response = serviceEngineerService.updateServiceEngineer(id, serviceEngineer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE ServiceEngineer ==> END

    // DELETE ServiceEngineer ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> deleteServiceEngineer(@PathVariable("id") String id) throws Exception {
        RestResponse response = serviceEngineerService.deleteServiceEngineer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // DELETE ServiceEngineer ==> END

    // ALL ServiceEngineer  LIST==> START
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchServiceEngineers(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                          @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                                          @RequestParam(value = "perPage", required = false, defaultValue = "5") int perPage) {
        RestResponse response = serviceEngineerService.fetchServiceEngineers(name, from, perPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // ALL ServiceEngineer  LIST==> END
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSpecficServiceEngineer(@PathVariable("id") String id) throws Exception {

        RestResponse response = serviceEngineerService.fetchSpecficServiceEngineer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
