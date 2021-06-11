package com.fxeye.foreignexchangeeye.controller.fristpage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.util_tool.BasicUtils;
import com.fxeye.foreignexchangeeye.util_tool.DUtils;
import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttp;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpUtils;
import com.libs.view.optional.anaother.ConstDefine;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class FristPageController {
    public static void GetJianjieFirst_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/api/minimalism/list");
    }

    public static void GetLatestSurveys(int i, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetLatestSurveys));
        arrayList.add(new BasicNameValuePair("count", String.valueOf(i)));
        arrayList.add(new BasicNameValuePair("lan", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        new OkHttp().OkHttpGetMethod(addNewsPublicParams(arrayList), handler, i2, UrlUtil.TEST_URL);
    }

    public static void GetHomeRecommendNewsList(String str, String str2, int i, String str3, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetPersonalizedList));
        if (str != null) {
            arrayList.add(new BasicNameValuePair("userid", str));
        }
        if (str2 != null) {
            arrayList.add(new BasicNameValuePair("imei", str2));
        }
        arrayList.add(new BasicNameValuePair("cnt", String.valueOf(i)));
        if (str3 != null) {
            arrayList.add(new BasicNameValuePair("cateid", str3));
        }
        List<NameValuePair> addNewsPublicParams = addNewsPublicParams(arrayList);
        Log.i("test", "result=..huoqunewslist");
        new OkHttp().OkHttpGetMethod(addNewsPublicParams, handler, i2, "https://ttapi.fx960.com/");
    }

    public static void GetRegulatorList(Context context, Handler handler, int i) {
        String GetCountryString = BasicUtils.GetCountryString(context);
        String GetLanguageString = BasicUtils.GetLanguageString(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetRegulators));
        arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        List<NameValuePair> addNewsPublicParams = addNewsPublicParams(arrayList);
        Log.i("test", "result=..huoqunewslist");
        new OkHttp().OkHttpGetMethod(addNewsPublicParams, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetEntries(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetEntries));
        List<NameValuePair> addNewsPublicParams = addNewsPublicParams(arrayList);
        Log.i("test", "result=..huoqunewslist");
        new OkHttp().OkHttpGetMethod(addNewsPublicParams, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetSpecifiedRegulator(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetSpecifiedRegulator));
        arrayList.add(new BasicNameValuePair("code", str));
        List<NameValuePair> addNewsPublicParams = addNewsPublicParams(arrayList);
        Log.i("test", "result=..huoqunewslist");
        new OkHttp().OkHttpGetMethod(addNewsPublicParams, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderItemsList(Context context, String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        String GetCountryString = BasicUtils.GetCountryString(context);
        String GetLanguageString = BasicUtils.GetLanguageString(context);
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderItems));
        arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        arrayList.add(new BasicNameValuePair("regulatorCode", str));
        if (DUtils.isObjEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("pageIndex", str2));
        } else {
            arrayList.add(new BasicNameValuePair("pageIndex", "1"));
        }
        if (DUtils.isObjEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("pageSize", str3));
        } else {
            arrayList.add(new BasicNameValuePair("pageSize", "30"));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("fuzzySearch", str4));
        }
        List<NameValuePair> addNewsPublicParams = addNewsPublicParams(arrayList, true);
        Log.i("test", "result=..huoqunewslist");
        new OkHttp().OkHttpGetMethod(addNewsPublicParams, handler, i, UrlUtil.TEST_URL);
    }

    public static String encodeURI(String str) {
        if (str.contains("\\")) {
            str = str.replace("\\", "%5C");
        }
        return str.contains(ConstDefine.SIGN_BAIFENHAO) ? str.replace(ConstDefine.SIGN_BAIFENHAO, "%25") : str;
    }

    public static List<NameValuePair> addNewsPublicParams(List<NameValuePair> list) {
        return addNewsPublicParams(list, false);
    }

    public static List<NameValuePair> addNewsPublicParams(List<NameValuePair> list, boolean z) {
        list.add(new BasicNameValuePair("Format", "JSON"));
        list.add(new BasicNameValuePair("Pattern", "encryption"));
        list.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        list.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        list.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        list.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(list, "GET", UrlUtil.TEST_Secret, z)));
        return list;
    }
}
