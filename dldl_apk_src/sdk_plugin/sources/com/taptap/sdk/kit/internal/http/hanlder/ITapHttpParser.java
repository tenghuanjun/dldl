package com.taptap.sdk.kit.internal.http.hanlder;

import com.taptap.sdk.okhttp3.Response;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapHttpParser.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J&\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;", "DataBean", "", "parse", "Lkotlin/Result;", "response", "Lcom/taptap/sdk/okhttp3/Response;", "parse-IoAF18A", "(Lcom/taptap/sdk/okhttp3/Response;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ITapHttpParser<DataBean> {
    /* JADX INFO: renamed from: parse-IoAF18A, reason: not valid java name */
    Object mo39parseIoAF18A(Response response);
}
