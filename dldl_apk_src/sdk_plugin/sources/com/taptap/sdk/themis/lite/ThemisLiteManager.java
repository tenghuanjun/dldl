package com.taptap.sdk.themis.lite;

import com.taptap.sdk.themis.lite.data.model.ThemisLiteConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: ThemisLiteManager.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0017B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/taptap/sdk/themis/lite/ThemisLiteManager;", "", "builder", "Lcom/taptap/sdk/themis/lite/ThemisLiteManager$Builder;", "(Lcom/taptap/sdk/themis/lite/ThemisLiteManager$Builder;)V", "config", "Lcom/taptap/sdk/themis/lite/data/model/ThemisLiteConfig;", "(Lcom/taptap/sdk/themis/lite/data/model/ThemisLiteConfig;)V", "_oneIDFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_tdidFlow", "tdidFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getTdidFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "getOneID", "Lcom/taptap/sdk/themis/lite/data/model/OneID;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTdid", "initialize", "", "preOneID", "Builder", "tap-themis-lite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ThemisLiteManager {
    private MutableStateFlow<String> _oneIDFlow;
    private MutableStateFlow<String> _tdidFlow;
    private final ThemisLiteConfig config;
    private final StateFlow<String> tdidFlow;

    /* JADX INFO: renamed from: com.taptap.sdk.themis.lite.ThemisLiteManager$getOneID$1, reason: invalid class name */
    /* JADX INFO: compiled from: ThemisLiteManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.themis.lite.ThemisLiteManager", f = "ThemisLiteManager.kt", i = {}, l = {70}, m = "getOneID", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ThemisLiteManager.this.getOneID(this);
        }
    }

    public /* synthetic */ ThemisLiteManager(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private ThemisLiteManager(ThemisLiteConfig themisLiteConfig) {
        this.config = themisLiteConfig;
        MutableStateFlow<String> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._tdidFlow = MutableStateFlow;
        this.tdidFlow = MutableStateFlow;
        this._oneIDFlow = StateFlowKt.MutableStateFlow(null);
    }

    public final StateFlow<String> getTdidFlow() {
        return this.tdidFlow;
    }

    private ThemisLiteManager(Builder builder) {
        this(builder.getConfig());
    }

    public final void initialize(String preOneID) {
        Intrinsics.checkNotNullParameter(preOneID, "preOneID");
        ThemisLite.InitThemis_v2(this.config.getAppID(), new ThemisLiteCallback() { // from class: com.taptap.sdk.themis.lite.ThemisLiteManager.initialize.1
            @Override // com.taptap.sdk.themis.lite.ThemisLiteCallback
            public void getThemisTapaid(String tapaid) {
                Intrinsics.checkNotNullParameter(tapaid, "tapaid");
                ThemisLiteManager.this._tdidFlow.setValue(tapaid);
            }

            @Override // com.taptap.sdk.themis.lite.ThemisLiteCallback
            public void getThemisOneIDData(String oneIDData) {
                Intrinsics.checkNotNullParameter(oneIDData, "oneIDData");
                ThemisLiteManager.this._oneIDFlow.setValue(oneIDData);
            }
        }, this.config.getTimeout(), preOneID, this.config.getInitThemisTapaid(), this.config.getInitGADID());
    }

    public final Object getTdid(Continuation<? super String> continuation) {
        return FlowKt.first(FlowKt.filterNotNull(this._tdidFlow), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getOneID(kotlin.coroutines.Continuation<? super com.taptap.sdk.themis.lite.data.model.OneID> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.taptap.sdk.themis.lite.ThemisLiteManager.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            com.taptap.sdk.themis.lite.ThemisLiteManager$getOneID$1 r0 = (com.taptap.sdk.themis.lite.ThemisLiteManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            com.taptap.sdk.themis.lite.ThemisLiteManager$getOneID$1 r0 = new com.taptap.sdk.themis.lite.ThemisLiteManager$getOneID$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> r5 = r4._oneIDFlow
            kotlinx.coroutines.flow.Flow r5 = (kotlinx.coroutines.flow.Flow) r5
            kotlinx.coroutines.flow.Flow r5 = kotlinx.coroutines.flow.FlowKt.filterNotNull(r5)
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.flow.FlowKt.first(r5, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.String r5 = (java.lang.String) r5
            kotlinx.serialization.json.Json$Default r0 = kotlinx.serialization.json.Json.INSTANCE     // Catch: java.lang.Throwable -> L68
            kotlinx.serialization.StringFormat r0 = (kotlinx.serialization.StringFormat) r0     // Catch: java.lang.Throwable -> L68
            kotlinx.serialization.modules.SerializersModule r1 = r0.getSerializersModule()     // Catch: java.lang.Throwable -> L68
            java.lang.Class<com.taptap.sdk.themis.lite.data.model.OneID> r2 = com.taptap.sdk.themis.lite.data.model.OneID.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.nullableTypeOf(r2)     // Catch: java.lang.Throwable -> L68
            kotlinx.serialization.KSerializer r1 = kotlinx.serialization.SerializersKt.serializer(r1, r2)     // Catch: java.lang.Throwable -> L68
            java.lang.String r2 = "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)     // Catch: java.lang.Throwable -> L68
            kotlinx.serialization.DeserializationStrategy r1 = (kotlinx.serialization.DeserializationStrategy) r1     // Catch: java.lang.Throwable -> L68
            java.lang.Object r5 = r0.decodeFromString(r1, r5)     // Catch: java.lang.Throwable -> L68
            com.taptap.sdk.themis.lite.data.model.OneID r5 = (com.taptap.sdk.themis.lite.data.model.OneID) r5     // Catch: java.lang.Throwable -> L68
            goto L6d
        L68:
            r5 = move-exception
            r5.printStackTrace()
            r5 = 0
        L6d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.themis.lite.ThemisLiteManager.getOneID(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: ThemisLiteManager.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/themis/lite/ThemisLiteManager$Builder;", "", "()V", "<set-?>", "Lcom/taptap/sdk/themis/lite/data/model/ThemisLiteConfig;", "config", "getConfig$tap_themis_lite_release", "()Lcom/taptap/sdk/themis/lite/data/model/ThemisLiteConfig;", "build", "Lcom/taptap/sdk/themis/lite/ThemisLiteManager;", "setAppID", "appID", "", "setInitGADID", "initGADID", "", "setInitThemisTapaid", "initThemisTapaid", "setTimeout", "timeout", "", "tap-themis-lite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Builder {
        private ThemisLiteConfig config = new ThemisLiteConfig(null, 0, null, false, false, 31, null);

        /* JADX INFO: renamed from: getConfig$tap_themis_lite_release, reason: from getter */
        public final ThemisLiteConfig getConfig() {
            return this.config;
        }

        public final Builder setAppID(String appID) {
            Intrinsics.checkNotNullParameter(appID, "appID");
            Builder builder = this;
            builder.config = ThemisLiteConfig.copy$default(builder.config, appID, 0L, null, false, false, 30, null);
            return builder;
        }

        public final Builder setTimeout(long timeout) {
            Builder builder = this;
            builder.config = ThemisLiteConfig.copy$default(builder.config, null, timeout, null, false, false, 29, null);
            return builder;
        }

        public final Builder setInitThemisTapaid(boolean initThemisTapaid) {
            Builder builder = this;
            builder.config = ThemisLiteConfig.copy$default(builder.config, null, 0L, null, initThemisTapaid, false, 23, null);
            return builder;
        }

        public final Builder setInitGADID(boolean initGADID) {
            Builder builder = this;
            builder.config = ThemisLiteConfig.copy$default(builder.config, null, 0L, null, false, initGADID, 15, null);
            return builder;
        }

        public final ThemisLiteManager build() {
            return new ThemisLiteManager(this, null);
        }
    }
}
