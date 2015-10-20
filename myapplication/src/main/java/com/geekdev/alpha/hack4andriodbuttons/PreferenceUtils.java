package com.geekdev.alpha.hack4andriodbuttons;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferenceUtils {
    private static final String PREF_NoButtons_MODE = "pref_NoButtons_mode";


    public static boolean isNoButtonsModeActive(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_NoButtons_MODE, false);
    }

    public static void setNoButtonsModeActive(final boolean active, final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_NoButtons_MODE, active).commit();
    }
}
