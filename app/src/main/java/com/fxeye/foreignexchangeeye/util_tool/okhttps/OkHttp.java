package com.fxeye.foreignexchangeeye.util_tool.okhttps;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class OkHttp {
    public void OkHttpGetMethod(List<NameValuePair> list, final Handler handler, final int i, String str) {
        final Message message = new Message();
        OkHttpClientManager.getAsyn(str, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
                Message message = new Message();
                message.what = -i;
                message.obj = iOException.toString();
                handler.sendMessage(message);
                Log.e("test", "result fail");
            }

            public void onResponse(String str) {
                message.obj = str;
                Log.i("test", "result ok" + str);
                Message message = new Message();
                message.what = i;
                message.arg1 = 200;
                handler.sendMessage(message);
            }
        }, list);
    }

    public void OkHttpPostMethod(List<NameValuePair> list, final Handler handler, final int i, String str) {
        final Message message = new Message();
        OkHttpClientManager.postAsyn(str, (OkHttpClientManager.StringCallback) new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
                Message message = new Message();
                message.what = -i;
                message.obj = iOException.toString();
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
        final Message message = new Message();
        OkHttpClientManager.downloadAsyn(str2, str, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                Message message = new Message();
                message.what = -i;
                handler.sendMessage(message);
            }

            public void onResponse(String str) {
                Message message = new Message();
                message.what = i;
                handler.sendMessage(message);
            }
        });
    }
}
