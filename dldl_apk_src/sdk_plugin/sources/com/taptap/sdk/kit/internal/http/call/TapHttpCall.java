package com.taptap.sdk.kit.internal.http.call;

import com.huya.mtp.http.monitor.Stat;
import com.huya.mtp.hyns.stat.NSStatReporter;
import com.taptap.sdk.kit.internal.http.TapErrorConstants;
import com.taptap.sdk.kit.internal.http.TapHttpException;
import com.taptap.sdk.kit.internal.http.call.ITapHttpCall;
import com.taptap.sdk.kit.internal.http.hanlder.ITapHttpParser;
import com.taptap.sdk.kit.internal.http.hanlder.ITapHttpRetry;
import com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam;
import com.taptap.sdk.kit.internal.utils.TapOpenlogHelper;
import com.taptap.sdk.okhttp3.Call;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: compiled from: TapHttpCall.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0019\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0011\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0003¢\u0006\u0002\u0010\u0012J.\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/call/TapHttpCall;", "DataBean", "Lcom/taptap/sdk/kit/internal/http/call/ITapHttpCall;", "param", "Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", "httpParser", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;", "httpRetry", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpRetry;", "(Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpRetry;)V", "getParam", "()Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", "enqueue", "delay", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueInner", Stat.EXECUTE_KEY, "(J)Ljava/lang/Object;", "executeInner", "onResultFailure", "", "call", "Lcom/taptap/sdk/okhttp3/Call;", "startTime", "it", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpCall<DataBean> implements ITapHttpCall<DataBean> {
    private final ITapHttpParser<DataBean> httpParser;
    private final ITapHttpRetry httpRetry;
    private final AbsTapHttpParam<?> param;

    /* JADX INFO: renamed from: com.taptap.sdk.kit.internal.http.call.TapHttpCall$enqueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: TapHttpCall.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.kit.internal.http.call.TapHttpCall", f = "TapHttpCall.kt", i = {0}, l = {28, 33}, m = "enqueue", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TapHttpCall<DataBean> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TapHttpCall<DataBean> tapHttpCall, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = tapHttpCall;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.enqueue(0L, this);
        }
    }

    /* JADX INFO: renamed from: com.taptap.sdk.kit.internal.http.call.TapHttpCall$enqueueInner$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapHttpCall.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.kit.internal.http.call.TapHttpCall", f = "TapHttpCall.kt", i = {0, 1}, l = {57, 222}, m = "enqueueInner", n = {"this", "this"}, s = {"L$0", "L$0"})
    static final class C01001 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ TapHttpCall<DataBean> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01001(TapHttpCall<DataBean> tapHttpCall, Continuation<? super C01001> continuation) {
            super(continuation);
            this.this$0 = tapHttpCall;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.enqueueInner(0L, this);
        }
    }

    public TapHttpCall(AbsTapHttpParam<?> param, ITapHttpParser<DataBean> httpParser, ITapHttpRetry httpRetry) {
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(httpParser, "httpParser");
        Intrinsics.checkNotNullParameter(httpRetry, "httpRetry");
        this.param = param;
        this.httpParser = httpParser;
        this.httpRetry = httpRetry;
    }

    @Override // com.taptap.sdk.kit.internal.http.call.ITapHttpCall
    /* JADX INFO: renamed from: enqueueResult-gIAlu-s */
    public Object mo33enqueueResultgIAlus(long j, Continuation<? super Result<? extends DataBean>> continuation) {
        return ITapHttpCall.DefaultImpls.m35enqueueResultgIAlus(this, j, continuation);
    }

    @Override // com.taptap.sdk.kit.internal.http.call.ITapHttpCall
    /* JADX INFO: renamed from: executeResult-IoAF18A */
    public Object mo34executeResultIoAF18A(long j) {
        return ITapHttpCall.DefaultImpls.m37executeResultIoAF18A(this, j);
    }

    public final AbsTapHttpParam<?> getParam() {
        return this.param;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.taptap.sdk.kit.internal.http.call.ITapHttpCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object enqueue(long r9, kotlin.coroutines.Continuation<? super DataBean> r11) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.taptap.sdk.kit.internal.http.call.TapHttpCall.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.taptap.sdk.kit.internal.http.call.TapHttpCall$enqueue$1 r0 = (com.taptap.sdk.kit.internal.http.call.TapHttpCall.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.taptap.sdk.kit.internal.http.call.TapHttpCall$enqueue$1 r0 = new com.taptap.sdk.kit.internal.http.call.TapHttpCall$enqueue$1
            r0.<init>(r8, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3f
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r11)
            goto L67
        L2d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L35:
            java.lang.Object r9 = r0.L$0
            com.taptap.sdk.kit.internal.http.call.TapHttpCall r9 = (com.taptap.sdk.kit.internal.http.call.TapHttpCall) r9
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L3d
            goto L67
        L3d:
            r10 = move-exception
            goto L4f
        L3f:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L4d
            r0.label = r4     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r11 = r8.enqueueInner(r9, r0)     // Catch: java.lang.Throwable -> L4d
            if (r11 != r1) goto L67
            return r1
        L4d:
            r10 = move-exception
            r9 = r8
        L4f:
            com.taptap.sdk.kit.internal.http.hanlder.ITapHttpRetry r11 = r9.httpRetry
            long r4 = r11.nextRetryMillis(r10)
            r6 = 0
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 < 0) goto L68
            r10 = 0
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r11 = r9.enqueue(r4, r0)
            if (r11 != r1) goto L67
            return r1
        L67:
            return r11
        L68:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.http.call.TapHttpCall.enqueue(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.taptap.sdk.kit.internal.http.call.ITapHttpCall
    public DataBean execute(long delay) {
        try {
            return executeInner(delay);
        } catch (Throwable th) {
            long jNextRetryMillis = this.httpRetry.nextRetryMillis(th);
            if (jNextRetryMillis >= 0) {
                return execute(jNextRetryMillis);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:0|2|(2:4|(1:6)(1:7))(0)|8|(1:(1:(2:12|40)(2:13|14))(1:15))(3:16|(2:18|(1:20))|21)|22|41|23|29|(1:31)|32|(1:34)|35|(1:37)|(1:39)(1:40)) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007a, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
    
        if (r10.isCompleted() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0081, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r10.resumeWith(kotlin.Result.m52constructorimpl(kotlin.ResultKt.createFailure(r3)));
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r3v11, types: [T, com.taptap.sdk.okhttp3.Call] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enqueueInner(long r8, kotlin.coroutines.Continuation<? super DataBean> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.http.call.TapHttpCall.enqueueInner(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onResultFailure(final Call call, final long startTime, final Throwable it, CancellableContinuation<? super DataBean> continuation) {
        if (this.param.enableTechnicalLog$tap_common_release()) {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            TapOpenlogHelper.INSTANCE.reportTechnicalLog("http_performance", new Function0<Map<String, ? extends String>>(this) { // from class: com.taptap.sdk.kit.internal.http.call.TapHttpCall.onResultFailure.1
                final /* synthetic */ TapHttpCall<DataBean> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                    this.this$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends String> invoke() {
                    Map<String, ? extends String> mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("succeed", "0"), TuplesKt.to("module_name", this.this$0.getParam().moduleName$tap_common_release()), TuplesKt.to("version_name", this.this$0.getParam().moduleVersion$tap_common_release()), TuplesKt.to("url", call.request().url().toString()), TuplesKt.to("method", call.request().method()), TuplesKt.to(NSStatReporter.NS_START_TIME, String.valueOf(startTime)), TuplesKt.to("end_time", String.valueOf(jCurrentTimeMillis)));
                    Throwable th = it;
                    if (th instanceof TapHttpException.ServerError) {
                        mapMutableMapOf.put(TapErrorConstants.ERROR_SERVER_ERROR, ((TapHttpException.ServerError) th).getError().getError());
                        mapMutableMapOf.put("http_code", String.valueOf(((TapHttpException.ServerError) it).getHttpCode()));
                    } else if (th instanceof TapHttpException.NoServerError) {
                        mapMutableMapOf.put("http_code", String.valueOf(((TapHttpException.NoServerError) th).getHttpCode()));
                    } else if (th instanceof TapHttpException.ParseDataError) {
                        mapMutableMapOf.put("http_code", String.valueOf(((TapHttpException.ParseDataError) th).getHttpCode()));
                        mapMutableMapOf.put("local_error_msg", "ParseDataError");
                    } else if (th instanceof TapHttpException.UnknownError) {
                        mapMutableMapOf.put("local_error_msg", "UnknownError");
                    }
                    return mapMutableMapOf;
                }
            });
        }
        if (continuation.isCompleted()) {
            return;
        }
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m52constructorimpl(ResultKt.createFailure(it)));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025 A[Catch: all -> 0x0032, TryCatch #0 {, blocks: (B:4:0x0006, B:5:0x0009, B:8:0x0025, B:10:0x002b), top: B:15:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final DataBean executeInner(long r4) throws com.taptap.sdk.kit.internal.http.TapHttpException.UnknownError {
        /*
            r3 = this;
            r0 = 0
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 <= 0) goto L9
            java.lang.Thread.sleep(r4)     // Catch: java.lang.Throwable -> L32
        L9:
            com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam<?> r4 = r3.param     // Catch: java.lang.Throwable -> L32
            com.taptap.sdk.okhttp3.Call r4 = r4.newCall$tap_common_release()     // Catch: java.lang.Throwable -> L32
            com.taptap.sdk.okhttp3.Response r4 = r4.execute()     // Catch: java.lang.Throwable -> L32
            com.taptap.sdk.kit.internal.http.hanlder.ITapHttpParser<DataBean> r5 = r3.httpParser     // Catch: java.lang.Throwable -> L32
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)     // Catch: java.lang.Throwable -> L32
            java.lang.Object r4 = r5.mo39parseIoAF18A(r4)     // Catch: java.lang.Throwable -> L32
            boolean r5 = kotlin.Result.m59isSuccessimpl(r4)     // Catch: java.lang.Throwable -> L32
            if (r5 == 0) goto L25
            return r4
        L25:
            java.lang.Throwable r4 = kotlin.Result.m55exceptionOrNullimpl(r4)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2c
            throw r4     // Catch: java.lang.Throwable -> L32
        L2c:
            com.taptap.sdk.kit.internal.http.TapHttpException$UnknownError r4 = new com.taptap.sdk.kit.internal.http.TapHttpException$UnknownError
            r4.<init>()
            throw r4
        L32:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.http.call.TapHttpCall.executeInner(long):java.lang.Object");
    }
}
