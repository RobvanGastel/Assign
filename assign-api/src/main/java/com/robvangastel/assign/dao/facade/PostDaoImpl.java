package com.robvangastel.assign.dao.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
public class PostDaoImpl extends AbstractDao<Post> implements IPostDao {

    @PersistenceContext(unitName ="AssignPU")
    private EntityManager entityManager;

    public PostDaoImpl() {
        super();
        setClassObj(Post.class);
    }

    @Override
    public List<Post> findByMessage(String message) {
        return null;
    }

    @Override
    public List<Post> findByUser(long id) {
        return null;
    }

    @Override
    public List<Post> findForUser(User user) {
        return null;
    }

    @Override
    public List<Post> findAllOrderedByDate() {
        return null;
    }
}
