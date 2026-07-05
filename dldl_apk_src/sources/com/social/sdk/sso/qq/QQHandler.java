package com.social.sdk.sso.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.social.sdk.common.SocialConstant;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.common.util.AppInstallUtils;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.Platform;
import com.social.sdk.platform.PlatformType;
import com.social.sdk.platform.QQ;
import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.sso.SSOHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class QQHandler extends SSOHandler {
    private Activity mActivity;
    private OnAuthListener mAuthListener;
    private Context mContext;
    private ShareListener mShareListener;
    private Tencent mTencent;
    private QQ qq;
    private IUiListener loginUiListener = new IUiListener() { // from class: com.social.sdk.sso.qq.QQHandler.1
        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject;
            if (obj == null || (jSONObject = (JSONObject) obj) == null) {
                LogUtils.e("onComplete response=null");
                if (QQHandler.this.mAuthListener != null) {
                    QQHandler.this.mAuthListener.onFailure(QQHandler.this.qq.getName(), "onComplete response=null");
                    return;
                }
                return;
            }
            LogUtils.i("qq登录成功");
            String strOptString = jSONObject.optString("openid");
            String strOptString2 = jSONObject.optString("access_token");
            Bundle bundle = new Bundle();
            bundle.putString("openid", strOptString);
            bundle.putString("access_token", strOptString2);
            bundle.putString(SocialConstant.APPID, QQHandler.this.qq.getAppId());
            if (QQHandler.this.mAuthListener != null) {
                QQHandler.this.mAuthListener.onSuccess(PlatformType.QQ, bundle);
            }
            QQHandler.this.mTencent.logout(QQHandler.this.mActivity);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str = "qq登录失败返回错误码errcode=" + uiError.errorCode + "  msg:" + uiError.errorMessage + "  detailMsg:" + uiError.errorDetail;
            if (QQHandler.this.mAuthListener != null) {
                QQHandler.this.mAuthListener.onFailure(QQHandler.this.qq.getName(), str);
            }
            LogUtils.e(str);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            if (QQHandler.this.mAuthListener != null) {
                QQHandler.this.mAuthListener.onCancel(QQHandler.this.qq.getName());
            }
            LogUtils.e("qq登录取消");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
            if (i == -19) {
                Toast.makeText(QQHandler.this.mContext, "onWarning: 请授权手Q访问分享的文件的读取权限!", 0).show();
            }
        }
    };
    private IUiListener shareUiListener = new IUiListener() { // from class: com.social.sdk.sso.qq.QQHandler.2
        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            LogUtils.i("qq分享 onComplete" + obj);
            if (QQHandler.this.mShareListener != null) {
                QQHandler.this.mShareListener.onSuccess(QQHandler.this.qq.getName());
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            LogUtils.i("qq分享 onError   errorCode= " + uiError.errorCode + " errorMessage= " + uiError.errorMessage + uiError.errorDetail);
            if (QQHandler.this.mShareListener != null) {
                QQHandler.this.mShareListener.onFailure(QQHandler.this.qq.getName(), uiError.errorMessage);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            LogUtils.i("qq分享 onCancel");
            if (QQHandler.this.mShareListener != null) {
                QQHandler.this.mShareListener.onCancel(QQHandler.this.qq.getName());
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
            if (i == -19) {
                Toast.makeText(QQHandler.this.mContext, "onWarning: 请授权手Q访问分享的文件的读取权限!", 0).show();
            }
        }
    };

    @Override // com.social.sdk.sso.SSOHandler
    public void onCreate(Context context, Platform platform) {
        super.onCreate(context, platform);
        this.mContext = context;
        this.qq = (QQ) platform;
        try {
            this.mTencent = Tencent.createInstance(getAppId(), this.mContext, context.getPackageName() + ".provider");
        } catch (NoSuchMethodError unused) {
            this.mTencent = Tencent.createInstance(getAppId(), this.mContext);
        }
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void authorize(Activity activity, OnAuthListener onAuthListener) {
        if (!isSupport()) {
            LogUtils.i("请设置qq appid");
            OnAuthListener onAuthListener2 = this.mAuthListener;
            if (onAuthListener2 != null) {
                onAuthListener2.onFailure(this.qq.getName(), "未集成qq登录！请设置qq  appid");
                return;
            }
            return;
        }
        this.mActivity = activity;
        this.mAuthListener = onAuthListener;
        this.mTencent.login(activity, "all", this.loginUiListener);
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void share(Activity activity, IShareMedia iShareMedia, ShareListener shareListener) {
        if (!isInstall()) {
            Toast.makeText(activity, "请安装qq客户端", 0).show();
            shareListener.onFailure(this.qq.getName(), "qq not install");
            LogUtils.e("qq not install");
        } else {
            if (!isSupport()) {
                LogUtils.i("请设置qq appid");
                if (shareListener != null) {
                    shareListener.onFailure(this.qq.getName(), "未集成qq！请设置qq  appid");
                    return;
                }
                return;
            }
            this.mShareListener = shareListener;
            this.mActivity = activity;
            Bundle bundleBuildShareReq = QQShareManager.buildShareReq(activity, iShareMedia, this.qq.getName());
            if (bundleBuildShareReq == null) {
                this.mShareListener.onFailure(this.qq.getName(), "不支持的分享类型");
            } else {
                this.mTencent.shareToQQ(activity, bundleBuildShareReq, this.shareUiListener);
            }
        }
    }

    @Override // com.social.sdk.sso.SSOHandler
    public boolean isInstall() {
        return AppInstallUtils.checkAppInstalled(this.mContext, "com.tencent.mm");
    }

    @Override // com.social.sdk.sso.SSOHandler
    public void onActivityResult(int i, int i2, Intent intent) {
        Tencent.onActivityResultData(i, i2, intent, this.loginUiListener);
    }

    @Override // com.social.sdk.sso.SSOHandler
    public String getAppId() {
        return this.qq.getAppId();
    }
}
