package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Rob
 */

@Controller
//@RequestMapping("/account/{userId}")
public class AccountRestController {
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping("/")
    public String index() {
        accountService.create(new Account("asdasd", "asdsadasd"));
        return "Hello World!";    
    }
    
}
