package com.social.sdk.sso.wechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.parameters.performfeatureconfig.PerformFeatureType;
import com.social.sdk.common.SocialConstant;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.Platform;
import com.social.sdk.platform.PlatformType;
import com.social.sdk.platform.Wechat;
import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.sso.SSOHandler;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class WechatHandler extends SSOHandler {
    private Activity mActivity;
    private OnAuthListener mAuthListener;
    private Context mContext;
    private ShareListener mShareListener;
    private IWXAPI mWXApi;
    private Wechat wechat;
    private String mLastTransaction = "";
    private IWXAPIEventHandler mEventHanler = new IWXAPIEventHandler() { // from class: com.social.sdk.sso.wechat.WechatHandler.1
        @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
        public void onReq(BaseReq baseReq) {
            LogUtils.i("WechatHandler onReq");
        }

        @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
        public void onResp(BaseResp baseResp) {
            LogUtils.i("WechatHandler onResp");
            if (!WechatHandler.this.mLastTransaction.equals(baseResp.transaction)) {
                LogUtils.i("transaction 不匹配");
                return;
            }
            int type = baseResp.getType();
            LogUtils.i("WechatHandler onResp type=" + type);
            if (type == 1) {
                LogUtils.i("WechatHandler onResp 授权返回");
                WechatHandler.this.onAuthCallback((SendAuth.Resp) baseResp);
            } else {
                if (type != 2) {
                    return;
                }
                LogUtils.i("WechatHandler onResp 分享返回");
                WechatHandler.this.onShareCallback((SendMessageToWX.Resp) baseResp);
            }
        }
    };

    @Override // com.social.sdk.sso.SSOHandler
    public void onCreate(Context context, Platform platform) {
        this.mContext = context;
        Wechat wechat = (Wechat) platform;
        this.wechat = wechat;
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, wechat.getAppId());
        this.mWXApi = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(this.wechat.getAppId());
    }

    @Override // com.social.sdk.sso.SSOHandler
    public boolean isInstall() {
        return this.mWXApi.isWXAppInstalled();
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void authorize(Activity activity, OnAuthListener onAuthListener) {
        if (!isSupport()) {
            LogUtils.i("请设置微信 appid");
            if (onAuthListener != null) {
                onAuthListener.onFailure(this.wechat.getName(), "未集成wechat登录！请设置wechat  appid");
                return;
            }
            return;
        }
        this.mActivity = activity;
        this.mAuthListener = onAuthListener;
        if (!isInstall()) {
            if (!isSupportQR()) {
                LogUtils.i("请设置微信 appkey");
                OnAuthListener onAuthListener2 = this.mAuthListener;
                if (onAuthListener2 != null) {
                    onAuthListener2.onFailure(this.wechat.getName(), "未设置wechat appkey,无法调起微信二维码登录!");
                    return;
                }
                return;
            }
            LogUtils.e("未安装微信客户端,打开二维码页面");
            Intent intent = new Intent(this.mContext, (Class<?>) WXQRActivity.class);
            intent.putExtra(SocialConstant.APPID, this.wechat.getAppId());
            intent.putExtra("appkey", this.wechat.getAppKey());
            this.mActivity.startActivityForResult(intent, 1);
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = Wechat.SCOPE;
        req.state = Wechat.STATE;
        req.transaction = buildTransaction("authorize");
        this.mLastTransaction = req.transaction;
        if (this.mWXApi.sendReq(req)) {
            return;
        }
        LogUtils.e("wechat sendReq fail");
        if (onAuthListener != null) {
            onAuthListener.onFailure(PlatformType.WECHAT, "wechat sendReq fail");
        }
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void share(Activity activity, IShareMedia iShareMedia, ShareListener shareListener) {
        if (!isInstall()) {
            Toast.makeText(activity, "请安装微信客户端", 0).show();
            shareListener.onFailure(this.wechat.getName(), "wx not install");
            LogUtils.e("wx not install");
            return;
        }
        if (!isSupport()) {
            LogUtils.i("请设置微信 appid");
            if (shareListener != null) {
                shareListener.onFailure(this.wechat.getName(), "未集成wechat登录！请设置wechat  appid");
                return;
            }
            return;
        }
        this.mActivity = activity;
        this.mShareListener = shareListener;
        SendMessageToWX.Req reqBuildReq = WechatShareManager.buildReq(iShareMedia, this.wechat.getName());
        reqBuildReq.transaction = buildTransaction(PerformFeatureType.TYPE_SHARE);
        this.mLastTransaction = reqBuildReq.transaction;
        if (this.mWXApi.sendReq(reqBuildReq)) {
            return;
        }
        ShareListener shareListener2 = this.mShareListener;
        if (shareListener2 != null) {
            shareListener2.onFailure(this.wechat.getName(), "sendReq fail");
        }
        LogUtils.e("wxapi sendReq fail");
    }

    private String buildTransaction(String str) {
        return str + System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthCallback(SendAuth.Resp resp) {
        LogUtils.i("WechatHandler onAuthCallback");
        LogUtils.i("WechatHandler resp.errCode=" + resp.errCode);
        int i = resp.errCode;
        if (i == -2) {
            LogUtils.i("用户取消微信授权");
            OnAuthListener onAuthListener = this.mAuthListener;
            if (onAuthListener != null) {
                onAuthListener.onCancel(PlatformType.WECHAT);
                return;
            }
            return;
        }
        if (i == 0) {
            LogUtils.i("WechatHandler BaseResp");
            Bundle bundle = new Bundle();
            bundle.putString("code", resp.code);
            bundle.putString(SocialConstant.APPID, this.wechat.getAppId());
            OnAuthListener onAuthListener2 = this.mAuthListener;
            if (onAuthListener2 != null) {
                onAuthListener2.onSuccess(PlatformType.WECHAT, bundle);
                return;
            }
            return;
        }
        String str = "微信授权失败，errcode=" + resp.errCode + " msg: " + resp.errStr;
        LogUtils.i(str);
        OnAuthListener onAuthListener3 = this.mAuthListener;
        if (onAuthListener3 != null) {
            onAuthListener3.onFailure(PlatformType.WECHAT, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShareCallback(SendMessageToWX.Resp resp) {
        LogUtils.i("WechatHandler onShareCallback");
        LogUtils.i("WechatHandler resp.errCode=" + resp.errCode);
        int i = resp.errCode;
        if (i == -2) {
            LogUtils.i("WechatHandler 分享取消");
            ShareListener shareListener = this.mShareListener;
            if (shareListener != null) {
                shareListener.onCancel(this.wechat.getName());
                return;
            }
            return;
        }
        if (i == 0) {
            LogUtils.i("WechatHandler 分享成功");
            ShareListener shareListener2 = this.mShareListener;
            if (shareListener2 != null) {
                shareListener2.onSuccess(this.wechat.getName());
                return;
            }
            return;
        }
        LogUtils.i("WechatHandler 分享失败");
        CharSequence charSequenceConcat = TextUtils.concat("weixin buildReq error (", String.valueOf(resp.errCode), "):", resp.errStr);
        ShareListener shareListener3 = this.mShareListener;
        if (shareListener3 != null) {
            shareListener3.onFailure(this.wechat.getName(), charSequenceConcat.toString());
        }
    }

    public IWXAPI getWXApi() {
        return this.mWXApi;
    }

    public IWXAPIEventHandler getEventHanler() {
        return this.mEventHanler;
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtils.i("WechatHandler onActivityResult  requestCode=" + i + " resultCode=" + i2);
        if (i == 1) {
            LogUtils.i("二维码页面返回");
            if (intent != null) {
                int intExtra = intent.getIntExtra(SocialConstant.KEY_OAUTHERRCODE, -1);
                LogUtils.i("WechatHandler onActivityResult  errCode=" + intExtra);
                if (intExtra == 0) {
                    if (this.mAuthListener != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("openid", intent.getStringExtra("openid"));
                        bundle.putString(SocialConstant.APPID, intent.getStringExtra(SocialConstant.APPID));
                        bundle.putString("code", intent.getStringExtra("code"));
                        this.mAuthListener.onSuccess(PlatformType.WECHAT, bundle);
                        return;
                    }
                    return;
                }
                String stringExtra = intent.getStringExtra(SocialConstant.KEY_OAUTH_MSG);
                LogUtils.e(stringExtra);
                OnAuthListener onAuthListener = this.mAuthListener;
                if (onAuthListener != null) {
                    onAuthListener.onFailure(PlatformType.WECHAT, stringExtra);
                }
            }
        }
    }

    @Override // com.social.sdk.sso.SSOHandler
    public boolean isSupport() {
        return super.isSupport();
    }

    @Override // com.social.sdk.sso.SSOHandler
    public String getAppId() {
        return this.wechat.getAppId();
    }

    public String getAppKey() {
        return this.wechat.getAppKey();
    }

    public boolean isSupportQR() {
        return !TextUtils.isEmpty(getAppKey());
    }
}
