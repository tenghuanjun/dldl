package com.taptap.sdk.servicemanager.services;

import com.huya.ciku.apm.model.LinkMicData;
import com.taptap.sdk.servicemanager.ServiceManager;
import com.taptap.sdk.servicemanager.utils.ReflectUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ServiceFactoryCollector.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00050\u00070\u0004JV\u0010\b\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00070\u0004\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000b0\u00042\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000b0\u0004H\u0002¨\u0006\r"}, d2 = {"Lcom/taptap/sdk/servicemanager/services/ServiceFactoryCollector;", "", "()V", "collect", "", "Ljava/lang/Class;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "", "merge", "K", LinkMicData.STAGE_V, "", "other", "tap-servicemanager_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ServiceFactoryCollector {
    public final Map<Class<? extends ServiceManager.Service>, Set<Class<? extends ServiceManager.Service>>> collect() {
        return merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(merge(MapsKt.emptyMap(), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_compliance_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_core_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_db_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_gid_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_initializer_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_license_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_login_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Sdk_tap_openlog_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Base_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Core_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Third_party_payment_alipay_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Third_party_payment_alipaycn_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Third_party_payment_braintree_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Third_party_payment_stripe_ServiceFactory")), ReflectUtils.INSTANCE.provide("com.taptap.sdk.services.Third_party_payment_wechat_ServiceFactory"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <K, V> Map<K, Set<V>> merge(Map<K, ? extends Collection<? extends V>> map, Map<K, ? extends Collection<? extends V>> map2) {
        Set setPlus = SetsKt.plus((Set) map.keySet(), (Iterable) map2.keySet());
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setPlus, 10)), 16));
        for (Object obj : setPlus) {
            linkedHashMap.put(obj, CollectionsKt.filterNotNull(SetsKt.setOf((Object[]) new Collection[]{map.get(obj), map2.get(obj)})));
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
        Iterator<T> it = linkedHashMap2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap3.put(entry.getKey(), CollectionsKt.toSet(CollectionsKt.flatten((List) entry.getValue())));
        }
        return linkedHashMap3;
    }
}
