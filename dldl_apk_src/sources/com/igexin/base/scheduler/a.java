package com.igexin.base.scheduler;

import android.text.TextUtils;
import com.igexin.base.scheduler.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements b.InterfaceC0057b, c {
    private static final int a = Runtime.getRuntime().availableProcessors() * 2;
    private Map<BaseTask, ScheduledFuture> c = new ConcurrentHashMap();
    private final Set<String> d = new HashSet();
    private final Map<String, LinkedBlockingQueue<BaseTask>> e = new HashMap();
    private b b = new b(a);

    public a() {
        this.b.a = this;
    }

    private ScheduledFuture b(BaseTask baseTask) {
        ScheduledFuture<?> scheduledFutureSchedule;
        ScheduledFuture scheduledFuture = this.c.get(baseTask);
        if (scheduledFuture != null) {
            if (scheduledFuture.cancel(false)) {
                this.c.remove(baseTask);
            }
            BlockingQueue<Runnable> queue = this.b.getQueue();
            if (queue != null) {
                queue.remove(scheduledFuture);
            }
        }
        long initDelay = baseTask.getInitDelay();
        if (baseTask.isPeriodic()) {
            long period = baseTask.getPeriod();
            scheduledFutureSchedule = period > 0 ? this.b.scheduleAtFixedRate(baseTask, initDelay, period, TimeUnit.MILLISECONDS) : this.b.scheduleWithFixedDelay(baseTask, initDelay, -period, TimeUnit.MILLISECONDS);
        } else {
            scheduledFutureSchedule = this.b.schedule(baseTask, initDelay, TimeUnit.MILLISECONDS);
        }
        this.c.put(baseTask, scheduledFutureSchedule);
        return scheduledFutureSchedule;
    }

    @Override // com.igexin.base.scheduler.b.InterfaceC0057b
    public final synchronized void a(BaseTask baseTask) {
        try {
            this.c.remove(baseTask);
            String groupName = baseTask.getGroupName();
            if (!TextUtils.isEmpty(groupName)) {
                LinkedBlockingQueue<BaseTask> linkedBlockingQueue = this.e.get(groupName);
                if (linkedBlockingQueue != null && linkedBlockingQueue.size() > 0) {
                    b(linkedBlockingQueue.poll());
                    return;
                }
                this.d.remove(groupName);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.igexin.base.scheduler.c
    public final void execute(BaseTask baseTask) {
        try {
            baseTask.onRunTask();
            baseTask.done();
        } catch (Throwable th) {
            baseTask.onException(th);
        }
    }

    @Override // com.igexin.base.scheduler.c
    public final synchronized void submit(BaseTask baseTask) {
        try {
            String groupName = baseTask.getGroupName();
            boolean zOffer = false;
            if (!TextUtils.isEmpty(groupName)) {
                if (this.d.contains(groupName)) {
                    if (this.e.get(groupName) == null) {
                        this.e.put(groupName, new LinkedBlockingQueue<>());
                    }
                    zOffer = this.e.get(groupName).offer(baseTask);
                } else {
                    this.d.add(groupName);
                }
            }
            if (zOffer) {
                return;
            }
            b(baseTask);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
