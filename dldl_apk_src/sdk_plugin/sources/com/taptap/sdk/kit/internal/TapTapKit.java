package com.taptap.sdk.kit.internal;

import android.content.Context;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleTracker;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapTapKit.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\u0015J\u000e\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020\u0015J\u000e\u0010'\u001a\u00020!2\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0014\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Lcom/taptap/sdk/kit/internal/TapTapKit;", "", "()V", "clientId", "", "getClientId$tap_common_release", "()Ljava/lang/String;", "setClientId$tap_common_release", "(Ljava/lang/String;)V", "clientToken", "getClientToken$tap_common_release", "setClientToken$tap_common_release", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isRND", "", "isRND$annotations", "()Z", "setRND", "(Z)V", "regionType", "Lcom/taptap/sdk/initializer/api/model/RegionType;", "getRegionType$tap_common_release", "()Lcom/taptap/sdk/initializer/api/model/RegionType;", "setRegionType$tap_common_release", "(Lcom/taptap/sdk/initializer/api/model/RegionType;)V", "initialize", "", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "isInitialized", "markInitialized", "value", "onCreate", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTapKit {
    public static Context context;
    private static boolean isRND;
    public static final TapTapKit INSTANCE = new TapTapKit();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static String clientId = "";
    private static String clientToken = "";
    private static RegionType regionType = RegionType.CN;

    @Deprecated(message = "这种方式来切换环境并不好，之后会移除")
    public static /* synthetic */ void isRND$annotations() {
    }

    private TapTapKit() {
    }

    public final String getClientId$tap_common_release() {
        return clientId;
    }

    public final void setClientId$tap_common_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        clientId = str;
    }

    public final String getClientToken$tap_common_release() {
        return clientToken;
    }

    public final void setClientToken$tap_common_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        clientToken = str;
    }

    public final RegionType getRegionType$tap_common_release() {
        return regionType;
    }

    public final void setRegionType$tap_common_release(RegionType regionType2) {
        Intrinsics.checkNotNullParameter(regionType2, "<set-?>");
        regionType = regionType2;
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 != null) {
            return context2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        context = context2;
    }

    public final boolean isRND() {
        return isRND;
    }

    public final void setRND(boolean z) {
        isRND = z;
    }

    public final void onCreate(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        setContext(applicationContext);
        TapActivityLifecycleTracker.INSTANCE.initialize(context2);
    }

    public final synchronized void initialize(TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        setContext(options.getContext());
        clientToken = options.getClientToken();
        clientId = options.getClientId();
        regionType = options.getRegion();
        TapActivityLifecycleTracker.INSTANCE.initialize(options.getContext());
        initialized.set(true);
    }

    public final boolean isInitialized() {
        return initialized.get();
    }

    public final void markInitialized(boolean value) {
        initialized.set(value);
    }
}
