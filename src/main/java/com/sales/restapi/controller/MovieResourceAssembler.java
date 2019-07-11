/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.sales.restapi.entities.Movie;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler ;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class MovieResourceAssembler  implements ResourceAssembler<Movie, Resource<Movie>> {
//public class CustomerResourceAssembler {
    @Override
    public Resource<Movie> toResource(Movie movie) {
        return new Resource<>(movie,
            linkTo(methodOn(MovieController.class).one(movie.getId())).withSelfRel(),
            linkTo(methodOn(MovieController.class).all()).withRel("movies"));
    }
    
}