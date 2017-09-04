package com.robvangastel.assign.dao.facade;

import com.robvangastel.assign.dao.AbstractDao;
import com.robvangastel.assign.dao.INotificationDao;
import com.robvangastel.assign.domain.Notification;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.NotificationException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@Stateless
public class NotificationDaoImpl extends AbstractDao<Notification> implements INotificationDao {

    @PersistenceContext(unitName = "assignPU")
    private EntityManager entityManager;

    public NotificationDaoImpl() {
        super();
        setClassObj(Notification.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notification> findAll(User user, int start, int size) {
        String queryString = "SELECT * FROM Notification n JOIN User u ON n.user_id = u.id ORDER BY n.dateCreated DESC";
        return entityManager.createNativeQuery(queryString, Notification.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public void create(Notification entity) throws NotificationException {
        entityManager.merge(entity);
    }
}
