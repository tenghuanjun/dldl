package com.mobile.auth.gatewayauth.utils;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.manager.FeatureManager;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e {
    private static e b;
    private ConcurrentHashMap<String, StringBuffer> a = new ConcurrentHashMap<>();

    private e() {
    }

    public static e a() {
        try {
            if (b == null) {
                synchronized (e.class) {
                    if (b == null) {
                        b = new e();
                    }
                }
            }
            return b;
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

    public String a(String str) {
        StringBuffer stringBufferRemove;
        try {
            if (FeatureManager.getInstance().get(FeatureManager.FEATURE_KEY_PERFORMANCE_TRACKER) == null && (stringBufferRemove = this.a.remove(str)) != null) {
                return stringBufferRemove.toString();
            }
            return null;
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

    public void a(String str, String str2, long j) {
        try {
            a(str, str2, j.a(j));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(String str, String str2, String str3) {
        StringBuffer stringBuffer;
        try {
            if (FeatureManager.getInstance().get(FeatureManager.FEATURE_KEY_PERFORMANCE_TRACKER) != null) {
                return;
            }
            if (this.a.containsKey(str)) {
                stringBuffer = this.a.get(str);
            } else {
                synchronized (this.a) {
                    if (this.a.containsKey(str)) {
                        stringBuffer = null;
                    } else {
                        stringBuffer = new StringBuffer(str);
                        this.a.put(str, stringBuffer);
                    }
                }
            }
            stringBuffer.append("[" + str2 + ":" + str3 + "]");
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
