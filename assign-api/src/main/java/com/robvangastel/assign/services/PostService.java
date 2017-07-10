package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Calendar;
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

    public List<Post> findAll(User user, int start, int size) {
        return postDao.findAll(user, start, size);
    }

    public List<Post> findByQuery(User user, String query, int start, int size) {
        return postDao.findByQuery(user, query, start, size);
    }

    public List<Post> findByUser(long id, int start, int size) {
        return postDao.findByUser(id, start, size);
    }

    public void delete(long id) throws Exception  {
        postDao.deleteById(id);
    }

    public Post create(Post entity) throws Exception {
        return postDao.create(entity);
    }

    public Post setDone(Post entity, boolean done) {
        entity.setDone(done);
        return postDao.update(entity);
    }

}
