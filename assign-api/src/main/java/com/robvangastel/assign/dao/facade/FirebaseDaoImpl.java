package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IFirebaseDao;
import com.robvangastel.assign.domain.Firebase;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Rob van Gastel
 */
@Stateless
public class FirebaseDaoImpl extends AbstractDao<Firebase> implements IFirebaseDao {

    @PersistenceContext(unitName = "assignPU")
    private EntityManager entityManager;

    public FirebaseDaoImpl() {
        super();
        setClassObj(Firebase.class);
    }
}
