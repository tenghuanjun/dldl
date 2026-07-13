package a.a.a.c.n;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: Async.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Executor f96a;

    public static void a(Runnable runnable) {
        if (f96a == null) {
            synchronized (a.class) {
                if (f96a == null) {
                    f96a = Executors.newSingleThreadExecutor();
                }
            }
        }
        f96a.execute(runnable);
    }
}
