package com.example.ssbeproject.repository;


import com.example.ssbeproject.entities.SiteAdmin;
import dev.morphia.Datastore;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class SiteAdminRepository {
    @Autowired
    private Datastore datastore;

    public  void addSiteAdminRep(SiteAdmin siteAdmin){
        datastore.save(siteAdmin);
    }

    public void updateSiteAdmin(Object id, SiteAdmin siteAdmin){
        Query<SiteAdmin> query = datastore.createQuery(SiteAdmin.class).filter("_id",id);
        UpdateOperations<SiteAdmin> update = datastore.createUpdateOperations(SiteAdmin.class);
        update.set("name",siteAdmin.getName());
        datastore.update(query,update);
    }
    public void deleteSiteAdmin(Object id) {
        Query<SiteAdmin> query = datastore.createQuery(SiteAdmin.class).filter("_id", id);
        datastore.delete(query);
    }
    public List<SiteAdmin> fetchSiteAdmins(String name, int from, int perPage) {
        FindOptions findOptions = new FindOptions();
        findOptions.skip(from);
        findOptions.limit(perPage);

        Query<SiteAdmin> query = datastore.createQuery(SiteAdmin.class);

        if(!StringUtils.isEmpty(name)) {
            Pattern regexp =
                    Pattern.compile(name, Pattern.CASE_INSENSITIVE);
            query.filter("name", regexp);
        }

        List<SiteAdmin> siteAdmin =
                query.asList(findOptions);
        return siteAdmin;
    }

    public SiteAdmin fetchSpecficSiteAdmin(Object id){
        Query<SiteAdmin> query = datastore.createQuery(SiteAdmin.class).filter("_id", id);
        List<SiteAdmin> messages = query.find().toList();
        SiteAdmin res = null;
        if (messages.size() > 0) {
            res = messages.get(0);
        }
        return res;
    }
}
