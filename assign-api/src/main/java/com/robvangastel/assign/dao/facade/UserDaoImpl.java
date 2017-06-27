package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rob van Gastel
 */

@Stateless
public class UserDaoImpl extends AbstractDao<User> implements IUserDao {

    @PersistenceContext(unitName ="assignPU")
    private EntityManager entityManager;

    public UserDaoImpl() {
        super();
        setClassObj(User.class);
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email);

        if(query.getResultList().isEmpty()) {
            return null;
        } else {
            return (User) query.getSingleResult();
        }
    }

    @Override
    public User create(User entity) {
        entityManager.persist(entity);
        return entity;
    }
}
