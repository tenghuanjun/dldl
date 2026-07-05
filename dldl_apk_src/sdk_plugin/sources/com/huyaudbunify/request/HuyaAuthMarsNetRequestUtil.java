package com.huyaudbunify.request;

import android.util.Base64;
import android.util.Log;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.Callback;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.NSUserInfoApi;
import com.huya.mtp.hyns.api.NSVerifyApi;
import com.huya.mtp.hyns.api.Request;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.TrustUserInfo;
import com.huyaudbunify.inter.IIHttpCallBack;
import com.huyaudbunify.inter.IIRegTrustInfoCallBack;
import com.huyaudbunify.util.HuyaDeveloperUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAuthMarsNetRequestUtil {
    private static volatile HuyaAuthMarsNetRequestUtil mInstance;
    private IIRegTrustInfoCallBack mTrustInfoCallBack;

    public static HuyaAuthMarsNetRequestUtil getInstance() {
        if (mInstance == null) {
            synchronized (HuyaAuthMarsNetRequestUtil.class) {
                if (mInstance == null) {
                    mInstance = new HuyaAuthMarsNetRequestUtil();
                }
            }
        }
        return mInstance;
    }

    public void setTrustInfoCallBack(IIRegTrustInfoCallBack iIRegTrustInfoCallBack) {
        this.mTrustInfoCallBack = iIRegTrustInfoCallBack;
    }

    public void init() {
        ((NSLongLinkApi) NS.get(NSLongLinkApi.class)).addPushListener(new NSLongLinkApi.PushListener() { // from class: com.huyaudbunify.request.HuyaAuthMarsNetRequestUtil.1
            @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
            public void onLinkStateChange(int i) {
                if (i == 4) {
                    if (HuyaAuthMarsNetRequestUtil.this.mTrustInfoCallBack != null) {
                        HuyaAuthMarsNetRequestUtil.this.mTrustInfoCallBack.regTrustInfo();
                    }
                    HuyaAuth.getInstance().setNetType(4);
                    return;
                }
                HuyaAuth.getInstance().setNetType(3);
            }

            @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
            public void onPush(NSLongLinkApi.HySignalMessage hySignalMessage) {
                if (hySignalMessage.iUri == 10220052 || hySignalMessage.iUri == 10220053) {
                    HuyaAuth.getInstance().pushMessage(hySignalMessage.iUri, Base64.encodeToString(hySignalMessage.getSMsg(), 0));
                }
            }
        });
    }

    public void sendNet(int i, String str, byte[] bArr, IIHttpCallBack iIHttpCallBack) {
        sendMarsNet(i, 3, str, bArr, iIHttpCallBack);
    }

    private void sendMarsNet(final int i, int i2, String str, byte[] bArr, final IIHttpCallBack iIHttpCallBack) {
        Log.i("udbauth", "sendMarsNet :" + str);
        Request.Builder builder = new Request.Builder();
        builder.cmdId(3);
        builder.body(bArr);
        builder.retryCount(0);
        builder.channel(i2);
        builder.cgi(str);
        builder.totalTimeout(10000);
        ((NSLongLinkApi) NS.get(NSLongLinkApi.class)).newCall(builder.build()).enqueue(new Callback() { // from class: com.huyaudbunify.request.HuyaAuthMarsNetRequestUtil.2
            @Override // com.huya.mtp.hyns.api.Callback
            public void onResponse(byte[] bArr2, int i3, int i4) {
                iIHttpCallBack.onResponse(bArr2, i, i3, i4);
            }
        });
    }

    public void regMarsTrustInfo(TrustUserInfo trustUserInfo, NSVerifyApi.VerifyBizListenerV2 verifyBizListenerV2) {
        ((NSUserInfoApi) NS.get(NSUserInfoApi.class)).updateUserInfo(new NSUserInfoApi.NSUserInfo.Builder().setUid(trustUserInfo.getUid()).setToken(trustUserInfo.getToken()).setTokenType(trustUserInfo.getTokenType()).setLogin(true).build());
        ((NSVerifyApi) NS.get(NSVerifyApi.class)).verifyTokenV2(verifyBizListenerV2);
    }

    public void unRegMarsTrusInfo(final long j, final int i) {
        if (i > 0) {
            ((NSVerifyApi) NS.get(NSVerifyApi.class)).unVerifyTokenIfNeed(new NSVerifyApi.VerifyBizListener() { // from class: com.huyaudbunify.request.HuyaAuthMarsNetRequestUtil.3
                @Override // com.huya.mtp.hyns.api.NSVerifyApi.VerifyBizListener
                public void onResult(boolean z, String str) {
                    if (!HuyaDeveloperUtils.getInstance().isForbidLog() && !z) {
                        Log.i("udbauth", "unRegMarsTrusInfo error msg:" + str);
                    }
                    if (z) {
                        return;
                    }
                    HuyaAuthMarsNetRequestUtil.this.unRegMarsTrusInfo(j, i - 1);
                }
            });
        }
    }
}
