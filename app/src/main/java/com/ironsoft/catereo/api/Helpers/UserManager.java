package com.ironsoft.catereo.api.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class UserManager {

    private static final String PREFS_NAME = "TokenPrefs";
    private static final String ACCESS_TOKEN = "AccessToken";
    private static final String EXPIRATION = "expiration";
    private static final String USER_ID = "userId";
    private static final String USERNAME = "userName";
    private static final String USER_EMAIL = "userEmail";
    private static final String USER_PHONE = "phone";
    private static final String USER_DISPLAYNAME = "displayName";
    private static final String USER_POSITION = "position";
    private static final String USER_ROLE = "role";



    public static void saveUserData(Context context, String accessToken, String expiration, String userId, String userName, String userEmail, String userPhone, String userDisplayName, String userPosition, String userRole) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.putString(EXPIRATION, expiration);
        editor.putString(USER_ID, userId);
        editor.putString(USERNAME, userName);
        editor.putString(USER_EMAIL, userEmail);
        editor.putString(USER_PHONE, userPhone);
        editor.putString(USER_DISPLAYNAME, userDisplayName);
        editor.putString(USER_POSITION, userPosition);
        editor.putString(USER_ROLE, userRole);
        editor.apply();
    }

    public static String getUserToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(ACCESS_TOKEN, null);
    }
    public static String getExpiration(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(EXPIRATION, null);
    }
    public static String getUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_ID, null);
    }
    public static String getUserName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USERNAME, null);
    }
    public static String getUserEmail(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_EMAIL, null);
    }
    public static String getUserPhone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_PHONE, null);
    }
    public static String getUserDisplayname(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_DISPLAYNAME, null);
    }

    public static String getUserRole(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_ROLE, null);
    }
    public static String getUserPosition(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_POSITION, null);
    }
}


