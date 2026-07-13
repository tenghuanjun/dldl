package com.bytedance.applog.collector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.b;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public class Collector extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra("K_DATA");
        if (stringArrayExtra == null || stringArrayExtra.length <= 0) {
            LoggerImpl.global().error(Collections.singletonList("Collector"), "Event is null", new Object[0]);
        } else {
            b.a(stringArrayExtra);
        }
    }
}
