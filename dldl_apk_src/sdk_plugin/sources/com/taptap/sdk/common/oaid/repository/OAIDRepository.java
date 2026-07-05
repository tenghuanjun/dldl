package com.taptap.sdk.common.oaid.repository;

import com.taptap.sdk.common.oaid.cert.OAIDCertProvider;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
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

/* JADX INFO: compiled from: OAIDRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u0004\u0018\u00010\tJ\u0013\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u000fR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/common/oaid/repository/OAIDRepository;", "", "certProvider", "Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;", "oaidHelper", "Lcom/taptap/sdk/common/oaid/helper/OAIDHelper;", "(Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;Lcom/taptap/sdk/common/oaid/helper/OAIDHelper;)V", "_oaidFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "oaidFlow", "Lkotlinx/coroutines/flow/StateFlow;", "fetchOaid", "", "getCurrentOAID", "getOAID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OAIDRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String OAID;
    private final MutableStateFlow<String> _oaidFlow;
    private final OAIDCertProvider certProvider;
    private final CoroutineScope coroutineScope;
    private final StateFlow<String> oaidFlow;
    private final OAIDHelper oaidHelper;

    public OAIDRepository(OAIDCertProvider certProvider, OAIDHelper oaidHelper) {
        Intrinsics.checkNotNullParameter(certProvider, "certProvider");
        Intrinsics.checkNotNullParameter(oaidHelper, "oaidHelper");
        this.certProvider = certProvider;
        this.oaidHelper = oaidHelper;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        MutableStateFlow<String> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._oaidFlow = MutableStateFlow;
        this.oaidFlow = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final void initialize() {
        this.oaidHelper.initialize();
        fetchOaid();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.common.oaid.repository.OAIDRepository$fetchOaid$1, reason: invalid class name */
    /* JADX INFO: compiled from: OAIDRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.oaid.repository.OAIDRepository$fetchOaid$1", f = "OAIDRepository.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OAIDRepository.this.new AnonymousClass1(continuation);
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
                String str = OAIDRepository.OAID;
                String str2 = str;
                if (!(str2 == null || str2.length() == 0)) {
                    OAIDRepository.this._oaidFlow.setValue(str);
                    return Unit.INSTANCE;
                }
                this.label = 1;
                obj = OAIDRepository.this.oaidHelper.tryGetOaidByReflect(OAIDRepository.this.certProvider, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            String str3 = (String) obj;
            if (str3 != null) {
                OAIDRepository oAIDRepository = OAIDRepository.this;
                Companion companion = OAIDRepository.INSTANCE;
                OAIDRepository.OAID = str3;
                oAIDRepository._oaidFlow.setValue(str3);
            }
            return Unit.INSTANCE;
        }
    }

    private final void fetchOaid() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    public final Object getOAID(Continuation<? super String> continuation) {
        return FlowKt.firstOrNull(FlowKt.filterNotNull(this.oaidFlow), continuation);
    }

    public final String getCurrentOAID() {
        return this.oaidFlow.getValue();
    }

    /* JADX INFO: compiled from: OAIDRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/common/oaid/repository/OAIDRepository$Companion;", "", "()V", "OAID", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
