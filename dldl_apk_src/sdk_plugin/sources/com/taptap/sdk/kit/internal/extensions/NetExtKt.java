package com.taptap.sdk.kit.internal.extensions;

import com.taptap.sdk.kit.internal.http.TapErrorConstants;
import com.taptap.sdk.kit.internal.http.TapHttpException;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0006"}, d2 = {"isBusinessError", "", "", "isNoHostError", "isServerError", "isTokenExpired", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class NetExtKt {
    public static final boolean isServerError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th instanceof TapHttpException.ServerError) {
            int httpCode = ((TapHttpException.ServerError) th).getHttpCode();
            if (500 <= httpCode && httpCode < 600) {
                return true;
            }
        }
        if (th instanceof TapHttpException.NoServerError) {
            int httpCode2 = ((TapHttpException.NoServerError) th).getHttpCode();
            if (500 <= httpCode2 && httpCode2 < 600) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isNoHostError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        return th instanceof UnknownHostException;
    }

    public static final boolean isTokenExpired(Throwable th) {
        Integer code;
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th instanceof TapHttpException.ServerError) {
            TapHttpException.ServerError serverError = (TapHttpException.ServerError) th;
            if (Intrinsics.areEqual(serverError.getError().getError(), TapErrorConstants.ERROR_BUSINESS_ERROR) && (code = serverError.getError().getCode()) != null && code.intValue() == 200000) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isBusinessError(Throwable th) {
        Integer code;
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th instanceof TapHttpException.ServerError) {
            TapHttpException.ServerError serverError = (TapHttpException.ServerError) th;
            if (Intrinsics.areEqual(serverError.getError().getError(), TapErrorConstants.ERROR_BUSINESS_ERROR) && (code = serverError.getError().getCode()) != null && code.intValue() == 200001) {
                return true;
            }
        }
        return false;
    }
}
