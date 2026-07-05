package com.taptap.sdk.servicemanager.utils;

import com.taptap.sdk.servicemanager.ServiceManager;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joor.Reflect;

/* JADX INFO: compiled from: ReflectUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00050\u00070\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/servicemanager/utils/ReflectUtils;", "", "()V", "provide", "", "Ljava/lang/Class;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "", "clazz", "", "tap-servicemanager_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ReflectUtils {
    public static final ReflectUtils INSTANCE = new ReflectUtils();

    private ReflectUtils() {
    }

    public final Map<Class<? extends ServiceManager.Service>, Set<Class<? extends ServiceManager.Service>>> provide(String clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            Object obj = Reflect.onClass(clazz).create().call("provide").get();
            Intrinsics.checkNotNullExpressionValue(obj, "{\n            Reflect.on…         .get()\n        }");
            return (Map) obj;
        } catch (Throwable unused) {
            return MapsKt.emptyMap();
        }
    }
}
