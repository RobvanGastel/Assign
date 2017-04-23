package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.dao.IUserDao;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
public class PostService implements Serializable {

    @Inject
    private IUserDao userDao;

    @Inject
    private IPostDao postDao;
}
