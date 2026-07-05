package com.taptap.sdk.db.biz.iap.common;

import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: compiled from: IIAPTracker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker;", "", "setOnLogPurchaseListener", "", "l", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker$ILogPurchaseListener;", "startTracker", "context", "Landroid/content/Context;", "stopTracker", "ILogPurchaseListener", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface IIAPTracker {

    /* JADX INFO: compiled from: IIAPTracker.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker$ILogPurchaseListener;", "", "onLogPurchase", "", "purchaseJson", "", "skuDetailsJson", "isSubscription", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface ILogPurchaseListener {
        void onLogPurchase(String purchaseJson, String skuDetailsJson, boolean isSubscription);
    }

    void setOnLogPurchaseListener(ILogPurchaseListener l);

    void startTracker(Context context);

    void stopTracker();
}
