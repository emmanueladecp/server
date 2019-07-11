/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.entities;

/**
 *
 * @author ADMIN
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Sales {
    private @Id @GeneratedValue Long id;
    private Integer qtybuy;
    
    @JsonIgnore
    @OneToOne
    private Customer customer;
    
    @JsonIgnore
    @OneToOne
    private Movie movie;
    
    public Sales(Customer customer, Movie movie, Integer qtybuy){
        this.qtybuy = qtybuy;
        this.customer = customer;
        this.movie = movie;
    }

}

