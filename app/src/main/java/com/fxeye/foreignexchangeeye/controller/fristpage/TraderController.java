package com.fxeye.foreignexchangeeye.controller.fristpage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.controller.AboutController;
import com.fxeye.foreignexchangeeye.controller.BaseController;
import com.fxeye.foreignexchangeeye.controller.UserController;
import com.fxeye.foreignexchangeeye.entity.collect.Collect_list;
import com.fxeye.foreignexchangeeye.entity.trader.TraderResponse;
import com.fxeye.foreignexchangeeye.util_tool.BasicUtils;
import com.fxeye.foreignexchangeeye.util_tool.TraderConstant;
import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttp;
import com.libs.view.optional.anaother.ConstDefine;
import com.wiki.exposure.framework.utils.LanguageType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TraderController extends BaseController {
    public static void GetTraderSurveys(String code, int index, int size, String languageCode, String countryCode, Handler handler, int what96) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderSurveys));
        arrayList.add(new BasicNameValuePair("code", code));
        if (index > 0) {
            arrayList.add(new BasicNameValuePair("index", String.valueOf(index)));
        }
        if (size > 0) {
            arrayList.add(new BasicNameValuePair("size", String.valueOf(size)));
        }
        arrayList.add(new BasicNameValuePair("countryCode", countryCode));
        arrayList.add(new BasicNameValuePair("lan", languageCode));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, what96, UrlUtil.TEST_URL);
    }

    public static String getLabel(List<TraderResponse.ResultBean.LabelsBean> list) {
        List<TraderResponse.ResultBean.LabelsBean.DataBean> data;
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (TraderResponse.ResultBean.LabelsBean next : list) {
            if (!(next == null || (data = next.getData()) == null || data.size() <= 0)) {
                sb.append(data.get(0).getLabelName());
                sb.append(" | ");
            }
        }
        if (sb.length() > 1 && sb.length() > 3) {
            sb.delete(sb.length() - 3, sb.length() - 1);
        }
        return sb.toString();
    }

    public static String getCollect_Label(List<Collect_list.ContentBean.ResultBean.ItemsBean.LabelsBean> list) {
        List<Collect_list.ContentBean.ResultBean.ItemsBean.LabelsBean.DataBean> data;
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Collect_list.ContentBean.ResultBean.ItemsBean.LabelsBean next : list) {
            if (!(next == null || (data = next.getData()) == null || data.size() <= 0)) {
                sb.append(data.get(0).getLabelName());
                sb.append(" | ");
            }
        }
        if (sb.length() > 1 && sb.length() > 3) {
            sb.delete(sb.length() - 3, sb.length() - 1);
        }
        return sb.toString();
    }

    public static String getContract(List<TraderResponse.ResultBean.ContractsBean> list, int i) {
        for (TraderResponse.ResultBean.ContractsBean next : list) {
            if (next.getType() == i) {
                return next.getContract();
            }
        }
        return "";
    }

