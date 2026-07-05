package com.taptap.sdk.db.biz.iap.lib2plus;

import android.content.SharedPreferences;
import com.taptap.sdk.db.TapDB;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppPurchaseLoggerManager.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\t\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\rH\u0001¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001aJC\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u00142\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H\u0001¢\u0006\u0002\b\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0007J>\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\r2\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H\u0007J\b\u0010!\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/InAppPurchaseLoggerManager;", "", "()V", "CACHE_CLEAR_TIME_LIMIT_SEC", "", InAppPurchaseLoggerManager.LAST_CLEARED_TIME, "", InAppPurchaseLoggerManager.LAST_QUERY_PURCHASE_HISTORY_TIME, "PRODUCT_DETAILS_STORE", InAppPurchaseLoggerManager.PURCHASE_DETAILS_SET, "PURCHASE_IN_CACHE_INTERVAL", "PURCHASE_TIME", "cachedPurchaseMap", "", "", "cachedPurchaseSet", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "cacheDeDupPurchase", "", "Lorg/json/JSONObject;", "purchaseDetailsMap", "cacheDeDupPurchase$tap_db_release", "clearOutdatedProductInfoInCache", "", "clearOutdatedProductInfoInCache$tap_db_release", "constructLoggingReadyMap", "skuDetailsMap", "constructLoggingReadyMap$tap_db_release", "eligibleQueryPurchaseHistory", "", "filterPurchaseLogging", "readPurchaseCache", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class InAppPurchaseLoggerManager {
    private static final int CACHE_CLEAR_TIME_LIMIT_SEC = 604800;
    private static final String LAST_CLEARED_TIME = "LAST_CLEARED_TIME";
    private static final String LAST_QUERY_PURCHASE_HISTORY_TIME = "LAST_QUERY_PURCHASE_HISTORY_TIME";
    private static final String PRODUCT_DETAILS_STORE = "com.taptap.internal.iap.PRODUCT_DETAILS";
    private static final String PURCHASE_DETAILS_SET = "PURCHASE_DETAILS_SET";
    private static final int PURCHASE_IN_CACHE_INTERVAL = 86400;
    private static final String PURCHASE_TIME = "purchaseTime";
    private static SharedPreferences sharedPreferences;
    public static final InAppPurchaseLoggerManager INSTANCE = new InAppPurchaseLoggerManager();
    private static final Set<String> cachedPurchaseSet = new CopyOnWriteArraySet();
    private static final Map<String, Long> cachedPurchaseMap = new ConcurrentHashMap();

    private InAppPurchaseLoggerManager() {
    }

    private final void readPurchaseCache() {
        SharedPreferences sharedPreferences2 = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getSharedPreferences("com.taptap.internal.SKU_DETAILS", 0);
        SharedPreferences sharedPreferences3 = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getSharedPreferences("com.taptap.internal.PURCHASE", 0);
        if (sharedPreferences2.contains(LAST_CLEARED_TIME)) {
            sharedPreferences2.edit().clear().apply();
            sharedPreferences3.edit().clear().apply();
        }
        SharedPreferences sharedPreferences4 = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getSharedPreferences(PRODUCT_DETAILS_STORE, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences4, "TapDB.options.context.ge…RE, Context.MODE_PRIVATE)");
        sharedPreferences = sharedPreferences4;
        Set<String> set = cachedPurchaseSet;
        if (sharedPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences4 = null;
        }
        Set<String> stringSet = sharedPreferences4.getStringSet(PURCHASE_DETAILS_SET, new HashSet());
        if (stringSet == null) {
            stringSet = new HashSet<>();
        }
        set.addAll(stringSet);
        Iterator<String> it = cachedPurchaseSet.iterator();
        while (it.hasNext()) {
            List listSplit$default = StringsKt.split$default((CharSequence) it.next(), new String[]{";"}, false, 2, 2, (Object) null);
            cachedPurchaseMap.put((String) listSplit$default.get(0), Long.valueOf(Long.parseLong((String) listSplit$default.get(1))));
        }
        clearOutdatedProductInfoInCache$tap_db_release();
    }

    @JvmStatic
    public static final Map<String, String> filterPurchaseLogging(Map<String, JSONObject> purchaseDetailsMap, Map<String, ? extends JSONObject> skuDetailsMap) {
        Intrinsics.checkNotNullParameter(purchaseDetailsMap, "purchaseDetailsMap");
        Intrinsics.checkNotNullParameter(skuDetailsMap, "skuDetailsMap");
        INSTANCE.readPurchaseCache();
        InAppPurchaseLoggerManager inAppPurchaseLoggerManager = INSTANCE;
        return inAppPurchaseLoggerManager.constructLoggingReadyMap$tap_db_release(inAppPurchaseLoggerManager.cacheDeDupPurchase$tap_db_release(purchaseDetailsMap), skuDetailsMap);
    }

    public final Map<String, JSONObject> cacheDeDupPurchase$tap_db_release(Map<String, JSONObject> purchaseDetailsMap) {
        Intrinsics.checkNotNullParameter(purchaseDetailsMap, "purchaseDetailsMap");
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        for (Map.Entry entry : MapsKt.toMap(purchaseDetailsMap).entrySet()) {
            String str = (String) entry.getKey();
            JSONObject jSONObject = (JSONObject) entry.getValue();
            try {
                if (jSONObject.has("purchaseToken")) {
                    String string = jSONObject.getString("purchaseToken");
                    if (cachedPurchaseMap.containsKey(string)) {
                        purchaseDetailsMap.remove(str);
                    } else {
                        cachedPurchaseSet.add(string + ';' + jCurrentTimeMillis);
                    }
                }
            } catch (Exception unused) {
            }
        }
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        sharedPreferences2.edit().putStringSet(PURCHASE_DETAILS_SET, cachedPurchaseSet).apply();
        return new HashMap(purchaseDetailsMap);
    }

    public final void clearOutdatedProductInfoInCache$tap_db_release() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        SharedPreferences sharedPreferences3 = null;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        long j = sharedPreferences2.getLong(LAST_CLEARED_TIME, 0L);
        if (j == 0) {
            SharedPreferences sharedPreferences4 = sharedPreferences;
            if (sharedPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            } else {
                sharedPreferences3 = sharedPreferences4;
            }
            sharedPreferences3.edit().putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
            return;
        }
        if (jCurrentTimeMillis - j > 604800) {
            for (Map.Entry entry : MapsKt.toMap(cachedPurchaseMap).entrySet()) {
                String str = (String) entry.getKey();
                long jLongValue = ((Number) entry.getValue()).longValue();
                if (jCurrentTimeMillis - jLongValue > 86400) {
                    cachedPurchaseSet.remove(str + ';' + jLongValue);
                    cachedPurchaseMap.remove(str);
                }
            }
            SharedPreferences sharedPreferences5 = sharedPreferences;
            if (sharedPreferences5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            } else {
                sharedPreferences3 = sharedPreferences5;
            }
            sharedPreferences3.edit().putStringSet(PURCHASE_DETAILS_SET, cachedPurchaseSet).putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
        }
    }

    @JvmStatic
    public static final boolean eligibleQueryPurchaseHistory() {
        INSTANCE.readPurchaseCache();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        SharedPreferences sharedPreferences3 = null;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        long j = sharedPreferences2.getLong(LAST_QUERY_PURCHASE_HISTORY_TIME, 0L);
        if (j != 0 && jCurrentTimeMillis - j < 86400) {
            return false;
        }
        SharedPreferences sharedPreferences4 = sharedPreferences;
        if (sharedPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        } else {
            sharedPreferences3 = sharedPreferences4;
        }
        sharedPreferences3.edit().putLong(LAST_QUERY_PURCHASE_HISTORY_TIME, jCurrentTimeMillis).apply();
        return true;
    }

    public final Map<String, String> constructLoggingReadyMap$tap_db_release(Map<String, ? extends JSONObject> purchaseDetailsMap, Map<String, ? extends JSONObject> skuDetailsMap) {
        Intrinsics.checkNotNullParameter(purchaseDetailsMap, "purchaseDetailsMap");
        Intrinsics.checkNotNullParameter(skuDetailsMap, "skuDetailsMap");
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ? extends JSONObject> entry : purchaseDetailsMap.entrySet()) {
            String key = entry.getKey();
            JSONObject value = entry.getValue();
            JSONObject jSONObject = skuDetailsMap.get(key);
            if (value != null && value.has(PURCHASE_TIME)) {
                try {
                    if (jCurrentTimeMillis - (value.getLong(PURCHASE_TIME) / 1000) <= 86400 && jSONObject != null) {
                        String string = value.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "purchaseDetail.toString()");
                        String string2 = jSONObject.toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "skuDetail.toString()");
                        linkedHashMap.put(string, string2);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return linkedHashMap;
    }
}
