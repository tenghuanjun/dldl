package com.social.sdk.sso.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.social.sdk.SocialApi;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.PlatformConfig;
import com.social.sdk.platform.PlatformType;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class WXCallbackActivity extends Activity implements IWXAPIEventHandler {
    private WechatHandler wechatHandler;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i("WXCallbackActivity onCreate");
        WechatHandler wechatHandler = (WechatHandler) SocialApi.getInstance().getSSOHandler(PlatformType.WECHAT);
        this.wechatHandler = wechatHandler;
        wechatHandler.onCreate(this, PlatformConfig.getPlatformConfig(PlatformType.WECHAT));
        this.wechatHandler.getWXApi().handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.i("WXCallbackActivity onNewIntent");
        setIntent(intent);
        WechatHandler wechatHandler = (WechatHandler) SocialApi.getInstance().getSSOHandler(PlatformType.WECHAT);
        this.wechatHandler = wechatHandler;
        wechatHandler.onCreate(this, PlatformConfig.getPlatformConfig(PlatformType.WECHAT));
        this.wechatHandler.getWXApi().handleIntent(intent, this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        LogUtils.i("WXCallbackActivity onReq");
        WechatHandler wechatHandler = this.wechatHandler;
        if (wechatHandler != null && baseReq != null) {
            wechatHandler.getEventHanler().onReq(baseReq);
        }
        finish();
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        LogUtils.i("WXCallbackActivity onResp");
        WechatHandler wechatHandler = this.wechatHandler;
        if (wechatHandler != null && baseResp != null) {
            wechatHandler.getEventHanler().onResp(baseResp);
        }
        finish();
    }
}
