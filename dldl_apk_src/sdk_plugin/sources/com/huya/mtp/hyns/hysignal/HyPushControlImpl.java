package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.wrapper.business.BaseBiz;
import com.huya.hysignal.wrapper.business.PushControlBiz;
import com.huya.mtp.hyns.api.NSPushControlApi;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyPushControlImpl implements NSPushControlApi {
    private BaseBiz mBaseBiz = Hal.getBaseBiz();
    private PushControlBiz mPushControlBiz = Hal.getPushControlBiz();

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateFrequencyConfig(Map<String, String> map) {
        this.mPushControlBiz.updateFrequencyConfig(map);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateExperimentConfig(Map<String, String> map) {
        this.mBaseBiz.updateExperimentConfig(map);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateMsgMaxCacheCount(long j) {
        this.mPushControlBiz.updateMsgMaxCacheCount(j);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateGroupMsgMaxCacheCount(long j) {
        this.mPushControlBiz.updateGroupMsgMaxCacheCount(j);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void resetUnableLostMsg(boolean z) {
        this.mPushControlBiz.resetUnableLostMsg(z);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateRegisterMsgUriSet(Set<Long> set) {
        this.mPushControlBiz.updateRegisterMsgUriSet(set);
    }

    @Override // com.huya.mtp.hyns.api.NSPushControlApi
    public void updateP2PEnableSwitch(Map<String, Boolean> map) {
        this.mPushControlBiz.updateP2PEnableSwitch(map);
    }
}
