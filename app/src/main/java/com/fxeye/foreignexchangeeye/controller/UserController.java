package com.fxeye.foreignexchangeeye.controller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.appsflyer.share.Constants;
import com.facebook.internal.ServerProtocol;
import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.entity.international_my.NewUser;
import com.fxeye.foreignexchangeeye.util_tool.BasicUtils;
import com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils;
import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttp;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpUtils;
import com.google.gson.Gson;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
//import cn.jpush.android.api.JThirdPlatFormInterface;

public class UserController {

    class TtmlNode {
        public static final String TAG_TT = "tt";
        public static final String TAG_HEAD = "head";
        public static final String TAG_BODY = "body";
        public static final String TAG_DIV = "div";
        public static final String TAG_P = "p";
        public static final String TAG_SPAN = "span";
        public static final String TAG_BR = "br";
        public static final String TAG_STYLE = "style";
        public static final String TAG_STYLING = "styling";
        public static final String TAG_LAYOUT = "layout";
        public static final String TAG_REGION = "region";
        public static final String TAG_METADATA = "metadata";
        public static final String TAG_IMAGE = "image";
        public static final String TAG_DATA = "data";
        public static final String TAG_INFORMATION = "information";

        public static final String ANONYMOUS_REGION_ID = "";
        public static final String ATTR_ID = "id";
        public static final String ATTR_TTS_ORIGIN = "origin";
        public static final String ATTR_TTS_EXTENT = "extent";
        public static final String ATTR_TTS_DISPLAY_ALIGN = "displayAlign";
        public static final String ATTR_TTS_BACKGROUND_COLOR = "backgroundColor";
        public static final String ATTR_TTS_FONT_STYLE = "fontStyle";
        public static final String ATTR_TTS_FONT_SIZE = "fontSize";
        public static final String ATTR_TTS_FONT_FAMILY = "fontFamily";
        public static final String ATTR_TTS_FONT_WEIGHT = "fontWeight";
        public static final String ATTR_TTS_COLOR = "color";
        public static final String ATTR_TTS_RUBY = "ruby";
        public static final String ATTR_TTS_RUBY_POSITION = "rubyPosition";
        public static final String ATTR_TTS_TEXT_DECORATION = "textDecoration";
        public static final String ATTR_TTS_TEXT_ALIGN = "textAlign";
        public static final String ATTR_TTS_TEXT_COMBINE = "textCombine";
        public static final String ATTR_TTS_TEXT_EMPHASIS = "textEmphasis";
        public static final String ATTR_TTS_WRITING_MODE = "writingMode";
        public static final String ATTR_TTS_SHEAR = "shear";
        public static final String ATTR_EBUTTS_MULTI_ROW_ALIGN = "multiRowAlign";

        // Values for ruby
        public static final String RUBY_CONTAINER = "container";
        public static final String RUBY_BASE = "base";
        public static final String RUBY_BASE_CONTAINER = "baseContainer";
        public static final String RUBY_TEXT = "text";
        public static final String RUBY_TEXT_CONTAINER = "textContainer";
        public static final String RUBY_DELIMITER = "delimiter";

        // Values for text annotation (i.e. ruby, text emphasis) position
        public static final String ANNOTATION_POSITION_BEFORE = "before";
        public static final String ANNOTATION_POSITION_AFTER = "after";
        public static final String ANNOTATION_POSITION_OUTSIDE = "outside";

        // Values for textDecoration
        public static final String LINETHROUGH = "linethrough";
        public static final String NO_LINETHROUGH = "nolinethrough";
        public static final String UNDERLINE = "underline";
        public static final String NO_UNDERLINE = "nounderline";
        public static final String ITALIC = "italic";
        public static final String BOLD = "bold";

        // Values for textAlign
        public static final String LEFT = "left";
        public static final String CENTER = "center";
        public static final String RIGHT = "right";
        public static final String START = "start";
        public static final String END = "end";

        // Values for textCombine
        public static final String COMBINE_NONE = "none";
        public static final String COMBINE_ALL = "all";

        // Values for writingMode
        public static final String VERTICAL = "tb";
        public static final String VERTICAL_LR = "tblr";
        public static final String VERTICAL_RL = "tbrl";

