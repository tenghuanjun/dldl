package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ServiceResult;", "", "service_list", "Lcom/cy/yyjia/zhe28/domain/ServiceResult$ServiceList;", "service_time", "", "(Lcom/cy/yyjia/zhe28/domain/ServiceResult$ServiceList;Ljava/lang/String;)V", "getService_list", "()Lcom/cy/yyjia/zhe28/domain/ServiceResult$ServiceList;", "getService_time", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "ServiceList", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ServiceResult {
    public static final int $stable = 0;
    private final ServiceList service_list;
    private final String service_time;

    public static /* synthetic */ ServiceResult copy$default(ServiceResult serviceResult, ServiceList serviceList, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            serviceList = serviceResult.service_list;
        }
        if ((i & 2) != 0) {
            str = serviceResult.service_time;
        }
        return serviceResult.copy(serviceList, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ServiceList getService_list() {
        return this.service_list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getService_time() {
        return this.service_time;
    }

    public final ServiceResult copy(ServiceList service_list, String service_time) {
        Intrinsics.checkNotNullParameter(service_list, "service_list");
        Intrinsics.checkNotNullParameter(service_time, "service_time");
        return new ServiceResult(service_list, service_time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceResult)) {
            return false;
        }
        ServiceResult serviceResult = (ServiceResult) other;
        return Intrinsics.areEqual(this.service_list, serviceResult.service_list) && Intrinsics.areEqual(this.service_time, serviceResult.service_time);
    }

    public int hashCode() {
        return (this.service_list.hashCode() * 31) + this.service_time.hashCode();
    }

    public String toString() {
        return "ServiceResult(service_list=" + this.service_list + ", service_time=" + this.service_time + ")";
    }

    public ServiceResult(ServiceList service_list, String service_time) {
        Intrinsics.checkNotNullParameter(service_list, "service_list");
        Intrinsics.checkNotNullParameter(service_time, "service_time");
        this.service_list = service_list;
        this.service_time = service_time;
    }

    public final ServiceList getService_list() {
        return this.service_list;
    }

    public final String getService_time() {
        return this.service_time;
    }

    /* JADX INFO: compiled from: ServiceResult.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ServiceResult$ServiceList;", "", "service_name", "", "(Ljava/lang/String;)V", "getService_name", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ServiceList {
        public static final int $stable = 0;
        private final String service_name;

        public ServiceList(String service_name) {
            Intrinsics.checkNotNullParameter(service_name, "service_name");
            this.service_name = service_name;
        }

        public final String getService_name() {
            return this.service_name;
        }
    }
}
