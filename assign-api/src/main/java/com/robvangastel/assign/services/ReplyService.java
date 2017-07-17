package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@Stateless
public class ReplyService implements Serializable {

    @Inject
    private IReplyDao replyDao;

    public boolean DidUserReply(User user, Post post) {
        return replyDao.DidUserReply(user, post);
    }

    public Reply findById(long id) {
        return replyDao.findById(id);
    }

    public List<Reply> findByUser(long id, int start, int size) {
        return replyDao.findByUser(id, start, size);
    }

    public List<Reply> findByPost(long id, int start, int size) {
        return replyDao.findByPost(id, start, size);
    }

    public void create(Reply entity) throws Exception {
        replyDao.create(entity);
    }

    public Reply setHelped(Reply entity, boolean done) {
        entity.setHelped(done);
        return replyDao.update(entity);
    }

    public void delete(long id) throws Exception {
        replyDao.deleteById(id);
    }
}