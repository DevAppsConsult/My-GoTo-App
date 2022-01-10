package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by admin on 17/05/2018.
 */

public class ProviderModule implements Serializable{
    int provider_id,user_id;
    String uuid,location,landmark,status,visible_name,image,description,service_type;
    double lon,lat;
    /**
     *     "provider": {
     "": 1,
     "": "951c26a7-86e9-56f6-99ce-13ca998a584d",
     "": 5,
     "": "Opposit Holiwats Eng.",
     "": "ICGC Christ Temple",
     "": "-0.1869644",
     "": "5.8137168",
     "created_at": "2018-01-11 07:49:31",
     "updated_at": "2017-12-23 23:24:06",
     "": "Not Verified",
     "": "New tea shop",
     "": "uakXpXZZlvoh5e8p5a3f6426187f2_.png",
     "": "Your magical teashop",
     "": "Retailer"
     },
     */

    public ProviderModule(int provider_id, int user_id, String uuid, String location, String landmark, String status, String visible_name, String image, String description, String service_type, double lon, double lat) {
        this.provider_id = provider_id;
        this.user_id = user_id;
        this.uuid = uuid;
        this.location = location;
        this.landmark = landmark;
        this.status = status;
        this.visible_name = visible_name;
        this.image = image;
        this.description = description;
        this.service_type = service_type;
        this.lon = lon;
        this.lat = lat;
    }

    public ProviderModule() {
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisible_name() {
        return visible_name;
    }

    public void setVisible_name(String visible_name) {
        this.visible_name = visible_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
