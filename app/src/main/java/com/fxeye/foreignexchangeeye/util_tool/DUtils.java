package com.fxeye.foreignexchangeeye.util_tool;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.fxeye.foreignexchangeeye.MyApplication;
import com.libs.view.optional.anaother.ConstDefine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String APPPACKAGE = "waihuitianyan";
    public static final String CONS_LOG_BASE_TAG = "App_Log : ";
    public static final String CONS_LOG_EVENT_BUS = "EventBug_Log : ";
    public static final String CONS_LOG_RESPONSE = "Response_Log : ";
    public static final String CONS_LOG_SCREENSHOT = "Screenshot_Log : ";
    public static final String CONS_LOG_WEB = "Web_Log : ";
    public static final int CORNER_ALL = 15;
    public static final int CORNER_BOTTOM_LEFT = 4;
    public static final int CORNER_BOTTOM_RIGHT = 8;
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 2;
    private static final int LOG_MAX_LENGTH = 2000;
    public static final String SAVEPATH = "/sdcard/crash/waihuitianyan/";
    public static final String TRADER_OPERATION = "Trader_operation ";
    private static boolean doubleCilck = false;
    private static boolean isLog = false;
    private static long lastClickTime;
    private static Context mContext;
    public static float sNoncomatDensity;
    public static float sNoncomatScaledDensity;

    public static void toastShow(CharSequence charSequence) {
        toastShowCenter(charSequence);
    }

    public static void toastShowCenter(CharSequence charSequence) {
        try {
            Toast.makeText((Context) MyApplication.getInstance(), charSequence.toString(), Toast.LENGTH_SHORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStringToDates(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDateToString(date.getTime(), str2);
    }

    public static String getDateToString(long j, String str) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        return simpleDateFormat.format(date);
    }

    public static String getCurDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        return simpleDateFormat.format(new Date());
    }

    public static String getTimezone() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static String mySplit(String str, int i) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < charArray.length && i > 0; i2++) {
            String valueOf = String.valueOf(charArray[i2]);
            if (valueOf.getBytes().length > 1) {
                i -= 2;
                if (i < 0) {
                    break;
                }
            } else {
                i--;
            }
            sb.append(valueOf);
        }
        return sb.toString();
    }

    public static boolean isObjEmpty(Object obj) {
        if (obj instanceof Collection) {
            if (obj != null && ((Collection) obj).size() > 0) {
                return true;
            }
            return false;
        } else if (obj instanceof Map) {
            if (obj != null && ((Map) obj).size() > 0) {
                return true;
            }
            return false;
        } else if (obj == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void init(Context context) {
        mContext = context;
        isLog = false;
    }

    public static void setCustomDensity(final Application application, Activity activity, boolean z, boolean z2) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncomatDensity == 0.0f) {
            sNoncomatDensity = displayMetrics.density;
            sNoncomatScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                public void onLowMemory() {
                }

                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0.0f) {
                        DUtils.sNoncomatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }
            });
        }
        float f = ((float) displayMetrics.widthPixels) / 360.0f;
        float f2 = sNoncomatScaledDensity;
        float f3 = sNoncomatDensity;
        float f4 = (f2 / f3) * f;
        int i = (int) (f * 160.0f);
        if (!z2) {
            if (z) {
                displayMetrics.density = f3;
                displayMetrics.scaledDensity = f2;
                displayMetrics.densityDpi = (int) (f3 * 160.0f);
            } else {
                displayMetrics.density = f;
                displayMetrics.scaledDensity = f4;
                displayMetrics.densityDpi = i;
            }
        }
        if (activity != null) {
            DisplayMetrics displayMetrics2 = activity.getResources().getDisplayMetrics();
            if (z) {
                float f5 = sNoncomatDensity;
                displayMetrics2.density = f5;
                displayMetrics2.scaledDensity = sNoncomatScaledDensity;
                displayMetrics2.densityDpi = (int) (f5 * 160.0f);
                return;
            }
            displayMetrics2.density = f;
            displayMetrics2.scaledDensity = f4;
            displayMetrics2.densityDpi = i;
        }
    }


    public static int getWindowWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }


    public static int getWindowHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }


    public static boolean isDoubleClick() {
        return isDoubleClick(2000);
    }

    public static boolean isDoubleClick(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - lastClickTime;
        if (0 < j && j < ((long) i)) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }

    public static void statusBarCompat(Activity activity, boolean z) {
        statusBarCompat(activity, z, false);
    }

    public static void statusBarCompat(Activity activity, boolean z, boolean z2) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            window.addFlags(67108864);
            window.addFlags(134217728);
        } else if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            if (z) {
                window.setStatusBarColor(0);
                if (!z2) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    window.getDecorView().setSystemUiVisibility(9216);
                } else {
                    window.setStatusBarColor(570425344);
                }
            } else {
                window.setStatusBarColor(570425344);
            }
        }
    }

    public static void iLog(String str) {
        showLog(CONS_LOG_BASE_TAG, str, "i");
    }

    public static void eLog(String str) {
        showLog(CONS_LOG_BASE_TAG, str, "e");
    }

    public static void dLog(String str) {
        showLog(CONS_LOG_BASE_TAG, str, "d");
    }

    public static void wLog(String str) {
        if (!isLog) {
            showLog(CONS_LOG_BASE_TAG, str, "w");
        }
    }

    public static void logI(String str) {
        if (!isLog) {
            showLog(CONS_LOG_BASE_TAG, str, "i");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void showLog(java.lang.String r17, java.lang.String r18, java.lang.String r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            int r3 = r18.length()
            r4 = 2000(0x7d0, float:2.803E-42)
            r6 = r4
            r4 = 0
            r7 = 0
        L_0x000f:
            r8 = 100
            if (r4 >= r8) goto L_0x011e
            java.lang.String r9 = "w"
            java.lang.String r10 = "i"
            java.lang.String r11 = "e"
            java.lang.String r12 = "d"
            r13 = 119(0x77, float:1.67E-43)
            r14 = 105(0x69, float:1.47E-43)
            r15 = 101(0x65, float:1.42E-43)
            r16 = -1
            if (r3 <= r6) goto L_0x00c4
            int r5 = r19.hashCode()
            if (r5 == r8) goto L_0x004b
            if (r5 == r15) goto L_0x0043
            if (r5 == r14) goto L_0x003b
            if (r5 == r13) goto L_0x0033
            goto L_0x0053
        L_0x0033:
            boolean r5 = r2.equals(r9)
            if (r5 == 0) goto L_0x0053
            r5 = 2
            goto L_0x0055
        L_0x003b:
            boolean r5 = r2.equals(r10)
            if (r5 == 0) goto L_0x0053
            r5 = 3
            goto L_0x0055
        L_0x0043:
            boolean r5 = r2.equals(r11)
            if (r5 == 0) goto L_0x0053
            r5 = 0
            goto L_0x0055
        L_0x004b:
            boolean r5 = r2.equals(r12)
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0055
        L_0x0053:
            r5 = r16
        L_0x0055:
            if (r5 == 0) goto L_0x00a6
            r8 = 1
            if (r5 == r8) goto L_0x008f
            r8 = 2
            if (r5 == r8) goto L_0x0078
            r8 = 3
            if (r5 == r8) goto L_0x0061
            goto L_0x00bc
        L_0x0061:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = r1.substring(r7, r6)
            android.util.Log.i(r5, r7)
            goto L_0x00bc
        L_0x0078:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = r1.substring(r7, r6)
            android.util.Log.w(r5, r7)
            goto L_0x00bc
        L_0x008f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = r1.substring(r7, r6)
            android.util.Log.d(r5, r7)
            goto L_0x00bc
        L_0x00a6:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = r1.substring(r7, r6)
            android.util.Log.e(r5, r7)
        L_0x00bc:
            int r5 = r6 + 2000
            int r4 = r4 + 1
            r7 = r6
            r6 = r5
            goto L_0x000f
        L_0x00c4:
            int r4 = r19.hashCode()
            if (r4 == r8) goto L_0x00e9
            if (r4 == r15) goto L_0x00e1
            if (r4 == r14) goto L_0x00d9
            if (r4 == r13) goto L_0x00d1
            goto L_0x00f1
        L_0x00d1:
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L_0x00f1
            r2 = 2
            goto L_0x00f3
        L_0x00d9:
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x00f1
            r2 = 3
            goto L_0x00f3
        L_0x00e1:
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x00f1
            r2 = 0
            goto L_0x00f3
        L_0x00e9:
            boolean r2 = r2.equals(r12)
            if (r2 == 0) goto L_0x00f1
            r2 = 1
            goto L_0x00f3
        L_0x00f1:
            r2 = r16
        L_0x00f3:
            if (r2 == 0) goto L_0x0117
            r4 = 1
            if (r2 == r4) goto L_0x010f
            r4 = 2
            if (r2 == r4) goto L_0x0107
            r4 = 3
            if (r2 == r4) goto L_0x00ff
            goto L_0x011e
        L_0x00ff:
            java.lang.String r1 = r1.substring(r7, r3)
            android.util.Log.i(r0, r1)
            goto L_0x011e
        L_0x0107:
            java.lang.String r1 = r1.substring(r7, r3)
            android.util.Log.w(r0, r1)
            goto L_0x011e
        L_0x010f:
            java.lang.String r1 = r1.substring(r7, r3)
            android.util.Log.d(r0, r1)
            goto L_0x011e
        L_0x0117:
            java.lang.String r1 = r1.substring(r7, r3)
            android.util.Log.e(r0, r1)
        L_0x011e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.util_tool.DUtils.showLog(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void remove() {
        mContext = null;
    }

    public static Bitmap zoomImg(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006c A[SYNTHETIC, Splitter:B:28:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0074 A[Catch:{ IOException -> 0x0070 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0080 A[SYNTHETIC, Splitter:B:39:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0088 A[Catch:{ IOException -> 0x0084 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap GetLocalOrNetBitmap(java.lang.String r5) {
        /*
            java.lang.String r0 = "https://"
            boolean r0 = r5.contains(r0)
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = "http://"
            boolean r0 = r5.contains(r0)
            if (r0 != 0) goto L_0x0021
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "file://mnt"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
        L_0x0021:
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            java.io.InputStream r5 = r2.openStream()     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            int r2 = com.fxeye.foreignexchangeeye.util_tool.DUtils.DConstant.IO_BUFFER_SIZE     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            r1.<init>(r5, r2)     // Catch:{ IOException -> 0x0064, all -> 0x0061 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x005e, all -> 0x005c }
            r5.<init>()     // Catch:{ IOException -> 0x005e, all -> 0x005c }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x005e, all -> 0x005c }
            int r3 = com.fxeye.foreignexchangeeye.util_tool.DUtils.DConstant.IO_BUFFER_SIZE     // Catch:{ IOException -> 0x005e, all -> 0x005c }
            r2.<init>(r5, r3)     // Catch:{ IOException -> 0x005e, all -> 0x005c }
            com.google.common.p080io.ByteStreams.copy((java.io.InputStream) r1, (java.io.OutputStream) r2)     // Catch:{ IOException -> 0x005a }
            r2.flush()     // Catch:{ IOException -> 0x005a }
            byte[] r5 = r5.toByteArray()     // Catch:{ IOException -> 0x005a }
            r3 = 0
            int r4 = r5.length     // Catch:{ IOException -> 0x005a }
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeByteArray(r5, r3, r4)     // Catch:{ IOException -> 0x005a }
            r1.close()     // Catch:{ IOException -> 0x0055 }
            r2.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0059:
            return r5
        L_0x005a:
            r5 = move-exception
            goto L_0x0067
        L_0x005c:
            r5 = move-exception
            goto L_0x007e
        L_0x005e:
            r5 = move-exception
            r2 = r0
            goto L_0x0067
        L_0x0061:
            r5 = move-exception
            r1 = r0
            goto L_0x007e
        L_0x0064:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x0067:
            r5.printStackTrace()     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x0072
            r1.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0072
        L_0x0070:
            r5 = move-exception
            goto L_0x0078
        L_0x0072:
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x007b
        L_0x0078:
            r5.printStackTrace()
        L_0x007b:
            return r0
        L_0x007c:
            r5 = move-exception
            r0 = r2
        L_0x007e:
            if (r1 == 0) goto L_0x0086
            r1.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0086
        L_0x0084:
            r0 = move-exception
            goto L_0x008c
        L_0x0086:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x008f
        L_0x008c:
            r0.printStackTrace()
        L_0x008f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.util_tool.DUtils.GetLocalOrNetBitmap(java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getDiskBitmap(String str) {
        try {
            if (new File(str).exists()) {
                return BitmapFactory.decodeFile(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0050 A[SYNTHETIC, Splitter:B:21:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[SYNTHETIC, Splitter:B:27:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0069 A[SYNTHETIC, Splitter:B:34:0x0069] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0055=Splitter:B:24:0x0055, B:18:0x004b=Splitter:B:18:0x004b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String saveFile(android.graphics.Bitmap r3, java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = com.fxeye.foreignexchangeeye.util_tool.DUtils.DConstant.ScreenshotAdPath
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0010
            r0.mkdir()
        L_0x0010:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.fxeye.foreignexchangeeye.util_tool.DUtils.DConstant.ScreenshotAdPath
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            r4 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x004a }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x004a }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x004a }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x004a }
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0042, all -> 0x003f }
            r2 = 80
            r3.compress(r4, r2, r1)     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0042, all -> 0x003f }
            r1.flush()     // Catch:{ FileNotFoundException -> 0x0045, IOException -> 0x0042, all -> 0x003f }
            r1.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x003f:
            r3 = move-exception
            r4 = r1
            goto L_0x0067
        L_0x0042:
            r3 = move-exception
            r4 = r1
            goto L_0x004b
        L_0x0045:
            r3 = move-exception
            r4 = r1
            goto L_0x0055
        L_0x0048:
            r3 = move-exception
            goto L_0x0067
        L_0x004a:
            r3 = move-exception
        L_0x004b:
            r3.printStackTrace()     // Catch:{ all -> 0x0048 }
            if (r4 == 0) goto L_0x0062
            r4.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x0054:
            r3 = move-exception
        L_0x0055:
            r3.printStackTrace()     // Catch:{ all -> 0x0048 }
            if (r4 == 0) goto L_0x0062
            r4.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0062:
            java.lang.String r3 = r0.getAbsolutePath()
            return r3
        L_0x0067:
            if (r4 == 0) goto L_0x0071
            r4.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0071:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fxeye.foreignexchangeeye.util_tool.DUtils.saveFile(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    public static Date stringToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    public static String getDateStr(String str) {
        try {
            return new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }

    static class DConstant {
        public static int IO_BUFFER_SIZE = 2048;
        public static String ScreenshotAdPath = (MyApplication.getInstance().getCacheDir() + "/screenshotAd/");

        DConstant() {
        }
    }

    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String str, Paint paint, Rect rect, int i, int i2, int i3) {
        Bitmap.Config config = bitmap.getConfig();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap copy = bitmap.copy(config, true);
        Canvas canvas = new Canvas(copy);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(-2013265920);
        paint2.setStrokeWidth(10.0f);
        canvas.drawRect((float) ((copy.getWidth() - rect.width()) - (i3 * 2)), (float) ((copy.getHeight() - rect.height()) - (((i3 * copy.getHeight()) / copy.getWidth()) * 2)), (float) copy.getWidth(), (float) copy.getHeight(), paint2);
        canvas.drawText(str, (float) i, (float) i2, paint);
        return copy;
    }

    public static Bitmap getSameForViewHeightBitmap(ImageView imageView, Bitmap bitmap, int i) {
        Bitmap bitmap2;
        if (imageView == null) {
            return bitmap;
        }
        int height = imageView.getHeight();
        int width = imageView.getWidth();
        if (width == 0) {
            width = SharedPreferencesUtils.checkFlagValue(imageView.getContext(), "windows_arg", "width", 0);
        }
        if (height != 0) {
            i = height;
        } else if (i == 0) {
            i = SharedPreferencesUtils.checkFlagValue(imageView.getContext(), "windows_arg", "height", 0);
        }
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        int i2 = (height2 * width) / i;
        if (i2 > width2) {
            int i3 = (width2 * i) / width;
            bitmap2 = Bitmap.createBitmap(bitmap, 0, Math.abs(height2 - i3) / 2, width2, i3);
        } else {
            bitmap2 = Bitmap.createBitmap(bitmap, Math.abs(width2 - i2) / 2, 0, i2, height2);
        }
        int width3 = (int) (((float) bitmap2.getWidth()) * ((((float) i) + 0.0f) / ((float) bitmap2.getHeight())));
        if (width3 < width) {
            width3 = width;
        }
        return zoomImg(bitmap2, width3, i);
    }

    public static BitmapInformation getResInfWH(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        BitmapInformation bitmapInformation = new BitmapInformation();
        bitmapInformation.setBitmap(decodeResource);
        bitmapInformation.setWidth(i2);
        bitmapInformation.setHeight(i3);
        return bitmapInformation;
    }

    public static class BitmapInformation {
        private Bitmap bitmap;
        private int height;
        private int width;

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public void setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i) {
            this.width = i;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i) {
            this.height = i;
        }
    }

    public static String utc2Local(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat2.format(Long.valueOf(date.getTime()));
    }

    public static void getWindowData(Activity activity) {
        if (SharedPreferencesUtils.checkFlagValue(activity, "windows_arg", "width_height", 0) != 1) {
            WindowManager windowManager = activity.getWindowManager();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            logI("windows==width=" + i + ";height=" + i2);
            SharedPreferencesUtils.putValue(activity, "windows_arg", "width", i);
            SharedPreferencesUtils.putValue(activity, "windows_arg", "height", i2);
            SharedPreferencesUtils.putValue(activity, "windows_arg", "width_height", 1);
        }
    }

    public static Rect getTextRect(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    private static void endOptionText(String str, StringBuilder sb, int i, int i2) {
        sb.delete(sb.length() - i2, sb.length());
        sb.append("\n");
        sb.append(str.substring(i - i2));
    }

    public static int getCharArr(int i, int i2, int i3) {
        if (i2 / i > i3 && i <= 5) {
            return getCharArr(i + 1, i2, i3);
        }
        return i;
    }

    public static int[] unDisplayViewSize(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    public static void unZip(Context context, String str, String str2, boolean z) throws IOException {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            ZipInputStream zipInputStream = new ZipInputStream(context.getAssets().open(str));
            byte[] bArr = new byte[1048576];
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                if (nextEntry.isDirectory()) {
                    File file2 = new File(str2 + File.separator + nextEntry.getName());
                    if (z || !file2.exists()) {
                        file2.mkdir();
                    }
                } else {
                    File file3 = new File(str2 + File.separator + nextEntry.getName());
                    if (z || !file3.exists()) {
                        file3.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file3);
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                    }
                }
            }
            zipInputStream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static Bitmap fillet(Bitmap bitmap, int i, int i2) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawARGB(0, 0, 0, 0);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(-16777216);
            float f = (float) i;
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), f, f, paint);
            int i3 = i2 ^ 15;
            if ((i3 & 1) != 0) {
                clipTopLeft(canvas, paint, i, width, height);
            }
            if ((i3 & 2) != 0) {
                clipTopRight(canvas, paint, i, width, height);
            }
            if ((i3 & 4) != 0) {
                clipBottomLeft(canvas, paint, i, width, height);
            }
            if ((i3 & 8) != 0) {
                clipBottomRight(canvas, paint, i, width, height);
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Rect rect = new Rect(0, 0, width - 0, height - 0);
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (Exception unused) {
            return bitmap;
        }
    }

    private static void clipTopLeft(Canvas canvas, Paint paint, int i, int i2, int i3) {
        canvas.drawRect(new Rect(0, 0, i, i), paint);
    }

    private static void clipTopRight(Canvas canvas, Paint paint, int i, int i2, int i3) {
        canvas.drawRect(new Rect(i2 - i, 0, i2, i), paint);
    }

    private static void clipBottomLeft(Canvas canvas, Paint paint, int i, int i2, int i3) {
        canvas.drawRect(new Rect(0, i3 - i, i, i3), paint);
    }

    private static void clipBottomRight(Canvas canvas, Paint paint, int i, int i2, int i3) {
        canvas.drawRect(new Rect(i2 - i, i3 - i, i2, i3), paint);
    }

    public static void copyFilesFromAssets(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                new File(str2).mkdirs();
                for (String str3 : list) {
                    copyFilesFromAssets(context, str + File.separator + str3, str2 + File.separator + str3);
                }
                return;
            }
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap loadBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return createBitmap;
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        return Pattern.compile("[『』\n\r<b>]").matcher(str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":").replaceAll("，", ",").replaceAll("；", ConstDefine.DIVIDER_SIGN_FENGHAO).replaceAll("？", "?").replaceAll("《", "<").replaceAll("》", "》").replaceAll("（", "(").replaceAll("）", ")")).replaceAll("").trim();
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String getHHmm(long j) {
        return new SimpleDateFormat("HH:mm", Locale.CHINA).format(Long.valueOf(j));
    }

    public static String getHHmmss(long j) {
        return new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(Long.valueOf(j));
    }

    public static String getyyyyMMddHHmmss(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(Long.valueOf(j));
    }

    public static String getyyyyMMdd(long j) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(Long.valueOf(j));
    }

    public static StateListDrawable getStateListDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = (StateListDrawable) new StateListDrawable().mutate();
        stateListDrawable.addState(new int[]{16842908}, drawable2);
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        stateListDrawable.addState(new int[]{-16842908}, drawable);
        stateListDrawable.addState(new int[]{-16842919}, drawable);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static long getStringToDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String formatString(String str) {
        return str.replaceAll("(?<=\\d)(?=(?:\\d{3})+$)", ",");
    }
}
