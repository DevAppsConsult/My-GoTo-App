package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 13/06/2018.
 */

public class ForgotPasswordTable extends RealmObject{
    @PrimaryKey
    int primaryId;

    String email;

    public ForgotPasswordTable(int primaryId, String email) {
        this.primaryId = primaryId;
        this.email = email;
    }

    public ForgotPasswordTable() {
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
