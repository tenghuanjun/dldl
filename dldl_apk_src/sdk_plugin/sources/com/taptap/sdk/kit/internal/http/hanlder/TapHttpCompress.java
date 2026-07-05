package com.taptap.sdk.kit.internal.http.hanlder;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapHttpCompress.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpCompress;", "", "()V", "None", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpCompress {

    /* JADX INFO: compiled from: TapHttpCompress.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpCompress$None;", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpCompress;", "()V", "handle", "", "content", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class None implements ITapHttpCompress {
        @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpCompress
        public byte[] handle(byte[] content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return content;
        }
    }
}
