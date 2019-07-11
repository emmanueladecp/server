/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.restapi.exception;

/**
 *
 * @author ADMIN
 */
public class MovieNotFoundException extends RuntimeException{
    
    public MovieNotFoundException(Long id){
        super("Could not find movie " + id);
    }
}
