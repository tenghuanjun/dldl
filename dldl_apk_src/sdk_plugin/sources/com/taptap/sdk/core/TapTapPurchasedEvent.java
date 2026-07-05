package com.taptap.sdk.core;

import com.taptap.sdk.db.constant.Common;
import defpackage.C$r8$backportedMethods$utility$Double$1$hashCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapPurchasedEvent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "", "orderId", "", "productName", "amount", "", "currencyType", "paymentMethod", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;)V", "getAmount", "()D", "getCurrencyType", "()Ljava/lang/String;", "getOrderId", "getPaymentMethod", "getProductName", "getProperties", "()Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "tap-db-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class TapTapPurchasedEvent {
    private final double amount;
    private final String currencyType;
    private final String orderId;
    private final String paymentMethod;
    private final String productName;
    private final JSONObject properties;

    public static /* synthetic */ TapTapPurchasedEvent copy$default(TapTapPurchasedEvent tapTapPurchasedEvent, String str, String str2, double d, String str3, String str4, JSONObject jSONObject, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tapTapPurchasedEvent.orderId;
        }
        if ((i & 2) != 0) {
            str2 = tapTapPurchasedEvent.productName;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            d = tapTapPurchasedEvent.amount;
        }
        double d2 = d;
        if ((i & 8) != 0) {
            str3 = tapTapPurchasedEvent.currencyType;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = tapTapPurchasedEvent.paymentMethod;
        }
        String str7 = str4;
        if ((i & 32) != 0) {
            jSONObject = tapTapPurchasedEvent.properties;
        }
        return tapTapPurchasedEvent.copy(str, str5, d2, str6, str7, jSONObject);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getAmount() {
        return this.amount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCurrencyType() {
        return this.currencyType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPaymentMethod() {
        return this.paymentMethod;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final JSONObject getProperties() {
        return this.properties;
    }

    public final TapTapPurchasedEvent copy(String orderId, String productName, double amount, String currencyType, String paymentMethod, JSONObject properties) {
        return new TapTapPurchasedEvent(orderId, productName, amount, currencyType, paymentMethod, properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapTapPurchasedEvent)) {
            return false;
        }
        TapTapPurchasedEvent tapTapPurchasedEvent = (TapTapPurchasedEvent) other;
        return Intrinsics.areEqual(this.orderId, tapTapPurchasedEvent.orderId) && Intrinsics.areEqual(this.productName, tapTapPurchasedEvent.productName) && Double.compare(this.amount, tapTapPurchasedEvent.amount) == 0 && Intrinsics.areEqual(this.currencyType, tapTapPurchasedEvent.currencyType) && Intrinsics.areEqual(this.paymentMethod, tapTapPurchasedEvent.paymentMethod) && Intrinsics.areEqual(this.properties, tapTapPurchasedEvent.properties);
    }

    public int hashCode() {
        String str = this.orderId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.productName;
        int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + C$r8$backportedMethods$utility$Double$1$hashCode.hashCode(this.amount)) * 31;
        String str3 = this.currencyType;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.paymentMethod;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        JSONObject jSONObject = this.properties;
        return iHashCode4 + (jSONObject != null ? jSONObject.hashCode() : 0);
    }

    public String toString() {
        return "TapTapPurchasedEvent(orderId=" + this.orderId + ", productName=" + this.productName + ", amount=" + this.amount + ", currencyType=" + this.currencyType + ", paymentMethod=" + this.paymentMethod + ", properties=" + this.properties + ')';
    }

    public TapTapPurchasedEvent(String str, String str2, double d, String str3, String str4, JSONObject jSONObject) {
        this.orderId = str;
        this.productName = str2;
        this.amount = d;
        this.currencyType = str3;
        this.paymentMethod = str4;
        this.properties = jSONObject;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final String getCurrencyType() {
        return this.currencyType;
    }

    public final String getPaymentMethod() {
        return this.paymentMethod;
    }

    public final JSONObject getProperties() {
        return this.properties;
    }
}
