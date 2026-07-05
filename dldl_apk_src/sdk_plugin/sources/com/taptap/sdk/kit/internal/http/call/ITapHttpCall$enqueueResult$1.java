package com.taptap.sdk.kit.internal.http.call;

import com.taptap.sdk.kit.internal.http.call.ITapHttpCall;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: ITapHttpCall.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.kit.internal.http.call.ITapHttpCall$DefaultImpls", f = "ITapHttpCall.kt", i = {}, l = {19}, m = "enqueueResult-gIAlu-s", n = {}, s = {})
final class ITapHttpCall$enqueueResult$1<DataBean> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    ITapHttpCall$enqueueResult$1(Continuation<? super ITapHttpCall$enqueueResult$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM35enqueueResultgIAlus = ITapHttpCall.DefaultImpls.m35enqueueResultgIAlus(null, 0L, this);
        return objM35enqueueResultgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM35enqueueResultgIAlus : Result.m51boximpl(objM35enqueueResultgIAlus);
    }
}
