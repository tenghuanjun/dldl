package com.huya.hysignal.wrapper.business;

import com.huya.hysignal.wrapper.listener.HySignalVerifyBizListener;
import com.huya.hysignal.wrapper.listener.HySignalVerifyListenerV2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface VerifyBiz extends PushBiz {
    void unVerifyTokenIfNeed(HySignalVerifyBizListener hySignalVerifyBizListener);

    void verifyTokenIfNeed(HySignalVerifyBizListener hySignalVerifyBizListener);

    void verifyTokenIfNeedV2(HySignalVerifyListenerV2 hySignalVerifyListenerV2);
}
