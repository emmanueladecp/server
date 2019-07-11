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
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    private @Id @GeneratedValue Long id;
    private String name;
    private String phone;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Sales> orders = new ArrayList<>();
    
    Customer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
}
