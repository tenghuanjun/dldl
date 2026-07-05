package com.taptap.sdk.common.oaid.helper;

import android.content.Context;
import android.util.Log;
import com.duowan.networkmars.hysignal.HySignalExecutor;
import com.taptap.sdk.common.oaid.ErrorCode;
import com.taptap.sdk.common.oaid.cert.OAIDCertProvider;
import com.taptap.sdk.common.oaid.exception.OAIDException;
import com.taptap.sdk.common.oaid.strategy.OAIDStrategy;
import com.taptap.sdk.common.oaid.strategy.OAIDStrategy1;
import com.taptap.sdk.common.oaid.strategy.OAIDStrategy2;
import com.taptap.sdk.common.oaid.strategy.OaidStrategy3;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: OAIDHelper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J=\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0010\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\nH\u0002J\u0014\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002J\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/taptap/sdk/common/oaid/helper/OAIDHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "strategies", "", "Lcom/taptap/sdk/common/oaid/strategy/OAIDStrategy;", "strategy", "initialize", "", "loadNativeSdk", "retry", "T", "maxRetryCount", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectStrategy", "shouldRetry", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "tryGetOaidByReflect", "", "certProvider", "Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;", "(Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OAIDHelper {
    private static final String TAG = OAIDHelper.class.getSimpleName();
    public static final long TIMEOUT = 5000;
    private final Context context;
    private final List<OAIDStrategy> strategies;
    private OAIDStrategy strategy;

    /* JADX INFO: compiled from: OAIDHelper.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorCode.values().length];
            try {
                iArr[ErrorCode.INIT_INFO_RESULT_OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ErrorCode.INIT_INFO_RESULT_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.taptap.sdk.common.oaid.helper.OAIDHelper$retry$1, reason: invalid class name */
    /* JADX INFO: compiled from: OAIDHelper.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.oaid.helper.OAIDHelper", f = "OAIDHelper.kt", i = {0, 0, 0, 0}, l = {106}, m = "retry", n = {"this", "block", "maxRetryCount", HySignalExecutor.DEFAULT_RETRYCOUNT_KEY}, s = {"L$0", "L$1", "I$0", "I$1"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OAIDHelper.this.retry(0, null, this);
        }
    }

    public OAIDHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.strategies = CollectionsKt.listOf((Object[]) new OAIDStrategy[]{new OAIDStrategy1("com.bun.miitmdid.core.MdidSdkHelper", "com.bun.miitmdid.interfaces.IIdentifierListener", "com.bun.miitmdid.interfaces.IdSupplier"), new OAIDStrategy2("com.bun.miitmdid.core.MdidSdkHelper", "com.bun.supplier.IIdentifierListener", "com.bun.supplier.IdSupplier", "com.bun.miitmdid.core.JLibrary"), new OaidStrategy3("com.bun.miitmdid.core.MdidSdkHelper", "com.bun.miitmdid.core.IIdentifierListener", "com.bun.miitmdid.core.IIdentifierListener", "com.bun.miitmdid.core.JLibrary")});
    }

    public final void initialize() {
        loadNativeSdk();
        selectStrategy();
    }

    private final void loadNativeSdk() {
        try {
            System.loadLibrary("msaoaidsec");
        } catch (Throwable unused) {
        }
    }

    private final void selectStrategy() {
        Object next;
        Iterator<T> it = this.strategies.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((OAIDStrategy) next).loadClassesByReflect()) {
                    break;
                }
            }
        }
        this.strategy = (OAIDStrategy) next;
    }

    /* JADX INFO: renamed from: com.taptap.sdk.common.oaid.helper.OAIDHelper$tryGetOaidByReflect$2, reason: invalid class name */
    /* JADX INFO: compiled from: OAIDHelper.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.common.oaid.helper.OAIDHelper$tryGetOaidByReflect$2", f = "OAIDHelper.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ OAIDCertProvider $certProvider;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(OAIDCertProvider oAIDCertProvider, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$certProvider = oAIDCertProvider;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OAIDHelper.this.new AnonymousClass2(this.$certProvider, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    OAIDStrategy oAIDStrategy = OAIDHelper.this.strategy;
                    if (oAIDStrategy == null) {
                        return null;
                    }
                    OAIDHelper oAIDHelper = OAIDHelper.this;
                    OAIDHelper$tryGetOaidByReflect$2$1$1 oAIDHelper$tryGetOaidByReflect$2$1$1 = new OAIDHelper$tryGetOaidByReflect$2$1$1(oAIDStrategy, oAIDHelper, this.$certProvider, null);
                    this.label = 1;
                    obj = oAIDHelper.retry(3, oAIDHelper$tryGetOaidByReflect$2$1$1, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return (String) obj;
            } catch (Exception e) {
                Log.d(OAIDHelper.TAG, "获取OAID失败: " + e.getMessage(), e);
                return null;
            }
        }
    }

    public final Object tryGetOaidByReflect(OAIDCertProvider oAIDCertProvider, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(oAIDCertProvider, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056 A[PHI: r10
  0x0056: PHI (r10v10 java.lang.Object) = (r10v6 java.lang.Object), (r10v1 java.lang.Object) binds: [B:20:0x0053, B:12:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0058 -> B:25:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object retry(int r8, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super T> r10) throws java.lang.Exception {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.taptap.sdk.common.oaid.helper.OAIDHelper.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            com.taptap.sdk.common.oaid.helper.OAIDHelper$retry$1 r0 = (com.taptap.sdk.common.oaid.helper.OAIDHelper.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.taptap.sdk.common.oaid.helper.OAIDHelper$retry$1 r0 = new com.taptap.sdk.common.oaid.helper.OAIDHelper$retry$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L40
            if (r2 != r3) goto L38
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r4 = r0.L$0
            com.taptap.sdk.common.oaid.helper.OAIDHelper r4 = (com.taptap.sdk.common.oaid.helper.OAIDHelper) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L36
            goto L56
        L36:
            r10 = move-exception
            goto L5d
        L38:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L40:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 0
            r4 = r7
        L45:
            r0.L$0 = r4     // Catch: java.lang.Exception -> L57
            r0.L$1 = r9     // Catch: java.lang.Exception -> L57
            r0.I$0 = r8     // Catch: java.lang.Exception -> L57
            r0.I$1 = r10     // Catch: java.lang.Exception -> L57
            r0.label = r3     // Catch: java.lang.Exception -> L57
            java.lang.Object r10 = r9.invoke(r0)     // Catch: java.lang.Exception -> L57
            if (r10 != r1) goto L56
            return r1
        L56:
            return r10
        L57:
            r2 = move-exception
            r6 = r9
            r9 = r8
            r8 = r10
            r10 = r2
            r2 = r6
        L5d:
            boolean r5 = r4.shouldRetry(r10)
            if (r5 == 0) goto L81
            if (r8 >= r9) goto L6a
            int r10 = r8 + 1
            r8 = r9
            r9 = r2
            goto L45
        L6a:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "请求失败，已达到最大重试次数: "
            r10.append(r0)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L81:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.common.oaid.helper.OAIDHelper.retry(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean shouldRetry(Exception error) {
        if (error instanceof OAIDException) {
            ErrorCode errorCode = ((OAIDException) error).getErrorCode();
            int i = errorCode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[errorCode.ordinal()];
            if (i != 1 && i != 2) {
                return true;
            }
        }
        return false;
    }
}
