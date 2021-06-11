package com.fxeye.foreignexchangeeye.util_tool.okhttps;

import android.util.Log;

import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.libs.view.optional.anaother.ConstDefine;
import com.mob.tools.utils.Dic;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class Utils {
    private static final int SPACE_TIME = 800;
    private static long lastClickTime;

    public static String local2UTC() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Dic.GPS));
        Date date = new Date();
        date.setTime(((new Date().getTime() / 1000) - 480) * 1000);
        return simpleDateFormat.format(date);
    }

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    public static synchronized boolean isDoubleClick() {
        boolean z;
        synchronized (Utils.class) {
            long currentTimeMillis = System.currentTimeMillis();
            z = currentTimeMillis - lastClickTime <= 800;
            lastClickTime = currentTimeMillis;
        }
        return z;
    }

    public static String getRandomString() {
        Date date = new Date();
        return date.getTime() + "";
    }

    public static String getToken(List<NameValuePair> list, String str, String str2) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(list.get(i).getName(), list.get(i).getValue());
        }
        String[] strArr = (String[]) hashMap.keySet().toArray(new String[0]);
        Arrays.sort(strArr);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            stringBuffer.append(OkHttpUtils.encode(strArr[i2]) + ConstDefine.DIVIDER_SIGN_DENGGHAO + OkHttpUtils.encode((String) hashMap.get(strArr[i2])));
            stringBuffer.append(strArr[i2]);
            stringBuffer.append(ConstDefine.DIVIDER_SIGN_DENGGHAO);
            stringBuffer.append((String) hashMap.get(strArr[i2]));
            if (i2 < strArr.length - 1) {
                stringBuffer.append("&");
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        Log.i("test", "codes=" + stringBuffer2);
        String authString = OkHttpUtils.getAuthString(str, stringBuffer2);
        Log.i("test", "codes  authString=" + authString);
        String HmacSHA1Encrypt = OkHttpUtils.HmacSHA1Encrypt(authString, str2);
        Log.i("test", "codes  HMAC=" + HmacSHA1Encrypt);
        String encode = OkHttpUtils.encode(HmacSHA1Encrypt);
        Log.i("test", "codes  token=" + encode);
        return encode;
    }

    public static List<NameValuePair> addPublicParams(List<NameValuePair> list) {
        list.add(new BasicNameValuePair("Format", "JSON"));
        list.add(new BasicNameValuePair("Pattern", "implicit"));
        list.add(new BasicNameValuePair("AccessKey", UrlUtil.Trend_Key));
        list.add(new BasicNameValuePair("Random", getRandomString()));
        list.add(new BasicNameValuePair("Timestamp", local2UTC()));
        return list;
    }

    public static String replcText(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("<br />", "\n").replaceAll("</?[^>]+>", "");
    }
}
