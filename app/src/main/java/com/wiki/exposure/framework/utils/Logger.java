package com.wiki.exposure.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private static Logger inst = null;
    private static int logLevel = 2;
    private Lock lock = new ReentrantLock();
    private String tagName = "ShopLogger";

    /* renamed from: d */
    public void mo54118d(String str, Object... objArr) {
    }

    private Logger() {
    }

    public static synchronized Logger getLogger(Class<?> cls) {
        Logger logger;
        synchronized (Logger.class) {
            if (inst == null) {
                inst = new Logger();
            }
            logger = inst;
        }
        return logger;
    }

    private String getFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    private String createMessage(String str) {
        String functionName = getFunctionName();
        long id = Thread.currentThread().getId();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        if (functionName != null) {
            str = functionName + " - " + String.valueOf(id) + " - " + str;
        }
        return format + " - " + str;
    }

    /* renamed from: i */
    public void mo54121i(String str, Object... objArr) {
        int i = logLevel;
    }

    /* renamed from: v */
    public void mo54123v(String str, Object... objArr) {
        int i = logLevel;
    }

    /* renamed from: e */
    public void mo54119e(String str, Object... objArr) {
        int i = logLevel;
    }

    private String getInputString(String str, Object... objArr) {
        return str == null ? "null log format" : String.format(str, objArr);
    }

    public void error(Exception exc) {
        int i = logLevel;
    }

    /* renamed from: w */
    public void mo54124w(String str, Object... objArr) {
        int i = logLevel;
    }

    public void setLevel(int i) {
        this.lock.lock();
        try {
            logLevel = i;
        } finally {
            this.lock.unlock();
        }
    }
}
