package com.taptap.sdk.core.internal.event.iap.lib2plus;

import com.taptap.sdk.db.biz.iap.lib2plus.ProductDetailsParamsWrapper;
import com.taptap.sdk.db.biz.iap.lib2plus.SkuDetailsParamsWrapper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InAppPurchaseQueryDetailsWrapper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ \u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u000e2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/core/internal/event/iap/lib2plus/InAppPurchaseQueryDetailsWrapper;", "", "skuDetailParamsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/SkuDetailsParamsWrapper;", "productDetailParamsWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper;", "(Lcom/taptap/sdk/db/biz/iap/lib2plus/SkuDetailsParamsWrapper;Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper;)V", "getProductDetailParamsWrapper", "()Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper;", "getSkuDetailParamsWrapper", "()Lcom/taptap/sdk/db/biz/iap/lib2plus/SkuDetailsParamsWrapper;", "getProductDetailsParams", "productMap", "", "", "getSkuDetailsParams", "skuType", "skuIDs", "", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class InAppPurchaseQueryDetailsWrapper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static InAppPurchaseQueryDetailsWrapper instance;
    private final ProductDetailsParamsWrapper productDetailParamsWrapper;
    private final SkuDetailsParamsWrapper skuDetailParamsWrapper;

    @JvmStatic
    public static final InAppPurchaseQueryDetailsWrapper getOrCreateInstance() {
        return INSTANCE.getOrCreateInstance();
    }

    public InAppPurchaseQueryDetailsWrapper(SkuDetailsParamsWrapper skuDetailsParamsWrapper, ProductDetailsParamsWrapper productDetailsParamsWrapper) {
        this.skuDetailParamsWrapper = skuDetailsParamsWrapper;
        this.productDetailParamsWrapper = productDetailsParamsWrapper;
    }

    public final SkuDetailsParamsWrapper getSkuDetailParamsWrapper() {
        return this.skuDetailParamsWrapper;
    }

    public final ProductDetailsParamsWrapper getProductDetailParamsWrapper() {
        return this.productDetailParamsWrapper;
    }

    /* JADX INFO: compiled from: InAppPurchaseQueryDetailsWrapper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/core/internal/event/iap/lib2plus/InAppPurchaseQueryDetailsWrapper$Companion;", "", "()V", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "instance", "Lcom/taptap/sdk/core/internal/event/iap/lib2plus/InAppPurchaseQueryDetailsWrapper;", "createInstance", "", "getOrCreateInstance", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final InAppPurchaseQueryDetailsWrapper getOrCreateInstance() {
            if (InAppPurchaseQueryDetailsWrapper.initialized.get()) {
                return InAppPurchaseQueryDetailsWrapper.instance;
            }
            createInstance();
            InAppPurchaseQueryDetailsWrapper.initialized.set(true);
            return InAppPurchaseQueryDetailsWrapper.instance;
        }

        private final void createInstance() {
            SkuDetailsParamsWrapper skuDetailsParamsWrapper = new SkuDetailsParamsWrapper();
            if (!(skuDetailsParamsWrapper.getReflectParams() != null)) {
                skuDetailsParamsWrapper = null;
            }
            ProductDetailsParamsWrapper productDetailsParamsWrapper = new ProductDetailsParamsWrapper();
            ProductDetailsParamsWrapper productDetailsParamsWrapper2 = productDetailsParamsWrapper.getReflectParams() != null ? productDetailsParamsWrapper : null;
            if (skuDetailsParamsWrapper == null && productDetailsParamsWrapper2 == null) {
                return;
            }
            InAppPurchaseQueryDetailsWrapper.instance = new InAppPurchaseQueryDetailsWrapper(skuDetailsParamsWrapper, productDetailsParamsWrapper2);
        }
    }

    public final Object getSkuDetailsParams(String skuType, List<String> skuIDs) {
        Intrinsics.checkNotNullParameter(skuType, "skuType");
        Intrinsics.checkNotNullParameter(skuIDs, "skuIDs");
        SkuDetailsParamsWrapper skuDetailsParamsWrapper = this.skuDetailParamsWrapper;
        if (skuDetailsParamsWrapper != null) {
            return skuDetailsParamsWrapper.getSkuDetailsParams(skuType, skuIDs);
        }
        return null;
    }

    public final Object getProductDetailsParams(Map<String, String> productMap) {
        ProductDetailsParamsWrapper productDetailsParamsWrapper = this.productDetailParamsWrapper;
        if (productDetailsParamsWrapper != null) {
            return productDetailsParamsWrapper.getProductDetailsParams(productMap);
        }
        return null;
    }
}
