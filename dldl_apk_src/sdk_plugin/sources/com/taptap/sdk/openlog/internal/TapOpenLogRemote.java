package com.taptap.sdk.openlog.internal;

import com.sqwan.common.track.SqTrackNetKey;
import com.taptap.sdk.openlog.utils.OpenlogExtrasUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapOpenLogRemote.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086 J\u0011\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086 J\u0006\u0010\b\u001a\u00020\u0006J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086 J\t\u0010\f\u001a\u00020\u0004H\u0086 J\t\u0010\r\u001a\u00020\u0004H\u0086 J\t\u0010\u000e\u001a\u00020\u0004H\u0086 J\u0011\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0086 J\t\u0010\u0011\u001a\u00020\u0004H\u0086 J\u0019\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0086 J\u0011\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0086 ¨\u0006\u0017"}, d2 = {"Lcom/taptap/sdk/openlog/internal/TapOpenLogRemote;", "", "()V", "disableModules", "", "modules", "", "enableModules", "getCommonVariables", "onAppStarted", "", "configStr", "onAppStopped", "onBackground", "onForeground", "onLogin", "userInfo", "onLogout", "sendOpenlog", "logStore", "logContent", "setExtraAppDurationParams", SqTrackNetKey.params, "tap-openlog_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapOpenLogRemote {
    public static final TapOpenLogRemote INSTANCE = new TapOpenLogRemote();

    public final native void disableModules(String modules);

    public final native void enableModules(String modules);

    public final native int onAppStarted(String configStr);

    public final native void onAppStopped();

    public final native void onBackground();

    public final native void onForeground();

    public final native void onLogin(String userInfo);

    public final native void onLogout();

    public final native void sendOpenlog(String logStore, String logContent);

    public final native void setExtraAppDurationParams(String params);

    private TapOpenLogRemote() {
    }

    public final String getCommonVariables() {
        String string = new JSONObject(OpenlogExtrasUtils.INSTANCE.getDynamicExtras()).toString();
        Intrinsics.checkNotNullExpressionValue(string, "JSONObject(getDynamicExtras()).toString()");
        return string;
    }
}
