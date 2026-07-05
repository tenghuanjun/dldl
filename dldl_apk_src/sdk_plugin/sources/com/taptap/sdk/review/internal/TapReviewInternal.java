package com.taptap.sdk.review.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.taptap.sdk.initializer.api.service.InitializerService;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.kit.internal.extensions.StringExtKt;
import com.taptap.sdk.review.ReviewCrossAppCodeCallback;
import com.taptap.sdk.review.extensions.SystemExtKt;
import com.taptap.sdk.servicemanager.ServiceManager;
import com.taptap.sdk.servicemanager.utils.ServiceManagerComponent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TapReviewInternal.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u000bR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/taptap/sdk/review/internal/TapReviewInternal;", "", "()V", "initializerService", "Lcom/taptap/sdk/initializer/api/service/InitializerService;", "getInitializerService", "()Lcom/taptap/sdk/initializer/api/service/InitializerService;", "initializerService$delegate", "Lkotlin/Lazy;", "goTapDownloadPage", "Lkotlin/Result;", "", "goTapDownloadPage-d1pmJ48", "()Ljava/lang/Object;", "handleReviewAction", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "crossAppCode", "", "handleReviewAction-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "openReview", "tap-review_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapReviewInternal {
    public static final TapReviewInternal INSTANCE = new TapReviewInternal();

    /* JADX INFO: renamed from: initializerService$delegate, reason: from kotlin metadata */
    private static final Lazy initializerService;

    private TapReviewInternal() {
    }

    static {
        ServiceManagerComponent serviceManagerComponent = ServiceManagerComponent.INSTANCE;
        initializerService = LazyKt.lazy(new Function0<InitializerService>() { // from class: com.taptap.sdk.review.internal.TapReviewInternal$special$$inlined$inject$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InitializerService invoke() {
                ServiceManager.Service service = ServiceManager.INSTANCE.getService(InitializerService.class);
                if (service != null) {
                    return (InitializerService) service;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.taptap.sdk.initializer.api.service.InitializerService");
            }
        });
    }

    private final InitializerService getInitializerService() {
        return (InitializerService) initializerService.getValue();
    }

    public final void openReview() {
        ReviewTracker.trackStart$tap_review_release$default(ReviewTracker.INSTANCE, "openReview", StringExtKt.generateSessionId(), null, 4, null);
        if (!SystemExtKt.isTapInstalled()) {
            Toast.makeText(TapTapKit.INSTANCE.getContext(), "下载 TapTap 客户端即可发布评价", 0).show();
            m46goTapDownloadPaged1pmJ48();
        } else {
            TapReviewApi.INSTANCE.fetchCrossAppCode(new ReviewCrossAppCodeCallback() { // from class: com.taptap.sdk.review.internal.TapReviewInternal.openReview.1
                @Override // com.taptap.sdk.review.ReviewCrossAppCodeCallback
                public void onCodeResult(String crossAppCode) {
                    if (crossAppCode != null) {
                        Result.m51boximpl(TapReviewInternal.INSTANCE.m47handleReviewActionIoAF18A(crossAppCode));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleReviewAction-IoAF18A, reason: not valid java name */
    public final Object m47handleReviewActionIoAF18A(String crossAppCode) {
        try {
            Result.Companion companion = Result.INSTANCE;
            String reviewUrl = getInitializerService().getReviewUrl("uri");
            TapReviewLoggerKt.logInfo("handleReviewAction: origin review uri=" + reviewUrl);
            Uri uri = Uri.parse(reviewUrl);
            Uri.Builder builderBuildUpon = uri.buildUpon();
            if (!uri.getQueryParameterNames().contains("tapsdk_cross_app_code")) {
                builderBuildUpon.appendQueryParameter("tapsdk_cross_app_code", crossAppCode);
            }
            Uri uriBuild = builderBuildUpon.appendQueryParameter("sdk_identifier", TapTapKit.INSTANCE.getContext().getPackageName()).build();
            TapReviewLoggerKt.logInfo("handleReviewAction: final review uri=" + uriBuild);
            Context context = TapTapKit.INSTANCE.getContext();
            Intent intent = new Intent();
            intent.setData(uriBuild);
            intent.setFlags(268435456);
            context.startActivity(intent);
            return Result.m52constructorimpl(uri);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m52constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: renamed from: goTapDownloadPage-d1pmJ48, reason: not valid java name */
    private final Object m46goTapDownloadPaged1pmJ48() {
        try {
            Result.Companion companion = Result.INSTANCE;
            String reviewUrl = getInitializerService().getReviewUrl("browser");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(reviewUrl));
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setFlags(268435456);
            TapTapKit.INSTANCE.getContext().startActivity(intent);
            return Result.m52constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m52constructorimpl(ResultKt.createFailure(th));
        }
    }
}
