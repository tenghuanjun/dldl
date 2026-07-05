package com.taptap.sdk.initializer.repository;

import com.sqwan.msdk.api.IMUrl;
import com.taptap.sdk.initializer.data.request.GateKeeperRequest;
import com.taptap.sdk.initializer.data.response.GateKeeper;
import com.taptap.sdk.initializer.repository.remote.GatekeeperApiService;
import com.taptap.sdk.kit.internal.utils.TapSignKit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GatekeeperRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.initializer.repository.GatekeeperRepository$initialize$1$networkData$1", f = "GatekeeperRepository.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
final class GatekeeperRepository$initialize$1$networkData$1 extends SuspendLambda implements Function1<Continuation<? super GateKeeper>, Object> {
    int label;
    final /* synthetic */ GatekeeperRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GatekeeperRepository$initialize$1$networkData$1(GatekeeperRepository gatekeeperRepository, Continuation<? super GatekeeperRepository$initialize$1$networkData$1> continuation) {
        super(1, continuation);
        this.this$0 = gatekeeperRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new GatekeeperRepository$initialize$1$networkData$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super GateKeeper> continuation) {
        return ((GatekeeperRepository$initialize$1$networkData$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            GatekeeperApiService gatekeeperApiService = this.this$0.apiService;
            String packageName = this.this$0.context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            this.label = 1;
            obj = gatekeeperApiService.fetchGatekeeper(new GateKeeperRequest(IMUrl.OS, packageName, TapSignKit.INSTANCE.getSign(this.this$0.context)), this);
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
