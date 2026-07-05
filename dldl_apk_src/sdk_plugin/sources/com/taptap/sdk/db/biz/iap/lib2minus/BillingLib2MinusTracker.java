package com.taptap.sdk.db.biz.iap.lib2minus;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleTracker;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.biz.iap.common.IAPEventUtils;
import com.taptap.sdk.db.biz.iap.common.IIAPTracker;
import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import com.taptap.sdk.db.biz.iap.lib2minus.BillingLib2MinusTracker;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import com.taptap.sdk.kit.internal.executor.TapNetExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BillingLib2MinusTracker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J0\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001ej\b\u0012\u0004\u0012\u00020\u0004`\u001f2\u0006\u0010 \u001a\u00020\u000bH\u0002J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0015H\u0016J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2minus/BillingLib2MinusTracker;", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker;", "()V", "BILLING_ACTIVITY_NAME", "", "SERVICE_INTERFACE_NAME", "TAG", "kotlin.jvm.PlatformType", "callbacks", "Lcom/taptap/sdk/base/utils/lifecycle/TapActivityLifecycleCallbacks;", "hasBillingActivity", "", "Ljava/lang/Boolean;", "hasBillingService", "inAppBillingObj", "", "intent", "Landroid/content/Intent;", "isTracking", "Ljava/util/concurrent/atomic/AtomicBoolean;", "listener", "Lcom/taptap/sdk/db/biz/iap/common/IIAPTracker$ILogPurchaseListener;", "serviceConnection", "Landroid/content/ServiceConnection;", "initializeIfNotInitialized", "", "logPurchase", "context", "Landroid/content/Context;", "purchases", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isSubscription", "setOnLogPurchaseListener", "l", "startTracker", "startTracking", "stopTracker", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BillingLib2MinusTracker implements IIAPTracker {
    private static final String BILLING_ACTIVITY_NAME = "com.android.billingclient.api.ProxyBillingActivity";
    private static final String SERVICE_INTERFACE_NAME = "com.android.vending.billing.IInAppBillingService$Stub";
    private static TapActivityLifecycleCallbacks callbacks;
    private static Boolean hasBillingActivity;
    private static Boolean hasBillingService;
    private static Object inAppBillingObj;
    private static Intent intent;
    private static IIAPTracker.ILogPurchaseListener listener;
    private static ServiceConnection serviceConnection;
    public static final BillingLib2MinusTracker INSTANCE = new BillingLib2MinusTracker();
    private static final String TAG = BillingLib2MinusTracker.class.getCanonicalName();
    private static final AtomicBoolean isTracking = new AtomicBoolean(false);

    private BillingLib2MinusTracker() {
    }

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void startTracker(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        initializeIfNotInitialized();
        if (Intrinsics.areEqual((Object) hasBillingService, (Object) false)) {
            return;
        }
        startTracking();
    }

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void stopTracker() {
        TapActivityLifecycleTracker tapActivityLifecycleTracker = TapActivityLifecycleTracker.INSTANCE;
        TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks = callbacks;
        ServiceConnection serviceConnection2 = null;
        if (tapActivityLifecycleCallbacks == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callbacks");
            tapActivityLifecycleCallbacks = null;
        }
        tapActivityLifecycleTracker.unregisterActivityLifecycleCallbacks(tapActivityLifecycleCallbacks);
        Context context = TapDB.INSTANCE.getOptions$tap_db_release().getContext();
        if (context instanceof Application) {
            try {
                Result.Companion companion = Result.INSTANCE;
                BillingLib2MinusTracker billingLib2MinusTracker = this;
                if (inAppBillingObj != null && serviceConnection != null) {
                    ServiceConnection serviceConnection3 = serviceConnection;
                    if (serviceConnection3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("serviceConnection");
                    } else {
                        serviceConnection2 = serviceConnection3;
                    }
                    context.unbindService(serviceConnection2);
                }
                Result.m52constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m52constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    @Override // com.taptap.sdk.db.biz.iap.common.IIAPTracker
    public void setOnLogPurchaseListener(IIAPTracker.ILogPurchaseListener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        listener = l;
    }

    private final void initializeIfNotInitialized() {
        if (hasBillingService != null) {
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(InAppPurchaseUtils.getClass(SERVICE_INTERFACE_NAME) != null);
        hasBillingService = boolValueOf;
        if (Intrinsics.areEqual((Object) boolValueOf, (Object) false)) {
            return;
        }
        hasBillingActivity = Boolean.valueOf(InAppPurchaseUtils.getClass(BILLING_ACTIVITY_NAME) != null);
        IAPEventUtils.clearSkuDetailsCache();
        Intent intent2 = new Intent("com.android.vending.billing.InAppBillingService.BIND").setPackage("com.android.vending");
        Intrinsics.checkNotNullExpressionValue(intent2, "Intent(\"com.android.vend…ge(\"com.android.vending\")");
        intent = intent2;
        serviceConnection = new ServiceConnection() { // from class: com.taptap.sdk.db.biz.iap.lib2minus.BillingLib2MinusTracker.initializeIfNotInitialized.1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                BillingLib2MinusTracker billingLib2MinusTracker = BillingLib2MinusTracker.INSTANCE;
                BillingLib2MinusTracker.inAppBillingObj = IAPEventUtils.asInterface(TapDB.INSTANCE.getOptions$tap_db_release().getContext(), service);
            }
        };
        callbacks = new AnonymousClass2();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.iap.lib2minus.BillingLib2MinusTracker$initializeIfNotInitialized$2, reason: invalid class name */
    /* JADX INFO: compiled from: BillingLib2MinusTracker.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u000f"}, d2 = {"com/taptap/sdk/db/biz/iap/lib2minus/BillingLib2MinusTracker$initializeIfNotInitialized$2", "Lcom/taptap/sdk/base/utils/lifecycle/TapActivityLifecycleCallbacks;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class AnonymousClass2 implements TapActivityLifecycleCallbacks {
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

        AnonymousClass2() {
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
            try {
                TapNetExecutor.getExecutor().execute(new Runnable() { // from class: com.taptap.sdk.db.biz.iap.lib2minus.-$$Lambda$BillingLib2MinusTracker$initializeIfNotInitialized$2$8CuswJX64B26cSyEFbm0wOtb7gU
                    @Override // java.lang.Runnable
                    public final void run() {
                        BillingLib2MinusTracker.AnonymousClass2.onActivityResumed$lambda$0();
                    }
                });
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onActivityResumed$lambda$0() {
            Context context = TapDB.INSTANCE.getOptions$tap_db_release().getContext();
            BillingLib2MinusTracker.INSTANCE.logPurchase(context, IAPEventUtils.getPurchasesInapp(context, BillingLib2MinusTracker.inAppBillingObj), false);
        }

        @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            try {
                if (Intrinsics.areEqual((Object) BillingLib2MinusTracker.hasBillingActivity, (Object) true) && Intrinsics.areEqual(activity.getLocalClassName(), BillingLib2MinusTracker.BILLING_ACTIVITY_NAME)) {
                    TapNetExecutor.getExecutor().execute(new Runnable() { // from class: com.taptap.sdk.db.biz.iap.lib2minus.-$$Lambda$BillingLib2MinusTracker$initializeIfNotInitialized$2$7pH_wIN27EiGxTtMvR03syYE1Yc
                        @Override // java.lang.Runnable
                        public final void run() {
                            BillingLib2MinusTracker.AnonymousClass2.onActivityStopped$lambda$1();
                        }
                    });
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onActivityStopped$lambda$1() {
            Context context = TapDB.INSTANCE.getOptions$tap_db_release().getContext();
            ArrayList<String> purchasesInapp = IAPEventUtils.getPurchasesInapp(context, BillingLib2MinusTracker.inAppBillingObj);
            if (purchasesInapp.isEmpty()) {
                purchasesInapp = IAPEventUtils.getPurchaseHistoryInapp(context, BillingLib2MinusTracker.inAppBillingObj);
            }
            BillingLib2MinusTracker.INSTANCE.logPurchase(context, purchasesInapp, false);
        }
    }

    private final void startTracking() {
        if (isTracking.compareAndSet(false, true)) {
            TapActivityLifecycleTracker tapActivityLifecycleTracker = TapActivityLifecycleTracker.INSTANCE;
            TapActivityLifecycleCallbacks tapActivityLifecycleCallbacks = callbacks;
            ServiceConnection serviceConnection2 = null;
            if (tapActivityLifecycleCallbacks == null) {
                Intrinsics.throwUninitializedPropertyAccessException("callbacks");
                tapActivityLifecycleCallbacks = null;
            }
            tapActivityLifecycleTracker.registerActivityLifecycleCallbacks(tapActivityLifecycleCallbacks);
            Context context = TapDB.INSTANCE.getOptions$tap_db_release().getContext();
            if (context instanceof Application) {
                Intent intent2 = intent;
                if (intent2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("intent");
                    intent2 = null;
                }
                ServiceConnection serviceConnection3 = serviceConnection;
                if (serviceConnection3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("serviceConnection");
                } else {
                    serviceConnection2 = serviceConnection3;
                }
                context.bindService(intent2, serviceConnection2, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logPurchase(Context context, ArrayList<String> purchases, boolean isSubscription) {
        IIAPTracker.ILogPurchaseListener iLogPurchaseListener;
        if (purchases.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (String purchase : purchases) {
            try {
                String sku = new JSONObject(purchase).getString(BillingClientConstants.PRODUCT_ID);
                Intrinsics.checkNotNullExpressionValue(sku, "sku");
                Intrinsics.checkNotNullExpressionValue(purchase, "purchase");
                map.put(sku, purchase);
                arrayList.add(sku);
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing in-app purchase data.", e);
            }
        }
        for (Map.Entry<String, String> entry : IAPEventUtils.getSkuDetails(context, arrayList, inAppBillingObj, isSubscription).entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String str = (String) map.get(key);
            if (str != null && (iLogPurchaseListener = listener) != null) {
                iLogPurchaseListener.onLogPurchase(str, value, isSubscription);
            }
        }
    }
}
