package com.fxeye.foreignexchangeeye.view.im_chatutils;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.controller.AboutController;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpUtils;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.libs.view.optional.anaother.ConstDefine;
import com.libs.view.optional.util.Logx;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IMOkHttpRequestManager {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    private static final MediaType MEDIA_TYPE_STRING = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private static final String TAG = "OkHttpRequestManager";
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST_JSON = 1;
    public static final int TYPE_POST_STRING = 2;
    private static Context mContext;
    private static volatile IMOkHttpRequestManager mInstance;
    private Gson gson = new Gson();
    private OkHttpClient mOkHttpClient;
    private Handler okHttpHandler;

    public interface ReqCallBack<String> {
        void onFailed(String str);

        void onSuccess(String t);
    }

    public IMOkHttpRequestManager(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
            builder.sslSocketFactory(instance.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            builder.connectTimeout(50, TimeUnit.SECONDS);
            builder.readTimeout(50, TimeUnit.SECONDS);
            builder.writeTimeout(50, TimeUnit.SECONDS);
            this.mOkHttpClient = builder.build();
            this.okHttpHandler = new Handler(context.getMainLooper());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IMOkHttpRequestManager getInstance() {
        mContext = MyApplication.getInstance();
        IMOkHttpRequestManager iMOkHttpRequestManager = mInstance;
        if (iMOkHttpRequestManager == null) {
            synchronized (IMOkHttpRequestManager.class) {
                iMOkHttpRequestManager = mInstance;
                if (iMOkHttpRequestManager == null) {
                    iMOkHttpRequestManager = new IMOkHttpRequestManager(mContext.getApplicationContext());
                    mInstance = iMOkHttpRequestManager;
                }
            }
        }
        return iMOkHttpRequestManager;
    }

    public void requestSyn(String str, int i, Map<String, String> map) {
        if (i == 0) {
            requestGetBySyn(str, map);
        } else if (i == 1) {
            requestPostBySyn(str, map);
        }
    }

    private void requestPostBySyn(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
                i++;
            }
            Response execute = this.mOkHttpClient.newCall(addHeaders().url(str).post(RequestBody.create(MEDIA_TYPE_JSON, sb.toString())).build()).execute();
            if (execute.isSuccessful()) {
                Logx.e("OkHttpRequestManagerresponse ----->" + execute.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logx.e("OkHttpRequestManagerrequestGetBySyn: " + e.toString());
        }
    }

    private void requestGetBySyn(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
                i++;
            }
            Request build = addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build();
            this.mOkHttpClient.newCall(build).execute();
            build.body().toString();
        } catch (Exception e) {
            e.printStackTrace();
            Logx.e("OkHttpRequestManagerrequestGetBySyn: " + e.toString());
        }
    }

    public <T> Call requestGetAddTo(String str, Map<String, Object> map, final ReqCallBack<String> reqCallBack) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                    Logx.e(IMOkHttpRequestManager.TAG + iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
                                IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                            } else if ("401".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack("401", reqCallBack);
                            } else if ("200".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
                            } else {
                                IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(e.toString(), reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call requestGetByASyn(String str, Map<String, Object> map, final ReqCallBack<String> reqCallBack) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ChatLoginController.getInstance().getIMAccessToken()).addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance())).addHeader(HttpHeaders.CONTENT_TYPE, "application/json").url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                    Logx.e(IMOkHttpRequestManager.TAG + iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        if (response.isSuccessful()) {
                            IMOkHttpRequestManager.this.successCallBack(string, reqCallBack);
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(e.toString(), reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call getAccessTokenByASyn(String str, Map<String, Object> map, final ReqCallBack reqCallBack) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        IMOkHttpRequestManager.this.successCallBack(response.body().string(), reqCallBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(e.toString(), reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call getByASyn(String str, Map<String, Object> map, final ReqCallBack reqCallBack) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                            } else {
                                IMOkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(e.toString(), reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public void failedCallBack(Handler handler, int i, int i2, String str) {
        Message message = new Message();
        message.what = -i;
        message.arg1 = i2;
        message.obj = str;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void failedCallBack(Handler handler, int i, int i2, String str, int i3) {
        Message message = new Message();
        message.what = -i;
        message.arg1 = i2;
        message.obj = str;
        message.arg2 = i3;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void successCallBack(Handler handler, int i, int i2, String str, int i3) {
        Message message = new Message();
        message.what = i;
        message.arg1 = i2;
        message.obj = str;
        message.arg2 = i3;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void successCallBack(Handler handler, int i, int i2, String str) {
        Message message = new Message();
        message.what = i;
        message.arg1 = i2;
        message.obj = str;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void getAsyn(List<NameValuePair> list, final Handler handler, final int i, String str) {
        if (list != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < list.size(); i2++) {
                stringBuffer.append(list.get(i2).getName() + ConstDefine.DIVIDER_SIGN_DENGGHAO + list.get(i2).getValue());
                if (i2 < list.size() - 1) {
                    stringBuffer.append("&");
                }
            }
            str = str + "?" + stringBuffer.toString();
        }
        this.mOkHttpClient.newCall(addHeaders().url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String string = response.body().string();
                    int code = response.code();
                    if (response.isSuccessful()) {
                        JSONObject jSONObject = new JSONObject(string);
                        if (jSONObject.has("code")) {
                            try {
                                code = Integer.parseInt(jSONObject.getString("code"));
                            } catch (Exception unused) {
                            }
                        }
                        if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        } else {
                            IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                        }
                    } else {
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                }
            }
        });
    }

    public <T> Call getByASyn(String str, Map<String, Object> map, final Handler handler, final int i) {
        StringBuilder sb = new StringBuilder();
        try {
            int i2 = 0;
            for (String next : map.keySet()) {
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i2++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        int code = response.code();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("code")) {
                                try {
                                    code = Integer.parseInt(jSONObject.getString("code"));
                                } catch (Exception unused) {
                                }
                            }
                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else {
                                IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call getByASyn(String str, Map<String, Object> map, final Handler handler, final int i, final int i2) {
        StringBuilder sb = new StringBuilder();
        try {
            int i3 = 0;
            for (String next : map.keySet()) {
                if (i3 > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i3++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        int code = response.code();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("code")) {
                                try {
                                    code = Integer.parseInt(jSONObject.getString("code"));
                                } catch (Exception unused) {
                                }
                            }
                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                                return;
                            }
                            Message message = new Message();
                            message.what = i;
                            message.arg1 = code;
                            message.arg2 = i2;
                            message.obj = string;
                            if (handler != null) {
                                handler.sendMessage(message);
                                return;
                            }
                            return;
                        }
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call requestGetByASynNo(String str, Map<String, Object> map, final ReqCallBack<String> reqCallBack) {
        StringBuilder sb = new StringBuilder();
        try {
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ChatLoginController.getInstance().getIMAccessToken()).addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance())).addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8").url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                    Logx.e(IMOkHttpRequestManager.TAG + iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    if (response.isSuccessful()) {
                        IMOkHttpRequestManager.this.successCallBack(string, reqCallBack);
                    } else {
                        IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public Call postAsyn(String str, Map<String, Object> map, final Handler handler, final int i) {
        try {
            RequestBody create = RequestBody.create(MEDIA_TYPE_JSON, this.gson.toJson((Object) map));
            Request.Builder addHeader = new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
            Request.Builder addHeader2 = addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
            StringBuilder sb = new StringBuilder();
            sb.append("Bearer ");
            sb.append(ChatLoginController.getInstance().getIMAccessToken());
            this.mOkHttpClient.newCall(addHeader2.addHeader(HttpHeaders.AUTHORIZATION, sb.toString()).url(str).post(create).build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        int code = response.code();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("code")) {
                                try {
                                    code = Integer.parseInt(jSONObject.getString("code"));
                                } catch (Exception unused) {
                                }
                            }
                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else if ("401".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else if ("200".equals(jSONObject.getString("code"))) {
                                if (jSONObject.has("Data")) {
                                    jSONObject.getString("Data");
                                }
                                IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                            } else {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                    }
                }
            });
            return null;
        } catch (Exception e) {
            Logx.e("OkHttpRequestManager>>>" + e.toString());
            return null;
        }
    }

    public Call newPostAsyn(String str, Map<String, Object> map, final Handler handler, final int i) {
        try {
            RequestBody create = RequestBody.create(MEDIA_TYPE_JSON, this.gson.toJson((Object) map));
            Request.Builder addHeader = new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
            Request.Builder addHeader2 = addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
            StringBuilder sb = new StringBuilder();
            sb.append("Bearer ");
            sb.append(ChatLoginController.getInstance().getIMAccessToken());
            this.mOkHttpClient.newCall(addHeader2.addHeader(HttpHeaders.AUTHORIZATION, sb.toString()).url(str).post(create).build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        int code = response.code();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("code")) {
                                try {
                                    code = Integer.parseInt(jSONObject.getString("code"));
                                } catch (Exception unused) {
                                }
                            }
                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else if ("401".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else if (!"200".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                            } else if (jSONObject.has("Data")) {
                                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("Data"));
                                if (jSONObject2.has("Data")) {
                                    IMOkHttpRequestManager.this.successCallBack(handler, i, code, jSONObject2.getString("Data"));
                                } else {
                                    IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                                }
                            } else {
                                IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                    }
                }
            });
            return null;
        } catch (Exception e) {
            Logx.e("OkHttpRequestManager>>>" + e.toString());
            return null;
        }
    }

    public Call postAsyn(String str, Map<String, Object> map, final Handler handler, final int i, final int i2) {
        try {
            RequestBody create = RequestBody.create(MEDIA_TYPE_JSON, this.gson.toJson((Object) map));
            Request.Builder addHeader = new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
            Request.Builder addHeader2 = addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
            StringBuilder sb = new StringBuilder();
            sb.append("Bearer ");
            sb.append(ChatLoginController.getInstance().getIMAccessToken());
            this.mOkHttpClient.newCall(addHeader2.addHeader(HttpHeaders.AUTHORIZATION, sb.toString()).url(str).post(create).build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString(), i2);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        int code = response.code();
                        if (response.isSuccessful()) {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("code")) {
                                try {
                                    code = Integer.parseInt(jSONObject.getString("code"));
                                } catch (Exception unused) {
                                }
                            }
                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string, i2);
                            } else if ("401".equals(jSONObject.getString("code"))) {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string, i2);
                            } else if ("200".equals(jSONObject.getString("code"))) {
                                if (jSONObject.has("Data")) {
                                    jSONObject.getString("Data");
                                }
                                IMOkHttpRequestManager.this.successCallBack(handler, i, code, string, i2);
                            } else {
                                IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string, i2);
                            }
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string, i2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString(), i2);
                    }
                }
            });
            return null;
        } catch (Exception e) {
            Logx.e("OkHttpRequestManager>>>" + e.toString());
            return null;
        }
    }

    public void postAsyn(List<NameValuePair> list, final Handler handler, final int i, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < list.size(); i2++) {
            stringBuffer.append(list.get(i2).getName() + ConstDefine.DIVIDER_SIGN_DENGGHAO + list.get(i2).getValue());
            if (i2 < list.size() - 1) {
                stringBuffer.append("&");
            }
        }
        String sb = OkHttpUtils.listToJson(list, (StringBuilder) null).toString();
        Logx.m5549d("Post接口发送>>>>>>" + str + "   " + sb);
        RequestBody create = RequestBody.create(MEDIA_TYPE_JSON, sb);
        Request.Builder addHeader = new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        Request.Builder addHeader2 = addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Bearer ");
        sb2.append(ChatLoginController.getInstance().getIMAccessToken());
        this.mOkHttpClient.newCall(addHeader2.addHeader(HttpHeaders.AUTHORIZATION, sb2.toString()).url(str).post(create).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, iOException.toString());
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String string = response.body().string();
                    int code = response.code();
                    if (response.isSuccessful()) {
                        JSONObject jSONObject = new JSONObject(string);
                        if (jSONObject.has("code")) {
                            try {
                                code = Integer.parseInt(jSONObject.getString("code"));
                            } catch (Exception unused) {
                            }
                        }
                        if (!jSONObject.has("Success") || !jSONObject.has("code")) {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        } else if ("401".equals(jSONObject.getString("code"))) {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        } else if ("200".equals(jSONObject.getString("code"))) {
                            if (jSONObject.has("Data")) {
                                jSONObject.getString("Data");
                            }
                            IMOkHttpRequestManager.this.successCallBack(handler, i, code, string);
                        } else {
                            IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                        }
                    } else {
                        IMOkHttpRequestManager.this.failedCallBack(handler, i, code, string);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    IMOkHttpRequestManager.this.failedCallBack(handler, i, -1, e.toString());
                }
            }
        });
    }

