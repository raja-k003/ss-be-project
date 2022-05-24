package com.example.ssbeproject.repository;


import com.example.ssbeproject.entities.ServiceEngineer;
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
public class ServiceEngineerRepository {
    @Autowired
    private Datastore datastore;

    public  void addServiceEngineerRep(ServiceEngineer serviceEngineer){
        datastore.save(serviceEngineer);
    }

    public void updateServiceEngineer(Object id, ServiceEngineer serviceEngineer){
        Query<ServiceEngineer> query = datastore.createQuery(ServiceEngineer.class).filter("_id",id);
        UpdateOperations<ServiceEngineer> update = datastore.createUpdateOperations(ServiceEngineer.class);
        update.set("name",serviceEngineer.getName());
        datastore.update(query,update);
    }
    public void deleteServiceEngineer(Object id) {
        Query<ServiceEngineer> query = datastore.createQuery(ServiceEngineer.class).filter("_id", id);
        datastore.delete(query);
    }
    public List<ServiceEngineer> fetchServiceEngineers(String name, int from, int perPage) {
        FindOptions findOptions = new FindOptions();
        findOptions.skip(from);
        findOptions.limit(perPage);

        Query<ServiceEngineer> query = datastore.createQuery(ServiceEngineer.class);

        if(!StringUtils.isEmpty(name)) {
            Pattern regexp =
                    Pattern.compile(name, Pattern.CASE_INSENSITIVE);
            query.filter("name", regexp);
        }

        List<ServiceEngineer> serviceEngineer =
                query.asList(findOptions);
        return serviceEngineer;
    }

    public ServiceEngineer fetchSpecficServiceEngineer(Object id){
        Query<ServiceEngineer> query = datastore.createQuery(ServiceEngineer.class).filter("_id", id);
        List<ServiceEngineer> messages = query.find().toList();
        ServiceEngineer res = null;
        if (messages.size() > 0) {
            res = messages.get(0);
        }
        return res;
    }
}
