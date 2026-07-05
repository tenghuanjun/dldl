package master.flame.danmaku.danmaku.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SystemClock {
    public static final long uptimeMillis() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public static final void sleep(long j) {
        android.os.SystemClock.sleep(j);
    }
}
