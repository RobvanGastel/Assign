package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.exception.ResponseException;

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

    @PersistenceContext(unitName ="assignPU")
    private EntityManager entityManager;

    public ReplyDaoImpl() {
        super();
        setClassObj(Reply.class);
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
    public Reply create(Reply entity) throws ResponseException {
        entityManager.merge(entity);
        return entity;

    }
}
