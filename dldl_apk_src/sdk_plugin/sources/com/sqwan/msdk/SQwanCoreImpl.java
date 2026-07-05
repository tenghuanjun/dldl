package com.sqwan.msdk;

import android.content.Context;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.InitBean;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.SQSdkInterface;
import com.sqwan.msdk.api.sdk._SQwan;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQwanCoreImpl extends BaseSQwanCore {
    private SQwanCoreImpl() {
    }

    public static SQwanCoreImpl getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SQwanCoreImpl();
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.msdk.BaseSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public SQSdkInterface getPlatform(Context context, InitBean initBean, SQResultListener sQResultListener) {
        LogUtil.w("SQ getPlatform --> userSdk: " + initBean.getUsesdk());
        return new _SQwan(context, initBean, sQResultListener);
    }
}
