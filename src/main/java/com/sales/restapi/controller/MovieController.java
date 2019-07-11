/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.controller;

import com.sales.restapi.entities.Movie;
import com.sales.restapi.exception.MovieNotFoundException;
import com.sales.restapi.repo.MovieRepository;
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
public class MovieController {
    private final MovieRepository movieRepository;
    private final MovieResourceAssembler movieResourceAssembler;
    
    public MovieController(MovieRepository movieRepository, MovieResourceAssembler movieResourceAssembler ){
        this.movieRepository = movieRepository;
        this.movieResourceAssembler = movieResourceAssembler;
    }
    
    // Aggregate root

     @GetMapping("/movies")
     Resources<Resource<Movie>> all() {
       List<Resource<Movie>> movies =  movieRepository.findByActiveMovie().stream()
            .map(movieResourceAssembler::toResource)
            .collect(Collectors.toList());
       
        return new Resources<>(movies,
            linkTo(methodOn(MovieController.class).all()).withSelfRel());
     }

     @PostMapping("/movies")
     ResponseEntity<?> newMovie(@RequestBody Movie newMovie) throws URISyntaxException {
        Resource<Movie> resource = movieResourceAssembler.toResource(movieRepository.save(newMovie));
        
        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
     }

     // Single item

     @GetMapping("/movies/{id}")
     Resource<Movie> one(@PathVariable Long id) {

       Movie movie = movieRepository.findById(id)
         .orElseThrow(() -> new MovieNotFoundException(id));
        
         return movieResourceAssembler.toResource(movie);
     }

     @PutMapping("/movies/{id}")
     ResponseEntity<?> replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) throws URISyntaxException{

       Movie updatedMovie = movieRepository.findById(id)
         .map(movie -> {
           movie.setName(newMovie.getName());
           movie.setDate(newMovie.getDate());
           movie.setStarttime(newMovie.getStarttime());
           movie.setEndtime(newMovie.getEndtime());
           movie.setStock(newMovie.getStock());
           return movieRepository.save(movie);
         })
         .orElseGet(() -> {
           newMovie.setId(id);
           return movieRepository.save(newMovie);
         });
       
        Resource<Movie> resource = movieResourceAssembler.toResource(updatedMovie);

        return ResponseEntity
        .created(new URI(resource.getId().expand().getHref()))
        .body(resource);
     }

     @DeleteMapping("/movies/{id}")
     ResponseEntity<?> deleteMovie(@PathVariable Long id) {
       movieRepository.deleteById(id);
       
       return ResponseEntity.noContent().build();
     }
}
