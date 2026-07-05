package com.taptap.sdk.kit.internal.utils;

import android.os.SystemClock;
import com.sqwan.msdk.api.IMUrl;
import com.taptap.sdk.kit.internal.TapTapKit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: UIUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/UIUtil;", "", "()V", "MIN_CLICK_INTERVAL", "", "lastClickTime", "", "statusBarHeight", "getStatusBarHeight", "()I", "statusBarHeight$delegate", "Lkotlin/Lazy;", "isFastClick", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UIUtil {
    private static final int MIN_CLICK_INTERVAL = 200;
    public static final UIUtil INSTANCE = new UIUtil();
    private static long lastClickTime = -1;

    /* JADX INFO: renamed from: statusBarHeight$delegate, reason: from kotlin metadata */
    private static final Lazy statusBarHeight = LazyKt.lazy(new Function0<Integer>() { // from class: com.taptap.sdk.kit.internal.utils.UIUtil$statusBarHeight$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            int identifier = TapTapKit.INSTANCE.getContext().getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS);
            return Integer.valueOf(identifier > 0 ? TapTapKit.INSTANCE.getContext().getResources().getDimensionPixelSize(identifier) : -1);
        }
    });

    private UIUtil() {
    }

    public final int getStatusBarHeight() {
        return ((Number) statusBarHeight.getValue()).intValue();
    }

    public final boolean isFastClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = lastClickTime;
        if (j != -1 && jElapsedRealtime - j < 200) {
            return true;
        }
        lastClickTime = jElapsedRealtime;
        return false;
    }
}
