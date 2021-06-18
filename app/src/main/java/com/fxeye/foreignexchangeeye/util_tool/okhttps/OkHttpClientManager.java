package com.fxeye.foreignexchangeeye.util_tool.okhttps;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;

import com.google.common.net.HttpHeaders;
import com.libs.view.optional.anaother.ConstDefine;
import com.libs.view.optional.util.Logx;
import com.wiki.exposure.gallerypick.utils.AppUtils;

import org.apache.http.NameValuePair;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientManager {
    public static final int CONNECT_TIMEOUT = 40;
    public static final int READ_TIMEOUT = 40;
    private static final String TAG = "OkHttpClientManager";
    public static final int WRITE_TIMEOUT = 40;
    private static OkHttpClientManager mInstance;
    private Handler mDelivery = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient = new OkHttpClient.Builder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).connectTimeout(40, TimeUnit.SECONDS).readTimeout(40, TimeUnit.SECONDS).writeTimeout(40, TimeUnit.SECONDS).build();
    /* access modifiers changed from: private */
    public HashMap<Request, TimeUrl> time = new HashMap<>();

    public interface StringCallback {
        void onFailure(Request request, IOException iOException);

        void onResponse(String str);
    }

    private Param[] validateParam(Param[] paramArr) {
        return paramArr == null ? new Param[0] : paramArr;
    }

    private static class TimeUrl {
        public long start;
        public String url;

        public TimeUrl(long j, String str) {
            this.start = j;
            this.url = str;
        }
    }

    private OkHttpClientManager() {
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    private Response _getAsyn(String str) throws IOException {
        return this.mOkHttpClient.newCall(new Request.Builder().url(str).build()).execute();
    }

    private String _getAsString(String str) throws IOException {
        return _getAsyn(str).body().string();
    }

    private void _getAsyn(String str, StringCallback stringCallback, List<NameValuePair> list) {
        if (list != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i).getName() + ConstDefine.DIVIDER_SIGN_DENGGHAO + list.get(i).getValue());
                if (i < list.size() - 1) {
                    stringBuffer.append("&");
                }
            }
            str = str + "?" + stringBuffer.toString();
        }
        Logx.m5549d("_getAsyn " + str);
        String basicData = AppUtils.getHttpHead();
        Logx.m5549d("_getAsyn " + basicData);
        Request build = new Request.Builder().addHeader("BasicData", AppUtils.getHttpHead()).url(str).build();
        this.time.put(build, new TimeUrl(SystemClock.elapsedRealtime(), str));
        deliveryResult(stringCallback, build);
    }

    private void _getAsyn(String str, StringCallback stringCallback) {
        deliveryResult(stringCallback, new Request.Builder().url(str).build());
    }

    private Response _post(String str, Param... paramArr) throws IOException {
        return this.mOkHttpClient.newCall(buildPostRequestArr(str, paramArr)).execute();
    }

    private String _postAsString(String str, Param... paramArr) throws IOException {
        return _post(str, paramArr).body().string();
    }

    private void _postAsyn(String str, StringCallback stringCallback, List<NameValuePair> list) {
        deliveryResult(stringCallback, buildPostRequest(str, list));
    }

    private void _postAsyn(String str, StringCallback stringCallback, Map<String, String> map) {
        deliveryResult(stringCallback, buildPostRequestArr(str, map2Params(map)));
    }

    private Response _post(String str, File[] fileArr, String[] strArr, Param... paramArr) throws IOException {
        return this.mOkHttpClient.newCall(buildMultipartFormRequest(str, fileArr, strArr, paramArr)).execute();
    }

    private Response _post(String str, File file, String str2) throws IOException {
        return this.mOkHttpClient.newCall(buildMultipartFormRequest(str, new File[]{file}, new String[]{str2}, (Param[]) null)).execute();
    }

    private Response _post(String str, File file, String str2, Param... paramArr) throws IOException {
        return this.mOkHttpClient.newCall(buildMultipartFormRequest(str, new File[]{file}, new String[]{str2}, paramArr)).execute();
    }

    private void _postAsyn(String str, StringCallback stringCallback, File[] fileArr, String[] strArr, Param... paramArr) throws IOException {
        deliveryResult(stringCallback, buildMultipartFormRequest(str, fileArr, strArr, paramArr));
    }

    private void _postAsyn(String str, StringCallback stringCallback, File file, String str2) throws IOException {
        deliveryResult(stringCallback, buildMultipartFormRequest(str, new File[]{file}, new String[]{str2}, (Param[]) null));
    }

    private void _postAsyn(String str, StringCallback stringCallback, File file, String str2, Param... paramArr) throws IOException {
        deliveryResult(stringCallback, buildMultipartFormRequest(str, new File[]{file}, new String[]{str2}, paramArr));
    }

    private void _downloadAsyn(final String str, final String str2, final StringCallback stringCallback) {
        this.mOkHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                OkHttpClientManager.this.sendFailedStringCallback(call.request(), iOException, stringCallback);
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|(3:5|6|(1:8)(1:45))|9|(2:11|12)|13|14|48) */
            /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0040 */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x0062 A[SYNTHETIC, Splitter:B:29:0x0062] */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x006d A[SYNTHETIC, Splitter:B:37:0x006d] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x0072 A[SYNTHETIC, Splitter:B:41:0x0072] */
            /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(okhttp3.Call r7, okhttp3.Response r8) throws java.io.IOException {
                /*
                    r6 = this;
                    r7 = 2048(0x800, float:2.87E-42)
                    byte[] r7 = new byte[r7]
                    r0 = 0
                    okhttp3.ResponseBody r1 = r8.body()     // Catch:{ IOException -> 0x0053, all -> 0x004f }
                    java.io.InputStream r1 = r1.byteStream()     // Catch:{ IOException -> 0x0053, all -> 0x004f }
                    java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    java.lang.String r3 = r4     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager r4 = com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager.this     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    java.lang.String r5 = r3     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    java.lang.String r4 = r4.getFileName(r5)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    r2.<init>(r3, r4)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    r3.<init>(r2)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                L_0x0021:
                    int r0 = r1.read(r7)     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    r4 = -1
                    if (r0 == r4) goto L_0x002d
                    r4 = 0
                    r3.write(r7, r4, r0)     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    goto L_0x0021
                L_0x002d:
                    r3.flush()     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager r7 = com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager.this     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    java.lang.String r0 = r2.getAbsolutePath()     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager$StringCallback r2 = r5     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    r7.sendSuccessStringCallback(r0, r2)     // Catch:{ IOException -> 0x0046, all -> 0x0044 }
                    if (r1 == 0) goto L_0x0040
                    r1.close()     // Catch:{ IOException -> 0x0040 }
                L_0x0040:
                    r3.close()     // Catch:{ IOException -> 0x0068 }
                    goto L_0x0068
                L_0x0044:
                    r7 = move-exception
                    goto L_0x006b
                L_0x0046:
                    r7 = move-exception
                    goto L_0x004d
                L_0x0048:
                    r7 = move-exception
                    r3 = r0
                    goto L_0x006b
                L_0x004b:
                    r7 = move-exception
                    r3 = r0
                L_0x004d:
                    r0 = r1
                    goto L_0x0055
                L_0x004f:
                    r7 = move-exception
                    r1 = r0
                    r3 = r1
                    goto L_0x006b
                L_0x0053:
                    r7 = move-exception
                    r3 = r0
                L_0x0055:
                    com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager r1 = com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager.this     // Catch:{ all -> 0x0069 }
                    okhttp3.Request r8 = r8.request()     // Catch:{ all -> 0x0069 }
                    com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager$StringCallback r2 = r5     // Catch:{ all -> 0x0069 }
                    r1.sendFailedStringCallback(r8, r7, r2)     // Catch:{ all -> 0x0069 }
                    if (r0 == 0) goto L_0x0065
                    r0.close()     // Catch:{ IOException -> 0x0065 }
                L_0x0065:
                    if (r3 == 0) goto L_0x0068
                    goto L_0x0040
                L_0x0068:
                    return
                L_0x0069:
                    r7 = move-exception
                    r1 = r0
                L_0x006b:
                    if (r1 == 0) goto L_0x0070
                    r1.close()     // Catch:{ IOException -> 0x0070 }
                L_0x0070:
                    if (r3 == 0) goto L_0x0075
                    r3.close()     // Catch:{ IOException -> 0x0075 }
                L_0x0075:
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager.C21661.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    /* access modifiers changed from: private */
    public String getFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1, str.length());
    }

    private void setErrorResId(final ImageView imageView, final int i) {
        this.mDelivery.post(new Runnable() {
            public void run() {
                imageView.setImageResource(i);
            }
        });
    }

    public static Response getAsyn(String str) throws IOException {
        return getInstance()._getAsyn(str);
    }

    public static String getAsString(String str) throws IOException {
        return getInstance()._getAsString(str);
    }

    public static void getAsyn(String str, StringCallback stringCallback) {
        getInstance()._getAsyn(str, stringCallback);
    }

    public static void getAsyn(String str, StringCallback stringCallback, List<NameValuePair> list) {
        getInstance()._getAsyn(str, stringCallback, list);
    }

    public static Response post(String str, Param... paramArr) throws IOException {
        return getInstance()._post(str, paramArr);
    }

    public static String postAsString(String str, Param... paramArr) throws IOException {
        return getInstance()._postAsString(str, paramArr);
    }

    public static void postAsyn(String str, StringCallback stringCallback, List<NameValuePair> list) {
        getInstance()._postAsyn(str, stringCallback, list);
    }

    public static void postAsyn(String str, StringCallback stringCallback, Map<String, String> map) {
        getInstance()._postAsyn(str, stringCallback, map);
    }

    public static Response post(String str, File[] fileArr, String[] strArr, Param... paramArr) throws IOException {
        return getInstance()._post(str, fileArr, strArr, paramArr);
    }

    public static Response post(String str, File file, String str2) throws IOException {
        return getInstance()._post(str, file, str2);
    }

    public static Response post(String str, File file, String str2, Param... paramArr) throws IOException {
        return getInstance()._post(str, file, str2, paramArr);
    }

    public static void postAsyn(String str, StringCallback stringCallback, File[] fileArr, String[] strArr, Param... paramArr) throws IOException {
        getInstance()._postAsyn(str, stringCallback, fileArr, strArr, paramArr);
    }

    public static void postAsyn(String str, StringCallback stringCallback, File file, String str2) throws IOException {
        getInstance()._postAsyn(str, stringCallback, file, str2);
    }

    public static void postAsyn(String str, StringCallback stringCallback, File file, String str2, Param... paramArr) throws IOException {
        getInstance()._postAsyn(str, stringCallback, file, str2, paramArr);
    }

    public static void downloadAsyn(String str, String str2, StringCallback stringCallback) {
        getInstance()._downloadAsyn(str, str2, stringCallback);
    }

    private Request buildMultipartFormRequest(String str, File[] fileArr, String[] strArr, Param[] paramArr) {
        File[] fileArr2 = fileArr;
        Param[] validateParam = validateParam(paramArr);
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Param param : validateParam) {
            type.addPart(Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + param.key + "\""), RequestBody.create((MediaType) null, param.value));
        }
        if (fileArr2 != null) {
            for (int i = 0; i < fileArr2.length; i++) {
                File file = fileArr2[i];
                String name = file.getName();
                type.addPart(Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + strArr[i] + "\"; filename=\"" + name + "\""), RequestBody.create(MediaType.parse(guessMimeType(name)), file));
            }
        }
        return new Request.Builder().url(str).post(type.build()).build();
    }

    private String guessMimeType(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    private Param[] map2Params(Map<String, String> map) {
        int i = 0;
        if (map == null) {
            return new Param[0];
        }
        Param[] paramArr = new Param[map.size()];
        for (Map.Entry next : map.entrySet()) {
            paramArr[i] = new Param((String) next.getKey(), (String) next.getValue());
            i++;
        }
        return paramArr;
    }

    private void deliveryResult(final StringCallback stringCallback, final Request request) {
        this.mOkHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                OkHttpClientManager.this.sendFailedStringCallback(call.request(), iOException, stringCallback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200) {
                        OkHttpClientManager.this.sendSuccessStringCallback(response.body().string(), stringCallback);
                    } else {
                        OkHttpClientManager.this.sendFailedStringCallback(response.request(), new IOException(response.body().string()), stringCallback);
                    }
                } catch (IOException e) {
                    OkHttpClientManager.this.sendFailedStringCallback(response.request(), e, stringCallback);
                }
                try {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    TimeUrl timeUrl = (TimeUrl) OkHttpClientManager.this.time.remove(request);
                    if (timeUrl != null) {
                        Logx.info("Interface return time(ms) " + (elapsedRealtime - timeUrl.start) + " " + request.method() + " " + timeUrl.url);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendFailedStringCallback(final Request request, final IOException iOException, final StringCallback stringCallback) {
        this.mDelivery.post(new Runnable() {
            public void run() {
                if (stringCallback != null) {
                    stringCallback.onFailure(request, iOException);
                }
                Logx.error("FailedString>>>>>>" + iOException);
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendSuccessStringCallback(final String str, final StringCallback stringCallback) {
        this.mDelivery.post(new Runnable() {
            public void run() {
                if (stringCallback != null) {
                    stringCallback.onResponse(str);
                }
                Log.i(TAG, "OkHttpClientManager sendSuccessStringCallback length " + str.length());
            }
        });
    }

    private Request buildPostRequestArr(String str, Param[] paramArr) {
        if (paramArr == null) {
            paramArr = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : paramArr) {
            if (param.value != null) {
                builder.add(param.key, param.value);
            }
        }
        return new Request.Builder().url(str).post(builder.build()).build();
    }

    private Request buildPostRequest(String str, List<NameValuePair> list) {
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() != null) {
                builder.add(list.get(i).getName(), list.get(i).getValue());
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < list.size(); i2++) {
            stringBuffer.append(list.get(i2).getName() + ConstDefine.DIVIDER_SIGN_DENGGHAO + list.get(i2).getValue());
            if (i2 < list.size() - 1) {
                stringBuffer.append("&");
            }
        }
        String sb = OkHttpUtils.listToJson(list, (StringBuilder) null).toString();
        Logx.m5549d("Post接口发送>>>>>>" + str + "   " + sb);
        Request build = new Request.Builder().url(str).addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8").addHeader("BasicData", AppUtils.getHttpHead()).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), sb)).build();
        this.time.put(build, new TimeUrl(SystemClock.elapsedRealtime(), str + "   " + sb));
        return build;
    }

    private Request buildGetRequest(String str, Param[] paramArr) {
        if (paramArr == null) {
            paramArr = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : paramArr) {
            if (param.value != null) {
                builder.add(param.key, param.value);
            }
        }
        builder.build();
        return new Request.Builder().url(str).build();
    }

    public static class Param {
        String key;
        String value;

        public Param() {
        }

        public Param(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }

    private static class TrustAllCerts implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        private TrustAllCerts() {
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private TrustAllHostnameVerifier() {
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }
}
