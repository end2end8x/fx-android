package com.fxeye.foreignexchangeeye.controller;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils;
import com.google.common.net.HttpHeaders;
import com.libs.view.optional.anaother.ConstDefine;
import com.libs.view.optional.controller.WaihuiDiffListDataController;
import com.libs.view.optional.util.Logx;

import java.util.Date;

public class AboutController {
    private static String app_version = "";
    private static Boolean sFastSimpleMode;
    private static Boolean updateFromOldVersion;

    public static boolean getAppThreeDayLock() {
        if (!TextUtils.isEmpty(SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "app_lock_three_day", "app_lock_three_day", ""))) {
            return false;
        }
        setAppThreeDayLock(WaihuiDiffListDataController.format_time.format(new Date()));
        return false;
    }

    public static void setAppThreeDayLock(String str) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "app_lock_three_day", "app_lock_three_day", str);
    }

    public static void setFastSimpleMode(boolean z) {
        sFastSimpleMode = Boolean.valueOf(z);
        MyApplication.getInstance().getSharedPreferences("sFastSimpleMode", 0).edit().putBoolean("sFastSimpleMode", z).commit();
    }

    public static boolean isFastSimpleMode() {
        if (sFastSimpleMode == null) {
            sFastSimpleMode = Boolean.valueOf(MyApplication.getInstance().getSharedPreferences("sFastSimpleMode", 0).getBoolean("sFastSimpleMode", false));
        }
        Logx.e("AboutController isFastSimpleMode == " + sFastSimpleMode);
        return sFastSimpleMode.booleanValue();
    }

    public static void setNightModle(Context context, boolean z) {
        context.getSharedPreferences("is_night", 0).edit().putBoolean("isNight", z).commit();
    }

    public static boolean getNightModle(Context context) {
        return context.getSharedPreferences("is_night", 0).getBoolean("isNight", false);
    }

    public static void setPushIconNews(Context context, boolean z) {
        context.getSharedPreferences("About_Shared", 0).edit().putBoolean("pushNews", z).commit();
    }

    public static boolean getPushIconNews(Context context) {
        return context.getSharedPreferences("About_Shared", 0).getBoolean("pushNews", true);
    }

    public static void setPushIconMonitor(Context context, boolean z) {
        context.getSharedPreferences("About_Shared", 0).edit().putBoolean("pushMonitor", z).commit();
    }

    public static boolean getPushIconMonitor(Context context) {
        return context.getSharedPreferences("About_Shared", 0).getBoolean("pushMonitor", true);
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getAppIsOneStart(Context context, String str) {
        return context.getSharedPreferences("Is_One_Start", 0).getBoolean(str, true);
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersion() {
        try {
            if (TextUtils.isEmpty(app_version)) {
                app_version = MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0).versionName;
                app_version = app_version.replace(ConstDefine.DIVIDER_SIGN_DIANHAO, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app_version;
    }

    public static void setAppIsOneStart(Context context, String str, boolean z) {
        context.getSharedPreferences("Is_One_Start", 0).edit().putBoolean(str, z).commit();
    }

    public static void setLink(Context context, String str) {
        context.getSharedPreferences(HttpHeaders.LINK, 0).edit().putString("Link_push", str).commit();
    }

    public static String getLink(Context context) {
        return context.getSharedPreferences(HttpHeaders.LINK, 0).getString("Link_push", "");
    }
}
