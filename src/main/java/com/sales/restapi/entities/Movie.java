/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Movie {
    private @Id @GeneratedValue Long id;
    private String name;
    private Date date;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Time starttime;
    
    @JsonFormat(pattern="HH:mm:ss")
    private Time endtime;
    
    private Integer stock;
  
    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Sales> orders = new ArrayList<>();
    
    Movie(String name, Date date, Time starttime, Time endtime, Integer stock){
        this.name = name;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.stock = stock;
    }
        
}
