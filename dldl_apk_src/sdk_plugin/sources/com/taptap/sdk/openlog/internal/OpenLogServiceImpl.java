package com.taptap.sdk.openlog.internal;

import com.taptap.sdk.common.services.OpenLogService;
import com.taptap.sdk.kit.internal.identifier.TapIdentifierUtil;
import com.taptap.sdk.kit.internal.openlog.ITapOpenlog;
import com.taptap.sdk.openlog.TapTapOpenlogSdk;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenLogServiceImpl.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u001e\u0010\u0012\u001a\u00020\u00062\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015H\u0016J\u0012\u0010\u0017\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/taptap/sdk/openlog/internal/OpenLogServiceImpl;", "Lcom/taptap/sdk/common/services/OpenLogService;", "delegate", "Lcom/taptap/sdk/openlog/TapTapOpenlogSdk;", "(Lcom/taptap/sdk/openlog/TapTapOpenlogSdk;)V", "enableHeartbeat", "", "enabled", "", "isStart", "login", "openId", "", "logout", "obtainOpenlog", "Lcom/taptap/sdk/kit/internal/openlog/ITapOpenlog;", "project", "version", "setExtraAppDurationParams", "jsonStr", "extras", "", "", "setOpenId", "tap-openlog_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OpenLogServiceImpl implements OpenLogService {
    private final TapTapOpenlogSdk delegate;

    /* JADX WARN: Multi-variable type inference failed */
    public OpenLogServiceImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public OpenLogServiceImpl(TapTapOpenlogSdk delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    public /* synthetic */ OpenLogServiceImpl(TapTapOpenlogSdk tapTapOpenlogSdk, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TapTapOpenlogSdk.INSTANCE : tapTapOpenlogSdk);
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void login(String openId) {
        Intrinsics.checkNotNullParameter(openId, "openId");
        this.delegate.login(openId);
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void logout() {
        this.delegate.logout();
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void setOpenId(String openId) {
        TapIdentifierUtil.INSTANCE.setOpenId(openId);
        String str = openId;
        if (str == null || str.length() == 0) {
            logout();
        } else {
            login(openId);
        }
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void enableHeartbeat(boolean enabled) {
        this.delegate.enableHeartbeat(enabled);
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public ITapOpenlog obtainOpenlog(String project, String version) {
        Intrinsics.checkNotNullParameter(project, "project");
        Intrinsics.checkNotNullParameter(version, "version");
        return this.delegate.obtainOpenlog(project, version);
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public boolean isStart() {
        return this.delegate.isHeartbeatStarted();
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void setExtraAppDurationParams(String jsonStr) {
        Intrinsics.checkNotNullParameter(jsonStr, "jsonStr");
        this.delegate.setExtraAppDurationParams(jsonStr);
    }

    @Override // com.taptap.sdk.common.services.OpenLogService
    public void setExtraAppDurationParams(Map<String, ? extends Object> extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        this.delegate.setExtraAppDurationParams(extras);
    }
}
