package com.taptap.sdk.kit.internal.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapTapException.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "", "message", "", "(Ljava/lang/String;)V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public class TapTapException extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TapTapException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
