package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by admin on 18/05/2018.
 */

public class ServiceProviderModule implements Serializable{
    int provider_id,user_id;
    String uuid,location,landmark,status,visible_name,service_type,image;
    double lon,lat;

    public ServiceProviderModule(int provider_id, int user_id, String uuid, String location, String landmark, String status, String visible_name, String service_type, double lon, double lat) {
        this.provider_id = provider_id;
        this.user_id = user_id;
        this.uuid = uuid;
        this.location = location;
        this.landmark = landmark;
        this.status = status;
        this.visible_name = visible_name;
        this.service_type = service_type;
        this.lon = lon;
        this.lat = lat;
    }

    public ServiceProviderModule(int provider_id, int user_id, String uuid, String location, String landmark, String status, String visible_name, String service_type, String image, double lon, double lat) {
        this.provider_id = provider_id;
        this.user_id = user_id;
        this.uuid = uuid;
        this.location = location;
        this.landmark = landmark;
        this.status = status;
        this.visible_name = visible_name;
        this.service_type = service_type;
        this.image = image;
        this.lon = lon;
        this.lat = lat;
    }

    public ServiceProviderModule() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
