package com.taptap.sdk.kit.internal.enginebridge.internal;

import com.taptap.sdk.kit.internal.enginebridge.IEngineBridgeService;
import com.taptap.sdk.kit.internal.enginebridge.annotation.EngineBridgeService;
import com.taptap.sdk.kit.internal.enginebridge.exception.BridgeExceptionMessage;
import com.taptap.sdk.kit.internal.enginebridge.exception.EngineBridgeException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EngineBridgeReflector.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010&\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\u0006\u0010\u000e\u001a\u00020\u0006R\"\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/internal/BridgeHolder;", "", "()V", "serviceMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Class;", "Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridgeService;", "getBridgeService", "", "serviceName", "", "registerService", "", "serviceClz", "service", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BridgeHolder {
    public static final BridgeHolder INSTANCE = new BridgeHolder();
    private static final ConcurrentHashMap<Class<? extends IEngineBridgeService>, IEngineBridgeService> serviceMap = new ConcurrentHashMap<>();

    private BridgeHolder() {
    }

    public final void registerService(Class<? extends IEngineBridgeService> serviceClz, IEngineBridgeService service) {
        Intrinsics.checkNotNullParameter(serviceClz, "serviceClz");
        Intrinsics.checkNotNullParameter(service, "service");
        serviceMap.put(serviceClz, service);
    }

    public final Map.Entry<Class<? extends IEngineBridgeService>, IEngineBridgeService> getBridgeService(String serviceName) {
        Object obj;
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Set<Map.Entry<Class<? extends IEngineBridgeService>, IEngineBridgeService>> setEntrySet = serviceMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "serviceMap.entries");
        Iterator<T> it = setEntrySet.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            EngineBridgeService engineBridgeService = (EngineBridgeService) ((Class) ((Map.Entry) next).getKey()).getAnnotation(EngineBridgeService.class);
            if (Intrinsics.areEqual(serviceName, engineBridgeService != null ? engineBridgeService.value() : null)) {
                obj = next;
                break;
            }
        }
        Map.Entry<Class<? extends IEngineBridgeService>, IEngineBridgeService> entry = (Map.Entry) obj;
        if (entry != null) {
            return entry;
        }
        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_SERVICE_ERROR.getMessage());
    }
}
