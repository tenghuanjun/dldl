package cn.thinkingdata.android.utils;

import android.os.SystemClock;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class k implements e {
    private final long a;
    private final long b = SystemClock.elapsedRealtime();

    public k(long j) {
        this.a = j;
    }

    @Override // cn.thinkingdata.android.utils.e
    public Date a(long j) {
        return new Date((j - this.b) + this.a);
    }
}
