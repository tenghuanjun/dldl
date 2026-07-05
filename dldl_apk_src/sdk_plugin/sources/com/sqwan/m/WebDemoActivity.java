package com.sqwan.m;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.plugin.standard.BaseActivity;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.auth.AuthConfigCache;
import com.sy37sdk.account.auth.AuthManager;
import com.sy37sdk.account.captcha.CaptchaDialog;
import com.sy37sdk.account.captcha.VerifyPhoneDialog;
import com.sy37sdk.account.floatview.SqFloatViewManager;
import com.sy37sdk.account.floatview.redpacket.RedPacketInfo;
import com.sy37sdk.account.floatview.redpacket.RedPacketManager;
import com.sy37sdk.account.policy.PolicyManager;
import com.sy37sdk.account.uagree.UAgreeCacheHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WebDemoActivity extends BaseActivity {
    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*android.app.Activity*/.onCreate(bundle);
        setContentView(2131296485);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showSQNoticeDialog(View view) {
        MultiSDKUtils.showNoticeDialog(this, "", getResources().getConfiguration().orientation == 2 ? "https://37.com.cn/aicc/1647508369014/vindex.html?pop_id=notice_2380" : "https://37.com.cn/aicc/1647508369014/index.html?pop_id=notice_2380");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showAuthDialog(View view) {
        AuthManager.getInstance(this).showPersonalDialog(transformURLForChannel("http://37.com.cn/sdk/sdk-smrz/?authType=2&code=2&triggerType=reportDevDuration&operation=1&needCurfew=0"), AuthConfigCache.isAuthFocus(), AuthConfigCache.needAccumulateDuration(), false, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showPolicyDialog(View view) {
        PolicyManager.getInstance().showPolicyDialog(this, "https://37.com.cn/sdk/anti-addiction/vertical-indulge.html?indulgeType=3&code=2&ageCode=2", false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showRedPacketDialog(View view) {
        final RedPacketInfo redPacketInfo = new RedPacketInfo();
        RedPacketInfo.WebViewConfig webViewConfig = new RedPacketInfo.WebViewConfig();
        webViewConfig.pop_url = "https://liyuanhn.com/huodong/20201207_gd_red_envelopes_popup?id=11";
        webViewConfig.height = 589;
        webViewConfig.width = 691;
        redPacketInfo.webViewConfig = webViewConfig;
        redPacketInfo.imgUrl = "https://imgcs.s98s2.com/image/webSite/article/1607595787000/7658fb42-3529-449e-8557-3b02ec6d5074.gif";
        redPacketInfo.jumpLink = "https://liyuanhn.com/huodong/20201207_gd_red_envelopes/?actid=1190";
        RedPacketManager.getInstance().downloadFloatViewGifImg(this, redPacketInfo, new RedPacketManager.DownloadGifCallback() { // from class: com.sqwan.m.WebDemoActivity.1
            @Override // com.sy37sdk.account.floatview.redpacket.RedPacketManager.DownloadGifCallback
            public void getLocalGifPath(String str, boolean z) {
                redPacketInfo.imgLocalPath = str;
                SqFloatViewManager.getInstance().showRedPacketFloat(WebDemoActivity.this, redPacketInfo, true);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showSQWebContainerDialog(View view) {
        MultiSDKUtils.showSQWebDialog((Context) this, transformURLForChannel("http://37.com.cn/community/vertical/54/home"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showSQWebContainerDialog2(View view) {
        MultiSDKUtils.showSQWebDialog((Context) this, transformURLForChannel("https://37.com.cn/service-system/accountappeal/accSubmit"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showUserProtocolDialog(View view) {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(this);
        sQWebViewDialog.setUrl(UAgreeCacheHelper.getUrlProtocol(this) + "&isAgree=true");
        sQWebViewDialog.show();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showPrivacyPolicyDialog(View view) {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(this);
        sQWebViewDialog.setUrl(UAgreeCacheHelper.getUrlPolicy(this) + "&isAgree=true");
        sQWebViewDialog.show();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showBaseNormalDialog(View view) {
        SQwanCore.getInstance().performFeature(this, "showAgeAppropriate", (Object) null, (SQResultListener) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showPayWebPage(View view) {
        SQwanCore.getInstance().pay(this, "A" + System.currentTimeMillis(), "一堆金币", "金币", "S001", "铁马金戈", "CP扩展字段", "RID0001", "路人甲", 1, 1.0f, 10, new SQResultListener() { // from class: com.sqwan.m.WebDemoActivity.2
            public void onSuccess(Bundle bundle) {
                ToastUtil.showToast(WebDemoActivity.this, "成功发起充值请求(充值结果以服务端为准)");
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(WebDemoActivity.this, str);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showVerifyPhoneDialog(View view) {
        String strConstructWebUrlParam = AppUtils.constructWebUrlParam(this, "http://37.com.cn/mt/user/account/upgrade/sept1?mobile=17012345602");
        VerifyPhoneDialog verifyPhoneDialog = new VerifyPhoneDialog(this);
        verifyPhoneDialog.setUrl(strConstructWebUrlParam);
        verifyPhoneDialog.setVerifyListener(new VerifyPhoneDialog.VerifyListener() { // from class: com.sqwan.m.WebDemoActivity.3
            @Override // com.sy37sdk.account.captcha.VerifyPhoneDialog.VerifyListener
            public void result(boolean z, String str) {
                ToastUtil.showToast(WebDemoActivity.this, "result：" + z + ", msg = " + str);
            }
        });
        verifyPhoneDialog.show();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showCaptchaDialog(View view) {
        String strConstructWebUrlParam = AppUtils.constructWebUrlParam(this, "https://37.com.cn/sdk/security-verify/tx-reg.html");
        CaptchaDialog captchaDialog = new CaptchaDialog(this);
        captchaDialog.setUrl(strConstructWebUrlParam);
        captchaDialog.setVerifyListener(new CaptchaDialog.VerifyListener() { // from class: com.sqwan.m.WebDemoActivity.4
            @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
            public void result(boolean z, String str) {
                ToastUtil.showToast(WebDemoActivity.this, "result：" + z + ", msg = " + str);
            }
        });
        captchaDialog.show();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void jsInterface(View view) {
        MultiSDKUtils.showSQWebDialog((Context) this, transformURLForChannel("https://37.com.cn/freestyle/sdk_Interface/"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onActivityResult(int i, int i2, Intent intent) {
        super/*android.app.Activity*/.onActivityResult(i, i2, intent);
        SQwanCore.getInstance().onActivityResult(i, i2, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String transformURLForChannel(String str) {
        return MultiSDKUtils.getPID(this).endsWith("1") ? MultiSDKUtils.constructCommonURL(this, str) : str;
    }
}
