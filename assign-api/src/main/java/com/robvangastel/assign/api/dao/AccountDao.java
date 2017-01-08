package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Account;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rob
 */

@Repository
public class AccountDao extends AbstractDao<Account> implements IAccountDao {

    public AccountDao() {
        super();
        setClassObj(Account.class);
    }
}