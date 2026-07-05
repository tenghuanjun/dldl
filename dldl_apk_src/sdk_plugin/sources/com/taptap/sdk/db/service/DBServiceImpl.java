package com.taptap.sdk.db.service;

import com.taptap.sdk.core.DBService;
import com.taptap.sdk.core.TapTapPurchasedEvent;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.event.DeviceLoginEvent;
import com.taptap.sdk.db.event.PurchasedEvent;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBServiceImpl.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/taptap/sdk/db/service/DBServiceImpl;", "Lcom/taptap/sdk/core/DBService;", "()V", "getCurrentGAID", "", "getCurrentOAID", "sendDeviceLoginEvent", "", "option", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "trackPaymentPurchase", "event", "Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DBServiceImpl implements DBService {
    @Override // com.taptap.sdk.core.DBService
    public String getCurrentOAID() {
        return TapDB.INSTANCE.getInstance().getCurrentOAID();
    }

    @Override // com.taptap.sdk.core.DBService
    public String getCurrentGAID() {
        return TapDB.INSTANCE.getInstance().getCurrentGAID();
    }

    @Override // com.taptap.sdk.core.DBService
    public void sendDeviceLoginEvent(TapTapSdkOptions option) {
        Intrinsics.checkNotNullParameter(option, "option");
        new DeviceLoginEvent.Builder(option.getProperties()).isAutomatically(true).build().submitNow();
    }

    @Override // com.taptap.sdk.core.DBService
    public void trackPaymentPurchase(TapTapPurchasedEvent event) {
        if (event != null) {
            new PurchasedEvent.Builder(event.getProperties()).isAutomatically(false).setOrderId(event.getOrderId()).setProductName(event.getProductName()).setAmount(event.getAmount()).setCurrencyType(event.getCurrencyType()).setPaymentMethod(event.getPaymentMethod()).build().submit();
        }
    }
}
