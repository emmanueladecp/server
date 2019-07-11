/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.repo;

import com.sales.restapi.entities.Sales;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ADMIN
 */
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByCustomerId(Long id);
    List<Sales> findByMovieId(Long id);
}
