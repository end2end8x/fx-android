//package com.wiki.exposure.framework.http;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import com.fxeye.foreignexchangeeye.controller.cloudcontroller.CloudNetController;
//import com.fxeye.foreignexchangeeye.view.im_chatutils.IMOkHttpRequestManager;
//import com.google.common.net.HttpHeaders;
//import com.google.gson.Gson;
//import com.libs.view.optional.anaother.ConstDefine;
//import com.wiki.exposure.framework.utils.Logger;
//import com.wiki.exposure.gallerypick.utils.AppUtils;
//import java.io.File;
//import java.io.IOException;
//import java.security.SecureRandom;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.KeyManager;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import org.json.JSONException;
//import org.json.JSONObject;
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//public class OkHttpRequestManager {
//    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
//    private static final MediaType MEDIA_TYPE_STRING = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
//    private static final String TAG = "OkHttpRequestManager";
//    public static final int TYPE_GET = 0;
//    public static final int TYPE_POST_JSON = 1;
//    public static final int TYPE_POST_STRING = 2;
//    private static Context mContext;
//    private static volatile OkHttpRequestManager mInstance;
//    private OkHttpClient mOkHttpClient;
//    private Handler okHttpHandler;
//
//    public interface ReqCallBack<T> extends IMOkHttpRequestManager.ReqCallBack<T> {
//        void onFailed(String str);
//
//        void onSuccess(T t);
//    }
//
//    public interface RequestCallBack {
//        void onResponse(String str, int i);
//    }
//
//    public Call requestPost(String str, Map<String, Object> map, final Handler handler, final int i) {
//        try {
//            CloudNetController.addPostPublicParams(map);
//            final Message message = new Message();
//            String json = new Gson().toJson((Object) map);
//            Logger logger = Logger.getLogger(getClass());
//            logger.mo54118d("mOkHttpRequest Post =%s", str + ConstDefine.DIVIDER_SIGN_DENGGHAO + json);
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8").url(str).post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json)).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    Message message = message;
//                    message.what = -i;
//                    message.obj = iOException.getMessage();
//                    handler.sendMessage(message);
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
//                                message.what = -i;
//                                message.obj = "error";
//                                handler.sendMessage(message);
//                                return;
//                            }
//                            message.obj = jSONObject.getString("Data");
//                            message.what = i;
//                            message.arg1 = 200;
//                            handler.sendMessage(message);
//                            return;
//                        }
//                        message.what = -i;
//                        message.obj = "error";
//                        handler.sendMessage(message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public Call newRequestPost(String str, Map<String, Object> map, final Handler handler, final int i) {
//        try {
//            CloudNetController.addPostPublicParams(map);
//            final Message message = new Message();
//            String json = new Gson().toJson((Object) map);
//            Logger logger = Logger.getLogger(getClass());
//            logger.mo54118d("mOkHttpRequest Post =%s", str + ConstDefine.DIVIDER_SIGN_DENGGHAO + json);
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8").url(str).post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json)).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    Message message = message;
//                    message.what = -i;
//                    message.obj = iOException.getMessage();
//                    handler.sendMessage(message);
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("ErrorCode") || !"200".equals(jSONObject.getString("ErrorCode"))) {
//                                message.what = -i;
//                                message.obj = "error";
//                            } else {
//                                message.obj = jSONObject.optJSONObject("Data");
//                                message.what = i;
//                                message.arg1 = 200;
//                            }
//                        } else {
//                            message.what = -i;
//                            message.obj = "error";
//                        }
//                        handler.sendMessage(message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public OkHttpRequestManager(Context context) {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        try {
//            TrustManager[] trustManagerArr = {new X509TrustManager() {
//                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
//                }
//
//                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
//                }
//
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[0];
//                }
//            }};
//            SSLContext instance = SSLContext.getInstance("SSL");
//            instance.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
//            builder.sslSocketFactory(instance.getSocketFactory());
//            builder.hostnameVerifier(new HostnameVerifier() {
//                public boolean verify(String str, SSLSession sSLSession) {
//                    return true;
//                }
//            });
//            builder.connectTimeout(50, TimeUnit.SECONDS);
//            builder.readTimeout(50, TimeUnit.SECONDS);
//            builder.writeTimeout(50, TimeUnit.SECONDS);
//            this.mOkHttpClient = builder.build();
//            this.okHttpHandler = new Handler(context.getMainLooper());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Call requestPosts(String str, Map<String, Object> map, final RequestCallBack requestCallBack, int i) {
//        try {
//            String json = new Gson().toJson((Object) map);
//            Logger logger = Logger.getLogger(getClass());
//            logger.mo54118d("mOkHttpRequest Post =%s", str + ConstDefine.DIVIDER_SIGN_DENGGHAO + json);
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8").url(str).post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json)).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, final IOException iOException) {
//                    Observable.create(new ObservableOnSubscribe<Object>() {
//                        public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
//                            observableEmitter.onNext(0);
//                        }
//                    }).subscribeOn(Schedulers.m11476io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
//                        public void onComplete() {
//                        }
//
//                        public void onError(Throwable th) {
//                        }
//
//                        public void onSubscribe(Disposable disposable) {
//                        }
//
//                        public void onNext(Object obj) {
//                            if (requestCallBack != null) {
//                                requestCallBack.onResponse(iOException.getMessage(), -1);
//                            }
//                        }
//                    });
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", iOException.getMessage());
//                }
//
//                public void onResponse(Call call, final Response response) throws IOException {
//                    try {
//                        final String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", string);
//                        Observable.create(new ObservableOnSubscribe<Object>() {
//                            public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
//                                observableEmitter.onNext(0);
//                            }
//                        }).subscribeOn(Schedulers.m11476io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
//                            public void onComplete() {
//                            }
//
//                            public void onError(Throwable th) {
//                            }
//
//                            public void onSubscribe(Disposable disposable) {
//                            }
//
//                            public void onNext(Object obj) {
//                                if (response.isSuccessful()) {
//                                    if (requestCallBack != null) {
//                                        requestCallBack.onResponse(string, 200);
//                                    }
//                                } else if (requestCallBack != null) {
//                                    requestCallBack.onResponse("error", -1);
//                                }
//                            }
//                        });
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public static OkHttpRequestManager getInstance(Context context) {
//        mContext = context;
//        OkHttpRequestManager okHttpRequestManager = mInstance;
//        if (okHttpRequestManager == null) {
//            synchronized (OkHttpRequestManager.class) {
//                okHttpRequestManager = mInstance;
//                if (okHttpRequestManager == null) {
//                    okHttpRequestManager = new OkHttpRequestManager(context.getApplicationContext());
//                    mInstance = okHttpRequestManager;
//                }
//            }
//        }
//        return okHttpRequestManager;
//    }
//
//    public void requestSyn(String str, int i, Map<String, String> map) {
//        if (i == 0) {
//            requestGetBySyn(str, map);
//        } else if (i == 1) {
//            requestPostBySyn(str, map);
//        }
//    }
//
//    private void requestPostBySyn(String str, Map<String, String> map) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
//                i++;
//            }
//            Response execute = this.mOkHttpClient.newCall(addHeaders().url(str).post(RequestBody.create(MEDIA_TYPE_JSON, sb.toString())).build()).execute();
//            if (execute.isSuccessful()) {
//                Log.e(TAG, "response ----->" + execute.body().string());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(TAG, "requestGetBySyn: " + e.toString());
//        }
//    }
//
//    private void requestGetBySyn(String str, Map<String, String> map) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
//                i++;
//            }
//            String format = String.format("%s?%s", new Object[]{str, sb.toString()});
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", format);
//            Request build = addHeaders().url(format).build();
//            this.mOkHttpClient.newCall(build).execute();
//            build.body().toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(TAG, "requestGetBySyn: " + e.toString());
//        }
//    }
//
//    public <T> Call requestAsyn(String str, int i, Map<String, Object> map, ReqCallBack<T> reqCallBack) {
//        if (i == 0) {
//            return requestGetByASyn(str, map, reqCallBack);
//        }
//        if (i == 1) {
//            return requestPostByAsyn(str, map, reqCallBack);
//        }
//        if (i != 2) {
//            return null;
//        }
//        return requestPostStingAsyn(str, map, reqCallBack);
//    }
//
//    public <T> Call requestGetAddTo(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
//                i++;
//            }
//            String format = String.format("%s?%s", new Object[]{str, sb.toString()});
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", format);
//            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(format).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
//                                OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                            } else if ("401".equals(jSONObject.getString("code"))) {
//                                OkHttpRequestManager.this.failedCallBack("401", reqCallBack);
//                            } else if ("200".equals(jSONObject.getString("code"))) {
//                                OkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
//                            } else {
//                                OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                            }
//                        } else {
//                            OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call requestGetByASyn(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
//                i++;
//            }
//            String format = String.format("%s?%s", new Object[]{str, sb.toString()});
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", format);
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json").addHeader("BasicData", AppUtils.getHttpHead()).url(format).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", string);
//                        if (response.isSuccessful()) {
//                            OkHttpRequestManager.this.successCallBack(string, reqCallBack);
//                        } else {
//                            OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call getByASyn(String str, Map<String, Object> map, final ReqCallBack reqCallBack) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
//                i++;
//            }
//            String format = String.format("%s?%s", new Object[]{str, sb.toString()});
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", format);
//            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(format).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
//                                OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                            } else {
//                                OkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
//                            }
//                        } else {
//                            OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call getByASyn(String str, Map<String, Object> map, final Handler handler, final int i) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i2 = 0;
//            for (String next : map.keySet()) {
//                if (i2 > 0) {
//                    sb.append("&");
//                }
//                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
//                i2++;
//            }
//            String format = String.format("%s?%s", new Object[]{str, sb.toString()});
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", format);
//            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(format).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    Message message = new Message();
//                    message.what = 101;
//                    message.arg1 = i;
//                    Handler handler = handler;
//                    if (handler != null) {
//                        handler.sendMessage(message);
//                    }
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code") || !"200".equals(jSONObject.getString("code"))) {
//                                Message message = new Message();
//                                message.what = 101;
//                                message.arg1 = i;
//                                if (handler != null) {
//                                    handler.sendMessage(message);
//                                    return;
//                                }
//                                return;
//                            }
//                            Message message2 = new Message();
//                            message2.what = i;
//                            message2.obj = jSONObject.getString("Data");
//                            if (handler != null) {
//                                handler.sendMessage(message2);
//                                return;
//                            }
//                            return;
//                        }
//                        Message message3 = new Message();
//                        message3.what = 101;
//                        message3.arg1 = i;
//                        if (handler != null) {
//                            handler.sendMessage(message3);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Message message4 = new Message();
//                        message4.what = 101;
//                        message4.arg1 = i;
//                        Handler handler = handler;
//                        if (handler != null) {
//                            handler.sendMessage(message4);
//                        }
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call requestGetByASynNo(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(next + ConstDefine.DIVIDER_SIGN_DENGGHAO + map.get(next));
//                i++;
//            }
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8").addHeader("BasicData", AppUtils.getHttpHead()).url(String.format("%s?%s", new Object[]{str, sb.toString()})).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest GET =%s", string);
//                    if (response.isSuccessful()) {
//                        OkHttpRequestManager.this.successCallBack(string, reqCallBack);
//                    } else {
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    private <T> Call requestPostByAsyn(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        try {
//            new StringBuilder();
//            String json = new Gson().toJson((Object) map);
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s?%s", str, json);
//            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(str).post(RequestBody.create(MEDIA_TYPE_JSON, json)).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest POST =%s", string);
//                    if (response.isSuccessful()) {
//                        OkHttpRequestManager.this.successCallBack(string, reqCallBack);
//                    } else {
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call postAsyn(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        try {
//            new StringBuilder();
//            String json = new Gson().toJson((Object) map);
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s?%s", str, json);
//            this.mOkHttpClient.newCall(new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json").addHeader("BasicData", AppUtils.getHttpHead()).url(str).post(RequestBody.create(MEDIA_TYPE_JSON, json)).build()).enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String string = response.body().string();
//                        Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s", string);
//                        if (response.isSuccessful()) {
//                            JSONObject jSONObject = new JSONObject(string);
//                            if (!jSONObject.has("Success") || !jSONObject.has("code")) {
//                                OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                            } else if ("401".equals(jSONObject.getString("code"))) {
//                                OkHttpRequestManager.this.failedCallBack("401", reqCallBack);
//                            } else if ("200".equals(jSONObject.getString("code"))) {
//                                OkHttpRequestManager.this.successCallBack(jSONObject.getString("Data"), reqCallBack);
//                            } else {
//                                OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                            }
//                        } else {
//                            OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return null;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    private <T> Call requestPostStingAsyn(String str, Map<String, Object> map, final ReqCallBack<T> reqCallBack) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            int i = 0;
//            for (String next : map.keySet()) {
//                if (i > 0) {
//                    sb.append("&");
//                }
//                sb.append(String.format("%s=%s", new Object[]{next, map.get(next)}));
//                i++;
//            }
//            String sb2 = sb.toString();
//            Logger.getLogger(getClass()).mo54118d("mOkHttpRequest Post =%s?%s", str, sb2);
//            Call newCall = this.mOkHttpClient.newCall(addHeaders().url(str).post(RequestBody.create(MEDIA_TYPE_STRING, sb2)).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest POST =%s", string);
//                    if (response.isSuccessful()) {
//                        OkHttpRequestManager.this.successCallBack(string, reqCallBack);
//                    } else if (401 == response.code()) {
//                        OkHttpRequestManager.this.failedCallBack("401", reqCallBack);
//                    } else {
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    public <T> Call uploadImage(String str, Map<String, Object> map, String str2, final ReqCallBack<T> reqCallBack) {
//        try {
//            MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
//            for (String next : map.keySet()) {
//                type.addFormDataPart(next, map.get(next) + "");
//            }
//            File file = new File(str2);
//            type.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("png"), file));
//            Call newCall = this.mOkHttpClient.newCall(new Request.Builder().url(str).post(type.build()).build());
//            newCall.enqueue(new Callback() {
//                public void onFailure(Call call, IOException iOException) {
//                    OkHttpRequestManager.this.failedCallBack("访问失败", reqCallBack);
//                    Log.e(OkHttpRequestManager.TAG, iOException.getMessage());
//                }
//
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    Logger.getLogger(getClass()).mo54118d("mOkHttpRequest POST =%s", string);
//                    if (response.isSuccessful()) {
//                        OkHttpRequestManager.this.successCallBack(string, reqCallBack);
//                    } else if (401 == response.code()) {
//                        OkHttpRequestManager.this.failedCallBack("401", reqCallBack);
//                    } else {
//                        OkHttpRequestManager.this.failedCallBack("服务器错误", reqCallBack);
//                    }
//                }
//            });
//            return newCall;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
//
//    private Request.Builder addHeaders() {
//        new StringBuilder();
//        return new Request.Builder().addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8").addHeader("BasicData", AppUtils.getHttpHead());
//    }
//
//    /* access modifiers changed from: private */
//    public <T> void successCallBack(final T t, final ReqCallBack<T> reqCallBack) {
//        this.okHttpHandler.post(new Runnable() {
//            public void run() {
//                ReqCallBack reqCallBack = reqCallBack;
//                if (reqCallBack != null) {
//                    reqCallBack.onSuccess(t);
//                }
//            }
//        });
//    }
//
//    /* access modifiers changed from: private */
//    public <T> void failedCallBack(final String str, final ReqCallBack<T> reqCallBack) {
//        this.okHttpHandler.post(new Runnable() {
//            public void run() {
//                ReqCallBack reqCallBack = reqCallBack;
//                if (reqCallBack != null) {
//                    reqCallBack.onFailed(str);
//                }
//            }
//        });
//    }
//}
