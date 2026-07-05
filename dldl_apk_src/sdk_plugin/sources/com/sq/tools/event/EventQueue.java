package com.sq.tools.event;

import android.content.Context;
import com.sq.tools.Logger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class EventQueue {
    EventRequest common;
    boolean isPostAvailable;
    String url;
    private final long INIT_DELAY = 1;
    private final long EVENT_POST_PERIOD = 5;
    private final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
    private final EventCollection collection = new EventCollection();
    int maxFlushNum = 100;
    final EventStorage storage = new EventStorage();
    EventPost poster = new EventPost();

    EventQueue() {
    }

    void schedulePost(final Context context) {
        this.singleThreadExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.sq.tools.event.-$$Lambda$EventQueue$FnQpbdH9OrXiqLDLuxfndiKCnoQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$schedulePost$0$EventQueue(context);
            }
        }, 1L, 5L, TimeUnit.MINUTES);
    }

    public /* synthetic */ void lambda$schedulePost$0$EventQueue(Context context) {
        Logger.info(Logger.tag(EventsTracker.TAG), "Scheduled flush Event start, post events to service if we have, otherwise do nothing", new Object[0]);
        lambda$post$3$EventQueue(context);
    }

    void add(final Context context, final FiledTools filedTools) {
        if (filedTools == null) {
            return;
        }
        this.singleThreadExecutor.execute(new Runnable() { // from class: com.sq.tools.event.-$$Lambda$EventQueue$tFKiIjEbXGUBJ2IZRKVMd7ZDaBk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$add$1$EventQueue(filedTools, context);
            }
        });
    }

    public /* synthetic */ void lambda$add$1$EventQueue(FiledTools filedTools, Context context) {
        Logger.info(Logger.tag(EventsTracker.TAG), "Add one event, current events size: %d, event content: %s", Integer.valueOf(this.collection.size()), filedTools.toString());
        this.collection.add(filedTools);
        if (context == null || this.collection.size() <= this.maxFlushNum) {
            return;
        }
        lambda$post$3$EventQueue(context);
    }

    void save(final Context context) {
        if (this.collection.isEmpty()) {
            return;
        }
        this.singleThreadExecutor.execute(new Runnable() { // from class: com.sq.tools.event.-$$Lambda$EventQueue$x5H1Ljsgi2l4pXF0_SDjEmIwsBk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$save$2$EventQueue(context);
            }
        });
    }

    public /* synthetic */ void lambda$save$2$EventQueue(Context context) {
        if (this.storage.saveEventToDisk(context, this.collection)) {
            this.collection.clear();
        }
    }

    void post(final Context context) {
        this.singleThreadExecutor.execute(new Runnable() { // from class: com.sq.tools.event.-$$Lambda$EventQueue$kwgn6klJGBZdG_TrFRlLqgIL-rI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$post$3$EventQueue(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: flushEvent, reason: merged with bridge method [inline-methods] */
    public void lambda$post$3$EventQueue(Context context) {
        if (this.isPostAvailable) {
            this.collection.add(this.storage.readAndClearStore(context));
            if (this.collection.isEmpty()) {
                return;
            }
            Logger.info(Logger.tag(EventsTracker.TAG), "start flush events...", new Object[0]);
            if (!this.poster.sendToServer(context, this.common, this.collection, this.url)) {
                this.storage.saveEventToDisk(context, this.collection);
            }
            this.collection.clear();
        }
    }
}
