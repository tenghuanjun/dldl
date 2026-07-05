package com.duowan.networkmars.push;

import com.duowan.auk.util.L;
import com.duowan.networkmars.dispatch.DispatcherContainer;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.duowan.networkmars.hysignal.ServicePushObserver;
import com.huya.live.common.api.BaseApi;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TransmitService implements IPushService, ServicePushObserver {
    private static final String TAG = "TransmitService";
    private AtomicInteger at;
    private ExecutorService exec;
    private DispatcherContainer<IPushWatcher, Integer> mCastDispatcher;
    private long mLastTime;
    private int mMsgTotalCount;
    private HashMap<Integer, Integer> mStatitis;

    private static class TransmitServiceHolder {
        private static final TransmitService INSTANCE = new TransmitService();

        private TransmitServiceHolder() {
        }
    }

    private TransmitService() {
        this.mCastDispatcher = null;
        this.exec = null;
        this.at = new AtomicInteger(0);
        this.mLastTime = 0L;
        this.mStatitis = new HashMap<>();
        this.mMsgTotalCount = 0;
    }

    public static TransmitService getInstance() {
        return TransmitServiceHolder.INSTANCE;
    }

    public TransmitService init(int i, int i2) {
        init(i, i, 0L, TimeUnit.MILLISECONDS, i2);
        return this;
    }

    public TransmitService init(int i, int i2, long j, TimeUnit timeUnit, int i3) {
        if (this.exec == null) {
            this.exec = new ThreadPoolExecutor(i, i2, j, timeUnit, new LinkedBlockingDeque(i3), new ThreadFactory() { // from class: com.duowan.networkmars.push.TransmitService.1
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("TransmitService:thread-" + UUID.randomUUID().toString());
                    return thread;
                }
            }, new RejectedExecutionHandler() { // from class: com.duowan.networkmars.push.TransmitService.2
                @Override // java.util.concurrent.RejectedExecutionHandler
                public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                    if (threadPoolExecutor.isShutdown()) {
                        return;
                    }
                    try {
                        System.out.println("waiting queue is full, putting... " + TransmitService.this.at.getAndIncrement());
                        threadPoolExecutor.getQueue().put(runnable);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (this.mCastDispatcher == null) {
            this.mCastDispatcher = new DispatcherContainer<>();
        }
        return this;
    }

    private void destroy() {
        ExecutorService executorService = this.exec;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public void start() {
        if (this.exec == null || this.mCastDispatcher == null) {
            BaseApi.crashIfDebug("PushService should call init before start", new Object[0]);
        }
        HySignalProxy.getInstance().registerPushEvent(this);
    }

    public void stop() {
        HySignalProxy.getInstance().unregisterPushEvent(this);
        destroy();
    }

    @Override // com.duowan.networkmars.push.IPushService
    public void regCastProto(IPushWatcher iPushWatcher, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = iPushWatcher == null ? AbstractJsonLexerKt.NULL : iPushWatcher.toString();
        objArr[1] = Integer.valueOf(i);
        L.info(TAG, "regCastProto watcher %s msgtype = %d", objArr);
        DispatcherContainer<IPushWatcher, Integer> dispatcherContainer = this.mCastDispatcher;
        if (dispatcherContainer == null) {
            return;
        }
        dispatcherContainer.subscribe(iPushWatcher, Integer.valueOf(i));
    }

    @Override // com.duowan.networkmars.push.IPushService
    public void unRegCastProto(IPushWatcher iPushWatcher, int i) {
        DispatcherContainer<IPushWatcher, Integer> dispatcherContainer = this.mCastDispatcher;
        if (dispatcherContainer == null) {
            return;
        }
        dispatcherContainer.unSubscribe(iPushWatcher, Integer.valueOf(i));
    }

    @Override // com.duowan.networkmars.hysignal.ServicePushObserver
    public void onReceiveEvent(final int i, final byte[] bArr) {
        addMsgCounterLog(i);
        DispatcherContainer<IPushWatcher, Integer> dispatcherContainer = this.mCastDispatcher;
        if (dispatcherContainer == null) {
            return;
        }
        List<IPushWatcher> dispatchers = dispatcherContainer.getDispatchers(Integer.valueOf(i));
        if (dispatchers == null) {
            L.debug(TAG, "onCastPush drop data, because of unSubscribe, uri %d", Integer.valueOf(i));
            return;
        }
        for (final IPushWatcher iPushWatcher : dispatchers) {
            this.exec.submit(new Runnable() { // from class: com.duowan.networkmars.push.TransmitService.3
                @Override // java.lang.Runnable
                public void run() {
                    iPushWatcher.onCastPush(i, bArr);
                }
            });
        }
    }

    private void addMsgCounterLog(int i) {
        this.mStatitis.put(Integer.valueOf(i), Integer.valueOf((this.mStatitis.containsKey(Integer.valueOf(i)) ? this.mStatitis.get(Integer.valueOf(i)).intValue() : 0) + 1));
        this.mMsgTotalCount++;
        if (System.currentTimeMillis() - this.mLastTime > 10000) {
            L.info(TAG, "castpush  mars: %d, %d,  event: %d, %s", Long.valueOf(HySignalProxy.getInstance().getMessageCount()), Long.valueOf(HySignalProxy.getInstance().getMessageByteCount()), Integer.valueOf(this.mMsgTotalCount), this.mStatitis.toString());
            this.mStatitis.clear();
            this.mMsgTotalCount = 0;
            this.mLastTime = System.currentTimeMillis();
        }
    }
}
