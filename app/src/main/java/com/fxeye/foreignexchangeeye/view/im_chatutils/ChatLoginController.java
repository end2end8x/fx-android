package com.fxeye.foreignexchangeeye.view.im_chatutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.fxeye.foreignexchangeeye.MainActivity;
import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.util_tool.https_controller.NetworkConnectionController;
import com.google.gson.Gson;
import com.libs.view.optional.controller.StockInitController;
import com.libs.view.optional.util.Logx;

public class ChatLoginController {
    /* access modifiers changed from: private */
    public static final String CLASS = ChatLoginController.class.getSimpleName();
    public static long checkTime = 60000;
    public static boolean enterConversationListActivity = true;
    public static IMAccessTokenBean imAccessTokenBean;
    private static ChatLoginController s_Instance = null;
    /* access modifiers changed from: private */
    public Gson gson = new Gson();
    /* access modifiers changed from: private */
    public final Handler handler;
    private Activity mActivty;
    private final HandlerThread mHandlerThread = new HandlerThread("Login_thread");
    public boolean mIsNetConnected = false;
    public boolean mIsWifi = false;
    private BroadcastReceiver mNetStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean unused = ChatLoginController.this.checkNetStatus();
        }
    };

    public static ChatLoginController getInstance() {
        if (s_Instance == null) {
            synchronized (StockInitController.class) {
                if (s_Instance == null) {
                    s_Instance = new ChatLoginController();
                }
            }
        }
        return s_Instance;
    }

    private ChatLoginController() {
        this.mHandlerThread.start();
        this.handler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message message) {
                try {
                    int i = message.what;
                    if (i == -151) {
                        String obj = message.obj.toString();
                        Logx.m5550e(ChatLoginController.CLASS + ">>>>api_Permission_Login error=" + obj);
                        ChatLoginController.this.handler.sendEmptyMessageDelayed(152, ChatLoginController.checkTime);
                    } else if (i == 151) {
                        String obj2 = message.obj.toString();
                        if (message.arg2 == 1001) {
                            Logx.m5550e(ChatLoginController.CLASS + ">>启动读取本地保存>>api_Permission_Login data=" + obj2);
                        } else {
                            Logx.m5550e(ChatLoginController.CLASS + ">>>>api_Permission_Login data=" + obj2);
                        }
                        IMAccessTokenBean iMAccessTokenBean = (IMAccessTokenBean) ChatLoginController.this.gson.fromJson(obj2, IMAccessTokenBean.class);
                        if (iMAccessTokenBean.isStatus()) {
                            iMAccessTokenBean.setGetSavedTime(System.currentTimeMillis());
                            ChatLoginController.imAccessTokenBean = iMAccessTokenBean;
                            if (iMAccessTokenBean.getExpires_in() > 0) {
                                ChatLoginController.checkTime = iMAccessTokenBean.getExpires_in() / 5;
                            }
                            ChatLoginController.this.saveCacheIMAccessToken(obj2);
                        }
                        ChatLoginController.this.handler.sendEmptyMessageDelayed(152, ChatLoginController.checkTime);
                    } else if (i == 152) {
                        ChatLoginController.this.handler.removeMessages(152);
                        ChatLoginController.this.checkTimeOut(false);
                        ChatLoginController.this.handler.sendEmptyMessageDelayed(152, ChatLoginController.checkTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public boolean isWifi() {
        return this.mIsWifi;
    }

    public boolean isNetConnected() {
        return this.mIsNetConnected;
    }

    /* access modifiers changed from: private */
    public boolean checkNetStatus() {
        try {
            @SuppressLint("MissingPermission")
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                this.mIsNetConnected = false;
                this.mIsWifi = false;
            } else {
                if (!this.mIsNetConnected && activeNetworkInfo.isConnected()) {
                    this.handler.sendEmptyMessageDelayed(152, 1000);
                }
                this.mIsNetConnected = activeNetworkInfo.isConnected();
                if (!this.mIsNetConnected) {
                    this.mIsWifi = false;
                } else if (activeNetworkInfo.getType() == 1) {
                    this.mIsWifi = true;
                } else {
                    this.mIsWifi = false;
                }
            }
            Logx.m5551i(CLASS + ">>>>checkNetStatus mIsWifi=" + this.mIsWifi + " mIsNetConnected=" + this.mIsNetConnected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mIsNetConnected;
    }

    public void checkTimeOut(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        Logx.m5551i(CLASS + ">>>>checkTimeOut current=" + currentTimeMillis + " mIsNetConnected=" + this.mIsNetConnected);
        IMAccessTokenBean iMAccessTokenBean = imAccessTokenBean;
        if (iMAccessTokenBean != null) {
            long getSavedTime = currentTimeMillis - iMAccessTokenBean.getGetSavedTime();
            Logx.m5551i(CLASS + ">>>>checkTimeOut current=" + currentTimeMillis + " lastSaveTime=" + imAccessTokenBean.getGetSavedTime() + "  checkTime=" + checkTime);
            if (getSavedTime > checkTime || z) {
                NetworkConnectionController.api_Permission_Login(this.handler, 151);
                return;
            }
            return;
        }
        NetworkConnectionController.api_Permission_Login(this.handler, 151);
    }

    /* access modifiers changed from: private */
    public void saveCacheIMAccessToken(String str) {
        SharedPreferences.Editor edit = MyApplication.getInstance().getSharedPreferences("IMAccessTokenBean", 0).edit();
        edit.putString("IMAccessTokenBean", str);
        edit.commit();
    }

    private String readCacheIMAccessToken() {
        return MyApplication.getInstance().getSharedPreferences("IMAccessTokenBean", 0).getString("IMAccessTokenBean", "");
    }

    public String getIMAccessToken() {
        IMAccessTokenBean iMAccessTokenBean = imAccessTokenBean;
        return iMAccessTokenBean != null ? iMAccessTokenBean.getAccess_token() : "";
    }

    public void api_Permission_Login() {
        try {
            if (StockInitController.isMainProcess()) {
                NetworkConnectionController.api_Permission_Login(this.handler, 151);
                String readCacheIMAccessToken = readCacheIMAccessToken();
                Log.d("test", "readCacheIMAccessToken " + readCacheIMAccessToken);
                if (!TextUtils.isEmpty(readCacheIMAccessToken)) {
                    Message obtainMessage = this.handler.obtainMessage(151, readCacheIMAccessToken);
                    obtainMessage.arg2 = 1001;
                    obtainMessage.sendToTarget();
                }
                this.handler.sendEmptyMessageDelayed(152, 1000);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                MyApplication.getInstance().registerReceiver(this.mNetStatusReceiver, intentFilter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finishActivity() {
        Activity activity = this.mActivty;
        if (activity == null) {
            return;
        }
        if (activity instanceof MainActivity) {
            this.mActivty = null;
            return;
        }
        activity.finish();
        this.mActivty = null;
    }

    public Handler getHandler() {
        return this.handler;
    }
}
