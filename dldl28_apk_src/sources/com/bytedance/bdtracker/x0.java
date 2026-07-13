package com.bytedance.bdtracker;

import com.bytedance.applog.ISessionObserver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class x0 implements ISessionObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CopyOnWriteArraySet<ISessionObserver> f337a = new CopyOnWriteArraySet<>();

    public void a(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f337a.add(iSessionObserver);
        }
    }

    public void b(ISessionObserver iSessionObserver) {
        if (iSessionObserver != null) {
            this.f337a.remove(iSessionObserver);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionBatchEvent(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f337a.iterator();
        while (it.hasNext()) {
            it.next().onSessionBatchEvent(j, str, jSONObject);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionStart(long j, String str) {
        Iterator<ISessionObserver> it = this.f337a.iterator();
        while (it.hasNext()) {
            it.next().onSessionStart(j, str);
        }
    }

    @Override // com.bytedance.applog.ISessionObserver
    public void onSessionTerminate(long j, String str, JSONObject jSONObject) {
        Iterator<ISessionObserver> it = this.f337a.iterator();
        while (it.hasNext()) {
            it.next().onSessionTerminate(j, str, jSONObject);
        }
    }
}
