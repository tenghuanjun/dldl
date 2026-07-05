package com.huya.hysignal.wrapper.business;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface PushControlBiz extends PushBiz {
    void resetUnableLostMsg(boolean z);

    void updateFrequencyConfig(Map<String, String> map);

    void updateGroupMsgMaxCacheCount(long j);

    void updateMsgMaxCacheCount(long j);

    void updateP2PEnableSwitch(Map<String, Boolean> map);

    void updateRegisterMsgUriSet(Set<Long> set);
}
