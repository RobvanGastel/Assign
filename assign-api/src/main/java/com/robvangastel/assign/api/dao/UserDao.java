package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.User;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author Rob
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);

}