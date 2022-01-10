package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;
import java.util.List;

/**
 * Created by isaac on 12/9/18.
 */

public class NotificationModule implements Serializable {
    int responseCode;
    String responseMessage;
    List<Notification> data;

    public NotificationModule(int responseCode, String responseMessage, List<Notification> data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public NotificationModule() {
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Notification> getData() {
        return data;
    }

    public void setData(List<Notification> data) {
        this.data = data;
    }
}
