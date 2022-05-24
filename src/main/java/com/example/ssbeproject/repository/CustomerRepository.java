package com.example.ssbeproject.repository;

import com.example.ssbeproject.entities.Customer;
import dev.morphia.Datastore;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Autowired
    private Datastore datastore;

    public void addCustomer(Customer customer){
        datastore.save(customer);
    }

    public void updateCustomer(Object id, Customer customer){
        Query<Customer> query = datastore.createQuery(Customer.class).filter("_id",id);
        UpdateOperations<Customer> update = datastore.createUpdateOperations(Customer.class);
        update.set("name",customer.getName());
        datastore.update(query,update);
    }
    public void deleteCustomer(Object id) {
        Query<Customer> query = datastore.createQuery(Customer.class).filter("_id", id);
        datastore.delete(query);
    }
    public List<Customer> fetchCustomers(String name, int from, int perPage) {
        FindOptions findOptions = new FindOptions();
        findOptions.skip(from);
        findOptions.limit(perPage);

        Query<Customer> query = datastore.createQuery(Customer.class);

        if(!StringUtils.isEmpty(name)) {
            Pattern regexp =
                    Pattern.compile(name, Pattern.CASE_INSENSITIVE);
            query.filter("name", regexp);
        }

        List<Customer> customers =
                query.asList(findOptions);
        return customers;
    }

    public Customer fetchCustomerRep(Object id){
        Query<Customer> query = datastore.createQuery(Customer.class).filter("_id", id);
        List<Customer> messages = query.find().toList();
        Customer res = null;
        if (messages.size() > 0) {
            res = messages.get(0);
        }
        return res;
    }
}
