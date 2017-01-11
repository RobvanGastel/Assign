/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Post;
import com.robvangastel.assign.api.repositories.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    /**
     * On calling GET on /posts
     * @return All users
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Post> get() {
        return postService.findAll();
    }
    
    /**
     * on calling POST on /posts
     * @param id of the poster
     * @param title 
     * @param description
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void post(@RequestParam("id") Long id, 
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        //Authentication check for user
        postService.create(new Post(title, description), id); 
    }
    
    /**
     * on calling DELETE on /posts
     * @param id of the user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        //Authentication check for user
        postService.delete(id);
    }
    
    /**
     * on calling PUT on /posts 
     * @param post entire Post object 
     */
    @RequestMapping(method = RequestMethod.PUT, 
            produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Post post) {
        if(post != null) {
            //Authentication check for user
            postService.update(post);
        }
    }
    
    /**
     * on calling GET on /posts
     * @param id of the user
     * @return user found with the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, 
            produces = "application/json")
    @ResponseBody
    public Post getById(@PathVariable("id") long id) {
        return postService.findById(id);
    }
}
