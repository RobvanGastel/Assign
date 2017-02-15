package com.robvangastel.assign.api.repositories;

import com.robvangastel.assign.api.dao.IAccountDao;
import com.robvangastel.assign.api.dao.IPostDao;
import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.domain.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rob
 */

@Service
@Transactional
public class PostService {

    @Autowired
    private IPostDao postDao;
    
    @Autowired
    private IAccountDao accountDao;

    public PostService() {
        super();
    }

    public void create(Post entity, Long id) {
        Account account = accountDao.findById(id);
        entity.setAccount(account);
        postDao.create(entity);
    }
    
    public void delete(long id) {
        Post entity = postDao.findById(id);
        postDao.delete(entity);
    }
    
    public List<Post> findBySearch(List<String> args) {
        //TODO 
        //Search Account Names for each arg
        //Search Post Title for each arg
        //Search Post Description for each arg
        
        
        
        return null;
    }
    
    public void update(Post entity) {
        postDao.update(entity);
    }

    public Post findById(long id) {
        return postDao.findById(id);
    }
    
    public List<Post> findByAccountId(long id) {
        return postDao.findByAccountId(id);
    }
    
    public List<Post> findAllOrdered(long id) {
        return postDao.findAllOrdered(id);
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }
}