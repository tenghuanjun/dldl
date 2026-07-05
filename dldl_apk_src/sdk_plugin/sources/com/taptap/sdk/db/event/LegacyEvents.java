package com.taptap.sdk.db.event;

import com.taptap.sdk.core.TapTapPurchasedEvent;
import com.taptap.sdk.db.event.PurchasedEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyEvents.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/db/event/LegacyEvents;", "", "()V", "submitPurchaseEvent", "", "event", "Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class LegacyEvents {
    public static final LegacyEvents INSTANCE = new LegacyEvents();

    private LegacyEvents() {
    }

    public final void submitPurchaseEvent(TapTapPurchasedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        new PurchasedEvent.Builder(event.getProperties()).setOrderId(event.getOrderId()).setProductName(event.getProductName()).setAmount(event.getAmount()).setCurrencyType(event.getCurrencyType()).setPaymentMethod(event.getPaymentMethod()).isAutomatically(true).build().submit();
    }
}
