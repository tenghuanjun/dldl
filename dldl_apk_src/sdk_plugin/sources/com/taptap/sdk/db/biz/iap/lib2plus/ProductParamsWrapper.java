package com.taptap.sdk.db.biz.iap.lib2plus;

import com.taptap.sdk.db.biz.iap.common.InAppPurchaseUtils;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProductParamsWrapper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\n\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper;", "Lcom/taptap/sdk/db/biz/iap/lib2plus/IParamsWrapper;", "()V", "reflectParams", "Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper$ReflectParams;", "ensureReflectParams", "", "getProductItem", "", BillingClientConstants.PRODUCT_ID, "", "productType", "getReflectParams", "ReflectParams", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ProductParamsWrapper implements IParamsWrapper {
    private ReflectParams reflectParams;

    private final void ensureReflectParams() {
        if (this.reflectParams == null) {
            Class<?> cls = InAppPurchaseUtils.getClass(ParamsConstants.CLASSNAME_QUERY_PRODUCT_DETAILS_PARAMS_PRODUCT);
            Class<?> cls2 = InAppPurchaseUtils.getClass(ParamsConstants.CLASSNAME_QUERY_PRODUCT_DETAILS_PARAMS_PRODUCT_BUILDER);
            if (cls == null || cls2 == null) {
                return;
            }
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", new Class[0]);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, ParamsConstants.METHOD_SET_PRODUCT_ID, String.class);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, ParamsConstants.METHOD_SET_PRODUCT_TYPE, String.class);
            Method method4 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null) {
                return;
            }
            this.reflectParams = new ReflectParams(cls, cls2, method, method2, method3, method4);
        }
    }

    public final Object getProductItem(String productId, String productType) {
        Object objInvokeMethod;
        Object objInvokeMethod2;
        Object objInvokeMethod3;
        String str = productId;
        if (str == null || str.length() == 0) {
            return null;
        }
        String str2 = productType;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        ensureReflectParams();
        ReflectParams reflectParams = this.reflectParams;
        if (reflectParams == null || (objInvokeMethod = InAppPurchaseUtils.invokeMethod(reflectParams.getProductItemClazz$tap_db_release(), reflectParams.getNewBuilderMethod$tap_db_release(), null, new Object[0])) == null || (objInvokeMethod2 = InAppPurchaseUtils.invokeMethod(reflectParams.getBuilderClazz$tap_db_release(), reflectParams.getSetTypeMethod$tap_db_release(), objInvokeMethod, productType)) == null || (objInvokeMethod3 = InAppPurchaseUtils.invokeMethod(reflectParams.getBuilderClazz$tap_db_release(), reflectParams.getSetProductIdMethod$tap_db_release(), objInvokeMethod2, productId)) == null) {
            return null;
        }
        return InAppPurchaseUtils.invokeMethod(reflectParams.getBuilderClazz$tap_db_release(), reflectParams.getBuildMethod$tap_db_release(), objInvokeMethod3, new Object[0]);
    }

    @Override // com.taptap.sdk.db.biz.iap.lib2plus.IParamsWrapper
    public ReflectParams getReflectParams() {
        ensureReflectParams();
        return this.reflectParams;
    }

    /* JADX INFO: compiled from: ProductParamsWrapper.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u0012\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÀ\u0003¢\u0006\u0002\b\u0014J\u0012\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0003HÀ\u0003¢\u0006\u0002\b\u0016J\u000e\u0010\u0017\u001a\u00020\u0006HÀ\u0003¢\u0006\u0002\b\u0018J\u000e\u0010\u0019\u001a\u00020\u0006HÀ\u0003¢\u0006\u0002\b\u001aJ\u000e\u0010\u001b\u001a\u00020\u0006HÀ\u0003¢\u0006\u0002\b\u001cJ\u000e\u0010\u001d\u001a\u00020\u0006HÀ\u0003¢\u0006\u0002\b\u001eJM\u0010\u001f\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\b\u0002\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u0014\u0010\t\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\b\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006'"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/lib2plus/ProductParamsWrapper$ReflectParams;", "", "productItemClazz", "Ljava/lang/Class;", "builderClazz", "newBuilderMethod", "Ljava/lang/reflect/Method;", "setProductIdMethod", "setTypeMethod", "buildMethod", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getBuildMethod$tap_db_release", "()Ljava/lang/reflect/Method;", "getBuilderClazz$tap_db_release", "()Ljava/lang/Class;", "getNewBuilderMethod$tap_db_release", "getProductItemClazz$tap_db_release", "getSetProductIdMethod$tap_db_release", "getSetTypeMethod$tap_db_release", "component1", "component1$tap_db_release", "component2", "component2$tap_db_release", "component3", "component3$tap_db_release", "component4", "component4$tap_db_release", "component5", "component5$tap_db_release", "component6", "component6$tap_db_release", "copy", "equals", "", "other", "hashCode", "", "toString", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class ReflectParams {
        private final Method buildMethod;
        private final Class<?> builderClazz;
        private final Method newBuilderMethod;
        private final Class<?> productItemClazz;
        private final Method setProductIdMethod;
        private final Method setTypeMethod;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ReflectParams copy$default(ReflectParams reflectParams, Class cls, Class cls2, Method method, Method method2, Method method3, Method method4, int i, Object obj) {
            if ((i & 1) != 0) {
                cls = reflectParams.productItemClazz;
            }
            if ((i & 2) != 0) {
                cls2 = reflectParams.builderClazz;
            }
            Class cls3 = cls2;
            if ((i & 4) != 0) {
                method = reflectParams.newBuilderMethod;
            }
            Method method5 = method;
            if ((i & 8) != 0) {
                method2 = reflectParams.setProductIdMethod;
            }
            Method method6 = method2;
            if ((i & 16) != 0) {
                method3 = reflectParams.setTypeMethod;
            }
            Method method7 = method3;
            if ((i & 32) != 0) {
                method4 = reflectParams.buildMethod;
            }
            return reflectParams.copy(cls, cls3, method5, method6, method7, method4);
        }

        public final Class<?> component1$tap_db_release() {
            return this.productItemClazz;
        }

        public final Class<?> component2$tap_db_release() {
            return this.builderClazz;
        }

        /* JADX INFO: renamed from: component3$tap_db_release, reason: from getter */
        public final Method getNewBuilderMethod() {
            return this.newBuilderMethod;
        }

        /* JADX INFO: renamed from: component4$tap_db_release, reason: from getter */
        public final Method getSetProductIdMethod() {
            return this.setProductIdMethod;
        }

        /* JADX INFO: renamed from: component5$tap_db_release, reason: from getter */
        public final Method getSetTypeMethod() {
            return this.setTypeMethod;
        }

        /* JADX INFO: renamed from: component6$tap_db_release, reason: from getter */
        public final Method getBuildMethod() {
            return this.buildMethod;
        }

        public final ReflectParams copy(Class<?> productItemClazz, Class<?> builderClazz, Method newBuilderMethod, Method setProductIdMethod, Method setTypeMethod, Method buildMethod) {
            Intrinsics.checkNotNullParameter(productItemClazz, "productItemClazz");
            Intrinsics.checkNotNullParameter(builderClazz, "builderClazz");
            Intrinsics.checkNotNullParameter(newBuilderMethod, "newBuilderMethod");
            Intrinsics.checkNotNullParameter(setProductIdMethod, "setProductIdMethod");
            Intrinsics.checkNotNullParameter(setTypeMethod, "setTypeMethod");
            Intrinsics.checkNotNullParameter(buildMethod, "buildMethod");
            return new ReflectParams(productItemClazz, builderClazz, newBuilderMethod, setProductIdMethod, setTypeMethod, buildMethod);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReflectParams)) {
                return false;
            }
            ReflectParams reflectParams = (ReflectParams) other;
            return Intrinsics.areEqual(this.productItemClazz, reflectParams.productItemClazz) && Intrinsics.areEqual(this.builderClazz, reflectParams.builderClazz) && Intrinsics.areEqual(this.newBuilderMethod, reflectParams.newBuilderMethod) && Intrinsics.areEqual(this.setProductIdMethod, reflectParams.setProductIdMethod) && Intrinsics.areEqual(this.setTypeMethod, reflectParams.setTypeMethod) && Intrinsics.areEqual(this.buildMethod, reflectParams.buildMethod);
        }

        public int hashCode() {
            return (((((((((this.productItemClazz.hashCode() * 31) + this.builderClazz.hashCode()) * 31) + this.newBuilderMethod.hashCode()) * 31) + this.setProductIdMethod.hashCode()) * 31) + this.setTypeMethod.hashCode()) * 31) + this.buildMethod.hashCode();
        }

        public String toString() {
            return "ReflectParams(productItemClazz=" + this.productItemClazz + ", builderClazz=" + this.builderClazz + ", newBuilderMethod=" + this.newBuilderMethod + ", setProductIdMethod=" + this.setProductIdMethod + ", setTypeMethod=" + this.setTypeMethod + ", buildMethod=" + this.buildMethod + ')';
        }

        public ReflectParams(Class<?> productItemClazz, Class<?> builderClazz, Method newBuilderMethod, Method setProductIdMethod, Method setTypeMethod, Method buildMethod) {
            Intrinsics.checkNotNullParameter(productItemClazz, "productItemClazz");
            Intrinsics.checkNotNullParameter(builderClazz, "builderClazz");
            Intrinsics.checkNotNullParameter(newBuilderMethod, "newBuilderMethod");
            Intrinsics.checkNotNullParameter(setProductIdMethod, "setProductIdMethod");
            Intrinsics.checkNotNullParameter(setTypeMethod, "setTypeMethod");
            Intrinsics.checkNotNullParameter(buildMethod, "buildMethod");
            this.productItemClazz = productItemClazz;
            this.builderClazz = builderClazz;
            this.newBuilderMethod = newBuilderMethod;
            this.setProductIdMethod = setProductIdMethod;
            this.setTypeMethod = setTypeMethod;
            this.buildMethod = buildMethod;
        }

        public final Class<?> getProductItemClazz$tap_db_release() {
            return this.productItemClazz;
        }

        public final Class<?> getBuilderClazz$tap_db_release() {
            return this.builderClazz;
        }

        public final Method getNewBuilderMethod$tap_db_release() {
            return this.newBuilderMethod;
        }

        public final Method getSetProductIdMethod$tap_db_release() {
            return this.setProductIdMethod;
        }

        public final Method getSetTypeMethod$tap_db_release() {
            return this.setTypeMethod;
        }

        public final Method getBuildMethod$tap_db_release() {
            return this.buildMethod;
        }
    }
}
