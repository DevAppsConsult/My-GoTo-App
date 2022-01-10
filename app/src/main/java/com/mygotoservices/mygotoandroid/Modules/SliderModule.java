package com.mygotoservices.mygotoandroid.Modules;

/**
 * Created by admin on 15/05/2018.
 */

public class SliderModule {
    int advert_id;
    String uuid,file_name;

    public SliderModule(int advert_id, String uuid, String file_name) {
        this.advert_id = advert_id;
        this.uuid = uuid;
        this.file_name = file_name;
    }

    public SliderModule() {
    }

    public int getAdvert_id() {
        return advert_id;
    }

    public void setAdvert_id(int advert_id) {
        this.advert_id = advert_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