        // Values for textEmphasis
        public static final String TEXT_EMPHASIS_NONE = "none";
        public static final String TEXT_EMPHASIS_AUTO = "auto";
        public static final String TEXT_EMPHASIS_MARK_DOT = "dot";
        public static final String TEXT_EMPHASIS_MARK_SESAME = "sesame";
        public static final String TEXT_EMPHASIS_MARK_CIRCLE = "circle";
        public static final String TEXT_EMPHASIS_MARK_FILLED = "filled";
        public static final String TEXT_EMPHASIS_MARK_OPEN = "open";
    }

    public static void Is_Chiese_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHIESE_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    interface PlaceFields {
        String PHONE = "phone";
    }

    public static void GetZhifu_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.PAYTYPE_DATA));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetMorenAddress_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.MOREN_DATA));
        arrayList.add(new BasicNameValuePair("userId", getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Up_Address_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, Handler handler, int i) {
        String str17 = str4;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UP_ADDRESS_DATA));
        String str18 = str;
        arrayList.add(new BasicNameValuePair("areaFlag", str));
        String str19 = str2;
        arrayList.add(new BasicNameValuePair("adId", str2));
        arrayList.add(new BasicNameValuePair("userId", getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("nationalCode", str4));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        StringBuilder sb = new StringBuilder();
        String str20 = str3;
        sb.append(str3);
        sb.append("");
        arrayList.add(new BasicNameValuePair("isDefault", sb.toString()));
        String str21 = str5;
        arrayList.add(new BasicNameValuePair("areaCode", str5));
        String str22 = str6;
        arrayList.add(new BasicNameValuePair("phonenumber", str6));
        String str23 = str7;
        arrayList.add(new BasicNameValuePair("contact", str7));
        String str24 = str8;
        arrayList.add(new BasicNameValuePair("address", str8));
        if (!TextUtils.isEmpty(str9)) {
            String str25 = str9;
            arrayList.add(new BasicNameValuePair("province", str9));
        }
        if (!TextUtils.isEmpty(str10)) {
            String str26 = str10;
            arrayList.add(new BasicNameValuePair("provinceCode", str10));
        }
        if (!TextUtils.isEmpty(str11)) {
            arrayList.add(new BasicNameValuePair("city", str11));
        }
        if (!TextUtils.isEmpty(str12)) {
            arrayList.add(new BasicNameValuePair("cityCode", str12));
        }
        if (!TextUtils.isEmpty(str13)) {
            arrayList.add(new BasicNameValuePair("county", str13));
        }
        if (!TextUtils.isEmpty(str14)) {
            arrayList.add(new BasicNameValuePair("countyCode", str14));
        }
        if (!TextUtils.isEmpty(str15) && str4.equals("158")) {
            arrayList.add(new BasicNameValuePair("taiwancode", str15));
        }
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("lastName", str16));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Add_Address_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, Handler handler, int i) {
        String str16 = str3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.ADD_ADDRESS_DATA));
        arrayList.add(new BasicNameValuePair("userId", getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("nationalCode", str3));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        StringBuilder sb = new StringBuilder();
        String str17 = str2;
        sb.append(str2);
        sb.append("");
        arrayList.add(new BasicNameValuePair("isDefault", sb.toString()));
        StringBuilder sb2 = new StringBuilder();
        String str18 = str;
        sb2.append(str);
        sb2.append("");
        arrayList.add(new BasicNameValuePair("areaFlag", sb2.toString()));
        String str19 = str4;
        arrayList.add(new BasicNameValuePair("areaCode", str4));
        String str20 = str5;
        arrayList.add(new BasicNameValuePair("phonenumber", str5));
        String str21 = str6;
        arrayList.add(new BasicNameValuePair("contact", str6));
        String str22 = str7;
        arrayList.add(new BasicNameValuePair("address", str7));
        if (!TextUtils.isEmpty(str8)) {
            String str23 = str8;
            arrayList.add(new BasicNameValuePair("province", str8));
        }
        if (!TextUtils.isEmpty(str9)) {
            String str24 = str9;
            arrayList.add(new BasicNameValuePair("provinceCode", str9));
        }
        if (!TextUtils.isEmpty(str10)) {
            String str25 = str10;
            arrayList.add(new BasicNameValuePair("city", str10));
        }
        if (!TextUtils.isEmpty(str11)) {
            arrayList.add(new BasicNameValuePair("cityCode", str11));
        }
        if (!TextUtils.isEmpty(str12)) {
            arrayList.add(new BasicNameValuePair("county", str12));
        }
        if (!TextUtils.isEmpty(str13)) {
            arrayList.add(new BasicNameValuePair("countyCode", str13));
        }
        if (!TextUtils.isEmpty(str14) && str3.equals("158")) {
            arrayList.add(new BasicNameValuePair("taiwancode", str14));
        }
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("lastName", str15));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER, true)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Delete_Address_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DELETE_ADDRESS_DATA));
        arrayList.add(new BasicNameValuePair("adId", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void NewPay(String str, String str2, int i, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "UserCenterPay"));
        arrayList.add(new BasicNameValuePair("userId", getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
//        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "0"));
        arrayList.add(new BasicNameValuePair("payment", i + ""));
        arrayList.add(new BasicNameValuePair("productCode", str));
        arrayList.add(new BasicNameValuePair("productType", str2));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i2, UrlUtil.TEST_URL_USER);
    }

    public static void Get_Shouhuo_CountryData(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SHOUHUO_DATA));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.GetIp(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetCNYExchangeList(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETCNYEXCHANGE));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetExchangeList(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETMONEYLIST));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetPaiJiaList(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETPAIJIALIST));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetExchangeRate(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETRATE));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("original", str));
        arrayList.add(new BasicNameValuePair("dest", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUserBaoDan(String str, int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.USER_BAODAN));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("userid", str));
        arrayList.add(new BasicNameValuePair("index", i + ""));
        arrayList.add(new BasicNameValuePair("size", i2 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetHistory_data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.HISTORY_DATA));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("traderCode", str + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static String getAppFilePathNew() {
        return SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "app_path_new", "app_path_new", "1");
    }

    public static void setAppFilePathNew(String str) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "app_path_new", "app_path_new", str);
    }

    public static String getAppFileNameNew() {
        return SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "app_name_new", "app_name_new", "1");
    }

    public static void setAppFileNameNew(String str) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "app_name_new", "app_name_new", str);
    }

    public static void ChangePhone(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHANGEPHONE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str2));
        arrayList.add(new BasicNameValuePair("code", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void CheckUser_Renzheng(String str, String str2, String str3, String str4, String str5, String str6, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.RENZHENG_SHIMING));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("number", str2));
        arrayList.add(new BasicNameValuePair("name", str3));
        arrayList.add(new BasicNameValuePair("last", str4));
        arrayList.add(new BasicNameValuePair("first", str5));
        arrayList.add(new BasicNameValuePair("countryCode", str6));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void CheckUserMsg(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHECKUSERMSG));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void CheckPhoneMsg(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHECKPHONEMSG));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UpdatePwd(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UPDATEPWD));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("opwd", str3));
        arrayList.add(new BasicNameValuePair("npwd", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUserComOrder(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETUSERORDER));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("pageIndex", str2));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("pageSize", str3));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void CheckGiftCode(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GIFTCODE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void GetVipProduct(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETVIP));
        arrayList.add(new BasicNameValuePair("type", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
//        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "0"));
        arrayList.add(new BasicNameValuePair("isReview", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetUserMessage(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETUSERMSG));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUserPrivilege(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETUSERPRIVILEGE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetPay_Way(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.PAY_WAY));
//        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "0"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Pay(String str, String str2, int i, String str3, String str4, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "UserCenterPay"));
        arrayList.add(new BasicNameValuePair("userId", getNewBDUserInfo(MyApplication.getContext()).getUserId()));
//        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "0"));
        arrayList.add(new BasicNameValuePair("payment", i + ""));
        arrayList.add(new BasicNameValuePair("productCode", str));
        arrayList.add(new BasicNameValuePair("productType", str2));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("orderOrginType", "22"));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("productPrice", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("productPriceCode", str4));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i2, UrlUtil.TEST_URL_USER);
    }

    public static void Create_Dingdan(String str, int i, String str2, String str3, String str4, String str5, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "PayCallback"));
        arrayList.add(new BasicNameValuePair("uid", getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("pyt", i + ""));
        arrayList.add(new BasicNameValuePair("type", str2));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "22"));
        arrayList.add(new BasicNameValuePair("trade_no", str3));
        arrayList.add(new BasicNameValuePair("token", str4 + ""));
        arrayList.add(new BasicNameValuePair(Constants.URL_MEDIA_SOURCE, str5 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i2, UrlUtil.TEST_URL_GUONEI);
    }

    public static void Create_Dingdan(String str, int i, String str2, String str3, String str4, String str5, String str6, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "PayCallback"));
        arrayList.add(new BasicNameValuePair("uid", getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("pyt", i + ""));
        arrayList.add(new BasicNameValuePair("type", str2));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "22"));
        arrayList.add(new BasicNameValuePair("trade_no", str3));
        arrayList.add(new BasicNameValuePair("token", str4 + ""));
        arrayList.add(new BasicNameValuePair(Constants.URL_MEDIA_SOURCE, str5 + ""));
        arrayList.add(new BasicNameValuePair("is_express", str6 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i2, UrlUtil.TEST_URL_GUONEI);
    }

    public static void GetAuthRoles(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETAUTHROLE));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
