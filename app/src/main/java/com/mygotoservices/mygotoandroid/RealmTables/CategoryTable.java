package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 18/05/2018.
 */

public class CategoryTable extends RealmObject{
    @PrimaryKey
    int category_id;

    String uuid,name,description,charge_type,file;
    double price;

    public CategoryTable(int category_id, String uuid, String name, String description, String charge_type, String file, double price) {
        this.category_id = category_id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.charge_type = charge_type;
        this.file = file;
        this.price = price;
    }

    public CategoryTable() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
