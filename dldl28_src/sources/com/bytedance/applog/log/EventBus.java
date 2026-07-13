package com.bytedance.applog.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class EventBus extends Thread implements Handler.Callback {
    private static final int EMIT_EVENT_MSG = 1;
    public static AbsSingleton<EventBus> global = new AbsSingleton<EventBus>() { // from class: com.bytedance.applog.log.EventBus.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bytedance.applog.log.AbsSingleton
        public EventBus create(Object... objArr) {
            return new EventBus();
        }
    };
    private Handler eventHandler;
    private final Map<String, List<Subscription>> subscriptionMap = new ConcurrentHashMap();

    public interface DataFetcher {
        Object fetch();
    }

    public interface Subscription {
        void sub(Object obj);
    }

    public EventBus() {
        start();
    }

    private static class MessageEvent {
        Object data;
        String event;

        MessageEvent(String str, Object obj) {
            this.event = str;
            this.data = obj;
        }
    }

    public void emit(String str, Object obj) {
        if (TextUtils.isEmpty(str) || !this.subscriptionMap.containsKey(str)) {
            return;
        }
        emit(new MessageEvent(str, obj));
    }

    public void emit(String str, DataFetcher dataFetcher) {
        if (TextUtils.isEmpty(str) || !this.subscriptionMap.containsKey(str) || dataFetcher == null) {
            return;
        }
        emit(new MessageEvent(str, dataFetcher.fetch()));
    }

    public synchronized void listen(String str, Subscription subscription) {
        List<Subscription> arrayList = this.subscriptionMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(subscription);
        this.subscriptionMap.put(str, arrayList);
    }

    public synchronized void remove(String str, Subscription subscription) {
        List<Subscription> list = this.subscriptionMap.get(str);
        if (list != null && list.contains(subscription)) {
            list.remove(subscription);
            if (list.size() == 0) {
                this.subscriptionMap.remove(str);
            } else {
                this.subscriptionMap.put(str, list);
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        Looper.prepare();
        this.eventHandler = new Handler(this);
        Looper.loop();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            emitEvent((MessageEvent) message.obj);
        }
        return true;
    }

    private void emit(MessageEvent messageEvent) {
        Handler handler = this.eventHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1, messageEvent));
        } else {
            emitEvent(messageEvent);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007e A[Catch: all -> 0x00b3, TRY_LEAVE, TryCatch #0 {all -> 0x00b3, blocks: (B:6:0x0012, B:9:0x0020, B:11:0x0026, B:13:0x0030, B:15:0x003c, B:26:0x0074, B:27:0x0078, B:29:0x007e, B:31:0x008a, B:33:0x009b, B:35:0x00a0, B:36:0x00a3, B:39:0x00ad, B:38:0x00a7, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:22:0x005c, B:24:0x0068), top: B:44:0x0012, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void emitEvent(com.bytedance.applog.log.EventBus.MessageEvent r9) {
        /*
            r8 = this;
            java.util.Map<java.lang.String, java.util.List<com.bytedance.applog.log.EventBus$Subscription>> r0 = r8.subscriptionMap
            java.lang.String r1 = r9.event
            java.lang.Object r0 = r0.get(r1)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto Ld
            return
        Ld:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.lang.String r2 = "applog_event_upload_eid"
            java.lang.String r3 = r9.event     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r3 = "responseByte"
            java.lang.String r4 = "$$EVENT_LOCAL_IDS"
            if (r2 == 0) goto L42
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2 instanceof org.json.JSONObject     // Catch: java.lang.Throwable -> Lb3
            if (r2 == 0) goto L42
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2.has(r4)     // Catch: java.lang.Throwable -> Lb3
            if (r2 == 0) goto L42
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: java.lang.Throwable -> Lb3
            java.lang.Object r2 = r2.opt(r4)     // Catch: java.lang.Throwable -> Lb3
            boolean r5 = r2 instanceof java.util.Collection     // Catch: java.lang.Throwable -> Lb3
            if (r5 == 0) goto L73
            java.util.Collection r2 = (java.util.Collection) r2     // Catch: java.lang.Throwable -> Lb3
            r1.addAll(r2)     // Catch: java.lang.Throwable -> Lb3
            goto L73
        L42:
            java.lang.String r2 = "applog_do_request_end"
            java.lang.String r5 = r9.event     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2.equals(r5)     // Catch: java.lang.Throwable -> Lb3
            if (r2 == 0) goto L73
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2 instanceof org.json.JSONObject     // Catch: java.lang.Throwable -> Lb3
            if (r2 == 0) goto L73
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = r2.has(r3)     // Catch: java.lang.Throwable -> Lb3
            if (r2 == 0) goto L73
            java.lang.Object r2 = r9.data     // Catch: java.lang.Throwable -> Lb3
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: java.lang.Throwable -> Lb3
            java.lang.Object r2 = r2.opt(r3)     // Catch: java.lang.Throwable -> Lb3
            boolean r5 = r2 instanceof byte[]     // Catch: java.lang.Throwable -> Lb3
            if (r5 == 0) goto L73
            byte[] r2 = (byte[]) r2     // Catch: java.lang.Throwable -> Lb3
            byte[] r2 = (byte[]) r2     // Catch: java.lang.Throwable -> Lb3
            java.lang.Object r2 = r2.clone()     // Catch: java.lang.Throwable -> Lb3
            byte[] r2 = (byte[]) r2     // Catch: java.lang.Throwable -> Lb3
            goto L74
        L73:
            r2 = 0
        L74:
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> Lb3
        L78:
            boolean r5 = r0.hasNext()     // Catch: java.lang.Throwable -> Lb3
            if (r5 == 0) goto Lb7
            java.lang.Object r5 = r0.next()     // Catch: java.lang.Throwable -> Lb3
            com.bytedance.applog.log.EventBus$Subscription r5 = (com.bytedance.applog.log.EventBus.Subscription) r5     // Catch: java.lang.Throwable -> Lb3
            java.lang.Object r6 = r9.data     // Catch: java.lang.Throwable -> Lb3
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch: java.lang.Throwable -> Lb3
            if (r6 == 0) goto Lad
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            java.lang.Object r7 = r9.data     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            java.lang.String r7 = r7.toString()     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            r6.<init>(r7)     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            boolean r7 = r1.isEmpty()     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            if (r7 != 0) goto L9e
            r6.put(r4, r1)     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
        L9e:
            if (r2 == 0) goto La3
            r6.put(r3, r2)     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
        La3:
            r5.sub(r6)     // Catch: org.json.JSONException -> La7 java.lang.Throwable -> Lb3
            goto L78
        La7:
            java.lang.Object r6 = r9.data     // Catch: java.lang.Throwable -> Lb3
            r5.sub(r6)     // Catch: java.lang.Throwable -> Lb3
            goto L78
        Lad:
            java.lang.Object r6 = r9.data     // Catch: java.lang.Throwable -> Lb3
            r5.sub(r6)     // Catch: java.lang.Throwable -> Lb3
            goto L78
        Lb3:
            r9 = move-exception
            r9.printStackTrace()
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.log.EventBus.emitEvent(com.bytedance.applog.log.EventBus$MessageEvent):void");
    }
}
