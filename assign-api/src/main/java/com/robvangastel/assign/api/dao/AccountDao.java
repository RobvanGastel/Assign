package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Query query = entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.email = :email")
                .setParameter("email", email);
        return (Account) query.getSingleResult();
    }

    @Override
    public List<Account> searchByName(String name) {
        Query Matches = entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.firstName LIKE %:name% OR a.surname LIKE %:name%")
                .setParameter("name", name);
        
        return Matches.getResultList();  
    }
}