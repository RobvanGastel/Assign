/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rob
 */
@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    private AccountService accountService;
        
    /***
     * API version mapped on /
     * @return API version of current one running
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String index() {
        
        //Initialize test users
        accountService.create(new Account("ZU_LUL@LULIN2k17.com", "Twisted Bow")); 
        return "Version: 0.1";
    }
    
}