//    public static void GetSpecifiedTrader(String traderCode, Handler handler, int what1) {
//        GetSpecifiedTrader(traderCode, (String) null, (String) null, handler, what1);
//    }

    public static void GetSpecifiedTrader(String traderCode, String languageCode, String countryCode, Handler handler, int what1) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetSpecifiedTrader));
        arrayList.add(new BasicNameValuePair("traderCode", traderCode));
        String appVersionName = AboutController.getAppVersionName(MyApplication.getContext());
        if (TextUtils.isEmpty(appVersionName)) {
            appVersionName = "";
        }
        arrayList.add(new BasicNameValuePair("ver", appVersionName));
        if (languageCode != null) {
            arrayList.add(new BasicNameValuePair("languageCode", languageCode));
            arrayList.add(new BasicNameValuePair("countryCode", countryCode));
        }
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, what1, UrlUtil.TEST_URL);
    }

    public static void GetTraderNewsList(String tradercode, String categorycode, int index, int size, Handler handler, int what43) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("tradercode", tradercode));
        arrayList.add(new BasicNameValuePair("categorycode", categorycode));
        arrayList.add(new BasicNameValuePair("pageIndex", String.valueOf(index)));
        arrayList.add(new BasicNameValuePair("pageSize", String.valueOf(size)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, what43, UrlUtil.TRADER_NEWS_LIST);
    }

    public static void getNotice(String tradercode, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("tradercode", tradercode));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TRADER_FLAGSHIP_NOTICE);
    }

    public static void GetTraderTransfer(String code, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderTransfer));
        arrayList.add(new BasicNameValuePair("code", code));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderEvidence(String code, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderEvidence));
        arrayList.add(new BasicNameValuePair("code", code));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetMT4Items(String code, String languageCode, String countryCode, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetMT4Items));
        arrayList.add(new BasicNameValuePair("code", code));
        arrayList.add(new BasicNameValuePair("languageCode", languageCode));
        arrayList.add(new BasicNameValuePair("countryCode", countryCode));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderAccount(String code, String languageCode, String countryCode, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderAccount));
        arrayList.add(new BasicNameValuePair("code", code));
        arrayList.add(new BasicNameValuePair("languageCode", languageCode));
        arrayList.add(new BasicNameValuePair("countryCode", countryCode));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderComplaint(String code, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderComplaint));
        arrayList.add(new BasicNameValuePair("code", code));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderDomain(String code, String languageCode, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderDomain));
        arrayList.add(new BasicNameValuePair("code", code));
        arrayList.add(new BasicNameValuePair("languageCode", languageCode));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderInterests(String code, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderInterests));
        arrayList.add(new BasicNameValuePair("code", code));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderRegulation(String code, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderRegulation));
        arrayList.add(new BasicNameValuePair("code", code));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetFakeTraders(Context context, String str, Handler handler, int i) {
        String GetCountryString = BasicUtils.GetCountryString(context);
        String GetLanguageString = BasicUtils.GetLanguageString(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetFakeTraders));
        arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        arrayList.add(new BasicNameValuePair("code", str));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetSkyRisk(String code, String type, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetSkyRisk));
        arrayList.add(new BasicNameValuePair("code", code));
        arrayList.add(new BasicNameValuePair("type", type));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void SubmitUpdating(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SubmitUpdating));
        arrayList.add(new BasicNameValuePair("userId", str2));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("type", str3));
        new OkHttp().OkHttpPostMethod(addEncryptionPOSTPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetAgents(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetAgents));
        arrayList.add(new BasicNameValuePair("code", str));
        if (str2 != null) {
            arrayList.add(new BasicNameValuePair("pageIndex", str2));
        }
        if (str3 != null) {
            arrayList.add(new BasicNameValuePair("pageSize", str3));
        }
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetLightMarkets(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetLightMarkets));
        arrayList.add(new BasicNameValuePair("code", str2));
        if (str != null) {
            arrayList.add(new BasicNameValuePair("uid", str));
        } else {
            arrayList.add(new BasicNameValuePair("uid", "0"));
        }
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetEpcProduct(String str, String str2, Handler handler, int i) {
        if (!AboutController.getAppThreeDayLock()) {
            Context applicationContext = MyApplication.getInstance().getApplicationContext();
            String GetCountryString = BasicUtils.GetCountryString(applicationContext);
            String GetLanguageString = BasicUtils.GetLanguageString(applicationContext);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetEpcProduct));
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
            if (str != null) {
                arrayList.add(new BasicNameValuePair("userid", str));
            } else {
                arrayList.add(new BasicNameValuePair("userid", "0"));
            }
            arrayList.add(new BasicNameValuePair("traderCode", str2));
            new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
        }
    }

    public static void UploadBehavior(String str, String str2, String str3, String str4, Handler handler, int i) {
        if (str3 != null && !str3.contains("B")) {
            str3 = "B" + str3;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.UploadBehavior));
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("cmd", "add");
            jSONObject2.put("timestamp", System.currentTimeMillis() / 1000);
            jSONObject2.put(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, ViewHierarchyConstants.VIEW_KEY);
            jSONObject2.put("action_num", "1");
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("userid", str);
            }
            jSONObject2.put("imei", str2);
            jSONObject2.put("itemid", str3);
            if (!TextUtils.isEmpty(str4)) {
                jSONObject2.put(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, str4);
            }
            jSONObject.put(GraphRequest.FIELDS_PARAM, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jSONArray.put(jSONObject);
        arrayList.add(new BasicNameValuePair("behavior", jSONArray.toString()));
        new OkHttp().OkHttpPostMethod(addEncryptionPOSTPublicParams(arrayList), handler, i, "https://ttapi.fx960.com/");
    }

    public static void GetEpcPermissionStatus(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetEpcPermissionStatus));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("trader", str2));
        arrayList.add(new BasicNameValuePair("product", str3));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUpdatingDate(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetUpdatingDate));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("type", str2));
        new OkHttp().OkHttpGetMethod(addImplicitPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetUltimateAds(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetUltimateAds));
        arrayList.add(new BasicNameValuePair("countryCode", str2));
        arrayList.add(new BasicNameValuePair("languageCode", str3));
        arrayList.add(new BasicNameValuePair("traderCode", str));
        arrayList.add(new BasicNameValuePair("ver", str4));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetScreenshotAd(Context context, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        String GetCountryString = BasicUtils.GetCountryString(context);
        String GetLanguageString = BasicUtils.GetLanguageString(context);
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetScreenshotAd));
        arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GJGetNewsAd(Context context, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        String GetCountryString = BasicUtils.GetCountryString(context);
        String GetLanguageString = BasicUtils.GetLanguageString(context);
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GJGetNewsAd));
        arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        arrayList.add(new BasicNameValuePair("isNew", "1"));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTraderFamilyTree(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderFamilyTree));
        arrayList.add(new BasicNameValuePair("code", str));
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static String GetTraderFamilyTree(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderFamilyTree));
        arrayList.add(new BasicNameValuePair("code", str));
        List<NameValuePair> addEncryptionGETPublicParams = addEncryptionGETPublicParams(arrayList);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < addEncryptionGETPublicParams.size(); i++) {
            stringBuffer.append(addEncryptionGETPublicParams.get(i).getName() + ConstDefine.DIVIDER_SIGN_DENGGHAO + addEncryptionGETPublicParams.get(i).getValue());
            if (i < addEncryptionGETPublicParams.size() - 1) {
                stringBuffer.append("&");
            }
        }
        return stringBuffer.toString();
    }

    public static void setStatus(String str, String str2, TextView textView) {
        textView.setText(str);
        if (!TextUtils.isEmpty(str2)) {
            textView.setBackgroundColor(Color.parseColor(str2));
        }
    }

    public static String setName(String str, String str2, TextView textView, TextView textView2) {
        String str3 = "";
        if (!TextUtils.isEmpty(str)) {
            str3 = str3 + str;
        }
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + str2;
        }
        if (str3.length() > 25) {
            textView.setText(str3.substring(0, 26) + "...");
        } else {
            textView.setText(str3);
        }
        if (textView2 != null) {
            textView2.setText(str3);
        }
        return str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002a A[SYNTHETIC, Splitter:B:19:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0030 A[SYNTHETIC, Splitter:B:23:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getImageFromAssetsFile(android.content.Context r1, java.lang.String r2) {
        /*
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.AssetManager r1 = r1.getAssets()
            r0 = 0
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ IOException -> 0x001e }
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ IOException -> 0x0017 }
            goto L_0x002d
        L_0x0017:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x002d
        L_0x001c:
            r2 = move-exception
            goto L_0x002e
        L_0x001e:
            r2 = move-exception
            goto L_0x0025
        L_0x0020:
            r2 = move-exception
            r1 = r0
            goto L_0x002e
        L_0x0023:
            r2 = move-exception
            r1 = r0
        L_0x0025:
            r2.printStackTrace()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ IOException -> 0x0017 }
        L_0x002d:
            return r0
        L_0x002e:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0038:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.controller.fristpage.TraderController.getImageFromAssetsFile(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static Drawable getImageDarwableFromAssetsFile(Context context, String str) {
        InputStream inputStream = null;
        Drawable drawable = null;
        try {
            inputStream = context.getResources().getAssets().open(str);
            drawable = Drawable.createFromStream(inputStream, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return drawable;
    }

    public static String topName(String str) {
        if (str.length() <= 15) {
            return str;
        }
        return str.substring(0, 15) + "...";
    }

    public static String createName(TraderResponse.ResultBean resultBean) {
        String shortName = resultBean.getShortName();
        if (!TextUtils.isEmpty(resultBean.getLocalShortName()) && !resultBean.getLocalShortName().equals(shortName)) {
            shortName = shortName + " " + resultBean.getLocalShortName();
        }
        return TextUtils.isEmpty(shortName) ? "--" : shortName;
    }

    public static String createDetailName(TraderResponse.ResultBean resultBean) {
        String shortName = resultBean.getShortName();
        if (!TextUtils.isEmpty(resultBean.getLocalShortName())) {
            shortName = resultBean.getLocalShortName();
        }
        return TextUtils.isEmpty(shortName) ? "--" : shortName;
    }

    public static boolean isVip(Context context) {
        String identity = UserController.getNewBDUserInfo(context).getIdentity();
        return !"0".equals(identity) && !"100".equals(identity) && !"300".equals(identity) && !"900".equals(identity) && !"600".equals(identity);
    }

    public static int getRequestNumFormRole(Context context) {
        if (!UserController.isNewUserLogin(context)) {
            return 8;
        }
        String identity = UserController.getNewBDUserInfo(context).getIdentity();
        return ("0".equals(identity) || "100".equals(identity) || "300".equals(identity) || "900".equals(identity) || "600".equals(identity)) ? 15 : 20;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x017d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String MainFun(android.graphics.Bitmap r17, android.content.Context r18, java.lang.String r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = 0
            java.lang.String r4 = "create_bitmap"
            java.lang.String r5 = "Null"
            java.lang.String r6 = "ScreenFileUrlIsNull"
            if (r0 != 0) goto L_0x0013
            com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.putStringValue(r1, r6, r4, r5)
            return r3
        L_0x0013:
            java.lang.String r7 = com.fxeye.foreignexchangeeye.util_tool.BasicUtils.GetLanguageString(r18)
            java.lang.String r8 = "notNull"
            com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.putStringValue(r1, r6, r4, r8)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r9 = "topUrl"
            r4.append(r9)
            r4.append(r2)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            java.lang.String r9 = ""
            java.lang.String r10 = "ScreenshotAdPath"
            java.lang.String r4 = com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.getStringValue(r1, r10, r4, r9)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "bottonUrl"
            r11.append(r12)
            r11.append(r2)
            r11.append(r7)
            java.lang.String r2 = r11.toString()
            java.lang.String r2 = com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.getStringValue(r1, r10, r2, r9)
            android.content.res.Resources r9 = r18.getResources()
            java.lang.String r10 = "zh-CN"
            boolean r10 = r10.equals(r7)
            if (r10 == 0) goto L_0x006d
            r7 = 2131558467(0x7f0d0043, float:1.874225E38)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r9, r7)
            r10 = 2131558466(0x7f0d0042, float:1.8742249E38)
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeResource(r9, r10)
            goto L_0x00b4
        L_0x006d:
            java.lang.String r10 = "zh-HK"
            boolean r10 = r10.equals(r7)
            if (r10 != 0) goto L_0x00a6
            java.lang.String r10 = "zh-TW"
            boolean r10 = r10.equals(r7)
            if (r10 == 0) goto L_0x0080
            goto L_0x00a6
        L_0x0080:
            java.lang.String r10 = "ko"
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L_0x0097
            r7 = 2131558750(0x7f0d015e, float:1.8742825E38)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r9, r7)
            r10 = 2131558749(0x7f0d015d, float:1.8742823E38)
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeResource(r9, r10)
            goto L_0x00b4
        L_0x0097:
            r7 = 2131558572(0x7f0d00ac, float:1.8742464E38)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r9, r7)
            r10 = 2131558571(0x7f0d00ab, float:1.8742462E38)
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeResource(r9, r10)
            goto L_0x00b4
        L_0x00a6:
            r7 = 2131558583(0x7f0d00b7, float:1.8742486E38)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r9, r7)
            r10 = 2131558582(0x7f0d00b6, float:1.8742484E38)
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeResource(r9, r10)
        L_0x00b4:
            r10 = 2
            boolean[] r11 = new boolean[r10]
            r11 = {0, 0} // fill-array
            boolean r12 = android.text.TextUtils.isEmpty(r4)
            r13 = 0
            r14 = 1
            if (r12 == 0) goto L_0x00c5
            r11[r13] = r14
            goto L_0x00cd
        L_0x00c5:
            android.graphics.Bitmap r12 = com.fxeye.foreignexchangeeye.util_tool.DUtils.getDiskBitmap(r4)
            if (r12 != 0) goto L_0x00ce
            r11[r13] = r14
        L_0x00cd:
            r12 = r7
        L_0x00ce:
            boolean r15 = android.text.TextUtils.isEmpty(r2)
            if (r15 == 0) goto L_0x00d7
            r11[r14] = r14
            goto L_0x00df
        L_0x00d7:
            android.graphics.Bitmap r15 = com.fxeye.foreignexchangeeye.util_tool.DUtils.getDiskBitmap(r2)
            if (r15 != 0) goto L_0x00e0
            r11[r14] = r14
        L_0x00df:
            r15 = r9
        L_0x00e0:
            int r13 = r11.length
            r16 = r13
            r13 = 0
        L_0x00e4:
            int r3 = r11.length
            if (r13 >= r3) goto L_0x00fc
            boolean r3 = r11[r13]
            if (r3 != 0) goto L_0x00ed
            int r16 = r16 + -1
        L_0x00ed:
            if (r16 != 0) goto L_0x00f9
            if (r7 == 0) goto L_0x00f4
            r7.recycle()
        L_0x00f4:
            if (r9 == 0) goto L_0x00f9
            r9.recycle()
        L_0x00f9:
            int r13 = r13 + 1
            goto L_0x00e4
        L_0x00fc:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = "Path=="
            r3.append(r7)
            r3.append(r4)
            java.lang.String r9 = " = "
            r3.append(r9)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            com.fxeye.foreignexchangeeye.util_tool.DUtils.logI(r3)
            android.graphics.Bitmap r3 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.mergeBitmap_TB(r12, r0, r14, r10)
            if (r3 != 0) goto L_0x0120
            r10 = r5
            goto L_0x0121
        L_0x0120:
            r10 = r8
        L_0x0121:
            java.lang.String r11 = "merge_Pic"
            com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.putStringValue(r1, r6, r11, r10)
            android.graphics.Bitmap r10 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.mergeBitmap_TB(r3, r15, r14, r14)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r7)
            r11.append(r4)
            r11.append(r9)
            r11.append(r2)
            java.lang.String r2 = r11.toString()
            com.fxeye.foreignexchangeeye.util_tool.DUtils.logI(r2)
            recycleBitmap(r17)
            recycleBitmap(r3)
            recycleBitmap(r12)
            recycleBitmap(r15)
            if (r10 != 0) goto L_0x0152
            r0 = r5
            goto L_0x0153
        L_0x0152:
            r0 = r8
        L_0x0153:
            java.lang.String r2 = "compress_Pic"
            com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.putStringValue(r1, r6, r2, r0)
            android.graphics.Bitmap r0 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.compressBitmap(r10)
            com.fxeye.foreignexchangeeye.MyApplication r2 = com.fxeye.foreignexchangeeye.MyApplication.getInstance()
            java.io.File r2 = r2.getCacheDir()
            java.lang.String r2 = r2.getAbsolutePath()
            if (r0 != 0) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r5 = r8
        L_0x016c:
            java.lang.String r3 = "save_Pic"
            com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.putStringValue(r1, r6, r3, r5)
            java.lang.String r0 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.savePic(r0, r14, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x017d
            r1 = 0
            return r1
        L_0x017d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.controller.fristpage.TraderController.MainFun(android.graphics.Bitmap, android.content.Context, java.lang.String):java.lang.String");
    }

    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static String getSplitString(String str) {
        String[] split = str.split(",");
        if (split.length <= 1) {
            return str;
        }
        String str2 = "";
        for (int i = 0; i < split.length - 1; i++) {
            str2 = str2 + split[i].trim() + "\n";
        }
        return str2 + split[split.length - 1].trim();
    }

    public static String getStatusType(Context context) {
        if (!UserController.isNewUserLogin(context)) {
            return TraderConstant.VIEW_STATUS_TYPE_COMMON;
        }
        String identity = UserController.getNewBDUserInfo(context).getIdentity();
        if ("0".equals(identity) || "900".equals(identity)) {
            return TraderConstant.VIEW_STATUS_TYPE_COMMON;
        }
        return "901".equals(identity) ? TraderConstant.VIEW_STATUS_TYPE_VIP : TraderConstant.VIEW_STATUS_TYPE_PROFESSION;
    }

    static /* synthetic */ void lambda$takePhoneDialog$0(Context context, String str, Dialog dialog, boolean z) {
        if (z) {
            if (context != null) {
                callUp(str, context);
            }
            dialog.dismiss();
        }
    }

    public static void callUp(String str, Context context) {
        str.replace("-", "");
        context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static void deletePhoto(String str) {
        if (str != null) {
            new Thread(new Runnable() {
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = str;
                }

                public final void run() {
                    TraderController.lambda$deletePhoto$1(this.f$0);
                }
            }).start();
        }
    }

    static /* synthetic */ void lambda$deletePhoto$1(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getTraderNewsLabelBgColor(String str) {
        if ("曝光".equals(str)) {
            return "#4169e1";
        }
        if ("资讯".equals(str)) {
            return "#f9811d";
        }
        return "交易商".equals(str) ? "#6ac8dc" : "#eb7027";
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap topAndBottomFun(android.graphics.Bitmap r10, android.content.Context r11, java.lang.String r12) {
        /*
            java.lang.String r0 = com.fxeye.foreignexchangeeye.util_tool.BasicUtils.GetLanguageString(r11)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "topUrl"
            r1.append(r2)
            r1.append(r12)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = ""
            java.lang.String r3 = "ScreenshotAdPath"
            java.lang.String r1 = com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.getStringValue(r11, r3, r1, r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "bottonUrl"
            r4.append(r5)
            r4.append(r12)
            r4.append(r0)
            java.lang.String r12 = r4.toString()
            java.lang.String r12 = com.fxeye.foreignexchangeeye.util_tool.SharedPreferencesUtils.getStringValue(r11, r3, r12, r2)
            android.content.res.Resources r11 = r11.getResources()
            r0 = 2131558918(0x7f0d0206, float:1.8743165E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r11, r0)
            r2 = 2131558917(0x7f0d0205, float:1.8743163E38)
            android.graphics.Bitmap r11 = android.graphics.BitmapFactory.decodeResource(r11, r2)
            r2 = 2
            boolean[] r3 = new boolean[r2]
            r3 = {0, 0} // fill-array
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x005c
            r3[r5] = r6
            goto L_0x0064
        L_0x005c:
            android.graphics.Bitmap r4 = com.fxeye.foreignexchangeeye.util_tool.DUtils.getDiskBitmap(r1)
            if (r4 != 0) goto L_0x0065
            r3[r5] = r6
        L_0x0064:
            r4 = r0
        L_0x0065:
            boolean r7 = android.text.TextUtils.isEmpty(r12)
            if (r7 == 0) goto L_0x006e
            r3[r6] = r6
            goto L_0x0076
        L_0x006e:
            android.graphics.Bitmap r7 = com.fxeye.foreignexchangeeye.util_tool.DUtils.getDiskBitmap(r12)
            if (r7 != 0) goto L_0x0077
            r3[r6] = r6
        L_0x0076:
            r7 = r11
        L_0x0077:
            int r8 = r3.length
        L_0x0078:
            int r9 = r3.length
            if (r5 >= r9) goto L_0x008c
            boolean r9 = r3[r5]
            if (r9 != 0) goto L_0x0081
            int r8 = r8 + -1
        L_0x0081:
            if (r8 != 0) goto L_0x0089
            r0.recycle()
            r11.recycle()
        L_0x0089:
            int r5 = r5 + 1
            goto L_0x0078
        L_0x008c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Path=="
            r11.append(r0)
            r11.append(r1)
            java.lang.String r3 = " = "
            r11.append(r3)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            com.fxeye.foreignexchangeeye.util_tool.DUtils.logI(r11)
            android.graphics.Bitmap r11 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.mergeBitmap_TB(r4, r10, r6, r2)
            android.graphics.Bitmap r2 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.mergeBitmap_TB(r11, r7, r6, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r1)
            r5.append(r3)
            r5.append(r12)
            java.lang.String r12 = r5.toString()
            com.fxeye.foreignexchangeeye.util_tool.DUtils.logI(r12)
            recycleBitmap(r10)
            recycleBitmap(r11)
            recycleBitmap(r4)
            recycleBitmap(r7)
            android.graphics.Bitmap r10 = com.fxeye.foreignexchangeeye.util_tool.BitmapUtils.compressBitmap(r2)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.controller.fristpage.TraderController.topAndBottomFun(android.graphics.Bitmap, android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static boolean isLongLanguage(String str) {
        return LanguageType.LANGUAGE_DE_LOCA1.equals(str) || LanguageType.LANGUAGE_RU_LOCA1.equals(str) || LanguageType.LANGUAGE_FR_LOCA1.equals(str) || LanguageType.LANGUAGE_YUENAN_LOCA1.equals(str) || LanguageType.LANGUAGE_TAIGUO_LOCA1.equals(str) || LanguageType.LANGUAGE_YUENAN_MS1.equals(str) || LanguageType.LANGUAGE_YUENAN_ES1.equals(str) || LanguageType.LANGUAGE_YUENAN_PT1.equals(str) || "fil".equals(str) || "it".equals(str) || "id".equals(str) || LanguageType.LANGUAGE_YINDI_LOCA1.equals(str);
    }

    public static boolean isHuiBaListLongLanguage(String str) {
        return LanguageType.LANGUAGE_DE_LOCA1.equals(str) || LanguageType.LANGUAGE_RU_LOCA1.equals(str) || LanguageType.LANGUAGE_FR_LOCA1.equals(str) || "en".equals(str) || LanguageType.LANGUAGE_YUENAN_LOCA1.equals(str) || LanguageType.LANGUAGE_TAIGUO_LOCA1.equals(str) || LanguageType.LANGUAGE_YUENAN_MS1.equals(str) || LanguageType.LANGUAGE_YUENAN_ES1.equals(str) || LanguageType.LANGUAGE_YUENAN_PT1.equals(str) || "id".equals(str) || LanguageType.LANGUAGE_YINDI_LOCA1.equals(str) || "fil".equals(str) || "it".equals(str) || LanguageType.LANGUAGE_JA_LOCA1.equals(str);
    }

    public static String createNameCE(TraderResponse.ResultBean resultBean) {
        String shortName = resultBean.getShortName();
        if (!TextUtils.isEmpty(resultBean.getLocalShortName())) {
            shortName = resultBean.getLocalShortName();
        }
        return TextUtils.isEmpty(shortName) ? "--" : shortName;
    }
}
