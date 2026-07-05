package com.taptap.sdk.core;

import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: DBService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/core/DBService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "getCurrentGAID", "", "getCurrentOAID", "sendDeviceLoginEvent", "", "option", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "trackPaymentPurchase", "event", "Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "tap-db-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface DBService extends ServiceManager.Service {
    String getCurrentGAID();

    String getCurrentOAID();

    void sendDeviceLoginEvent(com.taptap.sdk.initializer.api.option.TapTapSdkOptions option);

    void trackPaymentPurchase(TapTapPurchasedEvent event);
}
