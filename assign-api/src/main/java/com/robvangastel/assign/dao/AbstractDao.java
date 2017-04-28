package com.robvangastel.assign.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Rob on 23-4-2017.
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

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + classObj.getName()).getResultList();
    }

    public T create(final T entity) {
        entityManager.persist(entity);
        return entity;
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