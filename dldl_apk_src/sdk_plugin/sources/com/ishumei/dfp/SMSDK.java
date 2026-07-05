package com.ishumei.dfp;

import android.content.Context;
import com.ishumei.O000O00000OoO.O000O00000o0O;
import com.ishumei.O000O00000oO.O0000O000000oO;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SMSDK {
    public static final int LOCAL_ID = 0;
    public static final int OLD_ID = 2;
    public static final int SERVER_ID = 1;
    public static final int UNKNOWN_ID = -1;

    static {
        try {
            System.loadLibrary("smsdk");
        } catch (Throwable th) {
            O000O00000o0O.O0000O000000oO(th);
        }
    }

    public static String h(String str) {
        try {
            return O0000O000000oO.O0000O000000oO().O0000O000000oO(str);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int idType(String str) throws IOException {
        try {
            return new SMSDK().z3(str);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    public static String x1(String str, String str2) throws IOException {
        try {
            return new SMSDK().x2(str, str2);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    private native String x2(String str, String str2);

    public static String y1(boolean z) {
        return y1(z, null, false);
    }

    public static String y1(boolean z, String str, boolean z2) {
        try {
            return new SMSDK().y2(z, str, z2);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    private native String y2(boolean z, String str, boolean z2);

    private native String z1(Context context);

    public static String z2(Context context) throws IOException {
        try {
            return new SMSDK().z1(context);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    private native int z3(String str);
}
