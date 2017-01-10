/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.domain.Post;
import com.robvangastel.assign.api.domain.SocialChannels;
import com.robvangastel.assign.api.repositories.AccountService;
import com.robvangastel.assign.api.repositories.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
    /***
     * API version mapped on /
     * @return API version of current one running
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String index() {
        
        //Hashing password
        String PasswordHashRob = "LULIN2k17";
        String PasswordHashMax = "CSSISLEUK";

        //Initialize test users
        accountService.create(new Account("robiscool@mail.com", passwordEncoder.encode(PasswordHashRob), "Rob", 
                "Jackson", "06 1800 1337", new SocialChannels("1821311231ACX"))); 
        accountService.create(new Account("maximusMax@mail.com", passwordEncoder.encode(PasswordHashMax), "Max", 
                "Maximus", "06 9131 1337", new SocialChannels("3249-0daASSA"))); 
        postService.create(new Post("Hoe werkt HTML5?", "Ik snap niks van HTML5 help me!"), (long) 1);
        postService.create(new Post("Hoe center ik in CSS", "CSS is geweldig, als het werkt."), (long) 1);
        return "Version: 0.1";
    }
    
}
