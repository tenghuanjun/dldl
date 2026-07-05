package com.duowan.ark.thread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class PriorityRunnable implements Runnable {
    private String mKey;

    protected String getKey() {
        return this.mKey;
    }

    public PriorityRunnable(String str) {
        this.mKey = str;
    }
}
