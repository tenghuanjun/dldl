package com.taptap.sdk.review.internal;

import com.taptap.sdk.kit.internal.http.TapHttp;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TapReviewApi.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0002\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\"\u001b\u0010\b\u001a\u00020\t8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\u0007\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"URL_GET_CROSS_CODE", "", "reviewHttp", "Lcom/taptap/sdk/kit/internal/http/TapHttp;", "getReviewHttp", "()Lcom/taptap/sdk/kit/internal/http/TapHttp;", "reviewHttp$delegate", "Lkotlin/Lazy;", "reviewScope", "Lkotlinx/coroutines/CoroutineScope;", "getReviewScope", "()Lkotlinx/coroutines/CoroutineScope;", "reviewScope$delegate", "tap-review_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class TapReviewApiKt {
    private static final String URL_GET_CROSS_CODE = "/sdk-core/v1/cross-app-code";
    private static final Lazy reviewScope$delegate = LazyKt.lazy(TapReviewApiKt$reviewScope$2.INSTANCE);
    private static final Lazy reviewHttp$delegate = LazyKt.lazy(new Function0<TapHttp>() { // from class: com.taptap.sdk.review.internal.TapReviewApiKt$reviewHttp$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TapHttp invoke() {
            return TapHttp.INSTANCE.newBuilder("TapSDKCore", "4.5.7").enableAuthorization().build();
        }
    });

    public static final CoroutineScope getReviewScope() {
        return (CoroutineScope) reviewScope$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TapHttp getReviewHttp() {
        return (TapHttp) reviewHttp$delegate.getValue();
    }
}
