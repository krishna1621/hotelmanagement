package com.clarit.hs.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/graphql")
public interface IGraphqlservice{
    @PostMapping
    public ResponseEntity<Object> getAllCustomer(@RequestBody String query);


}
