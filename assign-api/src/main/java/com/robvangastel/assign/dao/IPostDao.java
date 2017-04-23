package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.PostException;

import java.util.List;

/**
 * Created by Rob on 23-4-2017.
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
     * Find Post(s) by message
     * @param message
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByMessage(String message);

    /***
     * Find Post(s) by User
     * @param id User id
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findByUser(long id);

    /***
     * Find Posts for User, includes Posts of his
     * following list
     * @param user
     * @return Found Post(s) or Null when
     * the Post(s) isn't found
     */
    List<Post> findForUser(User user);

    /***
     *
     * @return return all Posts
     */
    List<Post> findAll();

    /***
     *
     * @return Posts orderd by Date
     */
    List<Post> findAllOrderedByDate();

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