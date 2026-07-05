package com.taptap.sdk.common.gaid.repository;

import com.taptap.sdk.common.gaid.data.model.GAID;
import com.taptap.sdk.common.gaid.helper.GAIDHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: GAIDRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0002R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/taptap/sdk/common/gaid/repository/GAIDRepository;", "", "helper", "Lcom/taptap/sdk/common/gaid/helper/GAIDHelper;", "(Lcom/taptap/sdk/common/gaid/helper/GAIDHelper;)V", "_gaidFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "gaidFlow", "Lkotlinx/coroutines/flow/StateFlow;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getCurrentGAID", "getGAID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "", "tryFetchGAID", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GAIDRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static GAID GAID;
    private final MutableStateFlow<GAID> _gaidFlow;
    private final StateFlow<GAID> gaidFlow;
    private final GAIDHelper helper;
    private final CoroutineScope scope;

    public GAIDRepository(GAIDHelper helper) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.helper = helper;
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        MutableStateFlow<GAID> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._gaidFlow = MutableStateFlow;
        this.gaidFlow = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final void initialize() {
        tryFetchGAID();
    }

    public final Object getGAID(Continuation<? super GAID> continuation) {
        return FlowKt.firstOrNull(FlowKt.filterNotNull(this.gaidFlow), continuation);
    }

    public final GAID getCurrentGAID() {
        return this.gaidFlow.getValue();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.common.gaid.repository.GAIDRepository$tryFetchGAID$1, reason: invalid class name */
    /* JADX INFO: compiled from: GAIDRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.gaid.repository.GAIDRepository$tryFetchGAID$1", f = "GAIDRepository.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return GAIDRepository.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                GAID gaid = GAIDRepository.GAID;
                if (gaid != null) {
                    GAIDRepository.this._gaidFlow.setValue(gaid);
                    return Unit.INSTANCE;
                }
                this.label = 1;
                obj = GAIDRepository.this.helper.getAndroidId(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            GAID gaid2 = (GAID) obj;
            Companion companion = GAIDRepository.INSTANCE;
            GAIDRepository.GAID = gaid2;
            GAIDRepository.this._gaidFlow.setValue(gaid2);
            return Unit.INSTANCE;
        }
    }

    private final void tryFetchGAID() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: compiled from: GAIDRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/common/gaid/repository/GAIDRepository$Companion;", "", "()V", "GAID", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
