package com.fxeye.foreignexchangeeye.util_tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    public static void putValue(Context context, String str, String str2, int i) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putInt(str2, i);
        editor.commit();
    }

    public static int checkFlagValue(Context context, String str, String str2, int i) {
        return getSharedPreferences(context, str).getInt(str2, i);
    }

    private static SharedPreferences.Editor getEditor(Context context, String str) {
        return getSharedPreferences(context, str).edit();
    }

    private static SharedPreferences getSharedPreferences(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static void putJsonString(Context context, String str, String str2) {
        SharedPreferences.Editor editor = getEditor(context, "JSONString");
        editor.putString(str, str2);
        editor.commit();
    }

    public static String getJsonString(Context context, String str, String str2) {
        return getSharedPreferences(context, "JSONString").getString(str, str2);
    }

    public static void putFragmentId(Context context, String str, int i) {
        SharedPreferences.Editor editor = getEditor(context, "FragmentId");
        editor.putInt(str, i);
        editor.commit();
    }

    public static int getFragmentId(Context context, String str, int i) {
        return getSharedPreferences(context, "FragmentId").getInt(str, i);
    }

    public static void putStringValue(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor editor = getEditor(context, str);
        editor.putString(str2, str3);
        editor.commit();
    }

    public static String getStringValue(Context context, String str, String str2, String str3) {
        return getSharedPreferences(context, str).getString(str2, str3);
    }
}
