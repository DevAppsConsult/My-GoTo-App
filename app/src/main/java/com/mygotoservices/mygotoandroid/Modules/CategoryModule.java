package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by admin on 17/05/2018.
 */

public class CategoryModule implements Serializable{
    int category_id;
    String uuid,name,description,charge_type,file;
    Double price;

    /**
     "charge_type": "Session",
     * */

    public CategoryModule(int category_id, String uuid, String name, String description, String charge_type, String file, Double price) {
        this.category_id = category_id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.charge_type = charge_type;
        this.file = file;
        this.price = price;
    }

    public CategoryModule() {
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return name;
    }
}
