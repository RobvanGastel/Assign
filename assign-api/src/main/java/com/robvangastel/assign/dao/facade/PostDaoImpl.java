package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.exception.PostException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

@Stateless
public class PostDaoImpl extends AbstractDao<Post> implements IPostDao {

    @PersistenceContext(unitName ="assignPU")
    private EntityManager entityManager;

    public PostDaoImpl() {
        super();
        setClassObj(Post.class);
    }

    @Override
    public List<Post> findByDescription(String description) {
        description = "%" + description + "%";
        Query query = entityManager.createQuery(
                "SELECT p FROM Post p WHERE p.description like :description ORDER BY p.dateCreated DESC")
                .setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Post> findByEmail(String email) {
        Query query = entityManager.createQuery(
                "SELECT p FROM Post p, User u WHERE p.user_id = u.id AND u.email = :email ORDER BY p.dateCreated DESC")
                .setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public Post create(Post entity) throws PostException {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }
}
