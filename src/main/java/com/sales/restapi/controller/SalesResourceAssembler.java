/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.sales.restapi.entities.Sales;
import com.sales.restapi.entities.Customer;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler ;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class SalesResourceAssembler implements ResourceAssembler<Sales, Resource<Sales>>{

    @Override
    public Resource<Sales> toResource(Sales order) {
        return new Resource<>(order,
            linkTo(methodOn(SalesController.class).one(order.getId())).withSelfRel(),
            linkTo(methodOn(SalesController.class).all()).withRel("orders"),
            linkTo(methodOn(CustomerController.class).one(order.getCustomer().getId())).withRel("customer"),
            linkTo(methodOn(MovieController.class).one(order.getMovie().getId())).withRel("movie")    
        );
    }
    
}
