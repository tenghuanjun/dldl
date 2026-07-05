package com.taptap.sdk.common.services;

import com.taptap.sdk.kit.internal.openlog.ITapOpenlog;
import com.taptap.sdk.servicemanager.ServiceManager;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: OpenLogService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\tH&J\u001e\u0010\u000f\u001a\u00020\u00032\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012H&J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/common/services/OpenLogService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "enableHeartbeat", "", "enabled", "", "isStart", "login", "openId", "", "logout", "obtainOpenlog", "Lcom/taptap/sdk/kit/internal/openlog/ITapOpenlog;", "project", "version", "setExtraAppDurationParams", "jsonStr", "extras", "", "", "setOpenId", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface OpenLogService extends ServiceManager.Service {
    void enableHeartbeat(boolean enabled);

    boolean isStart();

    void login(String openId);

    void logout();

    ITapOpenlog obtainOpenlog(String project, String version);

    void setExtraAppDurationParams(String jsonStr);

    void setExtraAppDurationParams(Map<String, ? extends Object> extras);

    void setOpenId(String openId);
}
