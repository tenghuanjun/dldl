package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hal.HalUserInfo;
import com.huya.mtp.hyns.NSInnerConfig;
import com.huya.mtp.hyns.api.NSUserInfoApi;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyUserInfoImpl implements NSUserInfoApi {
    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    @Deprecated
    public void updateUserInfo(NSUserInfoApi.NSUserInfo nSUserInfo) {
        Hal.updateUserInfo(new HalUserInfo.Builder().setLogin(nSUserInfo.isLogin()).setToken(nSUserInfo.getToken()).setTokenType(nSUserInfo.getTokenType()).setUid(nSUserInfo.getUid()).build());
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateUid(long j) {
        Hal.getUserInfoBiz().updateUid(j);
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateIsLogin(boolean z) {
        Hal.getUserInfoBiz().updateIsLogin(z);
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateToken(String str) {
        Hal.getUserInfoBiz().updateToken(str);
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateTokenType(int i) {
        Hal.getUserInfoBiz().updateTokenType(i);
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateAppSrc(String str) {
        Hal.getUserInfoBiz().updateAppSrc(str);
    }

    @Override // com.huya.mtp.hyns.api.NSUserInfoApi
    public void updateDynamicConfig(Map<String, String> map) {
        NSInnerConfig.getInstance().setDynamicConfig(map);
    }
}
