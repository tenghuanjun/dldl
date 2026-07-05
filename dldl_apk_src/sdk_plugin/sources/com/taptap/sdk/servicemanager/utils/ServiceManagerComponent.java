package com.taptap.sdk.servicemanager.utils;

import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceManagerComponent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006H\u0086\bJ\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006H\u0086\b¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/servicemanager/utils/ServiceManagerComponent;", "", "()V", "inject", "Lkotlin/Lazy;", "T", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "injectOrNull", "tap-servicemanager_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ServiceManagerComponent {
    public static final ServiceManagerComponent INSTANCE = new ServiceManagerComponent();

    private ServiceManagerComponent() {
    }

    public final /* synthetic */ <T extends ServiceManager.Service> Lazy<T> inject() {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: com.taptap.sdk.servicemanager.utils.ServiceManagerComponent.inject.1
            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.jvm.functions.Function0
            public final ServiceManager.Service invoke() {
                ServiceManager.Companion companion = ServiceManager.INSTANCE;
                Intrinsics.reifiedOperationMarker(4, "T");
                ServiceManager.Service service = companion.getService(ServiceManager.Service.class);
                Intrinsics.reifiedOperationMarker(1, "T");
                return service;
            }
        });
    }

    public final /* synthetic */ <T extends ServiceManager.Service> Lazy<T> injectOrNull() {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Function0<T>() { // from class: com.taptap.sdk.servicemanager.utils.ServiceManagerComponent.injectOrNull.1
            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.jvm.functions.Function0
            public final ServiceManager.Service invoke() {
                ServiceManager.Companion companion = ServiceManager.INSTANCE;
                Intrinsics.reifiedOperationMarker(4, "T");
                return companion.getService(ServiceManager.Service.class);
            }
        });
    }
}
