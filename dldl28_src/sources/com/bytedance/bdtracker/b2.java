package com.bytedance.bdtracker;

import com.bytedance.applog.InitConfig;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final class b2 {
    public static final b2 b = new b2();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<InitConfig, Boolean> f218a = new LinkedHashMap();

    @JvmStatic
    public static final boolean a(InitConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        Boolean bool = f218a.get(config);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @JvmStatic
    public static final Object b(InitConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        Map<InitConfig, Boolean> map = f218a;
        Boolean bool = map.get(config);
        if (bool != null) {
            return bool;
        }
        map.put(config, true);
        return Unit.INSTANCE;
    }
}
