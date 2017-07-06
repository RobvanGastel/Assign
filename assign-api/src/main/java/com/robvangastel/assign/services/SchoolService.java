package com.robvangastel.assign.services;

import com.robvangastel.assign.CodeGenerator;
import com.robvangastel.assign.dao.ISchoolDao;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;

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

    public List<School> findAll(int start, int size) {
        return schoolDao.findAll(start, size);
    }

    public List<User> findUsersBySchool(long id, int start, int size) {
        return schoolDao.findUsersBySchool(id, start, size);
    }

    public List<User> findUsersBySchoolAndStudy(String study, long id, int start, int size) {
        return schoolDao.findUsersBySchoolAndStudy(study, id, start, size);
    }

    public Study addStudy(School entity, String study) {
        // TODO Check if study already exists
        return schoolDao.createStudy(new Study(entity, study));
    }

    public School create(School entity) {
        String code = CodeGenerator.getInstance().getCode(5);

        if(!schoolDao.isCodeUsed(code)) {
            entity.setSchoolCode(code);
            return schoolDao.create(entity);
        } else {
            code = CodeGenerator.getInstance().getCode(5);

            if(!schoolDao.isCodeUsed(code)) {
                entity.setSchoolCode(code);
                return schoolDao.create(entity);
            }
        }

        return null;
    }
}
