package com.ishumei.O000O00000o0O;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.ishumei.O000O0000OOoO.O000O00000oO;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private static O0000O000000oO O000O00000o0O;
    private Map<Long, Integer> O0000O000000oO = new HashMap();
    private SparseArray<Handler> O000O00000OoO = new SparseArray<>();
    private Handler O000O00000oO = null;
    private HandlerThread O000O0000O0oO = null;
    private HandlerThread O000O0000OOoO = null;
    private HandlerThread O000O0000Oo0O = null;
    private HandlerThread O000O0000OoO = null;
    private Handler O00O0000OooO = null;
    private Handler O00O0000o00O = null;
    private Handler O00O0000o0O = null;
    private Handler O00O0000o0OO = null;

    private O0000O000000oO() {
    }

    public static O0000O000000oO O000O00000OoO() {
        if (O000O00000o0O == null) {
            synchronized (O0000O000000oO.class) {
                if (O000O00000o0O == null) {
                    O000O00000o0O = new O0000O000000oO();
                }
            }
        }
        return O000O00000o0O;
    }

    public int O0000O000000oO() {
        return this.O0000O000000oO.get(Long.valueOf(Thread.currentThread().getId())).intValue();
    }

    public Handler O0000O000000oO(int i) {
        return this.O000O00000OoO.get(i);
    }

    public void O0000O000000oO(Runnable runnable, int i) {
        O0000O000000oO(runnable, i, false, 0L, false);
    }

    public void O0000O000000oO(Runnable runnable, int i, long j, boolean z) {
        O0000O000000oO(runnable, i, false, j, z);
    }

    public void O0000O000000oO(Runnable runnable, int i, boolean z, long j, boolean z2) {
        Handler handlerO0000O000000oO = O0000O000000oO(i);
        if (handlerO0000O000000oO == null) {
            O000O00000oO.O000O00000oO(TaskExecutor.TAG, "execute failed: known thread flag.");
            return;
        }
        if (z2) {
            handlerO0000O000000oO.removeCallbacks(runnable);
        }
        if (z) {
            handlerO0000O000000oO.postAtFrontOfQueue(runnable);
        } else {
            handlerO0000O000000oO.postDelayed(runnable, j);
        }
    }

    public void O000O00000o0O() {
        this.O000O00000oO = new Handler(Looper.getMainLooper());
        this.O000O0000O0oO = new HandlerThread("request thread");
        this.O000O0000OOoO = new HandlerThread("callback thread");
        this.O000O0000Oo0O = new HandlerThread("uploadChecker thread");
        this.O000O0000OoO = new HandlerThread("sensor thread");
        this.O000O0000O0oO.start();
        this.O000O0000OOoO.start();
        this.O000O0000Oo0O.start();
        this.O000O0000OoO.start();
        this.O00O0000OooO = new Handler(this.O000O0000O0oO.getLooper());
        this.O00O0000o00O = new Handler(this.O000O0000OOoO.getLooper());
        this.O00O0000o0O = new Handler(this.O000O0000Oo0O.getLooper());
        this.O00O0000o0OO = new Handler(this.O000O0000OoO.getLooper());
        this.O0000O000000oO.put(Long.valueOf(this.O000O00000oO.getLooper().getThread().getId()), 3);
        this.O0000O000000oO.put(Long.valueOf(this.O00O0000OooO.getLooper().getThread().getId()), 1);
        this.O0000O000000oO.put(Long.valueOf(this.O00O0000o00O.getLooper().getThread().getId()), 2);
        this.O0000O000000oO.put(Long.valueOf(this.O00O0000o0O.getLooper().getThread().getId()), 4);
        this.O0000O000000oO.put(Long.valueOf(this.O00O0000o0OO.getLooper().getThread().getId()), 5);
        this.O000O00000OoO.put(3, this.O000O00000oO);
        this.O000O00000OoO.put(1, this.O00O0000OooO);
        this.O000O00000OoO.put(2, this.O00O0000o00O);
        this.O000O00000OoO.put(4, this.O00O0000o0O);
        this.O000O00000OoO.put(5, this.O00O0000o0O);
    }
}
