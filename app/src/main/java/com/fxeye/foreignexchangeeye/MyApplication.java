package com.fxeye.foreignexchangeeye;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.webkit.WebView;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.fxeye.foreignexchangeeye.view.im_chatutils.ChatLoginController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyApplication extends MultiDexApplication {
    /* access modifiers changed from: private */
    public static MyApplication instance;
    private static Context context;
    /* access modifiers changed from: private */
    private ServiceConnection mServiceConn;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static String getProcessName(Context context2) {
        if (context2 == null) {
            return null;
        }
        try {
            for (ActivityManager.RunningAppProcessInfo next :
                    ((ActivityManager) context2.getSystemService(Activity.ACTIVITY_SERVICE)).getRunningAppProcesses()) {
                if (next.pid == Process.myPid()) {
                    return next.processName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initWebViewDataDirectory(Context context2) {
        if (Build.VERSION.SDK_INT >= 28) {
            String processName = getProcessName(context2);
            if (!context2.getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.i("Test", "===========-=-=-=-=-=-11111111111");
        instance = this;
        context = getApplicationContext();
        InitToken();
    }

    public static MyApplication getInstance() {
        return instance;
    }
    public static Context getContext() {
        return context;
    }

    private void disableAPIDialog() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke((Object) null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
                declaredField.setAccessible(true);
                declaredField.setBoolean(invoke, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void InitToken() {
        ChatLoginController.getInstance().api_Permission_Login();
    }
}