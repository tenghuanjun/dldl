package com.bytedance.framwork.core.sdklog;

import android.content.Context;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public final class LogLib {
    public static final String CHARSET_NAME = "UTF-8";
    private static boolean sInited;
    private static ILogDelegate sLogDelegateImpl = new DefaultLogDelegateImpl();

    public static class DefaultLogDelegateImpl implements ILogDelegate {
        @Override // com.bytedance.framwork.core.sdklog.LogLib.ILogDelegate
        public boolean isNetworkAvailable(Context context) {
            return false;
        }
    }

    public interface ILogDelegate {
        boolean isNetworkAvailable(Context context);
    }

    public static void init(ILogDelegate iLogDelegate) {
        if (sInited) {
            return;
        }
        if (iLogDelegate != null) {
            sLogDelegateImpl = iLogDelegate;
        }
        sInited = true;
    }

    static boolean isNetworkAvailable(Context context) {
        return sLogDelegateImpl.isNetworkAvailable(context);
    }

    public static byte[] safeGetBytes(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return str.getBytes("UTF-8");
                }
            } catch (UnsupportedEncodingException unused) {
                return str.getBytes();
            }
        }
        return null;
    }
}
