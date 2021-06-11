package com.libs.view.optional.controller;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;

import com.fxeye.foreignexchangeeye.MyApplication;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StockInitController {
    private static final String CLASS = "StockInitController ";
    public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    public static int interval = 10800;
    private static ArrayList<String> mCloseFirstAdsPopList = new ArrayList<>();
    private static String mUserIdFirstAdsPop = null;
    private static StockInitController s_Instance = null;
    public static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
    public static final SimpleDateFormat sdf11 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    public static final SimpleDateFormat sdf22 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat sdf33 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sdf5 = new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat sdf6 = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMdd");
    /* access modifiers changed from: private */
    public List<String> code_type = new ArrayList();
    /* access modifiers changed from: private */
    /* access modifiers changed from: private */
    public Gson gson = new Gson();
    /* access modifiers changed from: private */
    private Random random = new Random();

    @Deprecated
    public static boolean getCloseFirstAdsPopBean() {
        return false;
    }

    public static void setShowTimeFirstYouhuiquan(String str) {
    }

    public static StockInitController getInstance() {
        if (s_Instance == null) {
            synchronized (StockInitController.class) {
                if (s_Instance == null) {
                    s_Instance = new StockInitController();
                }
            }
        }
        return s_Instance;
    }

    public static void saveInitTime(Context context, String str, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences("Stock_init_time", 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static long getInitTime(Context context, String str) {
        return context.getSharedPreferences("Stock_init_time", 0).getLong(str, 0);
    }

    public static boolean isMainProcess() {
        try {
            int myPid = Process.myPid();
            String str = "";
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) MyApplication.getInstance().getSystemService(Activity.ACTIVITY_SERVICE)).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    str = next.processName;
                    break;
                }
            }
            if (str.equals(MyApplication.getInstance().getPackageName())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

}
