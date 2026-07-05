package com.taptap.sdk.initializer.api.check;

import android.widget.Toast;
import com.taptap.sdk.initializer.api.service.InitializerService;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.servicemanager.ServiceManager;
import com.taptap.sdk.servicemanager.utils.ServiceManagerComponent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TapSdkChecker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005²\u0006\f\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u008a\u0084\u0002"}, d2 = {"Lcom/taptap/sdk/initializer/api/check/TapSdkChecker;", "", "()V", "checkInitialize", "", "tap-common_release", "initializeService", "Lcom/taptap/sdk/initializer/api/service/InitializerService;"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapSdkChecker {
    public static final TapSdkChecker INSTANCE = new TapSdkChecker();

    private TapSdkChecker() {
    }

    private static final InitializerService checkInitialize$lambda$3$lambda$0(Lazy<? extends InitializerService> lazy) {
        return lazy.getValue();
    }

    public final boolean checkInitialize() {
        try {
            Result.Companion companion = Result.INSTANCE;
            TapSdkChecker tapSdkChecker = this;
            ServiceManagerComponent serviceManagerComponent = ServiceManagerComponent.INSTANCE;
            InitializerService initializerServiceCheckInitialize$lambda$3$lambda$0 = checkInitialize$lambda$3$lambda$0(LazyKt.lazy(new Function0<InitializerService>() { // from class: com.taptap.sdk.initializer.api.check.TapSdkChecker$checkInitialize$lambda$3$$inlined$injectOrNull$1
                /* JADX WARN: Can't rename method to resolve collision */
                /* JADX WARN: Type inference failed for: r0v1, types: [com.taptap.sdk.initializer.api.service.InitializerService, com.taptap.sdk.servicemanager.ServiceManager$Service] */
                @Override // kotlin.jvm.functions.Function0
                public final InitializerService invoke() {
                    return ServiceManager.INSTANCE.getService(InitializerService.class);
                }
            }));
            if (initializerServiceCheckInitialize$lambda$3$lambda$0 != null) {
                return initializerServiceCheckInitialize$lambda$3$lambda$0.checkInitialize();
            }
            Toast.makeText(TapTapKit.INSTANCE.getContext(), "当前应用还未初始化", 0).show();
            TapLogger.loge$default("TapInitializer", "当前应用还未初始化: 请在调用 SDK 业务接口前，先调用 [TapTapSDK.init()] 接口", null, 4, null);
            return false;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            if (Result.m55exceptionOrNullimpl(Result.m52constructorimpl(ResultKt.createFailure(th))) != null) {
                Toast.makeText(TapTapKit.INSTANCE.getContext(), "当前应用还未初始化", 0).show();
                TapLogger.loge$default("TapInitializer", "当前应用还未初始化: 请在调用 SDK 业务接口前，先调用 [TapTapSDK.init()] 接口", null, 4, null);
            }
            return false;
        }
    }
}