//        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "0"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetYan_EmailRoles(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("email", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("userId", str3));
        }
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.YANZHENG_EMAIL));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetYan_EmailGo(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("email", str));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("userId", str2));
        }
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GO_EMAIL));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void UserLogin(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.LOGIN));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("password", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UserYanzheng_Email(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.EMAIL_YANZHENG));
        arrayList.add(new BasicNameValuePair("email", str));
        arrayList.add(new BasicNameValuePair("userId", str2));
        arrayList.add(new BasicNameValuePair("code", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Up_PwData(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UP_PW_DATA));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("opwd", str2));
        arrayList.add(new BasicNameValuePair("npwd", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Email_Yanzheng(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.YANZHENG_EMAIL_LIANJIE));
        arrayList.add(new BasicNameValuePair("email", str));
        arrayList.add(new BasicNameValuePair("userId", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void ThirdRegister(String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.THIRDREGISTER));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("msgCode", str2));
        arrayList.add(new BasicNameValuePair("accountType", str3));
        arrayList.add(new BasicNameValuePair("openId", str4));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, str5));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void ThirdUserLogin(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.THIRDLOGIN));
        arrayList.add(new BasicNameValuePair("id", str));
        arrayList.add(new BasicNameValuePair("accountType", str2));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("equipmentType", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void ChangeNick(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHANGENICK));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("nick", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void ChangeMail(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHANGEMAIL));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("email", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void ChangeGender(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHANGEGENDER));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("gender", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void ChangeAvatar(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CHANGEAVATAR));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("avatar", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UploadImage(String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UPLOADIMAGE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("isHead", str2));
        arrayList.add(new BasicNameValuePair("isWater", str3));
        arrayList.add(new BasicNameValuePair("ext", str4));
        arrayList.add(new BasicNameValuePair("base64", str5));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UploadNewImage(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.USER_IMAG));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("isHead", str2));
        arrayList.add(new BasicNameValuePair("ext", str3));
        arrayList.add(new BasicNameValuePair("base64", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void UserRegister(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.REGISTER));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("msgCode", str2));
        arrayList.add(new BasicNameValuePair("password", str3));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UserResetPwd(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.RESETPWD));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("password", str2));
        arrayList.add(new BasicNameValuePair("msgCode", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetMsgCode(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETMSGCODE));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        arrayList.add(new BasicNameValuePair("authType", str2));
        arrayList.add(new BasicNameValuePair("msgType", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetNew_MsgCode(Context context, String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_NEWMSGCODE));
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str2));
        arrayList.add(new BasicNameValuePair("languageCode", str3));
        arrayList.add(new BasicNameValuePair("authType", str4));
        arrayList.add(new BasicNameValuePair("msgType", "0"));
        if (!TextUtils.isEmpty(getNewBDUserId(context))) {
            arrayList.add(new BasicNameValuePair("userId", getNewBDUserId(context)));
        }
        arrayList.add(new BasicNameValuePair("smsBusinessType", str5));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Sanfang_Zhuce(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UserCenterValidateRegisterPhone));
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str2));
        arrayList.add(new BasicNameValuePair("accountType", str3));
        arrayList.add(new BasicNameValuePair("registrationPlatform", "1"));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetLoginEmail_MsgCode(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_NEWEMAIL_MSGCODE));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str));
        }
        arrayList.add(new BasicNameValuePair("email", str2));
        arrayList.add(new BasicNameValuePair("emailType", str3));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetGeren_UpPhone(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_GEREN_PHONE));
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair("phoneNumber", str2));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("userId", str3));
        }
        arrayList.add(new BasicNameValuePair("smscode", str4));
        if (!TextUtils.isEmpty(BasicUtils.GetChosseGuoqi(MyApplication.getContext()))) {
            arrayList.add(new BasicNameValuePair("areaFlag", BasicUtils.GetChosseGuoqi(MyApplication.getContext())));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static NewUser getNewBDUserInfo(Context context) {
        NewUser newUser = new NewUser();
        String stringValue = SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "User_info_new", "User_info_new", "");
        return !stringValue.equals("") ? (NewUser) JSON.parseObject(stringValue, NewUser.class) : newUser;
    }

    public static String getNewBDUserId(Context context) {
        return getNewBDUserInfo(context).getUserId();
    }

    public static void setNewUserLogin(Context context, boolean z) {
        context.getSharedPreferences("User_Login_new", 0).edit().putBoolean("ISLOGIN_new", z).commit();
        NewUser newUser = new NewUser();
        if (!z) {
            setNewBDUserInfo(context, newUser);
        }
//        EventBus.getDefault().post(new MessageHelpYou(89, ""));
    }

    public static void setNewBDUserInfo(Context context, NewUser newUser) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "User_info_new", "User_info_new", new Gson().toJson((Object) newUser));
