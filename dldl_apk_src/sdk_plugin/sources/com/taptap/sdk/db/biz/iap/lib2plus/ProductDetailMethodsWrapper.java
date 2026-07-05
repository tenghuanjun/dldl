package com.taptap.sdk.db.biz.iap.lib2plus;

import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProductDetailMethodsWrapper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006#"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailMethodsWrapper;", "", "getOneTimePurchaseOfferDetailsMethod", "Ljava/lang/reflect/Method;", "getProductIdMethod", "getProductNameMethod", "getProductTitleMethod", "getProductDescriptionMethod", "getPriceAmountMicrosMethod", "getPriceCurrencyCodeMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getGetOneTimePurchaseOfferDetailsMethod", "()Ljava/lang/reflect/Method;", "getGetPriceAmountMicrosMethod", "getGetPriceCurrencyCodeMethod", "getGetProductDescriptionMethod", "getGetProductIdMethod", "getGetProductNameMethod", "getGetProductTitleMethod", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class ProductDetailMethodsWrapper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Method getOneTimePurchaseOfferDetailsMethod;
    private final Method getPriceAmountMicrosMethod;
    private final Method getPriceCurrencyCodeMethod;
    private final Method getProductDescriptionMethod;
    private final Method getProductIdMethod;
    private final Method getProductNameMethod;
    private final Method getProductTitleMethod;

    public static /* synthetic */ ProductDetailMethodsWrapper copy$default(ProductDetailMethodsWrapper productDetailMethodsWrapper, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, int i, Object obj) {
        if ((i & 1) != 0) {
            method = productDetailMethodsWrapper.getOneTimePurchaseOfferDetailsMethod;
        }
        if ((i & 2) != 0) {
            method2 = productDetailMethodsWrapper.getProductIdMethod;
        }
        Method method8 = method2;
        if ((i & 4) != 0) {
            method3 = productDetailMethodsWrapper.getProductNameMethod;
        }
        Method method9 = method3;
        if ((i & 8) != 0) {
            method4 = productDetailMethodsWrapper.getProductTitleMethod;
        }
        Method method10 = method4;
        if ((i & 16) != 0) {
            method5 = productDetailMethodsWrapper.getProductDescriptionMethod;
        }
        Method method11 = method5;
        if ((i & 32) != 0) {
            method6 = productDetailMethodsWrapper.getPriceAmountMicrosMethod;
        }
        Method method12 = method6;
        if ((i & 64) != 0) {
            method7 = productDetailMethodsWrapper.getPriceCurrencyCodeMethod;
        }
        return productDetailMethodsWrapper.copy(method, method8, method9, method10, method11, method12, method7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Method getGetOneTimePurchaseOfferDetailsMethod() {
        return this.getOneTimePurchaseOfferDetailsMethod;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Method getGetProductIdMethod() {
        return this.getProductIdMethod;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Method getGetProductNameMethod() {
        return this.getProductNameMethod;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Method getGetProductTitleMethod() {
        return this.getProductTitleMethod;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Method getGetProductDescriptionMethod() {
        return this.getProductDescriptionMethod;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Method getGetPriceAmountMicrosMethod() {
        return this.getPriceAmountMicrosMethod;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Method getGetPriceCurrencyCodeMethod() {
        return this.getPriceCurrencyCodeMethod;
    }

    public final ProductDetailMethodsWrapper copy(Method getOneTimePurchaseOfferDetailsMethod, Method getProductIdMethod, Method getProductNameMethod, Method getProductTitleMethod, Method getProductDescriptionMethod, Method getPriceAmountMicrosMethod, Method getPriceCurrencyCodeMethod) {
        return new ProductDetailMethodsWrapper(getOneTimePurchaseOfferDetailsMethod, getProductIdMethod, getProductNameMethod, getProductTitleMethod, getProductDescriptionMethod, getPriceAmountMicrosMethod, getPriceCurrencyCodeMethod);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProductDetailMethodsWrapper)) {
            return false;
        }
        ProductDetailMethodsWrapper productDetailMethodsWrapper = (ProductDetailMethodsWrapper) other;
        return Intrinsics.areEqual(this.getOneTimePurchaseOfferDetailsMethod, productDetailMethodsWrapper.getOneTimePurchaseOfferDetailsMethod) && Intrinsics.areEqual(this.getProductIdMethod, productDetailMethodsWrapper.getProductIdMethod) && Intrinsics.areEqual(this.getProductNameMethod, productDetailMethodsWrapper.getProductNameMethod) && Intrinsics.areEqual(this.getProductTitleMethod, productDetailMethodsWrapper.getProductTitleMethod) && Intrinsics.areEqual(this.getProductDescriptionMethod, productDetailMethodsWrapper.getProductDescriptionMethod) && Intrinsics.areEqual(this.getPriceAmountMicrosMethod, productDetailMethodsWrapper.getPriceAmountMicrosMethod) && Intrinsics.areEqual(this.getPriceCurrencyCodeMethod, productDetailMethodsWrapper.getPriceCurrencyCodeMethod);
    }

    public int hashCode() {
        Method method = this.getOneTimePurchaseOfferDetailsMethod;
        int iHashCode = (method == null ? 0 : method.hashCode()) * 31;
        Method method2 = this.getProductIdMethod;
        int iHashCode2 = (iHashCode + (method2 == null ? 0 : method2.hashCode())) * 31;
        Method method3 = this.getProductNameMethod;
        int iHashCode3 = (iHashCode2 + (method3 == null ? 0 : method3.hashCode())) * 31;
        Method method4 = this.getProductTitleMethod;
        int iHashCode4 = (iHashCode3 + (method4 == null ? 0 : method4.hashCode())) * 31;
        Method method5 = this.getProductDescriptionMethod;
        int iHashCode5 = (iHashCode4 + (method5 == null ? 0 : method5.hashCode())) * 31;
        Method method6 = this.getPriceAmountMicrosMethod;
        int iHashCode6 = (iHashCode5 + (method6 == null ? 0 : method6.hashCode())) * 31;
        Method method7 = this.getPriceCurrencyCodeMethod;
        return iHashCode6 + (method7 != null ? method7.hashCode() : 0);
    }

    public String toString() {
        return "ProductDetailMethodsWrapper(getOneTimePurchaseOfferDetailsMethod=" + this.getOneTimePurchaseOfferDetailsMethod + ", getProductIdMethod=" + this.getProductIdMethod + ", getProductNameMethod=" + this.getProductNameMethod + ", getProductTitleMethod=" + this.getProductTitleMethod + ", getProductDescriptionMethod=" + this.getProductDescriptionMethod + ", getPriceAmountMicrosMethod=" + this.getPriceAmountMicrosMethod + ", getPriceCurrencyCodeMethod=" + this.getPriceCurrencyCodeMethod + ')';
    }

    public ProductDetailMethodsWrapper(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7) {
        this.getOneTimePurchaseOfferDetailsMethod = method;
        this.getProductIdMethod = method2;
        this.getProductNameMethod = method3;
        this.getProductTitleMethod = method4;
        this.getProductDescriptionMethod = method5;
        this.getPriceAmountMicrosMethod = method6;
        this.getPriceCurrencyCodeMethod = method7;
    }

    public final Method getGetOneTimePurchaseOfferDetailsMethod() {
        return this.getOneTimePurchaseOfferDetailsMethod;
    }

    public final Method getGetProductIdMethod() {
        return this.getProductIdMethod;
    }

    public final Method getGetProductNameMethod() {
        return this.getProductNameMethod;
    }

    public final Method getGetProductTitleMethod() {
        return this.getProductTitleMethod;
    }

    public final Method getGetProductDescriptionMethod() {
        return this.getProductDescriptionMethod;
    }

    public final Method getGetPriceAmountMicrosMethod() {
        return this.getPriceAmountMicrosMethod;
    }

    public final Method getGetPriceCurrencyCodeMethod() {
        return this.getPriceCurrencyCodeMethod;
    }

    /* JADX INFO: compiled from: ProductDetailMethodsWrapper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailMethodsWrapper$Companion;", "", "()V", "getProductDetailMethodsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailMethodsWrapper;", "productDetailsClazz", "Ljava/lang/Class;", "productDetailsOneTimePurchaseOfferDetailsClazz", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ProductDetailMethodsWrapper getProductDetailMethodsWrapper(Class<?> productDetailsClazz, Class<?> productDetailsOneTimePurchaseOfferDetailsClazz) {
            Method method = InAppPurchaseUtils.getMethod(productDetailsClazz, BillingClientConstants.METHOD_GET_ONE_TIME_PURCHASE_OFFER_DETAILS, new Class[0]);
            Method method2 = InAppPurchaseUtils.getMethod(productDetailsClazz, BillingClientConstants.METHOD_GET_PRODUCT_ID, new Class[0]);
            Method method3 = InAppPurchaseUtils.getMethod(productDetailsClazz, BillingClientConstants.METHOD_GET_PRODUCT_NAME, new Class[0]);
            Method method4 = InAppPurchaseUtils.getMethod(productDetailsClazz, BillingClientConstants.METHOD_GET_PRODUCT_TITLE, new Class[0]);
            Method method5 = InAppPurchaseUtils.getMethod(productDetailsClazz, BillingClientConstants.METHOD_GET_PRODUCT_DESCRIPTION, new Class[0]);
            Method method6 = InAppPurchaseUtils.getMethod(productDetailsOneTimePurchaseOfferDetailsClazz, BillingClientConstants.METHOD_GET_PRICE_AMOUNT_MICROS, new Class[0]);
            Method method7 = InAppPurchaseUtils.getMethod(productDetailsOneTimePurchaseOfferDetailsClazz, BillingClientConstants.METHOD_GET_PRICE_CURRENCY_CODE, new Class[0]);
            if (method != null) {
                return new ProductDetailMethodsWrapper(method, method2, method3, method4, method5, method6, method7);
            }
            return null;
        }
    }
}
