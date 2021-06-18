package com.libs.view.optional.util;

import android.text.TextUtils;
import android.util.Log;

public class Logx {
    private static final boolean DEBUG = true;
    private static int MAX = 2048;
    private static final String TAG = "Logx";

    /* renamed from: d */
    public static void m5549d(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= MAX) {
            Log.d(TAG, str);
            return;
        }
        int i = 0;
        while (MAX * i < str.length()) {
            try {
                int i2 = MAX * i;
                i++;
                Log.d(TAG, str.substring(i2, MAX * i > str.length() ? str.length() : MAX * i));
            } catch (Exception unused) {
                return;
            }
        }
    }

    /* renamed from: e */
    public static void error(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= MAX) {
            Log.e(TAG, str);
            return;
        }
        int i = 0;
        while (MAX * i < str.length()) {
            try {
                int i2 = MAX * i;
                i++;
                Log.e(TAG, str.substring(i2, MAX * i > str.length() ? str.length() : MAX * i));
            } catch (Exception unused) {
                return;
            }
        }
    }

    /* renamed from: i */
    public static void info(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= MAX) {
            Log.i(TAG, str);
            return;
        }
        int i = 0;
        while (MAX * i < str.length()) {
            try {
                int i2 = MAX * i;
                i++;
                Log.i(TAG, str.substring(i2, MAX * i > str.length() ? str.length() : MAX * i));
            } catch (Exception unused) {
                return;
            }
        }
    }
}
