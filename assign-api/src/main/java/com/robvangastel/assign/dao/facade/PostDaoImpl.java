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
    public List<Post> findByQuery(String query, int start, int size) {
        query = "%" + query + "%";
        Query q = entityManager.createQuery(
                "SELECT p FROM Post p, User u \n" +
                        "WHERE u.id = p.user.id \n" +
                        "AND p.title like :query OR p.description like :query OR u.name like :query GROUP BY p.id ORDER BY p.dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("query", query);
        return q.getResultList();
    }

    @Override
    public List<Post> findByUser(long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT p FROM Post p, User u WHERE u.id = p.user.id AND u.id = :id ORDER BY p.dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findAll(int start, int size) {
        return entityManager.createQuery("from Post ORDER BY dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Post create(Post entity) throws PostException {
        entityManager.merge(entity);
        return entity;
    }
}
