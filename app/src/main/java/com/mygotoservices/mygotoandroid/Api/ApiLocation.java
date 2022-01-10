package com.mygotoservices.mygotoandroid.Api;

/**
 * Created by isaac on 5/15/18.
 */

public class ApiLocation {

/*
    static String location = "http://192.168.64.3/goto-api/public/v1/";
    static String base = "http://192.168.64.3/goto-api/";
    static String image = "http://192.168.64.3/goto-api/storage/app/";
*/
    static String location = "https://api.mygotoservices.com/public/v1/";
    static String base = "https://api.mygotoservices.com/";
    static String image = "https://api.mygotoservices.com/storage/app/";

    public static String getLocation()
    {
        return location;
    }

    public static String getBase()
    {
        return  base;
    }

    public static String getImage()
    {
        return  image;
    }

}
