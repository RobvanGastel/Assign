package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.exception.PostException;

import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

public interface IPostDao {

    /***
     * Find Post by id
     * @param id
     * @return Found Post or Null when
     * the Post isn't found
     */
    Post findById(long id);

    /***
     * Find Post(s) by query
     * @param query
     * @param start of the list
     * @param size of the list
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByQuery(String query, int start, int size);

    /***
     * Find Post(s) by user
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found Post(s) limited by 20 or Null when
     * the Post(s) isn't found
     */
    List<Post> findByUser(long id, int start, int size);

    /***
     * Find all post(s)
     * @param start of the list
     * @param size of the list
     * @return all the Post(s) within the start and size
     */
    List<Post> findAll(int start, int size);

    /***
     * Create a Post
     * @param entity Post to create
     * @return Created Post
     */
    Post create(Post entity) throws PostException;

    /***
     * Update Post with the same id
     * @param entity User to update
     * @return Post updated or Null when
     * the Post wasn't found
     */
    Post update(Post entity);

    /***
     * Delete Post
     * @param entity Post to delete
     */
    void delete(Post entity) throws PostException;

    /***
     * Delete Post by id
     * @param id id of the Post
     */
    void deleteById(long id) throws PostException;
}
