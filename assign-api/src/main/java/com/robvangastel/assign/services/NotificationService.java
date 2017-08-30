package com.robvangastel.assign.services;

import com.robvangastel.assign.CodeGenerator;
import com.robvangastel.assign.dao.INotificationDao;
import com.robvangastel.assign.domain.Notification;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rob van Gastel
 *
 */
@Stateless
public class NotificationService implements Serializable {
    
    @Inject
    private INotificationDao notificationDao;

    public Notification findById(long id) {
        return notificationDao.findById(id);
    }

    public List<Notification> findAll(User user, int start, int size) {
        return notificationDao.findAll(user, start, size);
    }

    public void delete(long id) throws Exception {
        notificationDao.deleteById(id);
    }

    public void create(Notification entity) throws Exception {
        notificationDao.create(entity);
    }
}
