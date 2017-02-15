package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Post;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rob
 */

@Repository
public class PostDao extends AbstractDao<Post> implements IPostDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PostDao() {
        super();
        setClassObj(Post.class);
    }

    @Override
    public List<Post> findByAccountId(long id) {
        Query query = entityManager.createQuery(
                "SELECT p FROM Post p WHERE p.account.id = :id")
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Post> findAllOrdered(long id) {
                Query query = entityManager.createQuery(
                "SELECT p FROM Post p WHERE :id NOT IN p.account.id ORDER BY p.dateCreated DESC")
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Post> FindBySearchQuery(List<String> args) {
        //TODO Add search by title, description
        //TODO Add search by Account email, firstname, surname
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
