package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.UserException;

import java.util.List;

/**
 * Created by Rob on 23-4-2017.
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
     * @param email
     * @return Found User or Null when
     * The User isn't found
     */
    User findByEmail(String email);

    /***
     * Find User by username
     * @param username
     * @return Found User or Null when
     * The User isn't found
     */
    User findByUsername(String username);

    /***
     * Find all Users
     * @return All Users
     */
    List<User> findAll();

    /***
     * Create User
     * @param entity User to create
     * @return Created User
     */
    User create(User entity) throws UserException;

    /***
     * Update User with the same id
     * @param entity User to update
     * @return User updated or Null when
     * the user wasn't found
     */
    User update(User entity) throws UserException;

    /***
     *  Set Role User
     * @param entity User to update role
     * @throws UserException
     */
    User updateRole(User entity) throws UserException;

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