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

    /**
     * Retrieves all Posts by User
     * @param id of the User
     * @return All Posts of the User
     */
    List<Post> findByAccountId(long id);

    /**
     * Retrieves all Posts
     * @return Posts ordered by date descending,
     * removes posts of the user requesting the Posts
     */
    List<Post> findAllOrdered(long id);

    /**
     * Checks title, description and name of the Account
     * of the Post
     * @param argument
     * @return Post found by search
     */
    List<Post> searchByTitleAndDescription(String argument);

    void create(Post entity);

    Post update(Post entity);

    void delete(Post entity);

    void deleteById(long entityId);
}
