package com.example.ssbeproject.repository;

import com.example.ssbeproject.entities.SalesPerson;
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
public class SalesPersonRepository {
    @Autowired
    private Datastore datastore;

    public  void addSalesPersonRep(SalesPerson salesPerson){
        datastore.save(salesPerson);
    }

    public void updateSalesPerson(Object id, SalesPerson salesPerson){
        Query<SalesPerson> query = datastore.createQuery(SalesPerson.class).filter("_id",id);
        UpdateOperations<SalesPerson> update = datastore.createUpdateOperations(SalesPerson.class);
        update.set("name",salesPerson.getName());
        datastore.update(query,update);
    }
    public void deleteSalesPerson(Object id) {
        Query<SalesPerson> query = datastore.createQuery(SalesPerson.class).filter("_id", id);
        datastore.delete(query);
    }
    public List<SalesPerson> fetchSalesPersons(String name, int from, int perPage) {
        FindOptions findOptions = new FindOptions();
        findOptions.skip(from);
        findOptions.limit(perPage);

        Query<SalesPerson> query = datastore.createQuery(SalesPerson.class);

        if(!StringUtils.isEmpty(name)) {
            Pattern regexp =
                    Pattern.compile(name, Pattern.CASE_INSENSITIVE);
            query.filter("name", regexp);
        }

        List<SalesPerson> salesPerson =
                query.asList(findOptions);
        return salesPerson;
    }

    public SalesPerson fetchSpecficSalesPerson(Object id){
        Query<SalesPerson> query = datastore.createQuery(SalesPerson.class).filter("_id", id);
        List<SalesPerson> messages = query.find().toList();
        SalesPerson res = null;
        if (messages.size() > 0) {
            res = messages.get(0);
        }
        return res;
    }
}
