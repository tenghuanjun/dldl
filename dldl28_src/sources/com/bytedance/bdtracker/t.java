package com.bytedance.bdtracker;

import com.bytedance.applog.event.AutoTrackEventType;
import com.bytedance.bdtracker.b;

/* JADX INFO: loaded from: classes2.dex */
public final class t implements b.d {
    @Override // com.bytedance.bdtracker.b.d
    public boolean a(d dVar) {
        return dVar.isBavEnabled() && dVar.getInitConfig() != null && AutoTrackEventType.a(dVar.getInitConfig().getAutoTrackEventType(), 8);
    }
}
