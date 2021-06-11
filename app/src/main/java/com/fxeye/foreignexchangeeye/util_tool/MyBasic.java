package com.fxeye.foreignexchangeeye.util_tool;

import android.content.Context;
import com.fxeye.foreignexchangeeye.controller.UserController;
import com.libs.view.optional.anaother.ConstDefine;
import org.json.JSONObject;

public class MyBasic {
    private static boolean insertboolean;
    private static long lastClickTime;


    public static String getAppInfo(Context context) {
        try {
            return GetDeleteShort(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String GetDeleteShort(String str) {
        return str.replace(ConstDefine.DIVIDER_SIGN_DIANHAO, "");
    }

    public static String getBiaoshi_type(Context context) {
        return context.getSharedPreferences("share_type", 0).getString("share_type_index", "");
    }

    public static void setBiaoshi_name(Context context, String str) {
        context.getSharedPreferences("nickname", 0).edit().putString("nickname_index", str).commit();
    }

    public static String getBiaoshi_name(Context context) {
        return context.getSharedPreferences("nickname", 0).getString("nickname_index", "");
    }

    public static void setBiaoshi_url(Context context, String str) {
        context.getSharedPreferences("headimgurl", 0).edit().putString("headimgurl_index", str).commit();
    }

    public static String getBiaoshi_url(Context context) {
        return context.getSharedPreferences("headimgurl", 0).getString("headimgurl_index", "");
    }

    public static String GetAll_Data(Context context) {
        String biaoshi_type = getBiaoshi_type(context);
        String biaoshi_name = getBiaoshi_name(context);
        String biaoshi_url = getBiaoshi_url(context);
        String newBDUserId = UserController.getNewBDUserId(context);
        return "https://m.forexcity.com/user/Ktvip.html?uid=" + newBDUserId + "&name=" + biaoshi_name + "&avatar=" + biaoshi_url + "&sharetype=" + biaoshi_type;
    }

    public static String GetTrade_no(String str, Context context) {
        try {
            return new JSONObject(str).getString("trade_no");
        } catch (Exception e) {
            DUtils.toastShow((CharSequence) "异常：" + e.getMessage());
            return null;
        }
    }
}
