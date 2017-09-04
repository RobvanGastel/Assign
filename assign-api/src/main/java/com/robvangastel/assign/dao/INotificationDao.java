package com.robvangastel.assign.dao;

import com.robvangastel.assign.domain.Notification;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.NotificationException;

import java.util.List;

/**
 * @author Rob van Gastel
 */
public interface INotificationDao {
    /***
     * Find Notification by id
     * @param id
     * @return Found Notification or Null when
     * the Notification isn't found
     */
    Notification findById(long id);

    /***
     * Find all Notification(s)
     * @param start of the list
     * @param size of the list
     * @return all the Notification(s) within the start and size
     */
    List<Notification> findAll(User user, int start, int size);

    /***
     * Create a Notification
     * @param entity Notification to create
     * @throws NotificationException
     */
    void create(Notification entity) throws NotificationException;

    /***
     * Update Notification with the same id
     * @param entity User to update
     * @return Notification updated or Null when
     * the Notification wasn't found
     */
    Notification update(Notification entity);

    /***
     * Delete Notification
     * @param entity Notification to delete
     * @throws NotificationException
     */
    void delete(Notification entity) throws NotificationException;

    /***
     * Delete Notification by id
     * @param id id of the Notification
     * @throws NotificationException
     */
    void deleteById(long id) throws NotificationException;
}
