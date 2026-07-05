package com.taptap.sdk.common.oaid.exception;

import com.taptap.sdk.common.oaid.ErrorCode;
import kotlin.Metadata;

/* JADX INFO: compiled from: OAIDException.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/common/oaid/exception/OAIDException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorCode", "Lcom/taptap/sdk/common/oaid/ErrorCode;", "(Lcom/taptap/sdk/common/oaid/ErrorCode;)V", "getErrorCode", "()Lcom/taptap/sdk/common/oaid/ErrorCode;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OAIDException extends Exception {
    private final ErrorCode errorCode;

    public OAIDException(ErrorCode errorCode) {
        super("init oaid sdk fail, err code: " + errorCode);
        this.errorCode = errorCode;
    }

    public final ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
