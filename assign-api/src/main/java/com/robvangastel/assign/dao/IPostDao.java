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
     * Find Post(s) by description
     * @param description
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByDescription(String description);

     /***
     * Find Post(s) by description
     * @param title
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByTitle(String title);

    /***
     * Find Post(s) by User
     * @param email User email
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByEmail(String email);

    /***
     *
     * @return return all Posts
     */
    List<Post> findAll();

    /***
     * Create a Post
     * @param entity Post to create
     * @return Created Post
     */
    Post create(Post entity) throws PostException;

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
