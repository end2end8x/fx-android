package com.fxeye.foreignexchangeeye.util_tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.libs.view.optional.anaother.ConstDefine;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.UUID;

public class BasicUtils {
    public static String firstNewsCode = "";
    private static long lastClickTime;
    public static String[] videoUrlList = {"0", "1", "2", "3", "4", "5", "6", "7", UrlUtil.TIJIAO_JIUCUO, UrlUtil.TOUSU_LOOK, "元"};
    private static String vlaueString;

    public static String GetCode() {
        return "100";
    }

    public static String GetPackname() {
        return "com.fxeye.foreignexchangeeye";
    }

    public static String GetUpBiaoshi() {
        return UrlUtil.UPDATA_ZIJI;
    }

    public static String GetLanguageString(Context context) {
        return context.getSharedPreferences("Data_language", 0).getString("language_dateTime", "vi");
    }

    public static String GetCountryString(Context context) {
        return context.getSharedPreferences("Data_country", 0).getString("country_dateTime", "vn");
    }

    public static void SetChosseGuoqi(Context context, String str) {
        context.getSharedPreferences("Data_ChosseGuoqi_name", 0).edit().putString("ChosseGuoqi_dateTime_name", str).commit();
    }

    public static String GetChosseGuoqi(Context context) {
        return context.getSharedPreferences("Data_ChosseGuoqi_name", 0).getString("ChosseGuoqi_dateTime_name", "");
    }

    public static String GetIp(Context context) {
        return context.getSharedPreferences("Data_ip", 0).getString("ip_dateTime", "");
    }

    @SuppressLint("MissingPermission")
    public static String getIPAddress(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement = inetAddresses.nextElement();
                            if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                return nextElement.getHostAddress();
                            }
                        }
                    }
                }
                return null;
            } catch (SocketException e) {
                e.printStackTrace();
                return null;
            }
        } else if (activeNetworkInfo.getType() == 1) {
            return intIP2StringIP(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
        } else {
            return null;
        }
    }

    public static String intIP2StringIP(int i) {
        return (i & 255) + ConstDefine.DIVIDER_SIGN_DIANHAO + ((i >> 8) & 255) + ConstDefine.DIVIDER_SIGN_DIANHAO + ((i >> 16) & 255) + ConstDefine.DIVIDER_SIGN_DIANHAO + ((i >> 24) & 255);
    }

    public static void SetUUid(Context context, String str) {
        context.getSharedPreferences("Data_uuid", 0).edit().putString("char_uuid", str).commit();
    }

    public static String GetUUid(Context context) {
        return context.getSharedPreferences("Data_uuid", 0).getString("char_uuid", "");
    }

    public static String getIMEI(Context context) {
        if (!TextUtils.isEmpty(GetUUid(context))) {
            return GetUUid(context);
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String uuid = UUID.randomUUID().toString();
            SetUUid(context, uuid);
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encode(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append("0" + hexString);
                } else {
                    sb.append(hexString);
                }
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
