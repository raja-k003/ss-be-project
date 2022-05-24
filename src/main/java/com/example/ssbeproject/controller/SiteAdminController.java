package com.example.ssbeproject.controller;


import com.example.ssbeproject.entities.SiteAdmin;
import com.example.ssbeproject.rest.RestResponse;
import com.example.ssbeproject.service.SiteAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/siteadmin")
public class SiteAdminController {
    @Autowired
    private SiteAdminService siteAdminService;
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<RestResponse> addSiteAdmin(@RequestBody SiteAdmin siteAdmin) throws Exception {
        RestResponse response = siteAdminService.addSiteAdminSer(siteAdmin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE SiteAdmin ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> updateSiteAdmin(@PathVariable("id") String id,
                                                              @RequestBody SiteAdmin siteAdmin) throws Exception {
        RestResponse response = siteAdminService.updateSiteAdmin(id, siteAdmin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // UPDATE SiteAdmin ==> END

    // DELETE SiteAdmin ==> START
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {
            "application/json"})
    public ResponseEntity<RestResponse> deleteSiteAdmin(@PathVariable("id") String id) throws Exception {
        RestResponse response = siteAdminService.deleteSiteAdmin(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // DELETE SiteAdmin ==> END

    // ALL SiteAdmin  LIST==> START
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSiteAdmins(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                              @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                                              @RequestParam(value = "perPage", required = false, defaultValue = "5") int perPage) {
        RestResponse response = siteAdminService.fetchSiteAdmins(name, from, perPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // ALL SiteAdmin  LIST==> END
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<RestResponse> fetchSpecficSiteAdmin(@PathVariable("id") String id) throws Exception {
        RestResponse response = siteAdminService.fetchSpecficSiteAdmin(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
