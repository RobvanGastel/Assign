/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.assign.api.controllers;

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
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String index() {
        return "Version: 0.1";
    }
    
}
