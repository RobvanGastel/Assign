package com.robvangastel.assign.api.controllers;

import com.robvangastel.assign.api.domain.Post;
import com.robvangastel.assign.api.domain.Tag;
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
        //TODO Add check for user
        
        //Default to UserID == 1 
        return postService.FindAllOrdered(1L);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Post> search(@RequestBody List<String> args) {
        //TODO Add check for user
        
        //Default to UserID == 1 
        return postService.FindAllOrderedByAccountId(1L);
    }
    
    /**
     * on calling POST on /posts
     * @param title 
     * @param tags List of Tags
     * @param description
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void post(@RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestBody List<Tag> tags) {

        //Default to UserID == 1
        long id = 1L;
        
        if(tags != null) {
            postService.create(new Post(title, description, tags), id); 
        } else {
            postService.create(new Post(title, description), id); 
        }
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
