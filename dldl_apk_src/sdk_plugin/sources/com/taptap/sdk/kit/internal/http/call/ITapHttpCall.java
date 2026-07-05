package com.taptap.sdk.kit.internal.http.call;

import com.huya.mtp.http.monitor.Stat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: ITapHttpCall.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001b\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0096@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0006J\u0017\u0010\n\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005H'¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/call/ITapHttpCall;", "DataBean", "", "enqueue", "delay", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueResult", "Lkotlin/Result;", "enqueueResult-gIAlu-s", Stat.EXECUTE_KEY, "(J)Ljava/lang/Object;", "executeResult", "executeResult-IoAF18A", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ITapHttpCall<DataBean> {
    Object enqueue(long j, Continuation<? super DataBean> continuation);

    /* JADX INFO: renamed from: enqueueResult-gIAlu-s, reason: not valid java name */
    Object mo33enqueueResultgIAlus(long j, Continuation<? super Result<? extends DataBean>> continuation);

    DataBean execute(long delay);

    /* JADX INFO: renamed from: executeResult-IoAF18A, reason: not valid java name */
    Object mo34executeResultIoAF18A(long delay);

    /* JADX INFO: compiled from: ITapHttpCall.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object enqueue$default(ITapHttpCall iTapHttpCall, long j, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: enqueue");
            }
            if ((i & 1) != 0) {
                j = -1;
            }
            return iTapHttpCall.enqueue(j, continuation);
        }

        public static /* synthetic */ Object execute$default(ITapHttpCall iTapHttpCall, long j, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: execute");
            }
            if ((i & 1) != 0) {
                j = -1;
            }
            return iTapHttpCall.execute(j);
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        /* JADX INFO: renamed from: enqueueResult-gIAlu-s, reason: not valid java name */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static <DataBean> java.lang.Object m35enqueueResultgIAlus(com.taptap.sdk.kit.internal.http.call.ITapHttpCall<DataBean> r4, long r5, kotlin.coroutines.Continuation<? super kotlin.Result<? extends DataBean>> r7) throws java.lang.Throwable {
            /*
                boolean r0 = r7 instanceof com.taptap.sdk.kit.internal.http.call.ITapHttpCall$enqueueResult$1
                if (r0 == 0) goto L14
                r0 = r7
                com.taptap.sdk.kit.internal.http.call.ITapHttpCall$enqueueResult$1 r0 = (com.taptap.sdk.kit.internal.http.call.ITapHttpCall$enqueueResult$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L19
            L14:
                com.taptap.sdk.kit.internal.http.call.ITapHttpCall$enqueueResult$1 r0 = new com.taptap.sdk.kit.internal.http.call.ITapHttpCall$enqueueResult$1
                r0.<init>(r7)
            L19:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L32
                if (r2 != r3) goto L2a
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L45
                goto L40
            L2a:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L32:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlin.Result$Companion r7 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L45
                r0.label = r3     // Catch: java.lang.Throwable -> L45
                java.lang.Object r7 = r4.enqueue(r5, r0)     // Catch: java.lang.Throwable -> L45
                if (r7 != r1) goto L40
                return r1
            L40:
                java.lang.Object r4 = kotlin.Result.m52constructorimpl(r7)     // Catch: java.lang.Throwable -> L45
                goto L50
            L45:
                r4 = move-exception
                kotlin.Result$Companion r5 = kotlin.Result.INSTANCE
                java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
                java.lang.Object r4 = kotlin.Result.m52constructorimpl(r4)
            L50:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.http.call.ITapHttpCall.DefaultImpls.m35enqueueResultgIAlus(com.taptap.sdk.kit.internal.http.call.ITapHttpCall, long, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: renamed from: enqueueResult-gIAlu-s$default, reason: not valid java name */
        public static /* synthetic */ Object m36enqueueResultgIAlus$default(ITapHttpCall iTapHttpCall, long j, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: enqueueResult-gIAlu-s");
            }
            if ((i & 1) != 0) {
                j = -1;
            }
            return iTapHttpCall.mo33enqueueResultgIAlus(j, continuation);
        }

        /* JADX INFO: renamed from: executeResult-IoAF18A, reason: not valid java name */
        public static <DataBean> Object m37executeResultIoAF18A(ITapHttpCall<DataBean> iTapHttpCall, long j) {
            try {
                Result.Companion companion = Result.INSTANCE;
                return Result.m52constructorimpl(iTapHttpCall.execute(j));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m52constructorimpl(ResultKt.createFailure(th));
            }
        }

        /* JADX INFO: renamed from: executeResult-IoAF18A$default, reason: not valid java name */
        public static /* synthetic */ Object m38executeResultIoAF18A$default(ITapHttpCall iTapHttpCall, long j, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeResult-IoAF18A");
            }
            if ((i & 1) != 0) {
                j = -1;
            }
            return iTapHttpCall.mo34executeResultIoAF18A(j);
        }
    }
}
