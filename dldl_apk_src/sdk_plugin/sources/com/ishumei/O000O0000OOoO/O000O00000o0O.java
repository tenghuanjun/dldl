package com.ishumei.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O {
    private long O0000O000000oO;
    private long O000O00000OoO;
    private volatile boolean O000O00000o0O;

    private static class O0000O000000oO {
        private static final O000O00000o0O O0000O000000oO = new O000O00000o0O();
    }

    private O000O00000o0O() {
        this.O000O00000o0O = false;
    }

    public static O000O00000o0O O0000O000000oO() {
        return O0000O000000oO.O0000O000000oO;
    }

    public void O000O00000OoO() {
        if (this.O0000O000000oO == 0) {
            this.O0000O000000oO = System.currentTimeMillis();
        }
    }

    public void O000O00000o0O() {
        if (this.O000O00000OoO == 0) {
            this.O000O00000OoO = System.currentTimeMillis();
        }
    }

    public long O000O00000oO() {
        if (this.O000O00000o0O) {
            return -1L;
        }
        this.O000O00000o0O = true;
        return this.O000O00000OoO - this.O0000O000000oO;
    }
}