//        EventBus.getDefault().post(new MessageHelpYou(89, ""));
    }

    public static boolean isNewUserLogin(Context context) {
        return context.getSharedPreferences("User_Login_new", 0).getBoolean("ISLOGIN_new", false);
    }

    public static String getThirdLoginUserAvatar(Context context) {
        return SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "Third_User_Avatar", "Third_User_Avatar", "");
    }

    public static void setThirdLoginUserAvatar(String str) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "Third_User_Avatar", "Third_User_Avatar", str);
    }

    public static void setLoginType(String str) {
        SharedPreferencesUtils.putStringValue(MyApplication.getInstance(), "login_type", "login_type", str);
    }

    public static String getLoginType() {
        return SharedPreferencesUtils.getStringValue(MyApplication.getInstance(), "login_type", "login_type", "111");
    }

    public static void GetUser_Data(String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("kdi", str));
        arrayList.add(new BasicNameValuePair("area", str2));
        arrayList.add(new BasicNameValuePair("remark", str3));
        arrayList.add(new BasicNameValuePair("timetick", str4));
        arrayList.add(new BasicNameValuePair("key", str5));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TONGJI);
    }

    public static void Get_Country_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("languageCode", str));
        if (!TextUtils.isEmpty(BasicUtils.GetIp(MyApplication.getContext()))) {
            arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.GetIp(MyApplication.getContext())));
        }
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETGUOJIA_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Languge_Data(Context context, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(context)));
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.LABGUGE_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Tishi_Data(Context context, String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("countryCode", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(context)));
        arrayList.add(new BasicNameValuePair("type", "0"));
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TISHI_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUser_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.USER_DATA));
        arrayList.add(new BasicNameValuePair("userId", str + ""));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void InternationalUserLogin(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.USER_LOGIN));
        arrayList.add(new BasicNameValuePair("account", str));
        arrayList.add(new BasicNameValuePair("password", str2));
        arrayList.add(new BasicNameValuePair("languageCode", str3));
        arrayList.add(new BasicNameValuePair("countryCode", str4));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.getIPAddress(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("equipmentType", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void NewChangeGender(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEWCHANGEGENDER));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("gender", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void NewChangeNick(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEWCHANGENICK));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("nick", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Verification_information(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.INFORMATION));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str));
        }
        arrayList.add(new BasicNameValuePair("email", str2));
        arrayList.add(new BasicNameValuePair("emailType", str3));
        arrayList.add(new BasicNameValuePair("languageCode", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetPhone_Code(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETPHONE_CODE));
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair("phoneNumber", str2));
        arrayList.add(new BasicNameValuePair("languageCode", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Verification_Code(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.VER_CODE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("areaCode", str2));
        arrayList.add(new BasicNameValuePair("phoneNumber", str3));
        arrayList.add(new BasicNameValuePair("code", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Kuaijie_Login(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.KUAIJIE_LOGIN));
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str2));
        arrayList.add(new BasicNameValuePair("msgCode", str3));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("equipmentType", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Get_Yanzheng_Data(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("areaCode", str2));
        arrayList.add(new BasicNameValuePair("phoneNumber", str3));
        arrayList.add(new BasicNameValuePair("smscode", str4));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str));
        }
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.YANZHENG_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Xiugai_phone(String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.XIUGAI_PHONE));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str));
        }
        arrayList.add(new BasicNameValuePair("areaCode", str2));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str3));
        arrayList.add(new BasicNameValuePair("npwd", str4));
