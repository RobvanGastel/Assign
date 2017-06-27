package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

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

    public Post findByEmail(String email) {
        return postDao.findByEmail(email);
    }

    public Post create(Post entity) throws Exception {
        return postDao.create(entity);
    }

    public List<Post> findByDescription(String description) {
        return postDao.findByDescription(description);
    }
}
