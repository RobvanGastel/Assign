package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.ReplyException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Rob van Gastel
 */

@Stateless
public class ReplyDaoImpl extends AbstractDao<Reply> implements IReplyDao {

    @PersistenceContext(unitName = "assignPU")
    private EntityManager entityManager;

    public ReplyDaoImpl() {
        super();
        setClassObj(Reply.class);
    }

    @Override
    public boolean DidUserReply(User user, Post post) {
        Query q = entityManager.createQuery(
                "SELECT r FROM Reply r WHERE r.user.id = :user_id AND r.post.id = :post_id ORDER BY r.dateCreated DESC")
                .setParameter("user_id", user.getId())
                .setParameter("post_id", post.getId());

        if (q.getResultList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Reply> findByUser(long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT r FROM Reply r, User u WHERE r.user.id = u.id AND u.id = :id ORDER BY r.dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public List<Reply> findByPost(long id, int start, int size) {
        Query q = entityManager.createQuery(
                "SELECT r FROM Reply r, Post p WHERE r.post.id = p.id AND p.id = :id ORDER BY r.dateCreated DESC")
                .setFirstResult(start)
                .setMaxResults(size)
                .setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public void create(Reply entity) throws ReplyException {
        entityManager.merge(entity);
    }
}
