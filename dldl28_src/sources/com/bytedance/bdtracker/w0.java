package com.bytedance.bdtracker;

import com.bytedance.applog.IEventObserver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class w0 implements IEventObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CopyOnWriteArraySet<IEventObserver> f335a = new CopyOnWriteArraySet<>();

    public void a(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f335a.add(iEventObserver);
        }
    }

    public void b(IEventObserver iEventObserver) {
        if (iEventObserver != null) {
            this.f335a.remove(iEventObserver);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEvent(String str, String str2, String str3, long j, long j2, String str4) {
        Iterator<IEventObserver> it = this.f335a.iterator();
        while (it.hasNext()) {
            it.next().onEvent(str, str2, str3, j, j2, str4);
        }
    }

    @Override // com.bytedance.applog.IEventObserver
    public void onEventV3(String str, JSONObject jSONObject) {
        Iterator<IEventObserver> it = this.f335a.iterator();
        while (it.hasNext()) {
            it.next().onEventV3(str, jSONObject);
        }
    }
}
