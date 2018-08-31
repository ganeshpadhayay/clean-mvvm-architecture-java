package com.example.data;

import android.util.Log;

import com.example.data.sharedpreference.SharedPreferenceHelper;

import static com.example.data.utils.Const.LOG_TAG;

//for logging purposes
public class Help {

    public static void L(String msg) {
        Log.i(LOG_TAG, msg);
    }

    public static void L(int value) {
        Log.i(LOG_TAG, String.valueOf(value));
    }

    public static void E(String msg) {
        Log.e(LOG_TAG, msg);
    }

    public static void E(int value) {
        E(String.valueOf(value));
    }

    public static void printSharedPref(SharedPreferenceHelper helper) {

//        Map<String, ?> keysLogin = helper.getWordPreference().getPref().getAll();
//
//        Map<String, ?> keysProfile = helper.getProfilePreferences().getPref().getAll();
//
//        for (Map.Entry<String, ?> entry : keysLogin.entrySet()) {
//            Help.L("Shared Pref Login = " + entry.getKey() + ": " +
//                    entry.getValue().toString());
//        }
//
//        for (Map.Entry<String, ?> entry : keysProfile.entrySet()) {
//            Help.L("Shared Pref Profile = " + entry.getKey() + ": " +
//                    entry.getValue().toString());
//        }
    }
}
