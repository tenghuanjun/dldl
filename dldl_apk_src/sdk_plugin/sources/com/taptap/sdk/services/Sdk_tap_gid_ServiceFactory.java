package com.taptap.sdk.services;

import com.taptap.sdk.common.services.GidService;
import com.taptap.sdk.gid.GidServiceImpl;
import com.taptap.sdk.servicemanager.ServiceManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: Sdk_tap_gid_ServiceFactory.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00050\u00070\u0004¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/services/Sdk_tap_gid_ServiceFactory;", "", "()V", "provide", "", "Ljava/lang/Class;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Sdk_tap_gid_ServiceFactory {
    public final Map<Class<? extends ServiceManager.Service>, Set<Class<? extends ServiceManager.Service>>> provide() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Object objEmptySet = linkedHashMap.get(GidService.class);
        if (objEmptySet == null) {
            objEmptySet = SetsKt.emptySet();
            linkedHashMap.put(GidService.class, objEmptySet);
        }
        Pair pair = TuplesKt.to(GidService.class, SetsKt.plus((Set<? extends Class>) objEmptySet, GidServiceImpl.class));
        linkedHashMap.put(pair.getFirst(), pair.getSecond());
        return linkedHashMap;
    }
}
