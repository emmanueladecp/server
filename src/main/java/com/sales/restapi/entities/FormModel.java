/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */

@Setter
@Getter
@NoArgsConstructor
@Data
public class FormModel {
    private String custid;
    private String movieid;
    private String custidhidden;
    private Integer moviebuy;
    private String movieidhidden;
    
    
    public FormModel(String custid, String movieid, String movieidhidden, String custidhidden, Integer moviebuy){
        this.custid = custid;
        this.movieid = movieid;
        this.moviebuy = moviebuy;
        this.custidhidden = custidhidden;
        this.movieidhidden = movieidhidden;
    }
            
    
}
