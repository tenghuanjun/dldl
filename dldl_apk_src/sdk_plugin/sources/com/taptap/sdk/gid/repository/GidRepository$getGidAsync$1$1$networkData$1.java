package com.taptap.sdk.gid.repository;

import com.sqwan.msdk.api.IMUrl;
import com.taptap.sdk.gid.data.response.Gid;
import com.taptap.sdk.gid.service.network.GidNetworkService;
import com.taptap.sdk.themis.lite.data.model.OneID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: GidRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "Lcom/taptap/sdk/gid/data/response/Gid;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.gid.repository.GidRepository$getGidAsync$1$1$networkData$1", f = "GidRepository.kt", i = {}, l = {102, 103}, m = "invokeSuspend", n = {}, s = {})
final class GidRepository$getGidAsync$1$1$networkData$1 extends SuspendLambda implements Function1<Continuation<? super Gid>, Object> {
    int label;
    final /* synthetic */ GidRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GidRepository$getGidAsync$1$1$networkData$1(GidRepository gidRepository, Continuation<? super GidRepository$getGidAsync$1$1$networkData$1> continuation) {
        super(1, continuation);
        this.this$0 = gidRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new GidRepository$getGidAsync$1$1$networkData$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Gid> continuation) {
        return ((GidRepository$getGidAsync$1$1$networkData$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.themisLiteManager.getOneID(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        GidNetworkService gidNetworkService = this.this$0.gidNetworkService;
        this.label = 2;
        obj = gidNetworkService.getGid(IMUrl.OS, (OneID) obj, this);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }
}
