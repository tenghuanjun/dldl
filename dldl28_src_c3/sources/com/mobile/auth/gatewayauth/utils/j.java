package com.mobile.auth.gatewayauth.utils;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class j {
    private static Pattern a = Pattern.compile("^[-\\+]?[\\d]*$");

    public static String a(int i) {
        try {
            return Integer.toHexString(i);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String a(long j) {
        try {
            try {
                return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss.SSS").format(new Date(j));
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        } catch (Exception unused) {
            return String.valueOf(j);
        }
    }

    public static String a(Long l, int i) {
        try {
            String str = "%0" + i + "x";
            l.longValue();
            return String.format(str, l);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
