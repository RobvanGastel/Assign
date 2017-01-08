package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Post;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rob
 */

@Repository
public class PostDao extends AbstractDao<Post> implements IPostDao {
        
    public PostDao() {
        super();
        setClassObj(Post.class);
    } 
}
