package cn.thinkingdata.android.utils;

import cn.thinkingdata.android.TDConfig;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class b {
    private static e b;
    private static final ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private final TDConfig a;

    public b(TDConfig tDConfig) {
        this.a = tDConfig;
    }

    public static void a(long j) {
        a(new k(j));
    }

    private static void a(e eVar) {
        c.writeLock().lock();
        b = eVar;
        c.writeLock().unlock();
    }

    public static void a(String... strArr) {
        if (strArr == null) {
            return;
        }
        a(new l(strArr));
    }

    public static e b() {
        return b;
    }

    public f a() {
        c.readLock().lock();
        e eVar = b;
        f pVar = eVar != null ? new p(eVar, this.a.getDefaultTimeZone()) : new o(new Date(), this.a.getDefaultTimeZone());
        c.readLock().unlock();
        return pVar;
    }

    public f a(Date date, TimeZone timeZone) {
        if (timeZone != null) {
            return new o(date, timeZone);
        }
        o oVar = new o(date, this.a.getDefaultTimeZone());
        oVar.c();
        return oVar;
    }
}