//        arrayList.add(new BasicNameValuePair(HwPayConstant.KEY_REQUESTID, str5));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Xiugai_Email(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.XIUGAI_EMAIL));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str));
        }
        arrayList.add(new BasicNameValuePair("email", str2));
        arrayList.add(new BasicNameValuePair("npwd", str3));
//        arrayList.add(new BasicNameValuePair(HwPayConstant.KEY_REQUESTID, str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void User_Zhuce_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.USER_ZHUCE));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str));
        if (!TextUtils.isEmpty(BasicUtils.GetChosseGuoqi(MyApplication.getContext()))) {
            arrayList.add(new BasicNameValuePair("areaFlag", BasicUtils.GetChosseGuoqi(MyApplication.getContext())));
        }
        arrayList.add(new BasicNameValuePair("areaCode", str2));
        arrayList.add(new BasicNameValuePair("password", str3));
        arrayList.add(new BasicNameValuePair("email", str4));
        arrayList.add(new BasicNameValuePair("sex", str5));
        arrayList.add(new BasicNameValuePair("lastname", str6));
        arrayList.add(new BasicNameValuePair("isSkip", str7));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.GetIp(MyApplication.getContext())));
//        arrayList.add(new BasicNameValuePair(HwPayConstant.KEY_REQUESTID, str8));
        arrayList.add(new BasicNameValuePair("registrationPlatform", "1"));
        arrayList.add(new BasicNameValuePair("deviceInformation", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        arrayList.add(new BasicNameValuePair("deviceCode", BasicUtils.getIMEI(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void GetSanfang_Zhuce(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_SANFANGZHUCE));
        String str14 = str;
        arrayList.add(new BasicNameValuePair("openId", str));
        String str15 = str2;
        arrayList.add(new BasicNameValuePair("accountType", str2));
        if (!TextUtils.isEmpty(str3)) {
            String str16 = str3;
            arrayList.add(new BasicNameValuePair("accountNick", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            String str17 = str4;
            arrayList.add(new BasicNameValuePair("accountHead", str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            String str18 = str5;
            arrayList.add(new BasicNameValuePair("msgCode", str5));
        }
        if (!TextUtils.isEmpty(BasicUtils.GetChosseGuoqi(MyApplication.getContext()))) {
            arrayList.add(new BasicNameValuePair("areaFlag", BasicUtils.GetChosseGuoqi(MyApplication.getContext())));
        }
        String str19 = str6;
        arrayList.add(new BasicNameValuePair("areaCode", str6));
        String str20 = str7;
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, str7));
        if (!TextUtils.isEmpty(str10)) {
            String str21 = str10;
            arrayList.add(new BasicNameValuePair("password", str10));
        }
        if (!TextUtils.isEmpty(str11)) {
            String str22 = str11;
            arrayList.add(new BasicNameValuePair("email", str11));
        }
        if (!TextUtils.isEmpty(str12)) {
            arrayList.add(new BasicNameValuePair("sex", str12));
        }
        if (!TextUtils.isEmpty(str13)) {
            arrayList.add(new BasicNameValuePair("lastname", str13));
        }
        String str23 = str8;
        arrayList.add(new BasicNameValuePair("isSkip", str8));
        arrayList.add(new BasicNameValuePair("applicationType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersionName(MyApplication.getContext()) + ""));
        if (!TextUtils.isEmpty(BasicUtils.GetIp(MyApplication.getContext()))) {
            arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.GetIp(MyApplication.getContext())));
        }
        String str24 = str9;
//        arrayList.add(new BasicNameValuePair(HwPayConstant.KEY_REQUESTID, str9));
        arrayList.add(new BasicNameValuePair("registrationPlatform", "1"));
        arrayList.add(new BasicNameValuePair("deviceInformation", "3"));
        arrayList.add(new BasicNameValuePair("deviceCode", BasicUtils.getIMEI(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Get_Phone_Data(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("areaCode", str));
        arrayList.add(new BasicNameValuePair("phoneNumber", str2));
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.PHONE_DATA));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }
}
