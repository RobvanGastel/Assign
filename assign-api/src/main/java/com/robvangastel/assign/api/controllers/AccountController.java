package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.repositories.AccountService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Rob
 */

@Controller
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    /**
     * On calling GET on /account
     * @return All users
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Account> get() {
        return accountService.findAll();
    }
    
    /**
     * on calling POST on /account
     * @param email of the user
     * @param password of the user
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void post(@RequestParam("email") String email, 
            @RequestParam("email") String password) {
        accountService.create(new Account(email, password)); 
    }
    
    /**
     * on calling DELETE on /account
     * @param id of the user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        accountService.delete(id);
    }
    
    /**
     * on calling PUT on /account 
     * @param account entire Account object 
     */
    @RequestMapping(method = RequestMethod.PUT, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Account account) {
        if(account != null) {
            accountService.update(account);
        }
    }
    
    /**
     * on calling GET on /account
     * @param id of the user
     * @return user found with the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, 
            produces = "application/json")
    @ResponseBody
    public Account getById(@PathVariable("id") long id) {
        return accountService.findById(id);
    }
    
}
