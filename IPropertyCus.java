package com.clarit.hs.service.items;

import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface IPropertyCus {

   public List<Customer> getAll(String id);

    public List<Customer> get(String customerName);

    public Customer add(Customer customer);

    public String delete(String name);

    public Customer update(Customer customer);

    public Customer patch(String name,JsonPatch jsonPatch) ;


}