package com.taptap.sdk.db.biz.iap.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: IAPEventUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010&\u001a\u0004\u0018\u00010\u00012\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0007J\b\u0010+\u001a\u00020,H\u0007J0\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/H\u0002J\u001e\u00101\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001f2\u0006\u0010'\u001a\u00020(2\u0006\u00102\u001a\u00020\u0004H\u0002J\u001e\u00103\u001a\u0004\u0018\u00010\"2\n\u00104\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u00105\u001a\u00020\u0004H\u0002J0\u00106\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0006\u0010'\u001a\u00020(2\u0006\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u0004H\u0002J*\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0006\u0010'\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u0007J2\u0010:\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0006\u0010'\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0004H\u0002J*\u0010;\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0006\u0010'\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u0007J*\u0010<\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\u0006\u0010'\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u0007JF\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040>2\u0006\u0010'\u001a\u00020(2\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\b\u00107\u001a\u0004\u0018\u00010\u00012\u0006\u0010@\u001a\u00020AH\u0007JF\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040>2\u0006\u0010'\u001a\u00020(2\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/2\b\u00107\u001a\u0004\u0018\u00010\u00012\u0006\u0010@\u001a\u00020AH\u0002J\u000e\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020\u0004JA\u0010E\u001a\u0004\u0018\u00010\u00012\u0006\u0010'\u001a\u00020(2\u0006\u00102\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\b\u0010F\u001a\u0004\u0018\u00010\u00012\u000e\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010HH\u0002¢\u0006\u0002\u0010IJ\"\u0010J\u001a\u00020A2\u0006\u0010'\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0004H\u0002J,\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040L2\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u00040.j\b\u0012\u0004\u0012\u00020\u0004`/H\u0002J\u001c\u0010M\u001a\u00020,2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040>H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0015*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R2\u0010\u001d\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u001ej\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f` X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010!\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0\u001ej\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"` X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n \u0015*\u0004\u0018\u00010$0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n \u0015*\u0004\u0018\u00010$0$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/common/IAPEventUtils;", "", "()V", "AS_INTERFACE", "", "CACHE_CLEAR_TIME_LIMIT_SEC", "", IAPEventUtils.DETAILS_LIST, "GET_PURCHASES", "GET_PURCHASE_HISTORY", "GET_SKU_DETAILS", "INAPP", IAPEventUtils.INAPP_CONTINUATION_TOKEN, IAPEventUtils.INAPP_PURCHASE_DATA_LIST, "IN_APP_BILLING_SERVICE", "IN_APP_BILLING_SERVICE_STUB", "IS_BILLING_SUPPORTED", IAPEventUtils.ITEM_ID_LIST, IAPEventUtils.LAST_CLEARED_TIME, "MAX_QUERY_PURCHASE_NUM", "PACKAGE_NAME", "kotlin.jvm.PlatformType", "PURCHASE_EXPIRE_TIME_SEC", "PURCHASE_INAPP_STORE", "PURCHASE_STOP_QUERY_TIME_SEC", IAPEventUtils.RESPONSE_CODE, "SKU_DETAILS_STORE", "SKU_DETAIL_EXPIRE_TIME_SEC", "SUBSCRIPTION", "classMap", "Ljava/util/HashMap;", "Ljava/lang/Class;", "Lkotlin/collections/HashMap;", "methodMap", "Ljava/lang/reflect/Method;", "purchaseInappSharedPrefs", "Landroid/content/SharedPreferences;", "skuDetailSharedPrefs", IAPEventUtils.AS_INTERFACE, "context", "Landroid/content/Context;", "service", "Landroid/os/IBinder;", "clearSkuDetailsCache", "", "filterPurchases", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "purchases", "getClass", "className", "getMethod", "classObj", "methodName", IAPEventUtils.GET_PURCHASE_HISTORY, "inAppBillingObj", "type", "getPurchaseHistoryInapp", IAPEventUtils.GET_PURCHASES, "getPurchasesInapp", "getPurchasesSubs", IAPEventUtils.GET_SKU_DETAILS, "", "skuList", "isSubscription", "", "getSkuDetailsFromGoogle", "hasFreeTrialPeirod", "skuDetail", "invokeMethod", "obj", "args", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", IAPEventUtils.IS_BILLING_SUPPORTED, "readSkuDetailsFromCache", "", "writeSkuDetailsToCache", "skuDetailsMap", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class IAPEventUtils {
    private static final String AS_INTERFACE = "asInterface";
    private static final int CACHE_CLEAR_TIME_LIMIT_SEC = 604800;
    private static final String DETAILS_LIST = "DETAILS_LIST";
    private static final String GET_PURCHASES = "getPurchases";
    private static final String GET_PURCHASE_HISTORY = "getPurchaseHistory";
    private static final String GET_SKU_DETAILS = "getSkuDetails";
    private static final String INAPP = "inapp";
    private static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final String INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
    private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String IS_BILLING_SUPPORTED = "isBillingSupported";
    private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
    private static final String LAST_CLEARED_TIME = "LAST_CLEARED_TIME";
    private static final int MAX_QUERY_PURCHASE_NUM = 30;
    private static final int PURCHASE_EXPIRE_TIME_SEC = 86400;
    private static final int PURCHASE_STOP_QUERY_TIME_SEC = 1200;
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final int SKU_DETAIL_EXPIRE_TIME_SEC = 43200;
    private static final String SUBSCRIPTION = "subs";
    public static final IAPEventUtils INSTANCE = new IAPEventUtils();
    private static final HashMap<String, Method> methodMap = new HashMap<>();
    private static final HashMap<String, Class<?>> classMap = new HashMap<>();
    private static final String PACKAGE_NAME = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getPackageName();
    private static final String SKU_DETAILS_STORE = "com.taptap.internal.SKU_DETAILS";
    private static final SharedPreferences skuDetailSharedPrefs = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getSharedPreferences(SKU_DETAILS_STORE, 0);
    private static final String PURCHASE_INAPP_STORE = "com.taptap.internal.PURCHASE";
    private static final SharedPreferences purchaseInappSharedPrefs = TapDB.INSTANCE.getOptions$tap_db_release().getContext().getSharedPreferences(PURCHASE_INAPP_STORE, 0);

    private IAPEventUtils() {
    }

    @JvmStatic
    public static final Object asInterface(Context context, IBinder service) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.invokeMethod(context, IN_APP_BILLING_SERVICE_STUB, AS_INTERFACE, null, new Object[]{service});
    }

    @JvmStatic
    public static final Map<String, String> getSkuDetails(Context context, ArrayList<String> skuList, Object inAppBillingObj, boolean isSubscription) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skuList, "skuList");
        Map<String, String> skuDetailsFromCache = INSTANCE.readSkuDetailsFromCache(skuList);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : skuList) {
            if (!skuDetailsFromCache.containsKey(str)) {
                arrayList.add(str);
            }
        }
        skuDetailsFromCache.putAll(INSTANCE.getSkuDetailsFromGoogle(context, arrayList, inAppBillingObj, isSubscription));
        return skuDetailsFromCache;
    }

    private final Map<String, String> getSkuDetailsFromGoogle(Context context, ArrayList<String> skuList, Object inAppBillingObj, boolean isSubscription) {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        if (inAppBillingObj != null && !skuList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ITEM_ID_LIST, skuList);
            Object[] objArr = new Object[4];
            objArr[0] = 3;
            objArr[1] = PACKAGE_NAME;
            objArr[2] = isSubscription ? SUBSCRIPTION : "inapp";
            objArr[3] = bundle;
            Object objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_SKU_DETAILS, inAppBillingObj, objArr);
            if (objInvokeMethod != null) {
                Bundle bundle2 = (Bundle) objInvokeMethod;
                if (bundle2.getInt(RESPONSE_CODE) == 0) {
                    ArrayList<String> stringArrayList = bundle2.getStringArrayList(DETAILS_LIST);
                    if (stringArrayList != null && skuList.size() == stringArrayList.size()) {
                        int size = skuList.size();
                        for (int i = 0; i < size; i++) {
                            String str = skuList.get(i);
                            Intrinsics.checkNotNullExpressionValue(str, "skuList[i]");
                            String str2 = stringArrayList.get(i);
                            Intrinsics.checkNotNullExpressionValue(str2, "skuDetailsList[i]");
                            linkedHashMap.put(str, str2);
                        }
                    }
                    writeSkuDetailsToCache(linkedHashMap);
                }
            }
        }
        return linkedHashMap;
    }

    private final Map<String, String> readSkuDetailsFromCache(ArrayList<String> skuList) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        for (String sku : skuList) {
            String string = skuDetailSharedPrefs.getString(sku, null);
            if (string != null) {
                List listSplit$default = StringsKt.split$default((CharSequence) string, new String[]{";"}, false, 2, 2, (Object) null);
                if (jCurrentTimeMillis - Long.parseLong((String) listSplit$default.get(0)) < 43200) {
                    Intrinsics.checkNotNullExpressionValue(sku, "sku");
                    linkedHashMap.put(sku, listSplit$default.get(1));
                }
            }
        }
        return linkedHashMap;
    }

    private final void writeSkuDetailsToCache(Map<String, String> skuDetailsMap) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences.Editor editorEdit = skuDetailSharedPrefs.edit();
        for (Map.Entry<String, String> entry : skuDetailsMap.entrySet()) {
            editorEdit.putString(entry.getKey(), jCurrentTimeMillis + ';' + entry.getValue());
        }
        editorEdit.apply();
    }

    private final boolean isBillingSupported(Context context, Object inAppBillingObj, String type) {
        Object objInvokeMethod;
        return (inAppBillingObj == null || (objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, IS_BILLING_SUPPORTED, inAppBillingObj, new Object[]{3, PACKAGE_NAME, type})) == null || ((Integer) objInvokeMethod).intValue() != 0) ? false : true;
    }

    @JvmStatic
    public static final ArrayList<String> getPurchasesInapp(Context context, Object inAppBillingObj) {
        Intrinsics.checkNotNullParameter(context, "context");
        IAPEventUtils iAPEventUtils = INSTANCE;
        return iAPEventUtils.filterPurchases(iAPEventUtils.getPurchases(context, inAppBillingObj, "inapp"));
    }

    @JvmStatic
    public static final ArrayList<String> getPurchasesSubs(Context context, Object inAppBillingObj) {
        Intrinsics.checkNotNullParameter(context, "context");
        IAPEventUtils iAPEventUtils = INSTANCE;
        return iAPEventUtils.filterPurchases(iAPEventUtils.getPurchases(context, inAppBillingObj, SUBSCRIPTION));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.ArrayList<java.lang.String> getPurchases(android.content.Context r13, java.lang.Object r14, java.lang.String r15) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r14 != 0) goto L8
            return r0
        L8:
            boolean r1 = r12.isBillingSupported(r13, r14, r15)
            if (r1 == 0) goto L5d
            r1 = 0
            r2 = 0
            r3 = r2
            r4 = 0
        L12:
            r5 = 4
            java.lang.Object[] r11 = new java.lang.Object[r5]
            r5 = 3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            r11[r1] = r6
            r6 = 1
            java.lang.String r7 = com.taptap.sdk.db.biz.iap.common.IAPEventUtils.PACKAGE_NAME
            r11[r6] = r7
            r6 = 2
            r11[r6] = r15
            r11[r5] = r3
            java.lang.String r8 = "com.android.vending.billing.IInAppBillingService"
            java.lang.String r9 = "getPurchases"
            r6 = r12
            r7 = r13
            r10 = r14
            java.lang.Object r3 = r6.invokeMethod(r7, r8, r9, r10, r11)
            if (r3 == 0) goto L56
            android.os.Bundle r3 = (android.os.Bundle) r3
            java.lang.String r5 = "RESPONSE_CODE"
            int r5 = r3.getInt(r5)
            if (r5 != 0) goto L56
            java.lang.String r5 = "INAPP_PURCHASE_DATA_LIST"
            java.util.ArrayList r5 = r3.getStringArrayList(r5)
            if (r5 == 0) goto L5d
            int r6 = r5.size()
            int r4 = r4 + r6
            java.util.Collection r5 = (java.util.Collection) r5
            r0.addAll(r5)
            java.lang.String r5 = "INAPP_CONTINUATION_TOKEN"
            java.lang.String r3 = r3.getString(r5)
            goto L57
        L56:
            r3 = r2
        L57:
            r5 = 30
            if (r4 >= r5) goto L5d
            if (r3 != 0) goto L12
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.biz.iap.common.IAPEventUtils.getPurchases(android.content.Context, java.lang.Object, java.lang.String):java.util.ArrayList");
    }

    public final boolean hasFreeTrialPeirod(String skuDetail) {
        Intrinsics.checkNotNullParameter(skuDetail, "skuDetail");
        try {
            String strOptString = new JSONObject(skuDetail).optString("freeTrialPeriod");
            if (strOptString != null) {
                return strOptString.length() > 0;
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    @JvmStatic
    public static final ArrayList<String> getPurchaseHistoryInapp(Context context, Object inAppBillingObj) {
        Class<?> cls;
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<String> arrayList = new ArrayList<>();
        return (inAppBillingObj == null || (cls = INSTANCE.getClass(context, IN_APP_BILLING_SERVICE)) == null || INSTANCE.getMethod(cls, GET_PURCHASE_HISTORY) == null) ? arrayList : INSTANCE.filterPurchases(INSTANCE.getPurchaseHistory(context, inAppBillingObj, "inapp"));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.ArrayList<java.lang.String> getPurchaseHistory(android.content.Context r19, java.lang.Object r20, java.lang.String r21) {
        /*
            r18 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r18.isBillingSupported(r19, r20, r21)
            if (r1 == 0) goto L95
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = 0
            r5 = 0
        L10:
            r6 = 5
            java.lang.Object[] r12 = new java.lang.Object[r6]
            r6 = 6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r12[r2] = r6
            java.lang.String r6 = com.taptap.sdk.db.biz.iap.common.IAPEventUtils.PACKAGE_NAME
            r13 = 1
            r12[r13] = r6
            r6 = 2
            r12[r6] = r21
            r6 = 3
            r12[r6] = r3
            r3 = 4
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            r12[r3] = r6
            java.lang.String r9 = "com.android.vending.billing.IInAppBillingService"
            java.lang.String r10 = "getPurchaseHistory"
            r7 = r18
            r8 = r19
            r11 = r20
            java.lang.Object r3 = r7.invokeMethod(r8, r9, r10, r11, r12)
            if (r3 == 0) goto L8c
            long r6 = java.lang.System.currentTimeMillis()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            android.os.Bundle r3 = (android.os.Bundle) r3
            java.lang.String r10 = "RESPONSE_CODE"
            int r10 = r3.getInt(r10)
            if (r10 != 0) goto L8c
            java.lang.String r10 = "INAPP_PURCHASE_DATA_LIST"
            java.util.ArrayList r10 = r3.getStringArrayList(r10)
            if (r10 != 0) goto L57
            goto L8c
        L57:
            java.util.Iterator r10 = r10.iterator()
        L5b:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L85
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch: org.json.JSONException -> L83
            r12.<init>(r11)     // Catch: org.json.JSONException -> L83
            java.lang.String r14 = "purchaseTime"
            long r14 = r12.getLong(r14)     // Catch: org.json.JSONException -> L83
            long r14 = r14 / r8
            long r14 = r6 - r14
            r16 = 1200(0x4b0, double:5.93E-321)
            int r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r12 <= 0) goto L7d
            r5 = 1
            goto L85
        L7d:
            r0.add(r11)     // Catch: org.json.JSONException -> L83
            int r4 = r4 + 1
            goto L5b
        L83:
            goto L5b
        L85:
            java.lang.String r6 = "INAPP_CONTINUATION_TOKEN"
            java.lang.String r3 = r3.getString(r6)
            goto L8d
        L8c:
            r3 = r1
        L8d:
            r6 = 30
            if (r4 >= r6) goto L95
            if (r3 == 0) goto L95
            if (r5 == 0) goto L10
        L95:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.biz.iap.common.IAPEventUtils.getPurchaseHistory(android.content.Context, java.lang.Object, java.lang.String):java.util.ArrayList");
    }

    private final ArrayList<String> filterPurchases(ArrayList<String> purchases) {
        ArrayList<String> arrayList = new ArrayList<>();
        SharedPreferences.Editor editorEdit = purchaseInappSharedPrefs.edit();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        for (String str : purchases) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(BillingClientConstants.PRODUCT_ID);
                long j = jSONObject.getLong("purchaseTime");
                String string2 = jSONObject.getString("purchaseToken");
                if (jCurrentTimeMillis - (j / 1000) <= 86400 && !Intrinsics.areEqual(purchaseInappSharedPrefs.getString(string, ""), string2)) {
                    editorEdit.putString(string, string2);
                    arrayList.add(str);
                }
            } catch (JSONException unused) {
            }
        }
        editorEdit.apply();
        return arrayList;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Method getMethod(Class<?> classObj, String methodName) {
        Class[] clsArr;
        Method method = methodMap.get(methodName);
        if (method != null) {
            return method;
        }
        switch (methodName.hashCode()) {
            case -1801122596:
                if (!methodName.equals(GET_PURCHASES)) {
                    clsArr = null;
                } else {
                    Class TYPE = Integer.TYPE;
                    Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
                    clsArr = new Class[]{TYPE, String.class, String.class, String.class};
                }
                break;
            case -1450694211:
                if (!methodName.equals(IS_BILLING_SUPPORTED)) {
                    clsArr = null;
                } else {
                    Class TYPE2 = Integer.TYPE;
                    Intrinsics.checkNotNullExpressionValue(TYPE2, "TYPE");
                    clsArr = new Class[]{TYPE2, String.class, String.class};
                }
                break;
            case -1123215065:
                clsArr = !methodName.equals(AS_INTERFACE) ? null : new Class[]{IBinder.class};
                break;
            case -594356707:
                if (!methodName.equals(GET_PURCHASE_HISTORY)) {
                    clsArr = null;
                } else {
                    Class TYPE3 = Integer.TYPE;
                    Intrinsics.checkNotNullExpressionValue(TYPE3, "TYPE");
                    clsArr = new Class[]{TYPE3, String.class, String.class, String.class, Bundle.class};
                }
                break;
            case -573310373:
                if (!methodName.equals(GET_SKU_DETAILS)) {
                    clsArr = null;
                } else {
                    Class TYPE4 = Integer.TYPE;
                    Intrinsics.checkNotNullExpressionValue(TYPE4, "TYPE");
                    clsArr = new Class[]{TYPE4, String.class, String.class, Bundle.class};
                }
                break;
            default:
                clsArr = null;
                break;
        }
        Method declaredMethod$tap_db_release = clsArr == null ? InAppPurchaseUtils.getDeclaredMethod$tap_db_release(classObj, methodName, null) : InAppPurchaseUtils.getDeclaredMethod$tap_db_release(classObj, methodName, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
        if (declaredMethod$tap_db_release != null) {
            methodMap.put(methodName, declaredMethod$tap_db_release);
        }
        return declaredMethod$tap_db_release;
    }

    private final Class<?> getClass(Context context, String className) {
        Class<?> cls = classMap.get(className);
        if (cls != null) {
            return cls;
        }
        Class<?> classFromContext$tap_db_release = InAppPurchaseUtils.getClassFromContext$tap_db_release(context, className);
        if (classFromContext$tap_db_release != null) {
            classMap.put(className, classFromContext$tap_db_release);
        }
        return classFromContext$tap_db_release;
    }

    private final Object invokeMethod(Context context, String className, String methodName, Object obj, Object[] args) {
        Method method;
        Class<?> cls = getClass(context, className);
        if (cls == null || (method = getMethod(cls, methodName)) == null) {
            return null;
        }
        return InAppPurchaseUtils.invokeMethod(cls, method, obj, Arrays.copyOf(args, args.length));
    }

    @JvmStatic
    public static final void clearSkuDetailsCache() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long j = skuDetailSharedPrefs.getLong(LAST_CLEARED_TIME, 0L);
        if (j == 0) {
            skuDetailSharedPrefs.edit().putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
        } else if (jCurrentTimeMillis - j > 604800) {
            skuDetailSharedPrefs.edit().clear().putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
        }
    }
}
