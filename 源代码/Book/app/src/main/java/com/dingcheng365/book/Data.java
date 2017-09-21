package com.dingcheng365.book;

import android.app.Application;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class Data extends Application {
    private static String application_userID;

    public String getApplication_userID(){
        return this.application_userID;
    }
    public void setApplication_userID(String c){
        this.application_userID= c;
    }
    @Override
    public void onCreate(){
        application_userID = "";
        super.onCreate();
    }
}
