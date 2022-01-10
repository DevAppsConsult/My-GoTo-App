package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by isaac on 12/9/18.
 */

public class Notification  implements Serializable {
    int id,user_id,booking_id;
    String uuid,notification,type,created_at,updated_at,created;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Notification(int id, int user_id, int booking_id, String uuid, String notification, String type, String created_at, String updated_at, String created) {
        this.id = id;
        this.user_id = user_id;
        this.booking_id = booking_id;
        this.uuid = uuid;
        this.notification = notification;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created = created;
    }

    public Notification(int id, int user_id, int booking_id, String uuid, String notification, String type, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.booking_id = booking_id;
        this.uuid = uuid;
        this.notification = notification;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
