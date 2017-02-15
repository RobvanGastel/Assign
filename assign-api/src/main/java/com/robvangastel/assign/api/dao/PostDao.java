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
    public List<Post> searchByTitleAndDescription(String argument) {
        Query Matches = entityManager.createQuery(
                "SELECT p FROM Post p WHERE p.title LIKE %:argument% OR p.description LIKE %:argument%")
                .setParameter("argument", argument);
        
        return Matches.getResultList(); 
    }
}
