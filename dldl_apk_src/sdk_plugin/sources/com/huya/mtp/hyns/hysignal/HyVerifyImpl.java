package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.wrapper.business.VerifyBiz;
import com.huya.hysignal.wrapper.listener.HySignalVerifyBizListener;
import com.huya.hysignal.wrapper.listener.HySignalVerifyListenerV2;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.api.NSVerifyApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyVerifyImpl implements NSVerifyApi {
    public static final String TAG = "-HyVerifyImpl";
    private VerifyBiz mVerifyBiz = Hal.getVerifyBiz();

    @Override // com.huya.mtp.hyns.api.NSVerifyApi
    public void verifyTokenIfNeed(final NSVerifyApi.VerifyBizListener verifyBizListener) {
        this.mVerifyBiz.verifyTokenIfNeed(new HySignalVerifyBizListener() { // from class: com.huya.mtp.hyns.hysignal.HyVerifyImpl.1
            @Override // com.huya.hysignal.wrapper.listener.HySignalVerifyBizListener
            public void onResult(boolean z, String str) {
                if (z) {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "授信成功 " + str);
                } else {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "授信失败 " + str);
                }
                NSVerifyApi.VerifyBizListener verifyBizListener2 = verifyBizListener;
                if (verifyBizListener2 != null) {
                    verifyBizListener2.onResult(z, str);
                }
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSVerifyApi
    public void verifyTokenV2(final NSVerifyApi.VerifyBizListenerV2 verifyBizListenerV2) {
        this.mVerifyBiz.verifyTokenIfNeedV2(new HySignalVerifyListenerV2() { // from class: com.huya.mtp.hyns.hysignal.HyVerifyImpl.2
            @Override // com.huya.hysignal.wrapper.listener.HySignalVerifyListenerV2
            public void onResult(boolean z, int i, String str) {
                if (z) {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "授信成功 " + str);
                } else {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "授信失败 " + str);
                }
                NSVerifyApi.VerifyBizListenerV2 verifyBizListenerV22 = verifyBizListenerV2;
                if (verifyBizListenerV22 != null) {
                    verifyBizListenerV22.onResult(z, i, str);
                }
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSVerifyApi
    public void unVerifyTokenIfNeed(final NSVerifyApi.VerifyBizListener verifyBizListener) {
        this.mVerifyBiz.unVerifyTokenIfNeed(new HySignalVerifyBizListener() { // from class: com.huya.mtp.hyns.hysignal.HyVerifyImpl.3
            @Override // com.huya.hysignal.wrapper.listener.HySignalVerifyBizListener
            public void onResult(boolean z, String str) {
                if (z) {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "反授信成功 " + str);
                } else {
                    MTPApi.LOGGER.info("NetService-HyVerifyImpl", "反授信失败 " + str);
                }
                NSVerifyApi.VerifyBizListener verifyBizListener2 = verifyBizListener;
                if (verifyBizListener2 != null) {
                    verifyBizListener2.onResult(z, str);
                }
            }
        });
    }
}
