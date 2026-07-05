package com.sq.sywebsocket.exceptions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IncompleteException extends Exception {
    private static final long serialVersionUID = 7330519489840500997L;
    private final int preferredSize;

    public IncompleteException(int i) {
        this.preferredSize = i;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }
}
