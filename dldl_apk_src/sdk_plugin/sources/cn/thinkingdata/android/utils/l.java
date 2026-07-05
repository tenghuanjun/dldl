package cn.thinkingdata.android.utils;

import android.os.SystemClock;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class l implements e {
    private long a;
    private long b;
    private final String[] c;
    private final Thread d;

    class a implements Runnable {
        final n a = new n();

        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (String str : l.this.c) {
                if (this.a.a(str, 3000)) {
                    TDLog.i("ThinkingAnalytics.NTP", "NTP offset from " + str + " is: " + this.a.a());
                    l.this.a = System.currentTimeMillis() + this.a.a();
                    l.this.b = SystemClock.elapsedRealtime();
                    return;
                }
            }
        }
    }

    public l(String... strArr) {
        Thread thread = new Thread(new a());
        this.d = thread;
        this.c = strArr;
        thread.start();
    }

    @Override // cn.thinkingdata.android.utils.e
    public Date a(long j) {
        try {
            this.d.join(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.b == 0 ? new Date((System.currentTimeMillis() - SystemClock.elapsedRealtime()) + j) : new Date((j - this.b) + this.a);
    }
}
