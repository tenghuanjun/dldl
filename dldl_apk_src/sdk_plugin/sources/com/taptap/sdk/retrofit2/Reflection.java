package com.taptap.sdk.retrofit2;

import android.os.Build;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class Reflection {
    boolean isDefaultMethod(Method method) {
        return false;
    }

    Reflection() {
    }

    @Nullable
    Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object[] objArr) throws Throwable {
        throw new AssertionError();
    }

    String describeMethodParameter(Method method, int i) {
        return "parameter #" + (i + 1);
    }

    static class Java8 extends Reflection {
        Java8() {
        }

        @Override // com.taptap.sdk.retrofit2.Reflection
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // com.taptap.sdk.retrofit2.Reflection
        Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object[] objArr) throws Throwable {
            return DefaultMethodSupport.invoke(method, cls, obj, objArr);
        }

        @Override // com.taptap.sdk.retrofit2.Reflection
        String describeMethodParameter(Method method, int i) {
            Parameter parameter = method.getParameters()[i];
            if (parameter.isNamePresent()) {
                return "parameter '" + parameter.getName() + '\'';
            }
            return super.describeMethodParameter(method, i);
        }
    }

    static final class Android24 extends Reflection {
        Android24() {
        }

        @Override // com.taptap.sdk.retrofit2.Reflection
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // com.taptap.sdk.retrofit2.Reflection
        Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object[] objArr) throws Throwable {
            if (Build.VERSION.SDK_INT < 26) {
                throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
            }
            return DefaultMethodSupport.invoke(method, cls, obj, objArr);
        }
    }
}
