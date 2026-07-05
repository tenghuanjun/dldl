package com.taptap.sdk.base.utils.lifecycle;

import android.app.Activity;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapActivityUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/base/utils/lifecycle/TapActivityUtils;", "", "()V", "isActivityAlive", "", "activity", "Landroid/app/Activity;", "tap-base_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapActivityUtils {
    public static final TapActivityUtils INSTANCE = new TapActivityUtils();

    private TapActivityUtils() {
    }

    public final boolean isActivityAlive(Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }
}
