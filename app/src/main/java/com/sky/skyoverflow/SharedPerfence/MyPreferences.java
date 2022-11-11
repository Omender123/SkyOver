package com.sky.skyoverflow.SharedPerfence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sky.skyoverflow.Model.Response.ProfileDetails;

public class MyPreferences {
    private static MyPreferences pref;
    private SharedPreferences sharedPreference;
    private Context ctx;
    private SharedPreferences.Editor editor;


    private MyPreferences(Context ctx) {
        this.ctx = ctx;
        sharedPreference = ctx.getSharedPreferences(PrefConf.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
    }

    public static MyPreferences getInstance(Context ctx) {
        if (pref == null) {
            pref = new MyPreferences(ctx);
        }
        return pref;
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }



    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putInteger(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putFloat(String key, Float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putMemberDetails(String key, ProfileDetails value) {
        Gson gson = new Gson();
        String data = gson.toJson(value);
        editor.putString(key, data);
        editor.commit();
    }


    public ProfileDetails getMemberDetails(String key, String defValue) {
        Gson gson = new Gson();
        String json = sharedPreference.getString(key, defValue);
        ProfileDetails obj = gson.fromJson(json, ProfileDetails.class);
        return obj ;
    }

    public String getString(String key, String defValue) {
        return sharedPreference.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreference.getBoolean(key, defValue);
    }

    public int getInteger(String key, int defValue) {
        return sharedPreference.getInt(key, defValue);
    }

    public Float getFloat(String key, Float defValue) {
        return sharedPreference.getFloat(key, defValue);
    }


    public void deletePreference(String key) {
        editor.remove(key);
        editor.commit();
    }

    public boolean clearPreferences() {
        return editor.clear().commit();
    }



    public void saveBooleanData(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }
}
