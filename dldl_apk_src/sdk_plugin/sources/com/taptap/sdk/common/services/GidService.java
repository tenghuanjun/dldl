package com.taptap.sdk.common.services;

import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GidService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/services/GidService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "getCurrentGid", "", "getGid", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTdid", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GidService extends ServiceManager.Service {
    String getCurrentGid();

    Object getGid(Continuation<? super String> continuation);

    Object getTdid(Continuation<? super String> continuation);
}
