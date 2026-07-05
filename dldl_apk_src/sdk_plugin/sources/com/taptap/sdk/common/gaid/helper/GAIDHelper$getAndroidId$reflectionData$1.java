package com.taptap.sdk.common.gaid.helper;

import com.taptap.sdk.common.gaid.data.model.GAID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GAIDHelper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$reflectionData$1", f = "GAIDHelper.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {})
final class GAIDHelper$getAndroidId$reflectionData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GAID>, Object> {
    int label;
    final /* synthetic */ GAIDHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GAIDHelper$getAndroidId$reflectionData$1(GAIDHelper gAIDHelper, Continuation<? super GAIDHelper$getAndroidId$reflectionData$1> continuation) {
        super(2, continuation);
        this.this$0 = gAIDHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GAIDHelper$getAndroidId$reflectionData$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super GAID> continuation) {
        return ((GAIDHelper$getAndroidId$reflectionData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.reflectionGAIDProvider.provideGAID(this.this$0.context, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
