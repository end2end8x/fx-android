package com.fxeye.foreignexchangeeye.util_tool.okhttps;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class OkHttp {
    private static final String TAG = "OkHttp";

    public void OkHttpGetMethod(List<NameValuePair> list, final Handler handler, final int i, String str) {
        OkHttpClientManager.getAsyn(str, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                Message message = new Message();
                message.what = -i;
                message.obj = iOException.getMessage();
                handler.sendMessage(message);
                Log.e(TAG, "onFailure ", iOException);
            }

            public void onResponse(String str) {
                Log.i(TAG, "OkHttpGetMethod onResponse length " + str.length());
                Message message = new Message();
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                handler.sendMessage(message);
            }
        }, list);
    }

    public void OkHttpPostMethod(List<NameValuePair> list, final Handler handler, final int i, String str) {
        OkHttpClientManager.postAsyn(str, (OkHttpClientManager.StringCallback) new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
                Message message = new Message();
                message.what = -i;
                message.obj = iOException.getMessage();
                handler.sendMessage(message);
            }

            public void onResponse(String str) {
                Message message = new Message();
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                handler.sendMessage(message);
            }
        }, list);
    }

    public void OkHttpDownloadMethod(String str, final Handler handler, final int i, String str2) {
        OkHttpClientManager.downloadAsyn(str2, str, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                Message message = new Message();
                message.what = -i;
                handler.sendMessage(message);
            }

            public void onResponse(String str) {
                Message message = new Message();
                message.obj = str;
                message.what = i;
                handler.sendMessage(message);
            }
        });
    }
}
