package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSPushControlProtocol;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSPushControlProtocol.class)
public interface NSPushControlApi {
    void resetUnableLostMsg(boolean z);

    @Deprecated
    void updateExperimentConfig(Map<String, String> map);

    void updateFrequencyConfig(Map<String, String> map);

    void updateGroupMsgMaxCacheCount(long j);

    void updateMsgMaxCacheCount(long j);

    void updateP2PEnableSwitch(Map<String, Boolean> map);

    void updateRegisterMsgUriSet(Set<Long> set);
}
