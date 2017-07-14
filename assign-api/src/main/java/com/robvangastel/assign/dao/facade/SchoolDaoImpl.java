package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.ISchoolDao;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Rob van Gastel
 */
public class SchoolDaoImpl extends AbstractDao<School> implements ISchoolDao {

    @PersistenceContext(unitName = "assignPU")
    private EntityManager entityManager;

    public SchoolDaoImpl() {
        super();
        setClassObj(School.class);
    }

    @Override
    public List<School> findAll(int start, int size) {
        return entityManager.createQuery("from School ORDER BY dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<User> findUsersBySchool(long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT st.students FROM School s INNER JOIN s.studies st WHERE s.id = :id")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public List<Study> findStudiesBySchool(long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT st.name FROM School s INNER JOIN s.studies st WHERE s.id = :id")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public List<User> findUsersBySchoolAndStudy(String study, long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT st.students FROM School s INNER JOIN s.studies st WHERE s.id = :id AND st.name = :study")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id)
                .setParameter("study", study);
        return q.getResultList();
    }

    @Override
    public boolean isCodeUsed(String code) {
        Query q = entityManager.createQuery(
                "SELECT s FROM School s WHERE s.schoolCode = :code")
                .setParameter("code", code);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean doesStudyAlreadyExist(Long id, String study) {
        Query q = entityManager.createQuery(
                "SELECT st.students FROM School s INNER JOIN s.studies st WHERE s.id = :id AND st.name = :study")
                .setParameter("id", id)
                .setParameter("study", study);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public School create(School entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Study createStudy(Study entity) {
        entityManager.persist(entity);
        return entity;
    }
}
