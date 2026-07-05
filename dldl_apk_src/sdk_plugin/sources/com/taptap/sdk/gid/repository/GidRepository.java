package com.taptap.sdk.gid.repository;

import com.taptap.sdk.gid.data.response.Gid;
import com.taptap.sdk.gid.service.GidService;
import com.taptap.sdk.gid.service.network.GidNetworkService;
import com.taptap.sdk.themis.lite.ThemisLiteManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: GidRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u0004\u0018\u00010\u000bJ\u0011\u0010\u0018\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u000bJ\u0011\u0010\u001d\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001e\u001a\u00020\u001bJ\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u000bH\u0002R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lcom/taptap/sdk/gid/repository/GidRepository;", "", "gidDiskService", "Lcom/taptap/sdk/gid/service/GidService;", "gidNetworkService", "Lcom/taptap/sdk/gid/service/network/GidNetworkService;", "themisLiteManager", "Lcom/taptap/sdk/themis/lite/ThemisLiteManager;", "(Lcom/taptap/sdk/gid/service/GidService;Lcom/taptap/sdk/gid/service/network/GidNetworkService;Lcom/taptap/sdk/themis/lite/ThemisLiteManager;)V", "_gidStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/taptap/sdk/gid/data/response/Gid;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "gidFlow", "Lkotlinx/coroutines/flow/Flow;", "getGidFlow", "()Lkotlinx/coroutines/flow/Flow;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "tdidFlow", "", "getTdidFlow", "getCurrentGid", "getGid", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGidAsync", "", "getGidFromDisk", "getTdid", "initialize", "saveToDisk", "gid", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GidRepository {
    private final MutableStateFlow<Gid> _gidStateFlow;
    private final CoroutineScope coroutineScope;
    private final GidService gidDiskService;
    private final Flow<Gid> gidFlow;
    private final GidNetworkService gidNetworkService;
    private final Mutex mutex;
    private final Flow<String> tdidFlow;
    private final ThemisLiteManager themisLiteManager;

    public GidRepository(GidService gidDiskService, GidNetworkService gidNetworkService, ThemisLiteManager themisLiteManager) {
        Intrinsics.checkNotNullParameter(gidDiskService, "gidDiskService");
        Intrinsics.checkNotNullParameter(gidNetworkService, "gidNetworkService");
        Intrinsics.checkNotNullParameter(themisLiteManager, "themisLiteManager");
        this.gidDiskService = gidDiskService;
        this.gidNetworkService = gidNetworkService;
        this.themisLiteManager = themisLiteManager;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        MutableStateFlow<Gid> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._gidStateFlow = MutableStateFlow;
        this.gidFlow = FlowKt.filterNotNull(MutableStateFlow);
        this.tdidFlow = FlowKt.filterNotNull(this.themisLiteManager.getTdidFlow());
    }

    public final Flow<Gid> getGidFlow() {
        return this.gidFlow;
    }

    public final Flow<String> getTdidFlow() {
        return this.tdidFlow;
    }

    public final void initialize() {
        String id;
        ThemisLiteManager themisLiteManager = this.themisLiteManager;
        Gid gidFromDisk = getGidFromDisk();
        if (gidFromDisk == null || (id = gidFromDisk.getId()) == null) {
            id = "";
        }
        themisLiteManager.initialize(id);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.gid.repository.GidRepository$getGidAsync$1, reason: invalid class name */
    /* JADX INFO: compiled from: GidRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.gid.repository.GidRepository$getGidAsync$1", f = "GidRepository.kt", i = {0, 1}, l = {172, 101}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return GidRepository.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:(1:(1:(8:6|41|7|22|(2:39|27)|30|31|32)(2:9|10))(1:11))(2:12|(1:14))|43|15|(1:17)|37|18|(1:20)(6:21|22|(0)|30|31|32)) */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0082, code lost:
        
            r0 = r1;
            r1 = r14;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L30
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r0 = r13.L$1
                com.taptap.sdk.gid.repository.GidRepository r0 = (com.taptap.sdk.gid.repository.GidRepository) r0
                java.lang.Object r1 = r13.L$0
                kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
                kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L84
                goto L7f
            L1b:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L23:
                java.lang.Object r1 = r13.L$1
                com.taptap.sdk.gid.repository.GidRepository r1 = (com.taptap.sdk.gid.repository.GidRepository) r1
                java.lang.Object r3 = r13.L$0
                kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
                kotlin.ResultKt.throwOnFailure(r14)
                r14 = r3
                goto L4b
            L30:
                kotlin.ResultKt.throwOnFailure(r14)
                com.taptap.sdk.gid.repository.GidRepository r14 = com.taptap.sdk.gid.repository.GidRepository.this
                kotlinx.coroutines.sync.Mutex r14 = com.taptap.sdk.gid.repository.GidRepository.access$getMutex$p(r14)
                com.taptap.sdk.gid.repository.GidRepository r1 = com.taptap.sdk.gid.repository.GidRepository.this
                r5 = r13
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r13.L$0 = r14
                r13.L$1 = r1
                r13.label = r3
                java.lang.Object r3 = r14.lock(r4, r5)
                if (r3 != r0) goto L4b
                return r0
            L4b:
                com.taptap.sdk.gid.data.response.Gid r3 = r1.getGidFromDisk()     // Catch: java.lang.Throwable -> L9d
                if (r3 == 0) goto L58
                kotlinx.coroutines.flow.MutableStateFlow r5 = com.taptap.sdk.gid.repository.GidRepository.access$get_gidStateFlow$p(r1)     // Catch: java.lang.Throwable -> L9d
                r5.setValue(r3)     // Catch: java.lang.Throwable -> L9d
            L58:
                com.taptap.sdk.common.network.retry.RetryUtils r3 = com.taptap.sdk.common.network.retry.RetryUtils.INSTANCE     // Catch: java.lang.Throwable -> L82
                com.taptap.sdk.common.network.retry.RetryStrategy$Exponential r12 = new com.taptap.sdk.common.network.retry.RetryStrategy$Exponential     // Catch: java.lang.Throwable -> L82
                r6 = 0
                r8 = 0
                r10 = 3
                r11 = 0
                r5 = r12
                r5.<init>(r6, r8, r10, r11)     // Catch: java.lang.Throwable -> L82
                com.taptap.sdk.common.network.retry.RetryStrategy r12 = (com.taptap.sdk.common.network.retry.RetryStrategy) r12     // Catch: java.lang.Throwable -> L82
                com.taptap.sdk.gid.repository.GidRepository$getGidAsync$1$1$networkData$1 r5 = new com.taptap.sdk.gid.repository.GidRepository$getGidAsync$1$1$networkData$1     // Catch: java.lang.Throwable -> L82
                r5.<init>(r1, r4)     // Catch: java.lang.Throwable -> L82
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch: java.lang.Throwable -> L82
                r13.L$0 = r14     // Catch: java.lang.Throwable -> L82
                r13.L$1 = r1     // Catch: java.lang.Throwable -> L82
                r13.label = r2     // Catch: java.lang.Throwable -> L82
                java.lang.Object r2 = r3.retry(r12, r5, r13)     // Catch: java.lang.Throwable -> L82
                if (r2 != r0) goto L7c
                return r0
            L7c:
                r0 = r1
                r1 = r14
                r14 = r2
            L7f:
                com.taptap.sdk.gid.data.response.Gid r14 = (com.taptap.sdk.gid.data.response.Gid) r14     // Catch: java.lang.Throwable -> L84
                goto L85
            L82:
                r0 = r1
                r1 = r14
            L84:
                r14 = r4
            L85:
                if (r14 == 0) goto L95
                com.taptap.sdk.gid.repository.GidRepository.access$saveToDisk(r0, r14)     // Catch: java.lang.Throwable -> L92
                kotlinx.coroutines.flow.MutableStateFlow r0 = com.taptap.sdk.gid.repository.GidRepository.access$get_gidStateFlow$p(r0)     // Catch: java.lang.Throwable -> L92
                r0.setValue(r14)     // Catch: java.lang.Throwable -> L92
                goto L95
            L92:
                r0 = move-exception
                r14 = r1
                goto L9e
            L95:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L92
                r1.unlock(r4)
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            L9d:
                r0 = move-exception
            L9e:
                r14.unlock(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.gid.repository.GidRepository.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void getGidAsync() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    public final Gid getCurrentGid() {
        return this._gidStateFlow.getValue();
    }

    public final Object getGid(Continuation<? super Gid> continuation) {
        return FlowKt.first(FlowKt.filterNotNull(this.gidFlow), continuation);
    }

    public final Object getTdid(Continuation<? super String> continuation) {
        return this.themisLiteManager.getTdid(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveToDisk(Gid gid) {
        try {
            GidService gidService = this.gidDiskService;
            Json.Companion companion = Json.INSTANCE;
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(Gid.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            gidService.setGid(companion.encodeToString(kSerializerSerializer, gid));
        } catch (Throwable unused) {
        }
    }

    public final Gid getGidFromDisk() {
        try {
            Json.Companion companion = Json.INSTANCE;
            String gid = this.gidDiskService.getGid();
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.nullableTypeOf(Gid.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (Gid) companion.decodeFromString(kSerializerSerializer, gid);
        } catch (Throwable unused) {
            return null;
        }
    }
}
