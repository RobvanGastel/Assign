package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

@Stateless
public class PostService implements Serializable {

    @Inject
    private IPostDao postDao;

    public Post findById(long id) {
        return postDao.findById(id);
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }

    public void delete(long id) throws Exception  {
        postDao.deleteById(id);
    }

    public Post create(Post entity) throws Exception {
        return postDao.create(entity);
    }

    public List<Post> findByEmail(String email) {
        return postDao.findByEmail(email);
    }

    public List<Post> findByTitle(String title) {
        return postDao.findByTitle(title);
    }

    public List<Post> findByDescription(String description) {
        return postDao.findByDescription(description);
    }
}
