package com.taptap.sdk.kit.internal.http.hanlder;

import com.taptap.sdk.kit.internal.http.TapHttpException;
import com.taptap.sdk.okhttp3.Response;
import com.taptap.sdk.okhttp3.ResponseBody;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapHttpParser2.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/SdkDefaultParser2;", "DataBean", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;", "()V", "parse", "Lkotlin/Result;", "response", "Lcom/taptap/sdk/okhttp3/Response;", "parse-IoAF18A", "(Lcom/taptap/sdk/okhttp3/Response;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SdkDefaultParser2<DataBean> implements ITapHttpParser<DataBean> {
    @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpParser
    /* JADX INFO: renamed from: parse-IoAF18A */
    public Object mo39parseIoAF18A(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        int iCode = response.code();
        ResponseBody responseBodyBody = response.body();
        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
        if (response.isSuccessful()) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m52constructorimpl(strString);
        }
        Result.Companion companion2 = Result.INSTANCE;
        return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.NoServerError(iCode, strString)));
    }
}
