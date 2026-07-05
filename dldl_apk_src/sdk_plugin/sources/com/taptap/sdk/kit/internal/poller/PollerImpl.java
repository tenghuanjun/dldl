package com.taptap.sdk.kit.internal.poller;

import com.taptap.sdk.kit.internal.poller.PollerState;
import com.taptap.sdk.kit.internal.poller.strategy.IPollerStrategy;
import com.taptap.sdk.kit.internal.poller.strategy.InfiniteStrategy;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: PollerImpl.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0016\u001a\u00020\u000fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J.\u0010\u0017\u001a\u00020\u00112\u001c\u0010\u0018\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerImpl;", "T", "Lcom/taptap/sdk/kit/internal/poller/Poller;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pollInterval", "", "pollStrategy", "Lcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;", "(Lkotlinx/coroutines/CoroutineScope;JLcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;)V", "_pollerStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/taptap/sdk/kit/internal/poller/PollerState;", "elapsedPollTime", "isPolling", "", "pollerJob", "Lkotlinx/coroutines/Job;", "pollerStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getPollerStateFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "canPoll", "startPoll", "pollBlock", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "stopPoll", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PollerImpl<T> implements Poller<T> {
    private final MutableStateFlow<PollerState<T>> _pollerStateFlow;
    private final CoroutineScope coroutineScope;
    private long elapsedPollTime;
    private boolean isPolling;
    private final long pollInterval;
    private final IPollerStrategy pollStrategy;
    private Job pollerJob;

    public PollerImpl(CoroutineScope coroutineScope, long j, IPollerStrategy pollStrategy) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(pollStrategy, "pollStrategy");
        this.coroutineScope = coroutineScope;
        this.pollInterval = j;
        this.pollStrategy = pollStrategy;
        this._pollerStateFlow = StateFlowKt.MutableStateFlow(PollerState.Initial.INSTANCE);
    }

    public /* synthetic */ PollerImpl(CoroutineScope coroutineScope, long j, InfiniteStrategy infiniteStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, (i & 2) != 0 ? 300L : j, (i & 4) != 0 ? new InfiniteStrategy() : infiniteStrategy);
    }

    @Override // com.taptap.sdk.kit.internal.poller.Poller
    public StateFlow<PollerState<T>> getPollerStateFlow() {
        return this._pollerStateFlow;
    }

    @Override // com.taptap.sdk.kit.internal.poller.Poller
    public Job startPoll(Function1<? super Continuation<? super T>, ? extends Object> pollBlock) {
        Intrinsics.checkNotNullParameter(pollBlock, "pollBlock");
        this.isPolling = true;
        this.elapsedPollTime = 0L;
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(this, pollBlock, null), 3, null);
        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>(this) { // from class: com.taptap.sdk.kit.internal.poller.PollerImpl$startPoll$2$1
            final /* synthetic */ PollerImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                ((PollerImpl) this.this$0)._pollerStateFlow.setValue(th instanceof CancellationException ? (PollerState) PollerState.Cancelled.INSTANCE : (PollerState) PollerState.Completed.INSTANCE);
                this.this$0.stopPoll();
            }
        });
        this.pollerJob = jobLaunch$default;
        return jobLaunch$default;
    }

    /* JADX INFO: renamed from: com.taptap.sdk.kit.internal.poller.PollerImpl$startPoll$1, reason: invalid class name */
    /* JADX INFO: compiled from: PollerImpl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.kit.internal.poller.PollerImpl$startPoll$1", f = "PollerImpl.kt", i = {0, 1}, l = {33, 37}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super T>, Object> $pollBlock;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PollerImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(PollerImpl<T> pollerImpl, Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = pollerImpl;
            this.$pollBlock = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$pollBlock, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0080 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0081 -> B:12:0x0031). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L29
                if (r1 == r3) goto L1f
                if (r1 != r2) goto L17
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                r11 = r1
                goto L30
            L17:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1f:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                r4 = r1
                r1 = r10
                goto L4f
            L29:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            L30:
                r1 = r10
            L31:
                boolean r4 = kotlinx.coroutines.CoroutineScopeKt.isActive(r11)
                if (r4 == 0) goto L83
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r4 = r1.this$0
                boolean r4 = r4.canPoll()
                if (r4 == 0) goto L83
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super T>, java.lang.Object> r4 = r1.$pollBlock
                r1.L$0 = r11
                r1.label = r3
                java.lang.Object r4 = r4.invoke(r1)
                if (r4 != r0) goto L4c
                return r0
            L4c:
                r9 = r4
                r4 = r11
                r11 = r9
            L4f:
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r5 = r1.this$0
                kotlinx.coroutines.flow.MutableStateFlow r5 = com.taptap.sdk.kit.internal.poller.PollerImpl.access$get_pollerStateFlow$p(r5)
                com.taptap.sdk.kit.internal.poller.PollerState$InProgress r6 = new com.taptap.sdk.kit.internal.poller.PollerState$InProgress
                r6.<init>(r11)
                r5.setValue(r6)
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r11 = r1.this$0
                long r5 = com.taptap.sdk.kit.internal.poller.PollerImpl.access$getElapsedPollTime$p(r11)
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r7 = r1.this$0
                long r7 = com.taptap.sdk.kit.internal.poller.PollerImpl.access$getPollInterval$p(r7)
                long r5 = r5 + r7
                com.taptap.sdk.kit.internal.poller.PollerImpl.access$setElapsedPollTime$p(r11, r5)
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r11 = r1.this$0
                long r5 = com.taptap.sdk.kit.internal.poller.PollerImpl.access$getPollInterval$p(r11)
                r11 = r1
                kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
                r1.L$0 = r4
                r1.label = r2
                java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r5, r11)
                if (r11 != r0) goto L81
                return r0
            L81:
                r11 = r4
                goto L31
            L83:
                com.taptap.sdk.kit.internal.poller.PollerImpl<T> r11 = r1.this$0
                kotlinx.coroutines.flow.MutableStateFlow r11 = com.taptap.sdk.kit.internal.poller.PollerImpl.access$get_pollerStateFlow$p(r11)
                com.taptap.sdk.kit.internal.poller.PollerState$Completed r0 = com.taptap.sdk.kit.internal.poller.PollerState.Completed.INSTANCE
                r11.setValue(r0)
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.poller.PollerImpl.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.taptap.sdk.kit.internal.poller.Poller
    public void stopPoll() {
        this.isPolling = false;
        Job job = this.pollerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pollerJob = null;
    }

    @Override // com.taptap.sdk.kit.internal.poller.Poller
    /* JADX INFO: renamed from: isPolling, reason: from getter */
    public boolean getIsPolling() {
        return this.isPolling;
    }

    @Override // com.taptap.sdk.kit.internal.poller.Poller
    public boolean canPoll() {
        return this.isPolling && this.pollStrategy.canPoll(this.pollInterval, this.elapsedPollTime);
    }
}
