package com.huya.berry.gamesdk.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TaskExecutor {
    public static final String TAG = "TaskExecutor";
    private static Handler proxyHandler;
    private static Looper proxyLooper;
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public interface Callback<T> {
        void onCallback(T t);
    }

    public interface Callback2<T1, T2> {
        void onCallback(T1 t1, T2 t2);
    }

    public interface Callback3<T1, T2, T3> {
        void onCallback(T1 t1, T2 t2, T3 t3);
    }

    static {
        proxyHandler = null;
        proxyLooper = null;
        HandlerThread handlerThread = new HandlerThread("workThread");
        handlerThread.start();
        proxyLooper = handlerThread.getLooper();
        proxyHandler = new Handler(proxyLooper);
    }

    public static Handler uiHandler() {
        return uiHandler;
    }

    public static Handler proxyHandler() {
        return proxyHandler;
    }

    public static ExecutorService executor() {
        return executor;
    }

    public static Looper proxyLooper() {
        return proxyLooper;
    }

    public static <T> Runnable callbackInUI(final Callback<T> callback, final T t) {
        if (callback == null) {
            return null;
        }
        Runnable runnable = new Runnable() { // from class: com.huya.berry.gamesdk.utils.TaskExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                callback.onCallback(t);
            }
        };
        runInUIThread(runnable);
        return runnable;
    }

    public static <T1, T2> Runnable callbackInUI(final Callback2<T1, T2> callback2, final T1 t1, final T2 t2) {
        if (callback2 == null) {
            return null;
        }
        Runnable runnable = new Runnable() { // from class: com.huya.berry.gamesdk.utils.TaskExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                callback2.onCallback(t1, t2);
            }
        };
        runInUIThread(runnable);
        return runnable;
    }

    public static <T1, T2, T3> Runnable callbackInUI(final Callback3<T1, T2, T3> callback3, final T1 t1, final T2 t2, final T3 t3) {
        if (callback3 == null) {
            return null;
        }
        Runnable runnable = new Runnable() { // from class: com.huya.berry.gamesdk.utils.TaskExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                callback3.onCallback(t1, t2, t3);
            }
        };
        runInUIThread(runnable);
        return runnable;
    }

    public static TaskFuture runInPoolThread(final Runnable runnable) {
        return new TaskFuture(executor.submit(new Runnable() { // from class: com.huya.berry.gamesdk.utils.TaskExecutor.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    Log.e(TaskExecutor.TAG, e.toString());
                }
            }
        }));
    }

    public static <V> Future<V> runInPoolThread(final Callable<V> callable) {
        return executor.submit(new Callable<V>() { // from class: com.huya.berry.gamesdk.utils.TaskExecutor.5
            @Override // java.util.concurrent.Callable
            public V call() {
                try {
                    return (V) callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    public static boolean runInProxyThread(Runnable runnable) {
        return proxyHandler.post(runnable);
    }

    public static boolean runInUIThread(Runnable runnable) {
        return uiHandler.post(runnable);
    }

    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isProxyThread() {
        return Looper.myLooper() != null && Looper.myLooper() == proxyLooper;
    }

    public static class TaskFuture {
        Future<?> future;

        public TaskFuture(Future<?> future) {
            this.future = future;
        }

        public boolean isCancelled() {
            return this.future.isCancelled();
        }

        public boolean isDone() {
            return this.future.isDone();
        }

        public void cancel(boolean z) {
            this.future.cancel(z);
        }
    }
}
