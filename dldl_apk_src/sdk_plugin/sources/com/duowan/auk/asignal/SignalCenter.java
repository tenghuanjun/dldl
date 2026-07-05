package com.duowan.auk.asignal;

import android.os.Handler;
import android.os.Looper;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.Bridge;
import com.duowan.auk.signal.SignalType;
import com.duowan.auk.signal.Slot;
import com.huya.mtp.utils.HandlerExecutor;
import com.huya.mtp.utils.HandlerPoolExecutor;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SignalCenter {
    public static final int BackgroundExecutorID = 2;
    public static final int MainExecutorID = 1;
    public static final int ThreadPool = 3;
    private static Bridge msBridge;

    static {
        initBridge();
    }

    public static <T> void register(T t) {
        msBridge.register(t);
    }

    public static <T> void unregister(T t) {
        msBridge.unregister(t);
    }

    public static <T> void send(T t) {
        msBridge.send(t);
    }

    public static <T> void send(T t, Object obj) {
        msBridge.send(t, obj);
    }

    public static <T> void send(T t, SignalType signalType) {
        msBridge.send((Object) t, signalType);
    }

    public static <T> void send(T t, SignalType signalType, String str) {
        msBridge.send(t, signalType, str);
    }

    public static void addOnConnectListener(Bridge.OnConnectListener onConnectListener) {
        msBridge.addOnConnectListener(onConnectListener);
    }

    public static void removeOnConnectListener(Bridge.OnConnectListener onConnectListener) {
        msBridge.removeOnConnectListener(onConnectListener);
    }

    public static void addOnExceptionListener(Bridge.OnExceptionListener onExceptionListener) {
        msBridge.addOnExceptionListener(onExceptionListener);
    }

    public static void removeOnExceptionListener(Bridge.OnExceptionListener onExceptionListener) {
        msBridge.removeOnExceptionListener(onExceptionListener);
    }

    public static Executor getExecutor(int i) {
        return msBridge.getExecutor(i);
    }

    private static void initBridge() {
        msBridge = new Bridge.Builder().bridgeExecutor(new HandlerExecutor("signalThread")).putExecutor(1, new Executor() { // from class: com.duowan.auk.asignal.SignalCenter.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }).putExecutor(2, new HandlerExecutor("backgroundThread")).putExecutor(3, new HandlerPoolExecutor()).build();
        addOnConnectListener(new Bridge.OnConnectListener() { // from class: com.duowan.auk.asignal.SignalCenter.2
            @Override // com.duowan.auk.signal.Bridge.OnConnectListener
            public void onConnect(Class<?> cls, Slot slot) {
                PropertyHelper.sOnConnect(cls, slot);
            }
        });
        addOnExceptionListener(new Bridge.OnExceptionListener() { // from class: com.duowan.auk.asignal.SignalCenter.3
            @Override // com.duowan.auk.signal.Bridge.OnExceptionListener
            public void onNullReceiver(Slot slot) {
                ArkUtils.crashIfDebug("null receiver: %s", slot);
            }

            @Override // com.duowan.auk.signal.Bridge.OnExceptionListener
            public void onNullExecutor(Slot slot) {
                ArkUtils.crashIfDebug("no exist executor: %s", slot);
            }

            @Override // com.duowan.auk.signal.Bridge.OnExceptionListener
            public void onInvokeSlotFail(Throwable th, Slot slot) {
                ArkUtils.crashIfDebug(th, "invoke slot fail: %s", slot);
            }
        });
    }
}
