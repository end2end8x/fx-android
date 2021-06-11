package com.wiki.exposure.gallerypick.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.controller.AboutController;

public class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAPIVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidVersion() {
        return "Android" + Build.VERSION.RELEASE;
    }

    public static String getHttpHead() {
        StringBuilder sb = new StringBuilder();
        String appVersion = AboutController.getAppVersion(MyApplication.getInstance());
        sb.append("1,");
        sb.append(getAndroidVersion());
        sb.append(",2,");
        sb.append(appVersion);
        return sb.toString();
    }
}
