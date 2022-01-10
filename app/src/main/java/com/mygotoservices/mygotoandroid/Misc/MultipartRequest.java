package com.mygotoservices.mygotoandroid.Misc;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.mygotoservices.mygotoandroid.BookServiceActivity;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;


import io.realm.Realm;

/**
 * Created by isaac on 2/15/18.
 */
public class MultipartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity();

    private final Response.Listener<String> mListener;
    private HashMap<String, String> mParams;


    public MultipartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        super(Method.POST, url, errorListener);
        mListener = listener;

        buildMultipartEntity();
    }


    private void buildMultipartEntity() {
        entity.addPart("image", new FileBody(new File(BookServiceActivity.path)));
        try {
            Realm realm = Realm.getDefaultInstance();
            UserTable userTable = realm.where(UserTable.class).findFirst();

            entity.addPart("user_id", new StringBody(userTable.getUser_id() + ""));
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success("Uploaded", getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);

    }
}


