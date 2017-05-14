package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Rob on 23-4-2017.
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
}
