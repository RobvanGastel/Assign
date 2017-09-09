package com.robvangastel.assign.services;

import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.ReplyException;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.firebase.domain.Data;
import com.robvangastel.assign.firebase.domain.Notification;
import com.robvangastel.assign.firebase.domain.Payload;

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

    public void create(User user, Post post) {

        // Check if he already replied to the post
        if (!replyDao.DidUserReply(user, post)) {

            // Send Notification on Reply
            String title = user.getName() + " wants to help you out!";
            String body = user.getName() + " offers to help you out with " + post.getTitle();

            Payload payload = new Payload(
                    new Notification(title, body),
                    new Data(post.getId()),
                    post.getUser()
                            .getFirebase().getNotificationKey());

            firebaseService.sendNotification(payload, post.getUser(), user);

            replyDao.create(new Reply(user, post));

        } else {
            throw new ReplyException("User already replied to the post.");
        }
    }

    public Reply setHelped(Reply entity, boolean done) {

        // Send Notification on Reply
        String title = entity.getUser().getName() + " wants to help you out!";
        String body = entity.getUser().getName() + " offers to help you out with " + entity.getPost().getTitle();

        Payload payload = new Payload(
                new Notification(title, body),
                new Data(entity.getPost().getId()),
                entity.getPost().getUser()
                        .getFirebase().getNotificationKey());

        firebaseService.sendNotification(payload, entity.getPost().getUser(), entity.getUser());

        entity.setHelped(done);
        return replyDao.update(entity);
    }

    public void delete(long id) throws Exception {
        replyDao.deleteById(id);
    }
}