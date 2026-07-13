package com.bun.miitmdid;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class n0 {
    public static BlockingQueue<Runnable> d = new ArrayBlockingQueue(3);
    public static ThreadFactory e = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f164a = 2;
    public static int c = 5;
    public static int b = 6000;
    public static ThreadPoolExecutor f = new ThreadPoolExecutor(f164a, c, b, TimeUnit.SECONDS, d, e);

    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f165a = new AtomicInteger();

        @Override // java.util.concurrent.ThreadFactory
        public native Thread newThread(Runnable runnable);
    }

    public static native void a(Runnable runnable);
}
