package com.clarit.hs.service.items.repo;

import com.clarit.hs.service.items.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryCus extends MongoRepository<Customer,String> {



    @Query(value = "{isOccupied:'?0'}")
    List<Customer> findAll(boolean isOccupied);


    @Query(value = "{name:'?0'}", delete = true)
    Customer deleteAll(String name);


    @Query(value = "{name:'?0'}")
    Customer findName(String name);


}

