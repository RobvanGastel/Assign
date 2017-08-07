package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.ReplyException;

import java.util.List;

/**
 * @author Rob van Gastel
 */

public interface IReplyDao {

    /***
     * Find Reply by id
     * @param id
     * @return Found Reply or Null when
     * the Reply isn't found
     */
    Reply findById(long id);

    /***
     * Did User reply to post
     * @param user
     * @param post
     * @return Boolean indicating true or false
     */
    boolean DidUserReply(User user, Post post);

    /***
     * Find Reply(s) by User
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found Reply(s) limited by 20 or Null when
     * the Reply(s) isn't found
     */
    List<Reply> findByUser(long id, int start, int size);

    /***
     * Find Reply(s) by Post
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found Reply(s) limited by 20 or Null when
     * the Reply(s) isn't found
     */
    List<Reply> findByPost(long id, int start, int size);

    /***
     * Create a Reply
     * @param entity Reply to create
     * @return Created Reply
     */
    void create(Reply entity) throws ReplyException;

    /***
     * Update Reply with the id
     * @param entity Reply to update
     * @return Reply updated or Null when
     * the user wasn't found
     */
    Reply update(Reply entity);

    /***
     * Delete Reply
     * @param entity Reply to delete
     */
    void delete(Reply entity) throws ReplyException;

    /***
     * Delete Reply by id
     * @param id id of the Reply
     */
    void deleteById(long id) throws ReplyException;
}
