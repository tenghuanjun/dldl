package com.taptap.sdk.common.services;

import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: AuthorizationService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/common/services/AuthorizationService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "obtainAuthorization", "", "url", "method", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface AuthorizationService extends ServiceManager.Service {
    String obtainAuthorization(String url, String method);
}
