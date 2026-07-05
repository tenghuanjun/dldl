package com.ishumei.O000O00000o0O;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class O000O00000OoO<T> implements Runnable {
    public int O000O00000o0O;
    public boolean O000O00000oO;
    public boolean O000O0000O0oO;
    public long O000O0000OOoO;
    public boolean O000O0000Oo0O;
    public T O000O0000OoO = null;

    public O000O00000OoO(boolean z, int i) {
        this.O000O00000o0O = -1;
        this.O000O00000oO = false;
        this.O000O0000O0oO = false;
        this.O000O0000OOoO = 0L;
        this.O000O0000Oo0O = false;
        this.O000O00000oO = z;
        this.O000O00000o0O = i;
        this.O000O0000O0oO = false;
        this.O000O0000OOoO = 0L;
        this.O000O0000Oo0O = false;
    }

    public O000O00000OoO(boolean z, int i, boolean z2, long j, boolean z3) {
        this.O000O00000o0O = -1;
        this.O000O00000oO = false;
        this.O000O0000O0oO = false;
        this.O000O0000OOoO = 0L;
        this.O000O0000Oo0O = false;
        this.O000O00000oO = z;
        this.O000O00000o0O = i;
        this.O000O0000O0oO = z2;
        this.O000O0000OOoO = j;
        this.O000O0000Oo0O = z3;
    }

    public final void O0000O000000oO() {
        if (this.O000O00000oO) {
            O0000O000000oO.O000O00000OoO().O0000O000000oO(this, this.O000O00000o0O, this.O000O0000O0oO, this.O000O0000OOoO, this.O000O0000Oo0O);
        } else {
            run();
        }
    }

    public final void O0000O000000oO(T t) {
        this.O000O0000OoO = t;
    }
}
