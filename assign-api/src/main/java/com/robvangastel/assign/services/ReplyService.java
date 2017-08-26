package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.firebase.domain.Data;
import com.robvangastel.assign.firebase.domain.Notification;
import com.robvangastel.assign.firebase.domain.Payload;
import com.robvangastel.assign.firebase.domain.Priority;

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

    @Inject
    private FirebaseService firebaseService;

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
        // TODO Add notification
        String title = entity.getUser().getName() + " wants to help you out!";
        String body = entity.getUser().getName() + " offers to help you out with " + entity.getPost().getTitle();

        Payload payload = new Payload(
                new Notification(title, body),
                new Data(true),
                entity.getPost().getUser()
                        .getFirebase().getNotificationKey(),  // Firebase key
                Priority.normal);

        firebaseService.sendNotification(payload, entity.getPost().getUser().getId());

        replyDao.create(entity);
    }

    public Reply setHelped(Reply entity, boolean done) {
        // TODO Add notification
        entity.setHelped(done);
        return replyDao.update(entity);
    }

    public void delete(long id) throws Exception {
        replyDao.deleteById(id);
    }
}