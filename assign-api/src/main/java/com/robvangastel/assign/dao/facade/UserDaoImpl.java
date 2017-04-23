package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.UserException;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
public class UserDaoImpl extends AbstractDao<User> implements IUserDao {

    @PersistenceContext(unitName ="AssignPU")
    private EntityManager entityManager;
        
    public UserDaoImpl() {
        super();
        setClassObj(User.class);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User updateRole(User entity) throws UserException {
        return null;
    }
}