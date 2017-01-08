package com.robvangastel.assign.api.repositories;

import com.robvangastel.assign.api.dao.IPostDao;
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
    private IPostDao dao;

    public PostService() {
        super();
    }

    public void create(Post entity) {
        dao.create(entity);
    }
    
    public void delete(long id) {
        Post entity = dao.findById(id);
        dao.delete(entity);
    }
    
    public void update(Post entity) {
        dao.update(entity);
    }

    public Post findById(long id) {
        return dao.findById(id);
    }

    public List<Post> findAll() {
        return dao.findAll();
    }
}