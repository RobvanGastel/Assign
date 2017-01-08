package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rob
 */

@Repository
public class AccountDao extends AbstractDao<Account> implements IAccountDao {

    @PersistenceContext
    private EntityManager entityManager;
        
    public AccountDao() {
        super();
        setClassObj(Account.class);
    }

    @Override
    public Account findByEmail(String email) {
        return entityManager.find(Account.class, email);
    }
}