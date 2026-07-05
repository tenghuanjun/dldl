package com.taptap.sdk.review;

import com.taptap.sdk.initializer.api.check.TapSdkChecker;
import com.taptap.sdk.review.internal.TapReviewInternal;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: TapTapReview.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/review/TapTapReview;", "", "()V", "openReview", "", "tap-review_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTapReview {
    public static final TapTapReview INSTANCE = new TapTapReview();

    private TapTapReview() {
    }

    @JvmStatic
    public static final void openReview() {
        if (TapSdkChecker.INSTANCE.checkInitialize()) {
            TapReviewInternal.INSTANCE.openReview();
        }
    }
}
