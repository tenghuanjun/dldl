package android.support.v4.os;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String str) {
        super(str == null ? "The operation has been canceled." : str);
    }
}
