package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.PostException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Stateless
public class PostDaoImpl extends AbstractDao<Post> implements IPostDao {

    @PersistenceContext(unitName = "assignPU")
    private EntityManager entityManager;

    public PostDaoImpl() {
        super();
        setClassObj(Post.class);
    }

    @Override
    public Post findByUrl(String url) {
        Query q = entityManager.createQuery(
                "FROM Post p WHERE p.url = :url")
                .setParameter("url", url);
        return (Post) q.getSingleResult();
    }

    @Override
    public Boolean isUrlUsed(String url) {
        Query q = entityManager.createQuery(
                "FROM Post p WHERE p.url = :url")
                .setParameter("url", url);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Post> findByQuery(User user, String query, int start, int size) {
        query = "%" + query + "%";

        String queryString = "SELECT * FROM Post p JOIN User u ON p.user_id = u.id JOIN Study s ON u.study_id = s.id JOIN School sc ON sc.id = s.school_id WHERE s.id = :study AND sc.id = :school AND LOWER(p.title) like LOWER(:query) OR LOWER(p.description) like LOWER(:query) OR LOWER(u.name) LIKE LOWER(:query) ORDER BY p.dateCreated DESC";
        return (List<Post>) entityManager.createNativeQuery(queryString, Post.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("query", query)
                .setParameter("study", user.getStudy().getId())
                .setParameter("school", user.getStudy().getSchool().getId())
                .getResultList();
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> findAll(User user, int start, int size) {
        String queryString = "SELECT * FROM Post p JOIN User u ON p.user_id = u.id JOIN Study s ON u.study_id = s.id JOIN School sc ON sc.id = s.school_id WHERE s.id = :study AND p.done < 1 ORDER BY p.dateCreated DESC";
        return entityManager.createNativeQuery(queryString, Post.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("study", user.getStudy().getId())
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> findAll(int start, int size) {
        String queryString = "SELECT * FROM Post p ORDER BY p.dateCreated DESC";
        return entityManager.createNativeQuery(queryString, Post.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public void create(Post entity) throws PostException {
        entityManager.merge(entity);
    }

    @Override
    public void deleteByQuery(long id) throws PostException {
        entityManager.createQuery("delete from Post where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
