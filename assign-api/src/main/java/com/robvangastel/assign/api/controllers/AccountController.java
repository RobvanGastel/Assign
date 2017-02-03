package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.domain.Post;
import com.robvangastel.assign.api.domain.SocialChannels;
import com.robvangastel.assign.api.repositories.AccountService;
import com.robvangastel.assign.api.repositories.PostService;

import java.util.List;
import org.apache.commons.validator.EmailValidator;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
    /**
     * On calling GET on /accounts
     * @return All users
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Account> get() {
        return accountService.findAll();
    }
    
    /**
     * on calling POST on /accounts
     * @param account Account object
     * @param FacebookId
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void post(@RequestBody Account account, @RequestParam("FacebookId") String FacebookId) {
        boolean valid = true;
        
        //Encode password
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        //Set SocialChannel(s)
        account.setSocialChannel(new SocialChannels(FacebookId));
        
        //Check if email is valid
        String email = account.getEmail();
        valid = EmailValidator.getInstance().isValid(email);
        
        //Check if the email exists
        if(accountService.findByEmail(email) != null) {
            valid = false;
        }
        
        if(valid) {
            accountService.create(account); 
        }
    }
    
    /**
     *  DISABLE ON PRODUCTION
     * on calling DELETE on /accounts
     * @param id of the user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        //Authentication check for user
        accountService.delete(id);
    }
    
    /**
     * on calling PUT on /accounts 
     * @param account entire Account object 
     */
    @RequestMapping(method = RequestMethod.PUT, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Account account) {
        if(account != null) {
            //Authentication check for user
            accountService.update(account);
        }
    }
    
    /**
     * on calling GET on /accounts
     * @param id of the user
     * @return user found with the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, 
            produces = "application/json")
    @ResponseBody
    public Account getById(@PathVariable("id") long id) {
        return accountService.findById(id);
    }
    
    /**
     * on calling GET on /accounts/{id}/posts
     * @param id of the user
     * @return posts found with the given account id
     */
    @RequestMapping(value = "{id}/posts", method = RequestMethod.GET, 
            produces = "application/json")
    @ResponseBody
    public List<Post> getPostByAccountId(@PathVariable("id") long id) {
        return postService.findPostsByAccountId(id);
    }
}
