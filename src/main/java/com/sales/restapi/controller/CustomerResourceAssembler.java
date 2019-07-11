/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.sales.restapi.entities.Customer;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler ;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class CustomerResourceAssembler  implements ResourceAssembler<Customer, Resource<Customer>> {
//public class CustomerResourceAssembler {
    @Override
    public Resource<Customer> toResource(Customer customer) {
        return new Resource<>(customer,
            linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
            linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
    }
    
}
