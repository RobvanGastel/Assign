package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.UserException;

import java.util.List;

/**
 * @author Rob van Gastel
 */

public interface IUserDao {

    /***
     * Find User by id
     * @param id
     * @return Found User or Null when
     * The User isn't found
     */
    User findById(long id);

    /**
     * Find User by email
     *
     * @param email
     * @return Found User or Null when
     * The User isn't found
     */
    User findByEmail(String email);

    /***
     * Find all user(s)
     * @param start of the list
     * @param size of the list
     * @return all the user(s) within the start and size
     */
    List<User> findAll(int start, int size);

    /***
     * Create User
     * @param entity User to create
     * @return Created User
     */
    void create(User entity) throws UserException;

    /***
     * Update User with the same id
     * @param entity User to update
     * @return User updated or Null when
     * the user wasn't found
     */
    User update(User entity) throws UserException;

    /***
     * Delete user
     * @param entity User to delete
     */
    void delete(User entity) throws UserException;

    /***
     * Delete user with the same id
     * @param id id to delete
     */
    void deleteById(long id) throws UserException;
}
