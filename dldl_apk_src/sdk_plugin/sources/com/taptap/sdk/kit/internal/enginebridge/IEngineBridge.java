package com.taptap.sdk.kit.internal.enginebridge;

import android.app.Activity;
import kotlin.Metadata;

/* JADX INFO: compiled from: IEngineBridge.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J \u0010\f\u001a\u00020\u00032\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u000fH&¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridge;", "", "execCommand", "", "command", "", "callback", "Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeCallback;", "execCommandAsync", "init", "activity", "Landroid/app/Activity;", "registerService", "serviceClz", "Ljava/lang/Class;", "Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridgeService;", "service", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface IEngineBridge {
    void execCommand(String command, EngineBridgeCallback callback);

    String execCommandAsync(String command);

    void init(Activity activity);

    void registerService(Class<? extends IEngineBridgeService> serviceClz, IEngineBridgeService service);

    /* JADX INFO: compiled from: IEngineBridge.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void execCommand$default(IEngineBridge iEngineBridge, String str, EngineBridgeCallback engineBridgeCallback, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: execCommand");
            }
            if ((i & 2) != 0) {
                engineBridgeCallback = null;
            }
            iEngineBridge.execCommand(str, engineBridgeCallback);
        }
    }
}
