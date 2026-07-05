package org.java_websocket.exceptions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NotSendableException extends RuntimeException {
    private static final long serialVersionUID = -6468967874576651628L;

    public NotSendableException() {
    }

    public NotSendableException(String str) {
        super(str);
    }

    public NotSendableException(Throwable th) {
        super(th);
    }

    public NotSendableException(String str, Throwable th) {
        super(str, th);
    }
}
