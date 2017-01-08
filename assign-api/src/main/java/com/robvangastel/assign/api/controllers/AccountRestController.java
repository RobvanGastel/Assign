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
@RequestMapping("/account")
public class AccountRestController {
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Account Create() {
        
        Account account = new Account("asdsad", "asdsadasd");
        accountService.create(account);
        return account;    
    }
    
}
