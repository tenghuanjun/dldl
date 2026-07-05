package com.taptap.sdk.common.network.utils;

import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.RequestBody;
import com.taptap.sdk.okio.Buffer;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: OkHttpUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/common/network/utils/OkHttpUtils;", "", "()V", "convertBody2String", "", "Lcom/taptap/sdk/okhttp3/Request;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OkHttpUtils {
    public static final OkHttpUtils INSTANCE = new OkHttpUtils();

    private OkHttpUtils() {
    }

    public final String convertBody2String(Request request) {
        String string;
        Intrinsics.checkNotNullParameter(request, "<this>");
        Buffer buffer = new Buffer();
        try {
            Buffer buffer2 = buffer;
            RequestBody requestBodyBody = request.body();
            if (requestBodyBody != null) {
                requestBodyBody.writeTo(buffer2);
                string = buffer2.readString(Charsets.UTF_8);
            } else {
                string = null;
            }
            CloseableKt.closeFinally(buffer, null);
            return string;
        } finally {
        }
    }
}
