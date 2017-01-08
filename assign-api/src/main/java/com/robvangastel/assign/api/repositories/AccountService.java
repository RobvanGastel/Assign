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

    public void create(final Account entity) {
        dao.create(entity);
    }

    public Account findOne(final long id) {
        return dao.findOne(id);
    }

    public List<Account> findAll() {
        return dao.findAll();
    }
}