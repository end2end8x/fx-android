package com.fxeye.foreignexchangeeye.util_tool.okhttps;

import android.util.Base64;
import android.util.Log;

import com.libs.view.optional.anaother.ConstDefine;
import com.mob.tools.utils.Dic;
import com.xiaomi.mipush.sdk.Constants;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OkHttpUtils {
    private static final String ENCODING = "UTF-8";
    private static final String MAC_NAME = "HMACSHA1";

    public static String getRandomString() {
        Date date = new Date();
        return date.getTime() + "";
    }

    public static String[] sortParam(Map<String, String> map) {
        String[] strArr = (String[]) map.keySet().toArray(new String[0]);
        Arrays.sort(strArr);
        return strArr;
    }

    public static String encodeString(List<NameValuePair> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            try {
                stringBuffer.append(URLEncoder.encode(list.get(i).getName(), "UTF-8") + ConstDefine.DIVIDER_SIGN_DENGGHAO + URLEncoder.encode(list.get(i).getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (i < list.size() - 1) {
                stringBuffer.append("&");
            }
        }
        return ("?" + stringBuffer.toString()).replaceAll("/+", "%20").replaceAll(ConstDefine.SIGN_XINGHAO, "%2A").replaceAll("%7E", Constants.WAVE_SEPARATOR);
    }

    public static String getAuthString(String str, String str2) {
        return str + "&" + encode("/") + "&" + encode(str2);
    }

    public static String getToken(String str) {
        return Base64.encodeToString(SHA(str).getBytes(), 0);
    }

    public static String SHA(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(MAC_NAME);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String HmacSHA1Encrypt(String str, String str2) {
        byte[] bArr;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), MAC_NAME);
            Mac instance = Mac.getInstance(MAC_NAME);
            instance.init(secretKeySpec);
            bArr = instance.doFinal(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
            return Base64.encodeToString(bArr, 2);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            bArr = null;
            return Base64.encodeToString(bArr, 2);
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            bArr = null;
            return Base64.encodeToString(bArr, 2);
        }
        return Base64.encodeToString(bArr, 2);
    }

    public static String encode(String str) {
        StringBuilder sb = new StringBuilder();
        str.getBytes();
        for (char c : str.toCharArray()) {
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~".indexOf(c) != -1) {
                sb.append(c);
            } else {
                sb.append(ConstDefine.SIGN_BAIFENHAO);
                sb.append(Integer.toHexString(c & 255).toUpperCase());
            }
        }
        return sb.toString();
    }

    public static String local2UTC() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Dic.GPS));
        Date date = new Date();
        date.setTime(((new Date().getTime() / 1000) - 480) * 1000);
        return simpleDateFormat.format(date);
    }

    public static String getToken(List<NameValuePair> list, String str, String str2) {
        return getToken(list, str, str2, false);
    }

    public static String getToken(List<NameValuePair> list, String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(list.get(i).getName(), list.get(i).getValue().replace("\\", ""));
            if (z) {
                hashMap.put(list.get(i).getName(), list.get(i).getValue().replace("%5C", "\\"));
                hashMap.put(list.get(i).getName(), list.get(i).getValue().replace("%25", ConstDefine.SIGN_BAIFENHAO));
            }
        }
        String[] strArr = (String[]) hashMap.keySet().toArray(new String[0]);
        Arrays.sort(strArr);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            try {
                stringBuffer.append(URLEncoder.encode(strArr[i2], "UTF-8") + ConstDefine.DIVIDER_SIGN_DENGGHAO + URLEncoder.encode((String) hashMap.get(strArr[i2]), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (i2 < strArr.length - 1) {
                stringBuffer.append("&");
            }
        }
        String replaceAll = stringBuffer.toString().replaceAll("\\+", "%20");
        Log.i("test", "codes=" + replaceAll);
        String authString = getAuthString(str, replaceAll);
        Log.i("test", "codes  authString=" + authString);
        String HmacSHA1Encrypt = HmacSHA1Encrypt(authString, str2);
        Log.i("test", "codes  HMAC=" + HmacSHA1Encrypt);
        String str3 = null;
        try {
            str3 = URLEncoder.encode(HmacSHA1Encrypt, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        Log.i("test", "codes  token=" + str3);
        return str3;
    }

    public static StringBuilder listToJson(List<NameValuePair> list, StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append("{");
        for (int i = 0; i < list.size(); i++) {
            NameValuePair nameValuePair = list.get(i);
            if (nameValuePair instanceof Map) {
                mapToJson((Map) nameValuePair, sb);
            } else if (nameValuePair instanceof List) {
                listToJson((List) nameValuePair, sb);
            } else if (nameValuePair instanceof NameValuePair) {
                NameValuePair nameValuePair2 = nameValuePair;
                try {
                    if (nameValuePair2.getValue().contains("{")) {
                        sb.append("\"" + nameValuePair2.getName() + "\":" + nameValuePair2.getValue());
                    } else {
                        sb.append("\"" + nameValuePair2.getName() + "\":\"" + nameValuePair2.getValue() + "\"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append("\"" + nameValuePair + "\"");
            }
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb;
    }

    public static StringBuilder mapToJson(Map<String, Object> map, StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append("{");
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            String str = "";
            String obj = next.getKey() != null ? next.getKey().toString() : str;
            sb.append("\"" + stringToJson(obj) + "\":");
            Object value = next.getValue();
            if (value instanceof List) {
                listToJson((List) value, sb);
            } else if (value instanceof Map) {
                mapToJson((Map) value, sb);
            } else {
                if (next.getValue() != null) {
                    str = next.getValue().toString();
                }
                sb.append("\"" + stringToJson(str) + "\"");
            }
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb;
    }

    private static String stringToJson(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 12) {
                stringBuffer.append("\\f");
            } else if (charAt == 13) {
                stringBuffer.append("\\r");
            } else if (charAt == '\"') {
                stringBuffer.append("\\\"");
            } else if (charAt == '/') {
                stringBuffer.append("\\/");
            } else if (charAt != '\\') {
                switch (charAt) {
                    case 8:
                        stringBuffer.append("\\b");
                        break;
                    case 9:
                        stringBuffer.append("\\t");
                        break;
                    case 10:
                        stringBuffer.append("\\n");
                        break;
                    default:
                        stringBuffer.append(charAt);
                        break;
                }
            } else {
                stringBuffer.append("\\\\");
            }
        }
        return stringBuffer.toString();
    }
}
