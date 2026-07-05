package com.taptap.sdk.db.biz.iap.lib2plus;

import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProductDetailsParamsWrapper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nJ\n\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper;", "Lcom/taptap/sdk/db/biz/iap/lib2plus/IParamsWrapper;", "()V", "reflectParams", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper$ReflectParams;", "ensureReflectParams", "", "getProductDetailsParams", "", "productMap", "", "", "getReflectParams", "ReflectParams", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ProductDetailsParamsWrapper implements IParamsWrapper {
    private ReflectParams reflectParams;

    private final void ensureReflectParams() {
        if (this.reflectParams == null) {
            Class<?> cls = InAppPurchaseUtils.getClass(ParamsConstants.CLASSNAME_QUERY_PRODUCT_DETAILS_PARAMS);
            Class<?> cls2 = InAppPurchaseUtils.getClass(ParamsConstants.CLASSNAME_QUERY_PRODUCT_DETAILS_PARAMS_BUILDER);
            if (cls == null || cls2 == null) {
                return;
            }
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", new Class[0]);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, ParamsConstants.METHOD_SET_PRODUCT_LIST, List.class);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            ProductParamsWrapper productParamsWrapper = new ProductParamsWrapper();
            if (method == null || method2 == null || method3 == null || productParamsWrapper.getReflectParams() == null) {
                return;
            }
            this.reflectParams = new ReflectParams(cls, cls2, method, method2, productParamsWrapper, method3);
        }
    }

    public final Object getProductDetailsParams(Map<String, String> productMap) {
        Object objInvokeMethod;
        Object objInvokeMethod2;
        if (productMap == null || productMap.isEmpty()) {
            return null;
        }
        ensureReflectParams();
        ReflectParams reflectParams = this.reflectParams;
        if (reflectParams == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : productMap.keySet()) {
            Object productItem = reflectParams.getProductItemWrapper().getProductItem(str, productMap.get(str));
            if (productItem != null) {
                arrayList.add(productItem);
            }
        }
        if (arrayList.isEmpty() || (objInvokeMethod = InAppPurchaseUtils.invokeMethod(reflectParams.getProductDetailsParamsClazz$tap_db_release(), reflectParams.getNewBuilderMethod(), null, new Object[0])) == null || (objInvokeMethod2 = InAppPurchaseUtils.invokeMethod(reflectParams.getBuilderClazz$tap_db_release(), reflectParams.getSetProductListMethod(), objInvokeMethod, arrayList)) == null) {
            return null;
        }
        return InAppPurchaseUtils.invokeMethod(reflectParams.getBuilderClazz$tap_db_release(), reflectParams.getBuildMethod(), objInvokeMethod2, new Object[0]);
    }

    /* JADX INFO: compiled from: ProductDetailsParamsWrapper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B=\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bR\u0014\u0010\n\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductDetailsParamsWrapper$ReflectParams;", "", "productDetailsParamsClazz", "Ljava/lang/Class;", "builderClazz", "newBuilderMethod", "Ljava/lang/reflect/Method;", "setProductListMethod", "productItemWrapper", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper;", "buildMethod", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper;Ljava/lang/reflect/Method;)V", "getBuildMethod$tap_db_release", "()Ljava/lang/reflect/Method;", "getBuilderClazz$tap_db_release", "()Ljava/lang/Class;", "getNewBuilderMethod$tap_db_release", "getProductDetailsParamsClazz$tap_db_release", "getProductItemWrapper$tap_db_release", "()Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper;", "getSetProductListMethod$tap_db_release", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class ReflectParams {
        private final Method buildMethod;
        private final Class<?> builderClazz;
        private final Method newBuilderMethod;
        private final Class<?> productDetailsParamsClazz;
        private final ProductParamsWrapper productItemWrapper;
        private final Method setProductListMethod;

        public ReflectParams(Class<?> productDetailsParamsClazz, Class<?> builderClazz, Method newBuilderMethod, Method setProductListMethod, ProductParamsWrapper productItemWrapper, Method buildMethod) {
            Intrinsics.checkNotNullParameter(productDetailsParamsClazz, "productDetailsParamsClazz");
            Intrinsics.checkNotNullParameter(builderClazz, "builderClazz");
            Intrinsics.checkNotNullParameter(newBuilderMethod, "newBuilderMethod");
            Intrinsics.checkNotNullParameter(setProductListMethod, "setProductListMethod");
            Intrinsics.checkNotNullParameter(productItemWrapper, "productItemWrapper");
            Intrinsics.checkNotNullParameter(buildMethod, "buildMethod");
            this.productDetailsParamsClazz = productDetailsParamsClazz;
            this.builderClazz = builderClazz;
            this.newBuilderMethod = newBuilderMethod;
            this.setProductListMethod = setProductListMethod;
            this.productItemWrapper = productItemWrapper;
            this.buildMethod = buildMethod;
        }

        public final Class<?> getProductDetailsParamsClazz$tap_db_release() {
            return this.productDetailsParamsClazz;
        }

        public final Class<?> getBuilderClazz$tap_db_release() {
            return this.builderClazz;
        }

        /* JADX INFO: renamed from: getNewBuilderMethod$tap_db_release, reason: from getter */
        public final Method getNewBuilderMethod() {
            return this.newBuilderMethod;
        }

        /* JADX INFO: renamed from: getSetProductListMethod$tap_db_release, reason: from getter */
        public final Method getSetProductListMethod() {
            return this.setProductListMethod;
        }

        /* JADX INFO: renamed from: getProductItemWrapper$tap_db_release, reason: from getter */
        public final ProductParamsWrapper getProductItemWrapper() {
            return this.productItemWrapper;
        }

        /* JADX INFO: renamed from: getBuildMethod$tap_db_release, reason: from getter */
        public final Method getBuildMethod() {
            return this.buildMethod;
        }
    }

    @Override // com.taptap.sdk.db.biz.iap.lib2plus.IParamsWrapper
    public ReflectParams getReflectParams() {
        ensureReflectParams();
        return this.reflectParams;
    }
}
