package com.taptap.sdk.startup.utils;

import com.taptap.sdk.startup.task.Task;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joor.Reflect;

/* JADX INFO: compiled from: ReflectUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/startup/utils/ReflectUtils;", "", "()V", "create", "", "Lcom/taptap/sdk/startup/task/Task;", "clazz", "", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ReflectUtils {
    public static final ReflectUtils INSTANCE = new ReflectUtils();

    private ReflectUtils() {
    }

    public final List<Task> create(String clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            Object obj = Reflect.onClass(clazz).create().call("create").get();
            Intrinsics.checkNotNullExpressionValue(obj, "{\n            Reflect.on…         .get()\n        }");
            return (List) obj;
        } catch (Throwable unused) {
            return CollectionsKt.emptyList();
        }
    }
}
