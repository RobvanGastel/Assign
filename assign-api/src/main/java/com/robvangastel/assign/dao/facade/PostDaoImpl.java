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
        return query.setMaxResults(50).getResultList();
    }

    @Override
    public List<Post> findByTitle(String title) {
        title = "%" + title + "%";
        Query query = entityManager.createQuery(
                "SELECT p FROM Post p WHERE p.title like :title ORDER BY p.dateCreated DESC")
                .setParameter("title", title);
        return query.setMaxResults(50).getResultList();
    }

    @Override
    public List<Post> findByEmail(String email) {
        Query query = entityManager.createQuery(
                "SELECT p FROM Post p, User u WHERE p.user.id = u.id AND u.email = :email ORDER BY p.dateCreated DESC")
                .setParameter("email", email);
        return query.setMaxResults(50).getResultList();
    }

    @Override
    public List<Post> findByQuery(String query) {
        query = "%" + query + "%";
        Query q = entityManager.createQuery(
                "SELECT p FROM Post p, User u \n" +
                        "WHERE u.id = p.user.id \n" +
                        "AND p.title like :query OR p.description like :query OR u.name like :query ORDER BY p.dateCreated DESC")
                .setParameter("query", query);
        return q.setMaxResults(50).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findAll() {
        return entityManager.createQuery("from Post ORDER BY dateCreated DESC").setMaxResults(50).getResultList();
    }

    @Override
    public Post create(Post entity) throws PostException {
        entityManager.merge(entity);
        return entity;
    }
}
