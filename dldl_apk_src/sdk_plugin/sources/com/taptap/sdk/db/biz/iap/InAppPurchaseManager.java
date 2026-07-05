package com.taptap.sdk.db.biz.iap;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import com.sqwan.bugless.util.FileUtil;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleTracker;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.biz.iap.common.AutomaticAnalyticsLogger;
import com.taptap.sdk.db.biz.iap.common.IIAPTracker;
import com.taptap.sdk.db.biz.iap.lib2minus.BillingLib2MinusTracker;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingLib2PlusTracker;
import com.taptap.sdk.kit.internal.TapLogger;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InAppPurchaseManager.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u000eH\u0007J\b\u0010\u0012\u001a\u00020\u000eH\u0007J\b\u0010\u0013\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/InAppPurchaseManager;", "", "()V", "GOOGLE_BILLINGCLIENT_VERSION", "", "TAG", "enabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "lifecycleCallbacks", "com/taptap/sdk/db/biz/iap/InAppPurchaseManager$lifecycleCallbacks$1", "Lcom/taptap/sdk/db/biz/iap/InAppPurchaseManager$lifecycleCallbacks$1;", "tracker", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker;", "setEnableAutoLogging", "", "enable", "", "startTracking", "stopTracking", "usingBillingLib2Plus", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class InAppPurchaseManager {
    private static final String GOOGLE_BILLINGCLIENT_VERSION = "com.google.android.play.billingclient.version";
    private static final String TAG = "InAppPurchaseManager";
    private static IIAPTracker tracker;
    public static final InAppPurchaseManager INSTANCE = new InAppPurchaseManager();
    private static final AtomicBoolean enabled = new AtomicBoolean(false);
    private static final InAppPurchaseManager$lifecycleCallbacks$1 lifecycleCallbacks = new TapActivityLifecycleCallbacks() { // from class: com.taptap.sdk.db.biz.iap.InAppPurchaseManager$lifecycleCallbacks$1
        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(outState, "outState");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks
        public void onBackground(Context context) {
            TapActivityLifecycleCallbacks.DefaultImpls.onBackground(this, context);
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks
        public void onForeground(Context context) {
            TapActivityLifecycleCallbacks.DefaultImpls.onForeground(this, context);
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            InAppPurchaseManager.startTracking();
        }
    };

    private InAppPurchaseManager() {
    }

    @JvmStatic
    public static final void setEnableAutoLogging(boolean enable) {
        enabled.set(enable);
        TapLogger.logd(TAG, "InAppPurchaseManager setEnableAutoLogging enable=" + enable);
        if (enable) {
            TapActivityLifecycleTracker.INSTANCE.registerActivityLifecycleCallbacks(lifecycleCallbacks);
            startTracking();
        } else {
            TapActivityLifecycleTracker.INSTANCE.unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
            stopTracking();
        }
    }

    @JvmStatic
    public static final void startTracking() {
        BillingLib2MinusTracker billingLib2MinusTracker;
        if (enabled.get()) {
            IIAPTracker iIAPTracker = null;
            if (tracker == null) {
                if (INSTANCE.usingBillingLib2Plus()) {
                    billingLib2MinusTracker = BillingLib2PlusTracker.INSTANCE;
                } else {
                    billingLib2MinusTracker = BillingLib2MinusTracker.INSTANCE;
                }
                tracker = billingLib2MinusTracker;
                if (billingLib2MinusTracker == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tracker");
                    billingLib2MinusTracker = null;
                }
                billingLib2MinusTracker.setOnLogPurchaseListener(new IIAPTracker.ILogPurchaseListener() { // from class: com.taptap.sdk.db.biz.iap.InAppPurchaseManager.startTracking.2
                    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker.ILogPurchaseListener
                    public void onLogPurchase(String purchase, String skuDetails, boolean isSubscription) {
                        Intrinsics.checkNotNullParameter(purchase, "purchase");
                        Intrinsics.checkNotNullParameter(skuDetails, "skuDetails");
                        AutomaticAnalyticsLogger.logPurchase(purchase, skuDetails, isSubscription);
                    }
                });
            }
            IIAPTracker iIAPTracker2 = tracker;
            if (iIAPTracker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tracker");
            } else {
                iIAPTracker = iIAPTracker2;
            }
            iIAPTracker.startTracker(TapDB.INSTANCE.getOptions$tap_db_release().getContext());
        }
    }

    @JvmStatic
    public static final void stopTracking() {
        IIAPTracker iIAPTracker = tracker;
        if (iIAPTracker != null) {
            if (iIAPTracker == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tracker");
                iIAPTracker = null;
            }
            iIAPTracker.stopTracker();
        }
    }

    private final boolean usingBillingLib2Plus() {
        try {
            Context context = TapDB.INSTANCE.getOptions$tap_db_release().getContext();
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "context.packageManager.g…TA_DATA\n                )");
            String string = applicationInfo.metaData.getString(GOOGLE_BILLINGCLIENT_VERSION);
            if (string == null) {
                return false;
            }
            return Integer.parseInt((String) StringsKt.split$default((CharSequence) string, new String[]{FileUtil.FILE_EXTENSION_SEPARATOR}, false, 3, 2, (Object) null).get(0)) >= 2;
        } catch (Exception unused) {
            return false;
        }
    }
}
