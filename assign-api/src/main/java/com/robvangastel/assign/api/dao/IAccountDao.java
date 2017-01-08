package com.robvangastel.assign.api.dao;

import com.robvangastel.assign.api.domain.Account;
import java.util.List;

/**
 *
 * @author Rob
 */

public interface IAccountDao {

    Account findById(long id);
    
    Account findByEmail(String email);

    List<Account> findAll();

    void create(Account entity);

    Account update(Account entity);

    void delete(Account entity);

    void deleteById(long entityId);

}