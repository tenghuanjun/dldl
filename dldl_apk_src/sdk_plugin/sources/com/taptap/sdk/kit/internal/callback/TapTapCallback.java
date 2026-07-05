package com.taptap.sdk.kit.internal.callback;

import com.taptap.sdk.kit.internal.exception.TapTapException;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapTapCallback.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/kit/internal/callback/TapTapCallback;", "RESULT", "", "onCancel", "", "onFail", "exception", "Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "onSuccess", "result", "(Ljava/lang/Object;)V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface TapTapCallback<RESULT> {

    /* JADX INFO: compiled from: TapTapCallback.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static <RESULT> void onCancel(TapTapCallback<RESULT> tapTapCallback) {
        }
    }

    void onCancel();

    void onFail(TapTapException exception);

    void onSuccess(RESULT result);
}
