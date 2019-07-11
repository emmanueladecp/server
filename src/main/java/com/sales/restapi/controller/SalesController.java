/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;

import com.sales.restapi.entities.Customer;
import com.sales.restapi.entities.FormModel;
import com.sales.restapi.entities.Movie;
import com.sales.restapi.entities.Sales;
import com.sales.restapi.exception.SalesNotFoundException;
import com.sales.restapi.repo.CustomerRepository;
import com.sales.restapi.repo.MovieRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sales.restapi.repo.SalesRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SalesController {
    private final SalesRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final SalesResourceAssembler orderResourceAssembler;
    
    public SalesController(SalesRepository orderRepository, SalesResourceAssembler orderResourceAssembler, CustomerRepository customerRepository, MovieRepository movieRepository){
        this.orderRepository = orderRepository;
        this.orderResourceAssembler = orderResourceAssembler;
        this.customerRepository = customerRepository;
        this.movieRepository = movieRepository;
    }
    
    // Aggregate root

     @GetMapping("/orders")
     Resources<Resource<Sales>> all() {
      
        List<Resource<Sales>> orders = orderRepository.findAll().stream()
            .map(orderResourceAssembler::toResource)
            .collect(Collectors.toList());

        return new Resources<>(orders,
            linkTo(methodOn(SalesController.class).all()).withSelfRel());
        
     }
     
     @PostMapping("/orders")
     ResponseEntity<?> newSales(@RequestBody FormModel formModel) throws URISyntaxException {
        
        Optional<Customer> cust = customerRepository.findById( Long.parseLong(formModel.getCustidhidden()) );
        Optional<Movie> movie = movieRepository.findById( Long.parseLong(formModel.getMovieidhidden()) );
         
         
        Sales newOrder = new Sales(cust.get(), movie.get(), formModel.getMoviebuy()); 
        
        Movie updatedMovie = movie.get();
        updatedMovie.setStock(updatedMovie.getStock() - formModel.getMoviebuy());
        movieRepository.save(updatedMovie);
        
        Resource<Sales> resource = orderResourceAssembler.toResource(orderRepository.save(newOrder));
        
        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
     }
     
     // Single item

     @GetMapping("/orders/{id}")
     Resource<Sales> one(@PathVariable Long id) {

       Sales order = orderRepository.findById(id)
         .orElseThrow(() -> new SalesNotFoundException(id));
        
         return orderResourceAssembler.toResource(order);
     }
     
     @PutMapping("/orders/{id}")
     ResponseEntity<?> replaceSales(@RequestBody Sales newOrder, @PathVariable Long id) throws URISyntaxException{

       Sales updatedOrder = orderRepository.findById(id)
         .map(order -> {
           //order.setCustid(newOrder.getCustid());
           //order.setMovieid(newOrder.getMovieid());
           order.setQtybuy(newOrder.getQtybuy());
           return orderRepository.save(order);
         })
         .orElseGet(() -> {
           newOrder.setId(id);
           return orderRepository.save(newOrder);
         });
       
        Resource<Sales> resource = orderResourceAssembler.toResource(updatedOrder);

        return ResponseEntity
        .created(new URI(resource.getId().expand().getHref()))
        .body(resource);
     }
     
     @DeleteMapping("/orders/{id}")
     ResponseEntity<?> deleteSales(@PathVariable Long id) {
       orderRepository.deleteById(id);
       
       return ResponseEntity.noContent().build();
     }
     
     @GetMapping("/customers/{id}/orders")
     Resources<Resource<Sales>> findSalesByCustomers(@PathVariable Long id) {
         
       List<Resource<Sales>> orders = orderRepository.findByCustomerId(id).stream()
            .map(orderResourceAssembler::toResource)
            .collect(Collectors.toList());
        
       return new Resources<>(orders);
       
     }
     
     @GetMapping("/movies/{id}/orders")
     Resources<Resource<Sales>> findSalesByMovies(@PathVariable Long id) {
         
       List<Resource<Sales>> orders = orderRepository.findByMovieId(id).stream()
            .map(orderResourceAssembler::toResource)
            .collect(Collectors.toList());
       
       return new Resources<>(orders);
       
     }
}
