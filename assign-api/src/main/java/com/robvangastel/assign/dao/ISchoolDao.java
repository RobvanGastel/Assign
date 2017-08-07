package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.SchoolException;

import java.util.List;

/**
 * @author Rob van Gastel
 */
public interface ISchoolDao {

    /***
     * Find School by id
     * @param id
     * @return Found School or Null when
     * the School isn't found
     */
    School findById(long id);

    /***
     * Find all School(s)
     * @param start of the list
     * @param size of the list
     * @return all the School(s) within the start and size
     */
    List<School> findAll(int start, int size);

    /***
     * Find School by user
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found User(s) or Null when
     * the User(s) isn't found
     */
    List<User> findUsersBySchool(long id, int start, int size);

    /***
     * Find Studies by school
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found Studies
     */
    List<Study> findStudiesBySchool(long id, int start, int size);

    /***
     * Users by school and study
     * @param id
     * @param start of the list
     * @param size of the list
     * @return Found User(s) or Null when
     * the User(s) isn't found
     */
    List<User> findUsersBySchoolAndStudy(String study, long id, int start, int size);

    /***
     * Check if the code is used
     * @param code
     * @return boolean indicating true or false
     */
    boolean isCodeUsed(String code);

    /***
     * Check if the study already exists in that school
     * @param id of the school
     * @param study name of the study
     * @return boolean indicating true or false
     */
    boolean doesStudyAlreadyExist(Long id, String study);

    /***
     * Create a School
     * @param entity School to create
     * @return Created School
     */
    void create(School entity);

    /***
     * Create a study for a school
     * @param entity
     * @return created Study
     */
    Study createStudy(Study entity);

    /***
     * Update School with the same id
     * @param entity School to update
     * @return School updated or Null when
     * the School wasn't found
     */
    School update(School entity);

    /***
     * Delete School
     * @param entity School to delete
     */
    void delete(School entity) throws SchoolException;

    /***
     * Delete School by id
     * @param id id of the School
     */
    void deleteById(long id) throws SchoolException;
}