//    public <T> Call postAsyn(String str, Map<String, Object> map, final OkHttpRequestManager.ReqCallBack<String> reqCallBack) {
//        try {
//            new StringBuilder();
//            RequestBody create = RequestBody.create(MEDIA_TYPE_JSON, new Gson().toJson((Object) map));
//            Request.Builder addHeader = new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//            Request.Builder addHeader2 = addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("Bearer ");
//            sb.append(ChatLoginController.getInstance().getIMAccessToken());
//            this.mOkHttpClient.newCall(addHeader2.addHeader(HttpHeaders.AUTHORIZATION, sb.toString()).url(str).post(create).build()).enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
//                    Logx.m5550e(IMOkHttpRequestManager.TAG + iOException.toString());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
//                                IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
//                            } else if ("401".equals(jSONObject.getString("code"))) {
//                                IMOkHttpRequestManager.this.failedCallBack("401", reqCallBack);
//                            } else if ("200".equals(jSONObject.getString("code"))) {
//                                IMOkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
//                            } else {
//                                IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
//                            }
//                        } else {
//                            IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        IMOkHttpRequestManager.this.failedCallBack(e.toString(), reqCallBack);
//                    }
//                }
//            });
//            return null;
//        } catch (Exception e) {
//            Logx.m5550e("OkHttpRequestManager>>>" + e.toString());
//            return null;
//        }
//    }

    private <T> Call requestPostStingAsyn(String str, Map<String, Object> map, final ReqCallBack<String> reqCallBack) {
        try {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (String next : map.keySet()) {
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
                i++;
            }
            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(str).post(RequestBody.create(MEDIA_TYPE_STRING, sb.toString())).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                    Logx.e(IMOkHttpRequestManager.TAG + iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    if (response.isSuccessful()) {
                        IMOkHttpRequestManager.this.successCallBack(string, reqCallBack);
                    } else if (401 == response.code()) {
                        IMOkHttpRequestManager.this.failedCallBack("401", reqCallBack);
                    } else {
                        IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    public <T> Call uploadImage(String str, Map<String, Object> map, String str2, final ReqCallBack<String> reqCallBack) {
        try {
            MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String next : map.keySet()) {
                type.addFormDataPart(next, map.get(next) + "");
            }
            File file = new File(str2);
            type.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("png"), file));
            MultipartBody build = type.build();
            Request.Builder builder = new Request.Builder();
            Request.Builder addHeader = builder.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ChatLoginController.getInstance().getIMAccessToken());
            Call newCall = this.mOkHttpClient.newCall(addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance())).url(str).post(build).build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    IMOkHttpRequestManager.this.failedCallBack(iOException.toString(), reqCallBack);
                    Logx.e(IMOkHttpRequestManager.TAG + iOException.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    if (response.isSuccessful()) {
                        IMOkHttpRequestManager.this.successCallBack(string, reqCallBack);
                    } else if (401 == response.code()) {
                        IMOkHttpRequestManager.this.failedCallBack("401", reqCallBack);
                    } else {
                        IMOkHttpRequestManager.this.failedCallBack(string, reqCallBack);
                    }
                }
            });
            return newCall;
        } catch (Exception e) {
            Logx.e(TAG + e.toString());
            return null;
        }
    }

    private Request.Builder addHeaders() {
        new StringBuilder();
        Request.Builder builder = new Request.Builder();
        Request.Builder addHeader = builder.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + ChatLoginController.getInstance().getIMAccessToken()).addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        return addHeader.addHeader("BasicData", "1," + Build.VERSION.RELEASE + ",1," + AboutController.getAppVersion(MyApplication.getInstance()));
    }

    /* access modifiers changed from: private */
    public <T> void successCallBack(final String t, final ReqCallBack<String> reqCallBack) {
        this.okHttpHandler.post(new Runnable() {
            public void run() {
//                ReqCallBack reqCallBack = reqCallBack;
                if (reqCallBack != null) {
                    reqCallBack.onSuccess(t);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public <T> void failedCallBack(final String str, final ReqCallBack<String> reqCallBack) {
        this.okHttpHandler.post(new Runnable() {
            public void run() {
//                ReqCallBack reqCallBack = reqCallBack;
                if (reqCallBack != null) {
                    reqCallBack.onFailed(str);
                }
            }
        });
    }
}
