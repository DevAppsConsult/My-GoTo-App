package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 18/05/2018.
 */

public class RetailerTable extends RealmObject{
    @PrimaryKey
    int provider_id;
    int user_id;
    String uuid,location,landmark,lon,lat,status,visible_name,image,description,service_type;

    public RetailerTable(int provider_id, int user_id, String uuid, String location, String landmark, String lon, String lat, String status, String visible_name, String image, String description, String service_type) {
        this.provider_id = provider_id;
        this.user_id = user_id;
        this.uuid = uuid;
        this.location = location;
        this.landmark = landmark;
        this.lon = lon;
        this.lat = lat;
        this.status = status;
        this.visible_name = visible_name;
        this.image = image;
        this.description = description;
        this.service_type = service_type;
    }

    public RetailerTable() {
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

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
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
}
