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
public class SalesNotFoundException extends RuntimeException {
    public SalesNotFoundException(Long id) {
         super("Could not find customer " + id);
    }
}
