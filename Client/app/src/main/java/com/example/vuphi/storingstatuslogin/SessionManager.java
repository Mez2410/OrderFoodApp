package com.example.vuphi.storingstatuslogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.vuphi.loginregister.LoginActivity;

import java.util.HashMap;

/**
 * Created by vuphi on 4/21/2017.
 */

public class SessionManager {
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Mezcal";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_POSITION = "position";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession (String id, String name, String position) {
        // Lưu trạng thái đăng nhập, true là trạng thái đăng nhập thành công
        editor.putBoolean(IS_LOGIN, true);

        // Lưu ID NV vào trong SharePreferences
        editor.putString(KEY_ID, id);

        // Lưu tên NV vào trong SharePreferences
        editor.putString(KEY_NAME, name);

        editor.putString(KEY_POSITION, position);

        // commit changes
        editor.commit();
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }
    }

    // Lấy ra thông tin NV (ID NV, Tên NV) đã đc lưu trong SharePreference
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));

        // user id
        user.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));

        // return user
        return user;
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    // Get Login State
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
