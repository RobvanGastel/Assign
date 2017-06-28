package com.robvangastel.assign.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 *
 * @author Rob van Gastel
 * @param <T>
 */

public abstract class AbstractDao<T extends Serializable> {

    private Class<T> classObj;

    @PersistenceContext(unitName ="assignPU")
    private EntityManager entityManager;

    public final void setClassObj(final Class<T> classObjToSet) {
        this.classObj = classObjToSet;
    }

    public T findById(final long id) {
        return entityManager.find(classObj, id);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long id) {
        final T entity = findById(id);
        delete(entity);
    }
}
