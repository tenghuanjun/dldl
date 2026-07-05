package com.taptap.sdk.common.oaid.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.joor.Reflect;

/* JADX INFO: compiled from: IdentifyListenerHandler.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BQ\u0012#\u0010\u0002\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u0012%\u0010\t\u001a!\u0012\u0017\u0012\u00150\nj\u0002`\u000b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011H\u0002¢\u0006\u0002\u0010\u0013J0\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011H\u0096\u0002¢\u0006\u0002\u0010\u0018R-\u0010\t\u001a!\u0012\u0017\u0012\u00150\nj\u0002`\u000b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0002\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/taptap/sdk/common/oaid/proxy/IdentifyListenerHandler;", "Ljava/lang/reflect/InvocationHandler;", "onSuccess", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "oaid", "", "onFail", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getOaidByReflect", "methodName", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "invoke", "proxy", "method", "Ljava/lang/reflect/Method;", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class IdentifyListenerHandler implements InvocationHandler {
    private final Function1<Exception, Unit> onFail;
    private final Function1<String, Unit> onSuccess;

    /* JADX WARN: Multi-variable type inference failed */
    public IdentifyListenerHandler(Function1<? super String, Unit> onSuccess, Function1<? super Exception, Unit> onFail) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFail, "onFail");
        this.onSuccess = onSuccess;
        this.onFail = onFail;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            Function1<String, Unit> function1 = this.onSuccess;
            String name = method.getName();
            Intrinsics.checkNotNullExpressionValue(name, "method.name");
            function1.invoke(getOaidByReflect(name, args));
            return null;
        } catch (Exception e) {
            this.onFail.invoke(e);
            return null;
        }
    }

    private final String getOaidByReflect(String methodName, Object[] args) {
        if (Intrinsics.areEqual(methodName, "OnSupport")) {
            Object obj = args[0];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
            if (((Boolean) obj).booleanValue()) {
                return (String) Reflect.on(args[1]).call("getOAID").get();
            }
            return null;
        }
        if (Intrinsics.areEqual(methodName, "onSupport")) {
            return (String) Reflect.on(args[0]).call("getOAID").get();
        }
        return null;
    }
}
