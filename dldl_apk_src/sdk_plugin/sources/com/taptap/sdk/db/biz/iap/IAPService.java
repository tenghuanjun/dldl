package com.taptap.sdk.db.biz.iap;

import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IAPService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000bB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/IAPService;", "", "builder", "Lcom/taptap/sdk/db/biz/iap/IAPService$Builder;", "(Lcom/taptap/sdk/db/biz/iap/IAPService$Builder;)V", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "enableAutoSubmitPurchaseEvent", "", "initialize", "Builder", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class IAPService {
    private final TapTapSdkOptions options;

    public /* synthetic */ IAPService(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private IAPService(TapTapSdkOptions tapTapSdkOptions) {
        this.options = tapTapSdkOptions;
    }

    private IAPService(Builder builder) {
        this(builder.getOptions());
    }

    public final IAPService initialize() {
        enableAutoSubmitPurchaseEvent();
        return this;
    }

    private final void enableAutoSubmitPurchaseEvent() {
        if (this.options.getRegion() == RegionType.GLOBAL && this.options.getAutoIAPEventEnabled()) {
            InAppPurchaseManager.setEnableAutoLogging(true);
        }
    }

    /* JADX INFO: compiled from: IAPService.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/db/biz/iap/IAPService$Builder;", "", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "getOptions", "()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "build", "Lcom/taptap/sdk/db/biz/iap/IAPService;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private final TapTapSdkOptions options;

        public Builder(TapTapSdkOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            this.options = options;
        }

        public final TapTapSdkOptions getOptions() {
            return this.options;
        }

        public final IAPService build() {
            return new IAPService(this, null);
        }
    }
}
