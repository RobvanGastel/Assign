package com.robvangastel.assign.services;

import com.robvangastel.assign.CodeGenerator;
import com.robvangastel.assign.dao.ISchoolDao;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.SchoolException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@Stateless
public class SchoolService implements Serializable {

    @Inject
    private ISchoolDao schoolDao;

    public School findById(long id) {
        return schoolDao.findById(id);
    }

    public List<School> findAll(int start, int size) {
        return schoolDao.findAll(start, size);
    }

    public List<Study> findStudiesBySchool(long id, int start, int size) {
        return schoolDao.findStudiesBySchool(id, start, size);
    }

    public List<User> findUsersBySchool(long id, int start, int size) {
        return schoolDao.findUsersBySchool(id, start, size);
    }

    public List<User> findUsersBySchoolAndStudy(String study, long id, int start, int size) {
        return schoolDao.findUsersBySchoolAndStudy(study, id, start, size);
    }

    public Study addStudy(School school, String study) {
        if (!schoolDao.doesStudyAlreadyExist(school.getId(), study)) {

            Study s = schoolDao.createStudy(new Study(school, study));
            schoolDao.update(school);

            return s;
        } else {
            throw new SchoolException("Study already exists");
        }
    }

    public void create(School entity) {
        // This generates the URL which has the total number of possiblities of
        // 916.132.832 different strings.
        String code = CodeGenerator.getInstance().getCode(5);

        while (schoolDao.isCodeUsed(code)) {
            code = CodeGenerator.getInstance().getCode(5);
        }

        entity.setSchoolCode(code);
        schoolDao.create(entity);
    }

    public void delete(long id) {
        schoolDao.deleteById(id);
    }
}
