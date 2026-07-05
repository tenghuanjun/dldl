package com.tencent.mars;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.tencent.mars.comm.PlatformComm;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Mars {
    private static volatile boolean hasInitialized;

    public static void loadDefaultMarsLibrary() {
        try {
            Log.i("mars.Mars", "loadDefaultMarsLibrary begin");
            System.loadLibrary("hycrypto");
            System.loadLibrary("hyssl");
            System.loadLibrary("c++_shared");
            System.loadLibrary("marsxlog");
            System.loadLibrary("hyquic");
            System.loadLibrary("marsstn");
            Log.i("mars.Mars", "loadDefaultMarsLibrary end");
        } catch (Throwable th) {
            Log.i("mars.Mars", "loadDefaultMarsLibrary failed = " + th.getMessage());
        }
    }

    public static void init(Context context, Handler handler) {
        PlatformComm.init(context, handler);
        hasInitialized = true;
    }

    public static void onCreate(boolean z) {
        if (z && hasInitialized) {
            BaseEvent.onCreate();
        } else {
            if (!z) {
                BaseEvent.onCreate();
                return;
            }
            throw new IllegalStateException("function MarsCore.init must be executed before Mars.onCreate when application firststartup.");
        }
    }

    public static void onDestroy() {
        BaseEvent.onDestroy();
    }
}
