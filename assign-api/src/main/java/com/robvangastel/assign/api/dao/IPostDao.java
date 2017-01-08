package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Post;
import java.util.List;

/**
 *
 * @author Rob
 */
public interface IPostDao {

    Post findById(long id);

    List<Post> findAll();

    void create(Post entity);

    Post update(Post entity);

    void delete(Post entity);

    void deleteById(long entityId); 
}
