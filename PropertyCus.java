package com.clarit.hs.service.items;

import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyCus implements IPropertyCus{

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "CUSTOMER";
    @Autowired
    ItemRepositoryCus itemRepositoryCus;

    @Override
    public List<Customer> getAll(String id)
    {
        List<Customer> customers = redisTemplate.opsForHash().values(KEY);
        return getAllCustomers();

    }

    @Override
    public Customer add(Customer customer){

        return bookCustomer(customer);
    }

    @Override

    public String delete(String name) {
        redisTemplate.opsForHash().delete(KEY,name);
        itemRepositoryCus.delete(itemRepositoryCus.deleteAll(name));
        return "deleted Successfully";
    }

    @Override

    public Customer update(Customer customer) {
        return updateCustomer(customer);
    }

    @Override

    public List<Customer> get(String customerName){
        Customer customer;
        customer = (Customer) redisTemplate.opsForHash().get(KEY,customerName);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        return customers;
    }

    private List<Customer> getAllCustomers(){
        return itemRepositoryCus.findAll();
    }

    private Customer bookCustomer(Customer customer){
            redisTemplate.opsForHash().put(KEY,customer.getName().toString(),customer);
        return itemRepositoryCus.save(customer);

    }
    private Customer updateCustomer(Customer customer){
        redisTemplate.opsForHash().put(KEY,customer.getName().toString(),customer);
        return itemRepositoryCus.save(customer);
    }

    @Override
    public Customer patch(String name, JsonPatch jsonPatch){

       Customer customer = (Customer) itemRepositoryCus.findName(name);
        Customer customer1 =  applyPatchToCustomer(jsonPatch,  customer);
        return itemRepositoryCus.save(customer1);
    }

    @Autowired
    ObjectMapper objectMapper;
    @SneakyThrows
    private Customer applyPatchToCustomer(JsonPatch jsonPatch, Customer customer) {
        JsonNode patched= jsonPatch.apply(objectMapper.convertValue(customer, JsonNode.class));
        return objectMapper.treeToValue(patched, Customer.class);

    }
}