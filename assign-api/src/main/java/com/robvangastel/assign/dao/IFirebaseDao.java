package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.Firebase;
import com.robvangastel.assign.exception.FirebaseException;

/**
 * @author Rob van Gastel
 */
public interface IFirebaseDao {

    /***
     * Find Firebase by id
     * @param id
     * @return Found Firebase or Null when
     * the Firebase isn't found
     */
    Firebase findById(long id);

    /***
     * Update Firebase with the same id
     * @param entity User to update
     * @return Firebase updated or Null when
     * the Firebase wasn't found
     */
    Firebase update(Firebase entity);

    /***
     * Delete Firebase
     * @param entity Firebase to delete
     */
    void delete(Firebase entity) throws FirebaseException;

    /***
     * Delete Firebase by id
     * @param id id of the Firebase
     */
    void deleteById(long id) throws FirebaseException;
}
