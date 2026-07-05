package com.taptap.sdk.common.oaid.helper;

import com.taptap.sdk.common.oaid.cert.OAIDCertProvider;
import com.taptap.sdk.common.oaid.strategy.OAIDStrategy;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: OAIDHelper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.common.oaid.helper.OAIDHelper$tryGetOaidByReflect$2$1$1", f = "OAIDHelper.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
final class OAIDHelper$tryGetOaidByReflect$2$1$1 extends SuspendLambda implements Function1<Continuation<? super String>, Object> {
    final /* synthetic */ OAIDCertProvider $certProvider;
    final /* synthetic */ OAIDStrategy $value;
    int label;
    final /* synthetic */ OAIDHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OAIDHelper$tryGetOaidByReflect$2$1$1(OAIDStrategy oAIDStrategy, OAIDHelper oAIDHelper, OAIDCertProvider oAIDCertProvider, Continuation<? super OAIDHelper$tryGetOaidByReflect$2$1$1> continuation) {
        super(1, continuation);
        this.$value = oAIDStrategy;
        this.this$0 = oAIDHelper;
        this.$certProvider = oAIDCertProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new OAIDHelper$tryGetOaidByReflect$2$1$1(this.$value, this.this$0, this.$certProvider, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super String> continuation) {
        return ((OAIDHelper$tryGetOaidByReflect$2$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.common.oaid.helper.OAIDHelper$tryGetOaidByReflect$2$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: OAIDHelper.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.oaid.helper.OAIDHelper$tryGetOaidByReflect$2$1$1$1", f = "OAIDHelper.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ OAIDCertProvider $certProvider;
        final /* synthetic */ OAIDStrategy $value;
        int label;
        final /* synthetic */ OAIDHelper this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(OAIDStrategy oAIDStrategy, OAIDHelper oAIDHelper, OAIDCertProvider oAIDCertProvider, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$value = oAIDStrategy;
            this.this$0 = oAIDHelper;
            this.$certProvider = oAIDCertProvider;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$value, this.this$0, this.$certProvider, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.$value.tryGetOAIDByReflect(this.this$0.context, this.$certProvider.provideOAIDCert(), this);
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(OAIDHelper.TIMEOUT, new AnonymousClass1(this.$value, this.this$0, this.$certProvider, null), this);
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
