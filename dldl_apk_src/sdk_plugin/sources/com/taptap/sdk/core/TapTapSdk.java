package com.taptap.sdk.core;

import android.content.Context;
import android.widget.Toast;
import com.taptap.sdk.core.utils.OptionsAdapter;
import com.taptap.sdk.initializer.TapTapSdkInitializer;
import com.taptap.sdk.initializer.api.model.Language;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.kit.internal.utils.localize.TapLocalizeUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapTapSdk.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0007¢\u0006\u0002\u0010\fJ1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\n\"\u00020\rH\u0007¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0007¨\u0006\u0012"}, d2 = {"Lcom/taptap/sdk/core/TapTapSdk;", "", "()V", "init", "", "context", "Landroid/content/Context;", "sdkOptions", "Lcom/taptap/sdk/core/TapTapSdkOptions;", "options", "", "", "(Landroid/content/Context;Lcom/taptap/sdk/core/TapTapSdkOptions;[Ljava/lang/String;)V", "Lcom/taptap/sdk/core/TapTapSdkBaseOptions;", "(Landroid/content/Context;Lcom/taptap/sdk/core/TapTapSdkOptions;[Lcom/taptap/sdk/core/TapTapSdkBaseOptions;)V", "setPreferredLanguage", "language", "Lcom/taptap/sdk/core/TapTapLanguage;", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTapSdk {
    public static final TapTapSdk INSTANCE = new TapTapSdk();

    private TapTapSdk() {
    }

    @JvmStatic
    public static final void init(Context context, TapTapSdkOptions sdkOptions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sdkOptions, "sdkOptions");
        init(context, sdkOptions, new TapTapSdkBaseOptions[0]);
    }

    @JvmStatic
    public static final void init(Context context, TapTapSdkOptions sdkOptions, String[] options) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sdkOptions, "sdkOptions");
        Intrinsics.checkNotNullParameter(options, "options");
        try {
            TapTapSdkInitializer.INSTANCE.setINSTANCE(OptionsAdapter.INSTANCE.adapt(new TapTapSdkInitializer.Builder(context).setClientId(sdkOptions.getClientId()).setClientToken(sdkOptions.getClientToken()).setRegion(RegionType.INSTANCE.from(sdkOptions.getRegion())).setChannel(sdkOptions.getChannel()).setAutoIAPEventEnabled(sdkOptions.getAutoIAPEventEnabled()).setOverrideBuiltInParameters(sdkOptions.getOverrideBuiltInParameters()).setGameVersion(sdkOptions.getGameVersion()).enableLog(sdkOptions.getEnableLog()).setProperties(sdkOptions.getProperties()).setLanguage(Language.INSTANCE.from(sdkOptions.getPreferredLanguage().getLanguage())).setOAIDCert(sdkOptions.getOaidCert()).disableReflectionOAID(sdkOptions.getDisableReflectionOAID()).disableAutoLogDeviceLogin(sdkOptions.getDisableAutoLogDeviceLogin()).setOAIDCert(sdkOptions.getOaidCert()).setScreenOrientation(sdkOptions.getScreenOrientation()), ArraysKt.toList(options)).build().initialize());
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                Toast.makeText(TapTapKit.INSTANCE.getContext(), e.getMessage(), 0).show();
            }
            TapLogger.loge$default(null, null, e, 3, null);
        }
    }

    @JvmStatic
    public static final void setPreferredLanguage(TapTapLanguage language) {
        Intrinsics.checkNotNullParameter(language, "language");
        TapLocalizeUtil.INSTANCE.setPreferredLanguage(language.getLanguage());
    }

    @JvmStatic
    public static final void init(Context context, TapTapSdkOptions sdkOptions, TapTapSdkBaseOptions... options) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sdkOptions, "sdkOptions");
        Intrinsics.checkNotNullParameter(options, "options");
        ArrayList arrayList = new ArrayList(options.length);
        for (TapTapSdkBaseOptions tapTapSdkBaseOptions : options) {
            arrayList.add(tapTapSdkBaseOptions.toString());
        }
        init(context, sdkOptions, (String[]) arrayList.toArray(new String[0]));
    }
}
