package com.artemonre.testcontacts.utils;

import android.util.Log;

import com.artemonre.testcontacts.BuildConfig;

public class MyLog{

    public static void d(String tag, String text){
        if(BuildConfig.DEBUG){
            Log.d(tag, text);
        }
    }

    public static void d(String tag, String text, Object where){
        if(BuildConfig.DEBUG){
            Log.d(tag, text + " in " + where.getClass().getSimpleName());
        }
    }

    public static void d(String tag, String text, Throwable exception){
        if(BuildConfig.DEBUG){
            Log.d(tag, text, exception);
        }
    }

    public static void e(String tag, String text){
        if(BuildConfig.DEBUG){
            Log.e(tag, text);
        }
    }

    public static void e(String tag, String text, Throwable exception){
        if(BuildConfig.DEBUG){
            Log.e(tag, text, exception);
        }
    }
}