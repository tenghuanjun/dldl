package com.taptap.sdk.db.biz.iap.common;

import com.taptap.sdk.core.TapTapPurchasedEvent;
import com.taptap.sdk.db.event.LegacyEvents;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: AutomaticAnalyticsLogger.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0004*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/common/AutomaticAnalyticsLogger;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "generateTapPurchasedEvent", "Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "purchase", "skuDetails", "logPurchase", "", "isSubscription", "", "getLongValue", "", "Lorg/json/JSONObject;", "key", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Long;", "getStringValue", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class AutomaticAnalyticsLogger {
    public static final AutomaticAnalyticsLogger INSTANCE = new AutomaticAnalyticsLogger();
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();

    private AutomaticAnalyticsLogger() {
    }

    @JvmStatic
    public static final void logPurchase(String purchase, String skuDetails, boolean isSubscription) {
        TapTapPurchasedEvent tapTapPurchasedEventGenerateTapPurchasedEvent;
        Intrinsics.checkNotNullParameter(purchase, "purchase");
        Intrinsics.checkNotNullParameter(skuDetails, "skuDetails");
        if (isSubscription || (tapTapPurchasedEventGenerateTapPurchasedEvent = INSTANCE.generateTapPurchasedEvent(purchase, skuDetails)) == null) {
            return;
        }
        LegacyEvents.INSTANCE.submitPurchaseEvent(tapTapPurchasedEventGenerateTapPurchasedEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.taptap.sdk.core.TapTapPurchasedEvent generateTapPurchasedEvent(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = "description"
            java.lang.String r1 = "productId"
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L85
            r3.<init>(r12)     // Catch: org.json.JSONException -> L85
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch: org.json.JSONException -> L85
            r12.<init>(r13)     // Catch: org.json.JSONException -> L85
            java.lang.String r13 = r11.getStringValue(r3, r1)     // Catch: org.json.JSONException -> L85
            if (r13 != 0) goto L19
            java.lang.String r13 = r11.getStringValue(r12, r1)     // Catch: org.json.JSONException -> L85
        L19:
            r4 = r13
            java.lang.String r13 = "name"
            java.lang.String r13 = r11.getStringValue(r12, r13)     // Catch: org.json.JSONException -> L85
            if (r13 != 0) goto L28
            java.lang.String r13 = "title"
            java.lang.String r13 = r11.getStringValue(r12, r13)     // Catch: org.json.JSONException -> L85
        L28:
            r5 = r13
            java.lang.String r13 = "price_amount_micros"
            java.lang.Long r13 = r11.getLongValue(r12, r13)     // Catch: org.json.JSONException -> L85
            r1 = 1
            r3 = 0
            if (r13 == 0) goto L55
            r6 = r13
            java.lang.Number r6 = (java.lang.Number) r6     // Catch: org.json.JSONException -> L85
            long r6 = r6.longValue()     // Catch: org.json.JSONException -> L85
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L42
            r6 = 1
            goto L43
        L42:
            r6 = 0
        L43:
            if (r6 == 0) goto L46
            goto L47
        L46:
            r13 = r2
        L47:
            if (r13 == 0) goto L55
            java.lang.Number r13 = (java.lang.Number) r13     // Catch: org.json.JSONException -> L85
            long r6 = r13.longValue()     // Catch: org.json.JSONException -> L85
            double r6 = (double) r6     // Catch: org.json.JSONException -> L85
            r13 = 10000(0x2710, float:1.4013E-41)
            double r8 = (double) r13     // Catch: org.json.JSONException -> L85
            double r6 = r6 / r8
            goto L57
        L55:
            r6 = 0
        L57:
            java.lang.String r13 = "price_currency_code"
            java.lang.String r8 = r11.getStringValue(r12, r13)     // Catch: org.json.JSONException -> L85
            java.lang.String r12 = r11.getStringValue(r12, r0)     // Catch: org.json.JSONException -> L85
            r13 = r12
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13     // Catch: org.json.JSONException -> L85
            if (r13 == 0) goto L6e
            int r13 = r13.length()     // Catch: org.json.JSONException -> L85
            if (r13 != 0) goto L6d
            goto L6e
        L6d:
            r1 = 0
        L6e:
            if (r1 != 0) goto L7a
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch: org.json.JSONException -> L85
            r13.<init>()     // Catch: org.json.JSONException -> L85
            r13.put(r0, r12)     // Catch: org.json.JSONException -> L85
            r10 = r13
            goto L7b
        L7a:
            r10 = r2
        L7b:
            com.taptap.sdk.core.TapTapPurchasedEvent r12 = new com.taptap.sdk.core.TapTapPurchasedEvent     // Catch: org.json.JSONException -> L85
            java.lang.String r9 = "google"
            r3 = r12
            r3.<init>(r4, r5, r6, r8, r9, r10)     // Catch: org.json.JSONException -> L85
            r2 = r12
            goto L8f
        L85:
            r12 = move-exception
            java.lang.String r13 = com.taptap.sdk.db.biz.iap.common.AutomaticAnalyticsLogger.TAG
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.String r0 = "Error parsing in-app subscription data."
            android.util.Log.e(r13, r0, r12)
        L8f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.biz.iap.common.AutomaticAnalyticsLogger.generateTapPurchasedEvent(java.lang.String, java.lang.String):com.taptap.sdk.core.TapTapPurchasedEvent");
    }

    private final String getStringValue(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            return jSONObject.optString(str);
        }
        return null;
    }

    private final Long getLongValue(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            return Long.valueOf(jSONObject.optLong(str));
        }
        return null;
    }
}
