package com.taptap.sdk.db.biz.iap.lib2plus;

import android.content.Context;
import com.taptap.sdk.core.internal.event.iap.lib2plus.InAppPurchaseQueryDetailsWrapper;
import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import com.taptap.sdk.db.biz.iap.lib2plus.ProductDetailsParamsWrapper;
import com.taptap.sdk.db.biz.iap.lib2plus.SkuDetailsParamsWrapper;
import com.taptap.sdk.kit.internal.TapLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 :2\u00020\u0001:\u00079:;<=>?B¹\u0002\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\f\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0012\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0012\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\b\u0010!\u001a\u0004\u0018\u00010\"\u0012\b\u0010#\u001a\u0004\u0018\u00010$¢\u0006\u0002\u0010%J(\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u000e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0-2\u0006\u0010.\u001a\u00020/H\u0002J\u0016\u00100\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u0006\u00101\u001a\u00020/J.\u00102\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u001c\b\u0002\u00103\u001a\u0016\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010-\u0012\u0004\u0012\u00020*\u0018\u000104H\u0002J\u0016\u00105\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u0006\u00106\u001a\u00020/J\u0018\u00107\u001a\u00020*2\u0006\u0010+\u001a\u00020(2\u0006\u0010.\u001a\u00020/H\u0002J\b\u00108\u001a\u00020*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;", "", "context", "Landroid/content/Context;", "billingClient", "billingClientClazz", "Ljava/lang/Class;", "purchaseResultClazz", "purchaseClazz", "purchaseResponseListenerClazz", "skuDetailsClazz", "productDetailsClazz", "productDetailsOneTimePurchaseOfferDetailsClazz", "purchaseHistoryRecordClazz", "skuDetailsResponseListenerClazz", "productDetailsResponseListenerClazz", "purchaseHistoryResponseListenerClazz", "queryPurchasesMethod", "Ljava/lang/reflect/Method;", "queryPurchasesStringAsyncMethod", "queryPurchasesParamsAsyncMethod", "getPurchaseListMethod", "getOriginalJsonMethod", "getOriginalJsonSkuMethod", "productDetailMethodsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailMethodsWrapper;", "getOriginalJsonPurchaseHistoryMethod", "querySkuDetailsAsyncMethod", "queryProductDetailsAsyncMethod", "queryPurchaseHistoryStringAsyncMethod", "queryPurchaseHistoryParamsAsyncMethod", "inAppPurchaseQueryDetailsWrapper", "Lcom/taptap/sdk/core/internal/event/iap/lib2plus/InAppPurchaseQueryDetailsWrapper;", "queryPurchaseParamsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/QueryPurchasesParamsWrapper;", "queryPurchaseHistoryParamsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/QueryPurchaseHistoryParamsWrapper;", "(Landroid/content/Context;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailMethodsWrapper;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/taptap/sdk/core/internal/event/iap/lib2plus/InAppPurchaseQueryDetailsWrapper;Lcom/taptap/sdk/db/biz/iap/lib2plus/QueryPurchasesParamsWrapper;Lcom/taptap/sdk/db/biz/iap/lib2plus/QueryPurchaseHistoryParamsWrapper;)V", "historyPurchaseSet", "", "", "queryDetailsAsync", "", "skuType", "skuIDs", "", "runnable", "Ljava/lang/Runnable;", "queryPurchase", "querySkuRunnable", "queryPurchaseAsync", "callback", "Lkotlin/Function1;", "queryPurchaseHistory", "queryPurchaseHistoryRunnable", BillingClientConstants.METHOD_QUERY_PURCHASE_HISTORY_ASYNC, BillingClientConstants.METHOD_START_CONNECTION, "BillingClientStateListenerWrapper", "Companion", "ProductDetailsResponseListenerWrapper", "PurchaseHistoryResponseListenerWrapper", "PurchaseResponseListenerWrapper", "PurchasesUpdatedListenerWrapper", "SkuDetailsResponseListenerWrapper", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class InAppPurchaseBillingClientWrapper {
    private static final String TAG = "InAppPurchaseBillingClientWrapper";
    private static InAppPurchaseBillingClientWrapper instance;
    private final Object billingClient;
    private final Class<?> billingClientClazz;
    private final Context context;
    private final Method getOriginalJsonMethod;
    private final Method getOriginalJsonPurchaseHistoryMethod;
    private final Method getOriginalJsonSkuMethod;
    private final Method getPurchaseListMethod;
    private final Set<String> historyPurchaseSet;
    private final InAppPurchaseQueryDetailsWrapper inAppPurchaseQueryDetailsWrapper;
    private final ProductDetailMethodsWrapper productDetailMethodsWrapper;
    private final Class<?> productDetailsClazz;
    private final Class<?> productDetailsOneTimePurchaseOfferDetailsClazz;
    private final Class<?> productDetailsResponseListenerClazz;
    private final Class<?> purchaseClazz;
    private final Class<?> purchaseHistoryRecordClazz;
    private final Class<?> purchaseHistoryResponseListenerClazz;
    private final Class<?> purchaseResponseListenerClazz;
    private final Class<?> purchaseResultClazz;
    private final Method queryProductDetailsAsyncMethod;
    private final Method queryPurchaseHistoryParamsAsyncMethod;
    private final QueryPurchaseHistoryParamsWrapper queryPurchaseHistoryParamsWrapper;
    private final Method queryPurchaseHistoryStringAsyncMethod;
    private final QueryPurchasesParamsWrapper queryPurchaseParamsWrapper;
    private final Method queryPurchasesMethod;
    private final Method queryPurchasesParamsAsyncMethod;
    private final Method queryPurchasesStringAsyncMethod;
    private final Method querySkuDetailsAsyncMethod;
    private final Class<?> skuDetailsClazz;
    private final Class<?> skuDetailsResponseListenerClazz;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final AtomicBoolean isServiceConnected = new AtomicBoolean(false);
    private static final Map<String, JSONObject> purchaseDetailsMap = new ConcurrentHashMap();
    private static final Map<String, JSONObject> skuDetailsMap = new ConcurrentHashMap();

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$PurchasesUpdatedListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "()V", "invoke", "", "proxy", "m", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class PurchasesUpdatedListenerWrapper implements InvocationHandler {
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method m, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(m, "m");
            return null;
        }
    }

    public /* synthetic */ InAppPurchaseBillingClientWrapper(Context context, Object obj, Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Class cls8, Class cls9, Class cls10, Class cls11, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, ProductDetailMethodsWrapper productDetailMethodsWrapper, Method method7, Method method8, Method method9, Method method10, Method method11, InAppPurchaseQueryDetailsWrapper inAppPurchaseQueryDetailsWrapper, QueryPurchasesParamsWrapper queryPurchasesParamsWrapper, QueryPurchaseHistoryParamsWrapper queryPurchaseHistoryParamsWrapper, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, obj, cls, cls2, cls3, cls4, cls5, cls6, cls7, cls8, cls9, cls10, cls11, method, method2, method3, method4, method5, method6, productDetailMethodsWrapper, method7, method8, method9, method10, method11, inAppPurchaseQueryDetailsWrapper, queryPurchasesParamsWrapper, queryPurchaseHistoryParamsWrapper);
    }

    @JvmStatic
    public static final synchronized InAppPurchaseBillingClientWrapper getOrCreateInstance(Context context) {
        return INSTANCE.getOrCreateInstance(context);
    }

    private InAppPurchaseBillingClientWrapper(Context context, Object obj, Class<?> cls, Class<?> cls2, Class<?> cls3, Class<?> cls4, Class<?> cls5, Class<?> cls6, Class<?> cls7, Class<?> cls8, Class<?> cls9, Class<?> cls10, Class<?> cls11, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, ProductDetailMethodsWrapper productDetailMethodsWrapper, Method method7, Method method8, Method method9, Method method10, Method method11, InAppPurchaseQueryDetailsWrapper inAppPurchaseQueryDetailsWrapper, QueryPurchasesParamsWrapper queryPurchasesParamsWrapper, QueryPurchaseHistoryParamsWrapper queryPurchaseHistoryParamsWrapper) {
        this.context = context;
        this.billingClient = obj;
        this.billingClientClazz = cls;
        this.purchaseResultClazz = cls2;
        this.purchaseClazz = cls3;
        this.purchaseResponseListenerClazz = cls4;
        this.skuDetailsClazz = cls5;
        this.productDetailsClazz = cls6;
        this.productDetailsOneTimePurchaseOfferDetailsClazz = cls7;
        this.purchaseHistoryRecordClazz = cls8;
        this.skuDetailsResponseListenerClazz = cls9;
        this.productDetailsResponseListenerClazz = cls10;
        this.purchaseHistoryResponseListenerClazz = cls11;
        this.queryPurchasesMethod = method;
        this.queryPurchasesStringAsyncMethod = method2;
        this.queryPurchasesParamsAsyncMethod = method3;
        this.getPurchaseListMethod = method4;
        this.getOriginalJsonMethod = method5;
        this.getOriginalJsonSkuMethod = method6;
        this.productDetailMethodsWrapper = productDetailMethodsWrapper;
        this.getOriginalJsonPurchaseHistoryMethod = method7;
        this.querySkuDetailsAsyncMethod = method8;
        this.queryProductDetailsAsyncMethod = method9;
        this.queryPurchaseHistoryStringAsyncMethod = method10;
        this.queryPurchaseHistoryParamsAsyncMethod = method11;
        this.inAppPurchaseQueryDetailsWrapper = inAppPurchaseQueryDetailsWrapper;
        this.queryPurchaseParamsWrapper = queryPurchasesParamsWrapper;
        this.queryPurchaseHistoryParamsWrapper = queryPurchaseHistoryParamsWrapper;
        this.historyPurchaseSet = new CopyOnWriteArraySet();
    }

    public final void queryPurchaseHistory(String skuType, final Runnable queryPurchaseHistoryRunnable) {
        Intrinsics.checkNotNullParameter(skuType, "skuType");
        Intrinsics.checkNotNullParameter(queryPurchaseHistoryRunnable, "queryPurchaseHistoryRunnable");
        TapLogger.logd(TAG, "queryPurchaseHistory, skuType=" + skuType);
        queryPurchaseHistoryAsync(skuType, new Runnable() { // from class: com.taptap.sdk.db.biz.iap.lib2plus.-$$Lambda$InAppPurchaseBillingClientWrapper$1JWeeLBLhzd63Exmfob5Uhgkt6E
            @Override // java.lang.Runnable
            public final void run() {
                InAppPurchaseBillingClientWrapper.queryPurchaseHistory$lambda$0(this.f$0, queryPurchaseHistoryRunnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void queryPurchaseHistory$lambda$0(InAppPurchaseBillingClientWrapper this$0, Runnable queryPurchaseHistoryRunnable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(queryPurchaseHistoryRunnable, "$queryPurchaseHistoryRunnable");
        this$0.queryDetailsAsync(BillingClientConstants.IN_APP, new ArrayList(this$0.historyPurchaseSet), queryPurchaseHistoryRunnable);
    }

    public final void queryPurchase(final String skuType, final Runnable querySkuRunnable) {
        Intrinsics.checkNotNullParameter(skuType, "skuType");
        Intrinsics.checkNotNullParameter(querySkuRunnable, "querySkuRunnable");
        TapLogger.logd(TAG, "queryPurchase, skuType=" + skuType);
        final Function1<List<?>, Unit> function1 = new Function1<List<?>, Unit>() { // from class: com.taptap.sdk.db.biz.iap.lib2plus.InAppPurchaseBillingClientWrapper$queryPurchase$doQuerySkuDetailAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<?> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<?> list) {
                if (list == null || !(!list.isEmpty())) {
                    return;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    Iterator<?> it = list.iterator();
                    while (it.hasNext()) {
                        Object objInvokeMethod = InAppPurchaseUtils.invokeMethod(this.this$0.purchaseClazz, this.this$0.getOriginalJsonMethod, it.next(), new Object[0]);
                        String str = objInvokeMethod instanceof String ? (String) objInvokeMethod : null;
                        if (str != null) {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has(BillingClientConstants.PRODUCT_ID)) {
                                String skuID = jSONObject.getString(BillingClientConstants.PRODUCT_ID);
                                arrayList.add(skuID);
                                Map<String, JSONObject> purchaseDetailsMap2 = InAppPurchaseBillingClientWrapper.INSTANCE.getPurchaseDetailsMap();
                                Intrinsics.checkNotNullExpressionValue(skuID, "skuID");
                                purchaseDetailsMap2.put(skuID, jSONObject);
                            }
                        }
                    }
                    this.this$0.queryDetailsAsync(skuType, arrayList, querySkuRunnable);
                } catch (JSONException unused) {
                }
            }
        };
        Method method = this.queryPurchasesMethod;
        if (method == null || this.getPurchaseListMethod == null) {
            queryPurchaseAsync(skuType, new Function1<List<?>, Unit>() { // from class: com.taptap.sdk.db.biz.iap.lib2plus.InAppPurchaseBillingClientWrapper.queryPurchase.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<?> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<?> list) {
                    function1.invoke(list);
                }
            });
            return;
        }
        Object objInvokeMethod = InAppPurchaseUtils.invokeMethod(this.purchaseResultClazz, this.getPurchaseListMethod, InAppPurchaseUtils.invokeMethod(this.billingClientClazz, method, this.billingClient, BillingClientConstants.IN_APP), new Object[0]);
        List<?> list = objInvokeMethod instanceof List ? (List) objInvokeMethod : null;
        if (list == null) {
            return;
        }
        function1.invoke(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void queryDetailsAsync(String skuType, List<String> skuIDs, Runnable runnable) {
        if (this.inAppPurchaseQueryDetailsWrapper.getSkuDetailParamsWrapper() != null) {
            Class<?> cls = this.skuDetailsResponseListenerClazz;
            InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.querySkuDetailsAsyncMethod, this.billingClient, this.inAppPurchaseQueryDetailsWrapper.getSkuDetailsParams(skuType, skuIDs), Proxy.newProxyInstance(cls != null ? cls.getClassLoader() : null, new Class[]{this.skuDetailsResponseListenerClazz}, new SkuDetailsResponseListenerWrapper(this, runnable)));
        } else if (this.inAppPurchaseQueryDetailsWrapper.getProductDetailParamsWrapper() != null) {
            Class<?> cls2 = this.productDetailsResponseListenerClazz;
            Object objNewProxyInstance = Proxy.newProxyInstance(cls2 != null ? cls2.getClassLoader() : null, new Class[]{this.productDetailsResponseListenerClazz}, new ProductDetailsResponseListenerWrapper(this, runnable));
            List listFilterNotNull = CollectionsKt.filterNotNull(skuIDs);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterNotNull, 10));
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext()) {
                arrayList.add(TuplesKt.to(skuType, (String) it.next()));
            }
            InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.queryProductDetailsAsyncMethod, this.billingClient, this.inAppPurchaseQueryDetailsWrapper.getProductDetailsParams(MapsKt.toMap(arrayList)), objNewProxyInstance);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void queryPurchaseAsync$default(InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        inAppPurchaseBillingClientWrapper.queryPurchaseAsync(str, function1);
    }

    private final void queryPurchaseAsync(String skuType, Function1<? super List<?>, Unit> callback) {
        TapLogger.logd(TAG, "queryPurchaseAsync, skuType=" + skuType);
        Class<?> cls = this.purchaseResponseListenerClazz;
        if (cls == null) {
            return;
        }
        Object objNewProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{this.purchaseResponseListenerClazz}, new PurchaseResponseListenerWrapper(callback));
        QueryPurchasesParamsWrapper queryPurchasesParamsWrapper = this.queryPurchaseParamsWrapper;
        Object queryPurchasesParams = queryPurchasesParamsWrapper != null ? queryPurchasesParamsWrapper.getQueryPurchasesParams(skuType) : null;
        if (queryPurchasesParams != null) {
            InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.queryPurchasesParamsAsyncMethod, this.billingClient, queryPurchasesParams, objNewProxyInstance);
        } else {
            InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.queryPurchasesStringAsyncMethod, this.billingClient, skuType, objNewProxyInstance);
        }
    }

    private final void queryPurchaseHistoryAsync(String skuType, Runnable runnable) {
        Object objNewProxyInstance = Proxy.newProxyInstance(this.purchaseHistoryResponseListenerClazz.getClassLoader(), new Class[]{this.purchaseHistoryResponseListenerClazz}, new PurchaseHistoryResponseListenerWrapper(this, runnable));
        QueryPurchaseHistoryParamsWrapper queryPurchaseHistoryParamsWrapper = this.queryPurchaseHistoryParamsWrapper;
        Object queryPurchaseHistoryParams = queryPurchaseHistoryParamsWrapper != null ? queryPurchaseHistoryParamsWrapper.getQueryPurchaseHistoryParams(skuType) : null;
        if (queryPurchaseHistoryParams != null) {
            TapLogger.logd(TAG, "queryPurchaseHistoryAsync has purchaseHistoryParams, skuType=" + skuType);
            InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.queryPurchaseHistoryParamsAsyncMethod, this.billingClient, queryPurchaseHistoryParams, objNewProxyInstance);
            return;
        }
        TapLogger.logd(TAG, "queryPurchaseHistoryAsync no purchaseHistoryParams, skuType=" + skuType);
        InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.queryPurchaseHistoryStringAsyncMethod, this.billingClient, skuType, objNewProxyInstance);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConnection() {
        Method method;
        Class<?> cls = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_BILLING_CLIENT_STATE_LISTENER);
        if (cls == null || (method = InAppPurchaseUtils.getMethod(this.billingClientClazz, BillingClientConstants.METHOD_START_CONNECTION, cls)) == null) {
            return;
        }
        InAppPurchaseUtils.invokeMethod(this.billingClientClazz, method, this.billingClient, Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new BillingClientStateListenerWrapper()));
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$BillingClientStateListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "()V", "invoke", "", "proxy", "m", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class BillingClientStateListenerWrapper implements InvocationHandler {
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method m, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(m, "m");
            if (Intrinsics.areEqual(m.getName(), BillingClientConstants.METHOD_ON_BILLING_SETUP_FINISHED)) {
                InAppPurchaseBillingClientWrapper.INSTANCE.isServiceConnected().set(true);
            } else {
                String name = m.getName();
                Intrinsics.checkNotNullExpressionValue(name, "m.name");
                if (StringsKt.endsWith$default(name, BillingClientConstants.METHOD_ON_BILLING_SERVICE_DISCONNECTED, false, 2, (Object) null)) {
                    InAppPurchaseBillingClientWrapper.INSTANCE.isServiceConnected().set(false);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B#\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J0\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0002\u0010\u0010R%\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$PurchaseResponseListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "callback", "Lkotlin/Function1;", "", "", "(Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;Lkotlin/jvm/functions/Function1;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class PurchaseResponseListenerWrapper implements InvocationHandler {
        private final Function1<List<?>, Unit> callback;

        /* JADX WARN: Multi-variable type inference failed */
        public PurchaseResponseListenerWrapper(Function1<? super List<?>, Unit> function1) {
            this.callback = function1;
        }

        public /* synthetic */ PurchaseResponseListenerWrapper(InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : function1);
        }

        public final Function1<List<?>, Unit> getCallback() {
            return this.callback;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(method, "method");
            TapLogger.logd(InAppPurchaseBillingClientWrapper.TAG, "PurchaseResponseListenerWrapper, method.name=" + method.getName());
            if (Intrinsics.areEqual(method.getName(), BillingClientConstants.METHOD_ON_PURCHASE_RESPONSE)) {
                Object obj = args != null ? args[1] : null;
                if (obj != null && (obj instanceof List)) {
                    Function1<List<?>, Unit> function1 = this.callback;
                    if (function1 != null) {
                        function1.invoke((List<?>) obj);
                    }
                } else {
                    Function1<List<?>, Unit> function12 = this.callback;
                    if (function12 != null) {
                        function12.invoke(null);
                    }
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002J0\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0002\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$PurchaseHistoryResponseListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "runnable", "Ljava/lang/Runnable;", "(Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;Ljava/lang/Runnable;)V", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "getPurchaseHistoryRecord", "", "purchaseHistoryRecordList", "", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class PurchaseHistoryResponseListenerWrapper implements InvocationHandler {
        private Runnable runnable;
        final /* synthetic */ InAppPurchaseBillingClientWrapper this$0;

        public PurchaseHistoryResponseListenerWrapper(InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper, Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.this$0 = inAppPurchaseBillingClientWrapper;
            this.runnable = runnable;
        }

        public final Runnable getRunnable() {
            return this.runnable;
        }

        public final void setRunnable(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "<set-?>");
            this.runnable = runnable;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(method, "method");
            TapLogger.logd(InAppPurchaseBillingClientWrapper.TAG, "PurchaseHistoryResponseListenerWrapper, method.name=" + method.getName());
            if (Intrinsics.areEqual(method.getName(), BillingClientConstants.METHOD_ON_PURCHASE_HISTORY_RESPONSE)) {
                Object obj = args != null ? args[1] : null;
                if (obj != null && (obj instanceof List)) {
                    getPurchaseHistoryRecord((List) obj);
                }
            }
            return null;
        }

        private final void getPurchaseHistoryRecord(List<?> purchaseHistoryRecordList) {
            Iterator<?> it = purchaseHistoryRecordList.iterator();
            while (it.hasNext()) {
                try {
                    Object objInvokeMethod = InAppPurchaseUtils.invokeMethod(this.this$0.purchaseHistoryRecordClazz, this.this$0.getOriginalJsonPurchaseHistoryMethod, it.next(), new Object[0]);
                    String str = objInvokeMethod instanceof String ? (String) objInvokeMethod : null;
                    if (str != null) {
                        JSONObject jSONObject = new JSONObject(str);
                        jSONObject.put(BillingClientConstants.PACKAGE_NAME, this.this$0.context.getPackageName());
                        if (jSONObject.has(BillingClientConstants.PRODUCT_ID)) {
                            String skuID = jSONObject.getString(BillingClientConstants.PRODUCT_ID);
                            this.this$0.historyPurchaseSet.add(skuID);
                            Map<String, JSONObject> purchaseDetailsMap = InAppPurchaseBillingClientWrapper.INSTANCE.getPurchaseDetailsMap();
                            Intrinsics.checkNotNullExpressionValue(skuID, "skuID");
                            purchaseDetailsMap.put(skuID, jSONObject);
                        }
                    }
                } catch (Exception unused) {
                }
            }
            this.runnable.run();
        }
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$SkuDetailsResponseListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "runnable", "Ljava/lang/Runnable;", "(Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;Ljava/lang/Runnable;)V", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "invoke", "", "proxy", "m", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "parseSkuDetails", "", "skuDetailsObjectList", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class SkuDetailsResponseListenerWrapper implements InvocationHandler {
        private Runnable runnable;
        final /* synthetic */ InAppPurchaseBillingClientWrapper this$0;

        public SkuDetailsResponseListenerWrapper(InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper, Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.this$0 = inAppPurchaseBillingClientWrapper;
            this.runnable = runnable;
        }

        public final Runnable getRunnable() {
            return this.runnable;
        }

        public final void setRunnable(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "<set-?>");
            this.runnable = runnable;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method m, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(m, "m");
            TapLogger.logd(InAppPurchaseBillingClientWrapper.TAG, "SkuDetailsResponseListenerWrapper, method.name=" + m.getName());
            if (Intrinsics.areEqual(m.getName(), BillingClientConstants.METHOD_ON_SKU_DETAILS_RESPONSE)) {
                Object obj = args != null ? args[1] : null;
                if (obj != null && (obj instanceof List)) {
                    parseSkuDetails((List) obj);
                }
            }
            return null;
        }

        public final void parseSkuDetails(List<?> skuDetailsObjectList) {
            Intrinsics.checkNotNullParameter(skuDetailsObjectList, "skuDetailsObjectList");
            Iterator<?> it = skuDetailsObjectList.iterator();
            while (it.hasNext()) {
                try {
                    Object objInvokeMethod = InAppPurchaseUtils.invokeMethod(this.this$0.skuDetailsClazz, this.this$0.getOriginalJsonSkuMethod, it.next(), new Object[0]);
                    String str = objInvokeMethod instanceof String ? (String) objInvokeMethod : null;
                    if (str != null) {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.has(BillingClientConstants.PRODUCT_ID)) {
                            String skuID = jSONObject.getString(BillingClientConstants.PRODUCT_ID);
                            Map<String, JSONObject> skuDetailsMap = InAppPurchaseBillingClientWrapper.INSTANCE.getSkuDetailsMap();
                            Intrinsics.checkNotNullExpressionValue(skuID, "skuID");
                            skuDetailsMap.put(skuID, jSONObject);
                        }
                    }
                } catch (Exception unused) {
                }
            }
            this.runnable.run();
        }
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$ProductDetailsResponseListenerWrapper;", "Ljava/lang/reflect/InvocationHandler;", "runnable", "Ljava/lang/Runnable;", "(Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;Ljava/lang/Runnable;)V", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "invoke", "", "proxy", "m", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "parseProductDetails", "", "productDetailsObjectList", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class ProductDetailsResponseListenerWrapper implements InvocationHandler {
        private Runnable runnable;
        final /* synthetic */ InAppPurchaseBillingClientWrapper this$0;

        public ProductDetailsResponseListenerWrapper(InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper, Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.this$0 = inAppPurchaseBillingClientWrapper;
            this.runnable = runnable;
        }

        public final Runnable getRunnable() {
            return this.runnable;
        }

        public final void setRunnable(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "<set-?>");
            this.runnable = runnable;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method m, Object[] args) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(m, "m");
            TapLogger.logd(InAppPurchaseBillingClientWrapper.TAG, "ProductDetailsResponseListenerWrapper, method.name=" + m.getName());
            if (Intrinsics.areEqual(m.getName(), BillingClientConstants.METHOD_ON_PRODUCT_DETAILS_RESPONSE)) {
                Object obj = args != null ? args[1] : null;
                if (obj != null && (obj instanceof List)) {
                    parseProductDetails((List) obj);
                }
            }
            return null;
        }

        private final void parseProductDetails(List<?> productDetailsObjectList) {
            for (Object obj : productDetailsObjectList) {
                try {
                    Class cls = this.this$0.productDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod = InAppPurchaseUtils.invokeMethod(cls, productDetailMethodsWrapper != null ? productDetailMethodsWrapper.getGetProductIdMethod() : null, obj, new Object[0]);
                    String str = objInvokeMethod instanceof String ? (String) objInvokeMethod : null;
                    Class cls2 = this.this$0.productDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper2 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod2 = InAppPurchaseUtils.invokeMethod(cls2, productDetailMethodsWrapper2 != null ? productDetailMethodsWrapper2.getGetProductNameMethod() : null, obj, new Object[0]);
                    String str2 = objInvokeMethod2 instanceof String ? (String) objInvokeMethod2 : null;
                    Class cls3 = this.this$0.productDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper3 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod3 = InAppPurchaseUtils.invokeMethod(cls3, productDetailMethodsWrapper3 != null ? productDetailMethodsWrapper3.getGetProductTitleMethod() : null, obj, new Object[0]);
                    String str3 = objInvokeMethod3 instanceof String ? (String) objInvokeMethod3 : null;
                    Class cls4 = this.this$0.productDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper4 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod4 = InAppPurchaseUtils.invokeMethod(cls4, productDetailMethodsWrapper4 != null ? productDetailMethodsWrapper4.getGetProductDescriptionMethod() : null, obj, new Object[0]);
                    String str4 = objInvokeMethod4 instanceof String ? (String) objInvokeMethod4 : null;
                    Class cls5 = this.this$0.productDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper5 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod5 = InAppPurchaseUtils.invokeMethod(cls5, productDetailMethodsWrapper5 != null ? productDetailMethodsWrapper5.getGetOneTimePurchaseOfferDetailsMethod() : null, obj, new Object[0]);
                    Class cls6 = this.this$0.productDetailsOneTimePurchaseOfferDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper6 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod6 = InAppPurchaseUtils.invokeMethod(cls6, productDetailMethodsWrapper6 != null ? productDetailMethodsWrapper6.getGetPriceAmountMicrosMethod() : null, objInvokeMethod5, new Object[0]);
                    Long l = objInvokeMethod6 instanceof Long ? (Long) objInvokeMethod6 : null;
                    Class cls7 = this.this$0.productDetailsOneTimePurchaseOfferDetailsClazz;
                    ProductDetailMethodsWrapper productDetailMethodsWrapper7 = this.this$0.productDetailMethodsWrapper;
                    Object objInvokeMethod7 = InAppPurchaseUtils.invokeMethod(cls7, productDetailMethodsWrapper7 != null ? productDetailMethodsWrapper7.getGetPriceCurrencyCodeMethod() : null, objInvokeMethod5, new Object[0]);
                    String str5 = objInvokeMethod7 instanceof String ? (String) objInvokeMethod7 : null;
                    String str6 = str;
                    if (!(str6 == null || str6.length() == 0)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(BillingClientConstants.PRODUCT_ID, str);
                        jSONObject.put("title", str3);
                        jSONObject.put("name", str2);
                        jSONObject.put("description", str4);
                        jSONObject.put("price_amount_micros", l);
                        jSONObject.put("price_currency_code", str5);
                        Map<String, JSONObject> skuDetailsMap = InAppPurchaseBillingClientWrapper.INSTANCE.getSkuDetailsMap();
                        Intrinsics.checkNotNull(str);
                        skuDetailsMap.put(str, jSONObject);
                    }
                } catch (Exception unused) {
                }
            }
            this.runnable.run();
        }
    }

    /* JADX INFO: compiled from: InAppPurchaseBillingClientWrapper.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0012\u001a\u0004\u0018\u00010\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper$Companion;", "", "()V", "TAG", "", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "instance", "Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseBillingClientWrapper;", "isServiceConnected", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "purchaseDetailsMap", "", "Lorg/json/JSONObject;", "getPurchaseDetailsMap", "()Ljava/util/Map;", "skuDetailsMap", "getSkuDetailsMap", "createBillingClient", "context", "Landroid/content/Context;", "billingClientClazz", "Ljava/lang/Class;", "createInstance", "", "getOrCreateInstance", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AtomicBoolean isServiceConnected() {
            return InAppPurchaseBillingClientWrapper.isServiceConnected;
        }

        public final Map<String, JSONObject> getPurchaseDetailsMap() {
            return InAppPurchaseBillingClientWrapper.purchaseDetailsMap;
        }

        public final Map<String, JSONObject> getSkuDetailsMap() {
            return InAppPurchaseBillingClientWrapper.skuDetailsMap;
        }

        @JvmStatic
        public final synchronized InAppPurchaseBillingClientWrapper getOrCreateInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (InAppPurchaseBillingClientWrapper.initialized.get()) {
                return InAppPurchaseBillingClientWrapper.instance;
            }
            createInstance(context);
            InAppPurchaseBillingClientWrapper.initialized.set(true);
            return InAppPurchaseBillingClientWrapper.instance;
        }

        private final void createInstance(Context context) {
            Method method;
            char c;
            Method method2;
            Object objCreateBillingClient;
            InAppPurchaseQueryDetailsWrapper orCreateInstance = InAppPurchaseQueryDetailsWrapper.INSTANCE.getOrCreateInstance();
            if (orCreateInstance == null) {
                return;
            }
            Class<?> cls = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_BILLING_CLIENT);
            Class<?> cls2 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASE);
            Class<?> cls3 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASES_RESULT);
            Class<?> cls4 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_SKU_DETAILS);
            Class<?> cls5 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PRODUCT_DETAILS);
            Class<?> cls6 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PRODUCT_DETAILS_ONE_TIME_PURCHASE_OFFER_DETAILS);
            Class<?> cls7 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASE_HISTORY_RECORD);
            Class<?> cls8 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_SKU_DETAILS_RESPONSE_LISTENER);
            Class<?> cls9 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PRODUCT_DETAILS_RESPONSE_LISTENER);
            Class<?> cls10 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASE_HISTORY_RESPONSE_LISTENER);
            Class<?> cls11 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchaseHistoryParams");
            Class<?> cls12 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchasesParams");
            Class<?> cls13 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASE_RESPONSE_LISTENER);
            if (cls == null || cls2 == null) {
                return;
            }
            if (cls4 == null && cls5 == null) {
                return;
            }
            if ((cls8 == null && cls9 == null) || cls7 == null || cls10 == null) {
                return;
            }
            Method method3 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PURCHASES, String.class);
            Method method4 = InAppPurchaseUtils.getMethod(cls3, BillingClientConstants.METHOD_GET_PURCHASE_LIST, new Class[0]);
            Method method5 = InAppPurchaseUtils.getMethod(cls2, BillingClientConstants.METHOD_GET_ORIGINAL_JSON, new Class[0]);
            Method method6 = InAppPurchaseUtils.getMethod(cls4, BillingClientConstants.METHOD_GET_ORIGINAL_JSON, new Class[0]);
            ProductDetailMethodsWrapper productDetailMethodsWrapper = ProductDetailMethodsWrapper.INSTANCE.getProductDetailMethodsWrapper(cls5, cls6);
            Method method7 = InAppPurchaseUtils.getMethod(cls7, BillingClientConstants.METHOD_GET_ORIGINAL_JSON, new Class[0]);
            if (orCreateInstance.getSkuDetailParamsWrapper() != null) {
                Class[] clsArr = new Class[2];
                SkuDetailsParamsWrapper.ReflectParams reflectParams = orCreateInstance.getSkuDetailParamsWrapper().getReflectParams();
                clsArr[0] = reflectParams != null ? reflectParams.getSkuDetailsParamsClazz$tap_db_release() : null;
                clsArr[1] = cls8;
                method = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_SKU_DETAILS_ASYNC, clsArr);
            } else {
                method = null;
            }
            if (orCreateInstance.getProductDetailParamsWrapper() != null) {
                Class[] clsArr2 = new Class[2];
                ProductDetailsParamsWrapper.ReflectParams reflectParams2 = orCreateInstance.getProductDetailParamsWrapper().getReflectParams();
                c = 0;
                clsArr2[0] = reflectParams2 != null ? reflectParams2.getProductDetailsParamsClazz$tap_db_release() : null;
                clsArr2[1] = cls9;
                method2 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PRODUCT_DETAILS_ASYNC, clsArr2);
            } else {
                c = 0;
                method2 = null;
            }
            Class[] clsArr3 = new Class[2];
            clsArr3[c] = String.class;
            clsArr3[1] = cls10;
            Method method8 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PURCHASE_HISTORY_ASYNC, clsArr3);
            Class[] clsArr4 = new Class[2];
            clsArr4[c] = cls11;
            clsArr4[1] = cls10;
            Method method9 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PURCHASE_HISTORY_ASYNC, clsArr4);
            Class[] clsArr5 = new Class[2];
            clsArr5[c] = String.class;
            clsArr5[1] = cls13;
            Method method10 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PURCHASES_ASYNC, clsArr5);
            Class[] clsArr6 = new Class[2];
            clsArr6[c] = cls12;
            clsArr6[1] = cls13;
            Method method11 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_QUERY_PURCHASES_ASYNC, clsArr6);
            if ((method3 == null && method10 == null && method11 == null) || method5 == null) {
                return;
            }
            if ((method6 == null && productDetailMethodsWrapper == null) || method7 == null) {
                return;
            }
            if (method == null && method2 == null) {
                return;
            }
            if ((method8 == null && method9 == null) || (objCreateBillingClient = createBillingClient(context, cls)) == null) {
                return;
            }
            InAppPurchaseBillingClientWrapper.instance = new InAppPurchaseBillingClientWrapper(context, objCreateBillingClient, cls, cls3, cls2, cls13, cls4, cls5, cls6, cls7, cls8, cls9, cls10, method3, method10, method11, method4, method5, method6, productDetailMethodsWrapper, method7, method, method2, method8, method9, orCreateInstance, new QueryPurchasesParamsWrapper(), new QueryPurchaseHistoryParamsWrapper(), null);
            InAppPurchaseBillingClientWrapper inAppPurchaseBillingClientWrapper = InAppPurchaseBillingClientWrapper.instance;
            Intrinsics.checkNotNull(inAppPurchaseBillingClientWrapper, "null cannot be cast to non-null type com.taptap.sdk.db.biz.iap.lib2plus.InAppPurchaseBillingClientWrapper");
            inAppPurchaseBillingClientWrapper.startConnection();
        }

        private final Object createBillingClient(Context context, Class<?> billingClientClazz) {
            Object objInvokeMethod;
            Object objInvokeMethod2;
            Object objInvokeMethod3;
            Class<?> cls = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_BILLING_CLIENT_BUILDER);
            Class<?> cls2 = InAppPurchaseUtils.getClass(BillingClientConstants.CLASSNAME_PURCHASE_UPDATED_LISTENER);
            if (cls == null || cls2 == null) {
                return null;
            }
            Method method = InAppPurchaseUtils.getMethod(billingClientClazz, "newBuilder", Context.class);
            Method method2 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_ENABLE_PENDING_PURCHASES, new Class[0]);
            Method method3 = InAppPurchaseUtils.getMethod(cls, BillingClientConstants.METHOD_SET_LISTENER, cls2);
            Method method4 = InAppPurchaseUtils.getMethod(cls, "build", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null || (objInvokeMethod = InAppPurchaseUtils.invokeMethod(billingClientClazz, method, null, context)) == null || (objInvokeMethod2 = InAppPurchaseUtils.invokeMethod(cls, method3, objInvokeMethod, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new PurchasesUpdatedListenerWrapper()))) == null || (objInvokeMethod3 = InAppPurchaseUtils.invokeMethod(cls, method2, objInvokeMethod2, new Object[0])) == null) {
                return null;
            }
            return InAppPurchaseUtils.invokeMethod(cls, method4, objInvokeMethod3, new Object[0]);
        }
    }
}
