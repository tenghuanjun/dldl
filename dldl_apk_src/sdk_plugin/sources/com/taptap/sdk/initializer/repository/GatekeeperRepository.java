package com.taptap.sdk.initializer.repository;

import android.content.Context;
import android.widget.Toast;
import com.taptap.sdk.common.network.throwable.ApiErr;
import com.taptap.sdk.common.network.throwable.TapNetworkException;
import com.taptap.sdk.initializer.data.response.GateKeeper;
import com.taptap.sdk.initializer.repository.local.GatekeeperStorage;
import com.taptap.sdk.initializer.repository.remote.GatekeeperApiService;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: GatekeeperRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u000bJ\n\u0010\u0018\u001a\u0004\u0018\u00010\u000bH\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/taptap/sdk/initializer/repository/GatekeeperRepository;", "", "context", "Landroid/content/Context;", "apiService", "Lcom/taptap/sdk/initializer/repository/remote/GatekeeperApiService;", "storage", "Lcom/taptap/sdk/initializer/repository/local/GatekeeperStorage;", "(Landroid/content/Context;Lcom/taptap/sdk/initializer/repository/remote/GatekeeperApiService;Lcom/taptap/sdk/initializer/repository/local/GatekeeperStorage;)V", "_gateKeeperFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "gateKeeperFlow", "Lkotlinx/coroutines/flow/Flow;", "getGateKeeperFlow", "()Lkotlinx/coroutines/flow/Flow;", "memoryData", "scope", "Lkotlinx/coroutines/CoroutineScope;", "fallbackErrorMsg", "", "throwable", "Lcom/taptap/sdk/common/network/throwable/TapNetworkException;", "getCurrentGateKeeper", "getGateKeeperFromDisk", "initialize", "Lkotlinx/coroutines/Job;", "showErrorToast", "", "", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GatekeeperRepository {
    private static final String DEFAULT_GATEKEEPER_ERROR_TOAST = "当前应用初始化信息错误, 请在 TapTap 开发者中心检查[当前应用包名]与[调用初始化接口设置的 clientId 、clientToken]是否匹配";
    private final MutableSharedFlow<GateKeeper> _gateKeeperFlow;
    private final GatekeeperApiService apiService;
    private final Context context;
    private final Flow<GateKeeper> gateKeeperFlow;
    private final GateKeeper memoryData;
    private final CoroutineScope scope;
    private final GatekeeperStorage storage;

    public GatekeeperRepository(Context context, GatekeeperApiService apiService, GatekeeperStorage storage) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiService, "apiService");
        Intrinsics.checkNotNullParameter(storage, "storage");
        this.context = context;
        this.apiService = apiService;
        this.storage = storage;
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        this.memoryData = new GateKeeper(new GateKeeper.Switch(true, true), (Integer) null, (GateKeeper.Urls) null, (GateKeeper.SdkConfig) null, 14, (DefaultConstructorMarker) null);
        MutableSharedFlow<GateKeeper> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._gateKeeperFlow = mutableSharedFlowMutableSharedFlow$default;
        this.gateKeeperFlow = FlowKt.distinctUntilChanged(mutableSharedFlowMutableSharedFlow$default);
    }

    public final Flow<GateKeeper> getGateKeeperFlow() {
        return this.gateKeeperFlow;
    }

    /* JADX INFO: renamed from: com.taptap.sdk.initializer.repository.GatekeeperRepository$initialize$1, reason: invalid class name */
    /* JADX INFO: compiled from: GatekeeperRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.initializer.repository.GatekeeperRepository$initialize$1", f = "GatekeeperRepository.kt", i = {}, l = {80, 90}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return GatekeeperRepository.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L21
                if (r1 == r4) goto L1b
                if (r1 != r3) goto L13
                kotlin.ResultKt.throwOnFailure(r13)
                goto L70
            L13:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L1b:
                kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L1f
                goto L5e
            L1f:
                r13 = move-exception
                goto L62
            L21:
                kotlin.ResultKt.throwOnFailure(r13)
                com.taptap.sdk.initializer.repository.GatekeeperRepository r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.this
                com.taptap.sdk.initializer.data.response.GateKeeper r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$getGateKeeperFromDisk(r13)
                com.taptap.sdk.initializer.repository.GatekeeperRepository r1 = com.taptap.sdk.initializer.repository.GatekeeperRepository.this
                kotlinx.coroutines.flow.MutableSharedFlow r5 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$get_gateKeeperFlow$p(r1)
                if (r13 != 0) goto L36
                com.taptap.sdk.initializer.data.response.GateKeeper r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$getMemoryData$p(r1)
            L36:
                r5.tryEmit(r13)
                com.taptap.sdk.common.network.retry.RetryUtils r13 = com.taptap.sdk.common.network.retry.RetryUtils.INSTANCE     // Catch: java.lang.Throwable -> L1f
                com.taptap.sdk.common.network.retry.RetryStrategy$Exponential r1 = new com.taptap.sdk.common.network.retry.RetryStrategy$Exponential     // Catch: java.lang.Throwable -> L1f
                r6 = 0
                r8 = 0
                r10 = 3
                r11 = 0
                r5 = r1
                r5.<init>(r6, r8, r10, r11)     // Catch: java.lang.Throwable -> L1f
                com.taptap.sdk.common.network.retry.RetryStrategy r1 = (com.taptap.sdk.common.network.retry.RetryStrategy) r1     // Catch: java.lang.Throwable -> L1f
                com.taptap.sdk.initializer.repository.GatekeeperRepository$initialize$1$networkData$1 r5 = new com.taptap.sdk.initializer.repository.GatekeeperRepository$initialize$1$networkData$1     // Catch: java.lang.Throwable -> L1f
                com.taptap.sdk.initializer.repository.GatekeeperRepository r6 = com.taptap.sdk.initializer.repository.GatekeeperRepository.this     // Catch: java.lang.Throwable -> L1f
                r5.<init>(r6, r2)     // Catch: java.lang.Throwable -> L1f
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch: java.lang.Throwable -> L1f
                r6 = r12
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L1f
                r12.label = r4     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r13 = r13.retry(r1, r5, r6)     // Catch: java.lang.Throwable -> L1f
                if (r13 != r0) goto L5e
                return r0
            L5e:
                com.taptap.sdk.initializer.data.response.GateKeeper r13 = (com.taptap.sdk.initializer.data.response.GateKeeper) r13     // Catch: java.lang.Throwable -> L1f
                r2 = r13
                goto L70
            L62:
                com.taptap.sdk.initializer.repository.GatekeeperRepository r1 = com.taptap.sdk.initializer.repository.GatekeeperRepository.this
                r4 = r12
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r12.label = r3
                java.lang.Object r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$showErrorToast(r1, r13, r4)
                if (r13 != r0) goto L70
                return r0
            L70:
                if (r2 == 0) goto L82
                com.taptap.sdk.initializer.repository.GatekeeperRepository r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.this
                com.taptap.sdk.initializer.repository.local.GatekeeperStorage r0 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$getStorage$p(r13)
                r0.saveGateKeeper(r2)
                kotlinx.coroutines.flow.MutableSharedFlow r13 = com.taptap.sdk.initializer.repository.GatekeeperRepository.access$get_gateKeeperFlow$p(r13)
                r13.tryEmit(r2)
            L82:
                kotlin.Unit r13 = kotlin.Unit.INSTANCE
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.initializer.repository.GatekeeperRepository.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Job initialize() {
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    public final GateKeeper getCurrentGateKeeper() {
        GateKeeper gateKeeper = (GateKeeper) CollectionsKt.firstOrNull((List) this._gateKeeperFlow.getReplayCache());
        return gateKeeper == null ? this.memoryData : gateKeeper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GateKeeper getGateKeeperFromDisk() {
        return this.storage.getGateKeeper();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.initializer.repository.GatekeeperRepository$showErrorToast$2, reason: invalid class name */
    /* JADX INFO: compiled from: GatekeeperRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.initializer.repository.GatekeeperRepository$showErrorToast$2", f = "GatekeeperRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Throwable $throwable;
        int label;
        final /* synthetic */ GatekeeperRepository this$0;

        /* JADX INFO: renamed from: com.taptap.sdk.initializer.repository.GatekeeperRepository$showErrorToast$2$WhenMappings */
        /* JADX INFO: compiled from: GatekeeperRepository.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ApiErr.values().length];
                try {
                    iArr[ApiErr.INVALID_CLIENT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Throwable th, GatekeeperRepository gatekeeperRepository, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$throwable = th;
            this.this$0 = gatekeeperRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$throwable, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Throwable th = this.$throwable;
            if (th instanceof TapNetworkException) {
                if (WhenMappings.$EnumSwitchMapping$0[((TapNetworkException) th).getErr().getApiErr().ordinal()] == 1) {
                    Toast.makeText(this.this$0.context, this.this$0.fallbackErrorMsg((TapNetworkException) this.$throwable), 0).show();
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showErrorToast(Throwable th, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(th, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fallbackErrorMsg(TapNetworkException throwable) {
        String msg = throwable.getErr().getMsg();
        if (!(msg.length() > 0)) {
            msg = null;
        }
        return msg == null ? DEFAULT_GATEKEEPER_ERROR_TOAST : msg;
    }
}
