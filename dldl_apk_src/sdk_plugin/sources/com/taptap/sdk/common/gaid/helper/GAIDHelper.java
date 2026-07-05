package com.taptap.sdk.common.gaid.helper;

import android.content.Context;
import com.taptap.sdk.common.gaid.provider.GAIDProvider;
import com.taptap.sdk.common.gaid.provider.ReflectionGAIDProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GAIDHelper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/common/gaid/helper/GAIDHelper;", "", "builder", "Lcom/taptap/sdk/common/gaid/helper/GAIDHelper$Builder;", "(Lcom/taptap/sdk/common/gaid/helper/GAIDHelper$Builder;)V", "context", "Landroid/content/Context;", "reflectionGAIDProvider", "Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "serviceGAIDProvider", "(Landroid/content/Context;Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;)V", "getAndroidId", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Builder", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GAIDHelper {
    private static final long TIME_OUT = 60000;
    private final Context context;
    private final GAIDProvider reflectionGAIDProvider;
    private final GAIDProvider serviceGAIDProvider;

    /* JADX INFO: renamed from: com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$1, reason: invalid class name */
    /* JADX INFO: compiled from: GAIDHelper.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.gaid.helper.GAIDHelper", f = "GAIDHelper.kt", i = {0}, l = {35, 42}, m = "getAndroidId", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GAIDHelper.this.getAndroidId(this);
        }
    }

    public /* synthetic */ GAIDHelper(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private GAIDHelper(Context context, GAIDProvider gAIDProvider, GAIDProvider gAIDProvider2) {
        this.context = context;
        this.reflectionGAIDProvider = gAIDProvider;
        this.serviceGAIDProvider = gAIDProvider2;
    }

    private GAIDHelper(Builder builder) {
        this(builder.getContext(), builder.getReflectionGAIDProvider(), builder.getServiceGAIDProvider());
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAndroidId(kotlin.coroutines.Continuation<? super com.taptap.sdk.common.gaid.data.model.GAID> r9) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.taptap.sdk.common.gaid.helper.GAIDHelper.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$1 r0 = (com.taptap.sdk.common.gaid.helper.GAIDHelper.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$1 r0 = new com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 60000(0xea60, double:2.9644E-319)
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L41
            if (r2 == r6) goto L39
            if (r2 != r5) goto L31
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6e
        L31:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L39:
            java.lang.Object r2 = r0.L$0
            com.taptap.sdk.common.gaid.helper.GAIDHelper r2 = (com.taptap.sdk.common.gaid.helper.GAIDHelper) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L57
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$reflectionData$1 r9 = new com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$reflectionData$1
            r9.<init>(r8, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r0.L$0 = r8
            r0.label = r6
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r3, r9, r0)
            if (r9 != r1) goto L56
            return r1
        L56:
            r2 = r8
        L57:
            com.taptap.sdk.common.gaid.data.model.GAID r9 = (com.taptap.sdk.common.gaid.data.model.GAID) r9
            if (r9 == 0) goto L5c
            return r9
        L5c:
            com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$serviceData$1 r9 = new com.taptap.sdk.common.gaid.helper.GAIDHelper$getAndroidId$serviceData$1
            r9.<init>(r2, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r3, r9, r0)
            if (r9 != r1) goto L6e
            return r1
        L6e:
            com.taptap.sdk.common.gaid.data.model.GAID r9 = (com.taptap.sdk.common.gaid.data.model.GAID) r9
            if (r9 == 0) goto L73
            return r9
        L73:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.common.gaid.helper.GAIDHelper.getAndroidId(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: GAIDHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/taptap/sdk/common/gaid/helper/GAIDHelper$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "reflectionGAIDProvider", "getReflectionGAIDProvider$tap_common_release", "()Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "serviceGAIDProvider", "getServiceGAIDProvider$tap_common_release", "build", "Lcom/taptap/sdk/common/gaid/helper/GAIDHelper;", "setReflectionGAIDProvider", "setServiceGAIDProvider", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private final Context context;
        private GAIDProvider reflectionGAIDProvider;
        private GAIDProvider serviceGAIDProvider;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.reflectionGAIDProvider = new ReflectionGAIDProvider();
            this.serviceGAIDProvider = new ReflectionGAIDProvider();
        }

        public final Context getContext() {
            return this.context;
        }

        /* JADX INFO: renamed from: getReflectionGAIDProvider$tap_common_release, reason: from getter */
        public final GAIDProvider getReflectionGAIDProvider() {
            return this.reflectionGAIDProvider;
        }

        /* JADX INFO: renamed from: getServiceGAIDProvider$tap_common_release, reason: from getter */
        public final GAIDProvider getServiceGAIDProvider() {
            return this.serviceGAIDProvider;
        }

        public final Builder setReflectionGAIDProvider(GAIDProvider reflectionGAIDProvider) {
            Intrinsics.checkNotNullParameter(reflectionGAIDProvider, "reflectionGAIDProvider");
            Builder builder = this;
            builder.reflectionGAIDProvider = reflectionGAIDProvider;
            return builder;
        }

        public final Builder setServiceGAIDProvider(GAIDProvider serviceGAIDProvider) {
            Intrinsics.checkNotNullParameter(serviceGAIDProvider, "serviceGAIDProvider");
            Builder builder = this;
            builder.serviceGAIDProvider = serviceGAIDProvider;
            return builder;
        }

        public final GAIDHelper build() {
            return new GAIDHelper(this, null);
        }
    }
}
