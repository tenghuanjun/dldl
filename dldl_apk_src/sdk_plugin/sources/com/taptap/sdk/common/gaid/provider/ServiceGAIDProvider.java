package com.taptap.sdk.common.gaid.provider;

import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: ServiceGAIDProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/common/gaid/provider/ServiceGAIDProvider;", "Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "()V", "createIntent", "Landroid/content/Intent;", "provideGAID", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryBindService", "", "intent", "connection", "Lcom/taptap/sdk/common/gaid/provider/GoogleAdServiceConnection;", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ServiceGAIDProvider implements GAIDProvider {
    private static final String ACTION = "com.google.android.gms.ads.identifier.service.START";
    private static final String PACKAGE = "com.google.android.gms";

    /* JADX INFO: renamed from: com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider$provideGAID$1, reason: invalid class name */
    /* JADX INFO: compiled from: ServiceGAIDProvider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider", f = "ServiceGAIDProvider.kt", i = {}, l = {19}, m = "provideGAID", n = {}, s = {})
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
            return ServiceGAIDProvider.this.provideGAID(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.taptap.sdk.common.gaid.provider.GAIDProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object provideGAID(android.content.Context r5, kotlin.coroutines.Continuation<? super com.taptap.sdk.common.gaid.data.model.GAID> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider$provideGAID$1 r0 = (com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider$provideGAID$1 r0 = new com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider$provideGAID$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            com.taptap.sdk.common.gaid.provider.GoogleAdServiceConnection r6 = new com.taptap.sdk.common.gaid.provider.GoogleAdServiceConnection
            r6.<init>()
            android.content.Intent r2 = r4.createIntent()
            boolean r5 = r4.tryBindService(r5, r2, r6)
            if (r5 != 0) goto L46
            r5 = 0
            return r5
        L46:
            r0.label = r3
            java.lang.Object r6 = r6.awaitBinder(r0)
            if (r6 != r1) goto L4f
            return r1
        L4f:
            android.os.IBinder r6 = (android.os.IBinder) r6
            com.taptap.sdk.common.gaid.provider.GoogleAdServiceConnection$GoogleAdInfo r5 = new com.taptap.sdk.common.gaid.provider.GoogleAdServiceConnection$GoogleAdInfo
            r5.<init>(r6)
            com.taptap.sdk.common.gaid.data.model.GAID r6 = new com.taptap.sdk.common.gaid.data.model.GAID
            java.lang.String r0 = r5.getAdvertiserId()
            boolean r5 = r5.isTrackingLimited()
            r6.<init>(r0, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider.provideGAID(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Intent createIntent() {
        Intent intent = new Intent(ACTION);
        intent.setPackage(PACKAGE);
        return intent;
    }

    private final boolean tryBindService(Context context, Intent intent, GoogleAdServiceConnection connection) {
        try {
            return context.bindService(intent, connection, 1);
        } catch (SecurityException unused) {
            return false;
        }
    }
}
