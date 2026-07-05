package com.taptap.sdk.gid;

import com.taptap.sdk.common.services.GidService;
import com.taptap.sdk.gid.data.response.Gid;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: GidServiceImpl.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/gid/GidServiceImpl;", "Lcom/taptap/sdk/common/services/GidService;", "()V", "getCurrentGid", "", "getGid", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTdid", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GidServiceImpl implements GidService {

    /* JADX INFO: renamed from: com.taptap.sdk.gid.GidServiceImpl$getGid$1, reason: invalid class name */
    /* JADX INFO: compiled from: GidServiceImpl.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.gid.GidServiceImpl", f = "GidServiceImpl.kt", i = {}, l = {20}, m = "getGid", n = {}, s = {})
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
            return GidServiceImpl.this.getGid(this);
        }
    }

    @Override // com.taptap.sdk.common.services.GidService
    public String getCurrentGid() {
        Gid currentGid;
        TapSdkGid iNSTANCE$tap_gid_release = TapSdkGid.INSTANCE.getINSTANCE$tap_gid_release();
        if (iNSTANCE$tap_gid_release == null || (currentGid = iNSTANCE$tap_gid_release.getCurrentGid()) == null) {
            return null;
        }
        return currentGid.getId();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.taptap.sdk.common.services.GidService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getGid(kotlin.coroutines.Continuation<? super java.lang.String> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.taptap.sdk.gid.GidServiceImpl.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            com.taptap.sdk.gid.GidServiceImpl$getGid$1 r0 = (com.taptap.sdk.gid.GidServiceImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            com.taptap.sdk.gid.GidServiceImpl$getGid$1 r0 = new com.taptap.sdk.gid.GidServiceImpl$getGid$1
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
            com.taptap.sdk.gid.TapSdkGid$Runtime r5 = com.taptap.sdk.gid.TapSdkGid.INSTANCE
            com.taptap.sdk.gid.TapSdkGid r5 = r5.getINSTANCE$tap_gid_release()
            if (r5 == 0) goto L4f
            r0.label = r3
            java.lang.Object r5 = r5.getGid(r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            com.taptap.sdk.gid.data.response.Gid r5 = (com.taptap.sdk.gid.data.response.Gid) r5
            if (r5 == 0) goto L4f
            java.lang.String r5 = r5.getId()
            goto L50
        L4f:
            r5 = 0
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.gid.GidServiceImpl.getGid(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.taptap.sdk.common.services.GidService
    public Object getTdid(Continuation<? super String> continuation) {
        TapSdkGid iNSTANCE$tap_gid_release = TapSdkGid.INSTANCE.getINSTANCE$tap_gid_release();
        if (iNSTANCE$tap_gid_release == null) {
            return null;
        }
        Object tdid = iNSTANCE$tap_gid_release.getTdid(continuation);
        return tdid == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tdid : (String) tdid;
    }
}
