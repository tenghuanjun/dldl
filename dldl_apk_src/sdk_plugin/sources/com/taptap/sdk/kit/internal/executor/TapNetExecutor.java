package com.taptap.sdk.kit.internal.executor;

import android.os.Handler;
import android.os.Looper;
import com.huya.mtp.http.monitor.Stat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapNetExecutor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\b\u0010\u001c\u001a\u00020\nH\u0007J\b\u0010\u001d\u001a\u00020\u0012H\u0007J\b\u0010\u001e\u001a\u00020\nH\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/taptap/sdk/kit/internal/executor/TapNetExecutor;", "", "()V", "CORE_POOL_SIZE", "", "CPU_COUNT", "KEEP_ALIVE_TIME", "", "MAX_POOL_SIZE", "executorService", "Ljava/util/concurrent/ExecutorService;", "getExecutorService", "()Ljava/util/concurrent/ExecutorService;", "executorService$delegate", "Lkotlin/Lazy;", "sThreadFactory", "Ljava/util/concurrent/ThreadFactory;", "scheduledExecutorService", "Ljava/util/concurrent/ScheduledExecutorService;", "getScheduledExecutorService", "()Ljava/util/concurrent/ScheduledExecutorService;", "scheduledExecutorService$delegate", "uiThread", "Ljava/util/concurrent/Executor;", "executeUIThread", "", "command", "Ljava/lang/Runnable;", "getExecutor", "getScheduledExecutor", "newThreadPoolExecutor", "UIThreadExecutor", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapNetExecutor {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final long KEEP_ALIVE_TIME = 1;
    private static final int MAX_POOL_SIZE;
    private static final ThreadFactory sThreadFactory;
    public static final TapNetExecutor INSTANCE = new TapNetExecutor();

    /* JADX INFO: renamed from: executorService$delegate, reason: from kotlin metadata */
    private static final Lazy executorService = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ExecutorService>() { // from class: com.taptap.sdk.kit.internal.executor.TapNetExecutor$executorService$2
        @Override // kotlin.jvm.functions.Function0
        public final ExecutorService invoke() {
            return TapNetExecutor.newThreadPoolExecutor();
        }
    });

    /* JADX INFO: renamed from: scheduledExecutorService$delegate, reason: from kotlin metadata */
    private static final Lazy scheduledExecutorService = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ScheduledExecutorService>() { // from class: com.taptap.sdk.kit.internal.executor.TapNetExecutor$scheduledExecutorService$2
        @Override // kotlin.jvm.functions.Function0
        public final ScheduledExecutorService invoke() {
            return Executors.newScheduledThreadPool(1);
        }
    });
    private static final Executor uiThread = new UIThreadExecutor();

    private TapNetExecutor() {
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        CORE_POOL_SIZE = iAvailableProcessors + 1;
        MAX_POOL_SIZE = (iAvailableProcessors * 2) + 1;
        sThreadFactory = new ThreadFactory() { // from class: com.taptap.sdk.kit.internal.executor.TapNetExecutor$sThreadFactory$1
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable r) {
                Intrinsics.checkNotNullParameter(r, "r");
                return new Thread(r, "TapHttpAsyncTask #" + this.mCount.getAndIncrement());
            }
        };
    }

    private final ExecutorService getExecutorService() {
        return (ExecutorService) executorService.getValue();
    }

    private final ScheduledExecutorService getScheduledExecutorService() {
        Object value = scheduledExecutorService.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-scheduledExecutorService>(...)");
        return (ScheduledExecutorService) value;
    }

    @JvmStatic
    public static final ExecutorService getExecutor() {
        return INSTANCE.getExecutorService();
    }

    @JvmStatic
    public static final ScheduledExecutorService getScheduledExecutor() {
        return INSTANCE.getScheduledExecutorService();
    }

    @JvmStatic
    public static final Executor uiThread() {
        return uiThread;
    }

    @JvmStatic
    public static final void executeUIThread(Runnable command) {
        Intrinsics.checkNotNullParameter(command, "command");
        uiThread.execute(command);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final ExecutorService newThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), sThreadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* JADX INFO: compiled from: TapNetExecutor.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/executor/TapNetExecutor$UIThreadExecutor;", "Ljava/util/concurrent/Executor;", "()V", Stat.EXECUTE_KEY, "", "command", "Ljava/lang/Runnable;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class UIThreadExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            Intrinsics.checkNotNullParameter(command, "command");
            new Handler(Looper.getMainLooper()).post(command);
        }
    }
}
