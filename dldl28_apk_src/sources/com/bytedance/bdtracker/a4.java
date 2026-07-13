package com.bytedance.bdtracker;

import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;

/* JADX INFO: loaded from: classes2.dex */
public class a4 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b4 f214a;

    public a4(b4 b4Var) {
        this.f214a = b4Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f214a.f220a.size(); i++) {
            try {
                String[] strArr = {"openudid", "clientudid", "serial_number", "sim_serial_number", "udid", MonitorConstants.KEY_DEVICE_ID};
                for (int i2 = 0; i2 < 6; i2++) {
                    String str = strArr[i2];
                    try {
                        b4 b4Var = this.f214a;
                        b4Var.a(b4Var.f220a.get(i), str);
                    } catch (Exception e) {
                        LoggerImpl.global().error("DeprecatedFileCleaner execute failed", e, new Object[0]);
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
    }
}
