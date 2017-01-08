package com.robvangastel.assign.api.repositories;

import com.robvangastel.assign.api.dao.IAccountDao;
import com.robvangastel.assign.api.domain.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rob
 */

@Service
@Transactional
public class AccountService {

    @Autowired
    private IAccountDao dao;

    public AccountService() {
        super();
    }

    public void create(Account entity) {
        dao.create(entity);
    }
    
    public void delete(long id) {
        Account entity = dao.findById(id);
        dao.delete(entity);
    }
    
    public void update(Account entity) {
        dao.update(entity);
    }

    public Account findById(long id) {
        return dao.findById(id);
    }
    
    public Account findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public List<Account> findAll() {
        return dao.findAll();
    }
}