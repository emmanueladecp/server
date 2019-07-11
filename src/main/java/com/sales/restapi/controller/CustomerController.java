/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;

import com.sales.restapi.entities.Customer;
import com.sales.restapi.exception.CustomerNotFoundException;
import com.sales.restapi.repo.CustomerRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author ADMIN
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerResourceAssembler customerResourceAssembler;

    public CustomerController(CustomerRepository customerRepository, CustomerResourceAssembler customerResourceAssembler) {
        this.customerRepository = customerRepository;
        this.customerResourceAssembler = customerResourceAssembler;
    }
    // Aggregate root

     @GetMapping("/customers")
     Resources<Resource<Customer>> all() {
      
        List<Resource<Customer>> customers = customerRepository.findAll().stream()
            .map(customerResourceAssembler::toResource)
            .collect(Collectors.toList());

        return new Resources<>(customers,
            linkTo(methodOn(CustomerController.class).all()).withSelfRel());
        
     }

     @PostMapping("/customers")
     ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) throws URISyntaxException {
        Resource<Customer> resource = customerResourceAssembler.toResource(customerRepository.save(newCustomer));
        
        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
     }

     // Single item

     @GetMapping("/customers/{id}")
     Resource<Customer> one(@PathVariable Long id) {

       Customer customer = customerRepository.findById(id)
         .orElseThrow(() -> new CustomerNotFoundException(id));
        
         return customerResourceAssembler.toResource(customer);
     }

     @PutMapping("/customers/{id}")
     ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) throws URISyntaxException{

       Customer updatedCustomer = customerRepository.findById(id)
         .map(customer -> {
           customer.setName(newCustomer.getName());
           customer.setPhone(newCustomer.getPhone());
           customer.setEmail(newCustomer.getEmail());
           return customerRepository.save(customer);
         })
         .orElseGet(() -> {
           newCustomer.setId(id);
           return customerRepository.save(newCustomer);
         });
       
        Resource<Customer> resource = customerResourceAssembler.toResource(updatedCustomer);

        return ResponseEntity
        .created(new URI(resource.getId().expand().getHref()))
        .body(resource);
     }

     @DeleteMapping("/customers/{id}")
     ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
       customerRepository.deleteById(id);
       
       return ResponseEntity.noContent().build();
     }
     
     
}
