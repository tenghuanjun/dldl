package com.taptap.sdk.login.api;

import android.app.Activity;
import com.taptap.sdk.kit.internal.callback.TapTapCallback;
import com.taptap.sdk.login.AccessToken;
import com.taptap.sdk.login.TapTapAccount;
import com.taptap.sdk.login.TapTapAccountInternal;
import com.taptap.sdk.login.TapTapLoginCallback;
import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: LoginService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH&¢\u0006\u0002\u0010\u000bJ1\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\r0\nH&¢\u0006\u0002\u0010\u000bJ\n\u0010\u000e\u001a\u0004\u0018\u00010\bH&J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J1\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH&¢\u0006\u0002\u0010\u000bJ1\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\nH&¢\u0006\u0002\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0016H&¨\u0006\u0018"}, d2 = {"Lcom/taptap/sdk/login/api/LoginService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "authWithScope", "", "activity", "Landroid/app/Activity;", "scopes", "", "", "callback", "Lcom/taptap/sdk/kit/internal/callback/TapTapCallback;", "(Landroid/app/Activity;[Ljava/lang/String;Lcom/taptap/sdk/kit/internal/callback/TapTapCallback;)V", "authWithScope2", "Lcom/taptap/sdk/login/AccessToken;", "getAccount", "getCurrentTapAccount", "Lcom/taptap/sdk/login/TapTapAccountInternal;", "loginWithScope", "loginWithScope2", "Lcom/taptap/sdk/login/TapTapAccount;", "logout", "registerTapLoginCallback", "Lcom/taptap/sdk/login/TapTapLoginCallback;", "unregisterTapLoginCallback", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface LoginService extends ServiceManager.Service {
    void authWithScope(Activity activity, String[] scopes, TapTapCallback<String> callback);

    void authWithScope2(Activity activity, String[] scopes, TapTapCallback<AccessToken> callback);

    String getAccount();

    TapTapAccountInternal getCurrentTapAccount();

    void loginWithScope(Activity activity, String[] scopes, TapTapCallback<String> callback);

    void loginWithScope2(Activity activity, String[] scopes, TapTapCallback<TapTapAccount> callback);

    void logout();

    void registerTapLoginCallback(TapTapLoginCallback callback);

    void unregisterTapLoginCallback(TapTapLoginCallback callback);
}
