package com.mygotoservices.mygotoandroid.AppController;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by finxl-isaac on 1/17/17.
 */

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema sessionSchema = realm.getSchema();
        /*
        if(sessionSchema.get("NewStock") == null)
        {
            sessionSchema.create("NewStock").addField("id",int.class, FieldAttribute.PRIMARY_KEY).addField("server_id",int.class)
                    .addField("id_stock_session",int.class).addField("id_product",int.class).addField("id_user",int.class)
                    .addField("id_user_server",int.class).addField("id_product_server",int.class).addField("id_stock_session_server",int.class)
                    .addField("timestamp",String.class).addField("arrival_date",String.class).addField("quantity",double.class).addField("purchasing_price",double.class);

            oldVersion++;

        }
        if(!sessionSchema.get("NewStock").hasPrimaryKey())
        {
            sessionSchema.get("NewStock").addPrimaryKey("id");
            oldVersion++;
        }
        */
    }

}

