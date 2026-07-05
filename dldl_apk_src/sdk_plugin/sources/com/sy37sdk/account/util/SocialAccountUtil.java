package com.sy37sdk.account.util;

import android.app.Activity;
import android.os.Bundle;
import com.social.sdk.SocialApi;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.platform.PlatformType;
import com.sqwan.common.mod.account.IBindWxListener;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SDKError;
import com.sy37sdk.account.AccountLogic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SocialAccountUtil {
    public static void socialBindAuthorize(final Activity activity, final IBindWxListener iBindWxListener) {
        SocialApi.getInstance().authorize(activity, PlatformType.WECHAT, new OnAuthListener() { // from class: com.sy37sdk.account.util.SocialAccountUtil.1
            public void onSuccess(PlatformType platformType, Bundle bundle) {
                LogUtil.i("微信授权回调：[bundle]" + bundle.toString());
                AccountLogic.getInstance(activity).bindWxOpenId(bundle.getString("code"), iBindWxListener);
            }

            public void onCancel(PlatformType platformType) {
                IBindWxListener iBindWxListener2 = iBindWxListener;
                if (iBindWxListener2 != null) {
                    iBindWxListener2.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, "取消登录");
                }
            }

            public void onFailure(PlatformType platformType, String str) {
                IBindWxListener iBindWxListener2 = iBindWxListener;
                if (iBindWxListener2 != null) {
                    iBindWxListener2.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, str);
                }
            }
        });
    }
}
