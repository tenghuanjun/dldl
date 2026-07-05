package com.taptap.sdk.db.biz.iap.lib2plus;

import android.content.Context;
import com.taptap.sdk.db.biz.iap.common.IIAPTracker;
import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import com.taptap.sdk.kit.internal.TapLogger;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BillingLib2PlusTracker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/BillingLib2PlusTracker;", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker;", "()V", "BILLING_CLIENT_PURCHASE_NAME", "", "TAG", "listener", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker$ILogPurchaseListener;", "logPurchase", "", "setOnLogPurchaseListener", "l", "startTracker", "context", "Landroid/content/Context;", "stopTracker", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BillingLib2PlusTracker implements IIAPTracker {
    private static final String TAG = "BillingLib2PlusTracker";
    private static IIAPTracker.ILogPurchaseListener listener;
    public static final BillingLib2PlusTracker INSTANCE = new BillingLib2PlusTracker();
    private static final String BILLING_CLIENT_PURCHASE_NAME = BillingClientConstants.CLASSNAME_PURCHASE;

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void stopTracker() {
    }

    private BillingLib2PlusTracker() {
    }

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void startTracker(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        TapLogger.logd(TAG, "BillingLib2PlusTracker startTracker");
        if (InAppPurchaseUtils.getClass(BILLING_CLIENT_PURCHASE_NAME) == null) {
            return;
        }
        TapLogger.logd(TAG, "BillingLib2PlusTracker purchase class enabled");
        InAppPurchaseBillingClientWrapper orCreateInstance = InAppPurchaseBillingClientWrapper.INSTANCE.getOrCreateInstance(context);
        if (orCreateInstance == null) {
            return;
        }
        TapLogger.logd(TAG, "BillingLib2PlusTracker billingClientWrapper enabled");
        if (InAppPurchaseBillingClientWrapper.INSTANCE.isServiceConnected().get()) {
            if (InAppPurchaseLoggerManager.eligibleQueryPurchaseHistory()) {
                orCreateInstance.queryPurchaseHistory(BillingClientConstants.IN_APP, new Runnable() { // from class: com.taptap.sdk.db.biz.iap.lib2plus.-$$Lambda$BillingLib2PlusTracker$hKjDkUbk4_A9PjdfFXmpEJvrUHc
                    @Override // java.lang.Runnable
                    public final void run() {
                        BillingLib2PlusTracker.startTracker$lambda$0();
                    }
                });
            } else {
                orCreateInstance.queryPurchase(BillingClientConstants.IN_APP, new Runnable() { // from class: com.taptap.sdk.db.biz.iap.lib2plus.-$$Lambda$BillingLib2PlusTracker$gy3pbaE02QjRxJWIZhsOTB-Lpek
                    @Override // java.lang.Runnable
                    public final void run() {
                        BillingLib2PlusTracker.startTracker$lambda$1();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTracker$lambda$0() {
        INSTANCE.logPurchase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTracker$lambda$1() {
        INSTANCE.logPurchase();
    }

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void setOnLogPurchaseListener(IIAPTracker.ILogPurchaseListener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        listener = l;
    }

    private final void logPurchase() {
        IIAPTracker.ILogPurchaseListener iLogPurchaseListener;
        for (Map.Entry<String, String> entry : InAppPurchaseLoggerManager.filterPurchaseLogging(InAppPurchaseBillingClientWrapper.INSTANCE.getPurchaseDetailsMap(), InAppPurchaseBillingClientWrapper.INSTANCE.getSkuDetailsMap()).entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null && (iLogPurchaseListener = listener) != null) {
                iLogPurchaseListener.onLogPurchase(key, value, false);
            }
        }
        InAppPurchaseBillingClientWrapper.INSTANCE.getPurchaseDetailsMap().clear();
    }
}
