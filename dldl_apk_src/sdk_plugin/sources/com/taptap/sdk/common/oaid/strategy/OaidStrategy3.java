package com.taptap.sdk.common.oaid.strategy;

import android.content.Context;
import com.taptap.sdk.common.oaid.ErrorCode;
import com.taptap.sdk.common.oaid.exception.OAIDException;
import com.taptap.sdk.common.oaid.proxy.IdentifyListenerHandler;
import com.taptap.sdk.common.oaid.version.Versions;
import java.lang.reflect.Proxy;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.joor.Reflect;

/* JADX INFO: compiled from: OaidStrategy3.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J%\u0010\u001d\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lcom/taptap/sdk/common/oaid/strategy/OaidStrategy3;", "Lcom/taptap/sdk/common/oaid/strategy/OAIDStrategy;", "mdidSdkHelperClass", "", "identifierListenerClass", "idSupplierClass", "jLibraryClass", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "identifyListener", "Lorg/joor/Reflect;", "jLibrary", "midSDKHelper", "supplier", "supportedVersion", "", "Lcom/taptap/sdk/common/oaid/version/Versions;", "getSupportedVersion", "()Ljava/util/Set;", "initPemCert", "", "context", "Landroid/content/Context;", "oaidCert", "initSdk", "", "handler", "", "loadClassesByReflect", "", "tryGetOAIDByReflect", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OaidStrategy3 implements OAIDStrategy {
    private final String idSupplierClass;
    private final String identifierListenerClass;
    private Reflect identifyListener;
    private Reflect jLibrary;
    private final String jLibraryClass;
    private final String mdidSdkHelperClass;
    private Reflect midSDKHelper;
    private Reflect supplier;
    private final Set<Versions> supportedVersion;

    /* JADX INFO: compiled from: OaidStrategy3.kt */
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

    public OaidStrategy3(String mdidSdkHelperClass, String identifierListenerClass, String idSupplierClass, String jLibraryClass) {
        Intrinsics.checkNotNullParameter(mdidSdkHelperClass, "mdidSdkHelperClass");
        Intrinsics.checkNotNullParameter(identifierListenerClass, "identifierListenerClass");
        Intrinsics.checkNotNullParameter(idSupplierClass, "idSupplierClass");
        Intrinsics.checkNotNullParameter(jLibraryClass, "jLibraryClass");
        this.mdidSdkHelperClass = mdidSdkHelperClass;
        this.identifierListenerClass = identifierListenerClass;
        this.idSupplierClass = idSupplierClass;
        this.jLibraryClass = jLibraryClass;
        this.supportedVersion = SetsKt.setOf((Object[]) new Versions[]{Versions.V_1_0_6, Versions.V_1_0_8, Versions.V_1_0_9, Versions.V_1_0_10, Versions.V_1_0_11, Versions.V_1_0_13});
    }

    @Override // com.taptap.sdk.common.oaid.strategy.OAIDStrategy
    public boolean loadClassesByReflect() {
        try {
            this.midSDKHelper = Reflect.onClass(this.mdidSdkHelperClass);
            this.identifyListener = Reflect.onClass(this.identifierListenerClass);
            this.supplier = Reflect.onClass(this.idSupplierClass);
            Reflect reflectOnClass = Reflect.onClass(this.jLibraryClass);
            this.jLibrary = reflectOnClass;
            return (this.midSDKHelper == null || this.identifyListener == null || this.supplier == null || reflectOnClass == null) ? false : true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.taptap.sdk.common.oaid.strategy.OAIDStrategy
    public void initPemCert(Context context, String oaidCert) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Reflect reflect = this.midSDKHelper;
            if (reflect != null) {
                reflect.call("InitCert", context, oaidCert);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.taptap.sdk.common.oaid.strategy.OAIDStrategy
    public Object tryGetOAIDByReflect(Context context, String str, Continuation<? super String> continuation) throws Throwable {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
        try {
            initPemCert(context, str);
            Reflect reflect = this.jLibrary;
            if (reflect != null) {
                reflect.call("InitEntry", context);
            }
            ClassLoader classLoader = context.getClassLoader();
            Class[] clsArr = new Class[1];
            Reflect reflect2 = this.identifyListener;
            clsArr[0] = reflect2 != null ? reflect2.type() : null;
            Object objNewProxyInstance = Proxy.newProxyInstance(classLoader, clsArr, new IdentifyListenerHandler(new Function1<String, Unit>() { // from class: com.taptap.sdk.common.oaid.strategy.OaidStrategy3$tryGetOAIDByReflect$2$errCode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                    invoke2(str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2) {
                    Continuation<String> continuation2 = safeContinuation2;
                    Result.Companion companion = Result.INSTANCE;
                    continuation2.resumeWith(Result.m52constructorimpl(str2));
                }
            }, new Function1<Exception, Unit>() { // from class: com.taptap.sdk.common.oaid.strategy.OaidStrategy3$tryGetOAIDByReflect$2$errCode$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Continuation<String> continuation2 = safeContinuation2;
                    Result.Companion companion = Result.INSTANCE;
                    continuation2.resumeWith(Result.m52constructorimpl(ResultKt.createFailure(error)));
                }
            }));
            Intrinsics.checkNotNullExpressionValue(objNewProxyInstance, "continuation ->\n        …  )\n                    )");
            ErrorCode errorCodeFrom = ErrorCode.INSTANCE.from(initSdk(context, objNewProxyInstance));
            int i = WhenMappings.$EnumSwitchMapping$0[errorCodeFrom.ordinal()];
            if (i != 1 && i != 2) {
                Result.Companion companion = Result.INSTANCE;
                safeContinuation2.resumeWith(Result.m52constructorimpl(ResultKt.createFailure(new OAIDException(errorCodeFrom))));
            }
        } catch (Exception e) {
            Result.Companion companion2 = Result.INSTANCE;
            safeContinuation2.resumeWith(Result.m52constructorimpl(ResultKt.createFailure(e)));
        }
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    @Override // com.taptap.sdk.common.oaid.strategy.OAIDStrategy
    public Set<Versions> getSupportedVersion() {
        return this.supportedVersion;
    }

    private final int initSdk(Context context, Object handler) {
        Reflect reflect = this.midSDKHelper;
        if (reflect == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Object obj = reflect.call("InitSdk", context, true, handler).get();
        Intrinsics.checkNotNullExpressionValue(obj, "checkNotNull(midSDKHelpe…ndler)\n            .get()");
        return ((Number) obj).intValue();
    }
}
