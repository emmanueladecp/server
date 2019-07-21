/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.repo;

import com.sales.restapi.entities.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    @Query("FROM Movie WHERE DATE >= CURRENT_DATE() AND STOCK > 0 ")
    public List<Movie> findByActiveMovie();
}
