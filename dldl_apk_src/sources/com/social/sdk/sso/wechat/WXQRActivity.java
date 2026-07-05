package com.social.sdk.sso.wechat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.social.sdk.common.SocialConstant;
import com.social.sdk.common.net.listener.OnRequestCallBack;
import com.social.sdk.common.util.EncryptUtils;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.Wechat;
import com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class WXQRActivity extends Activity implements OAuthListener {
    private String appKey;
    private String appid;
    WechatQRView qrView;
    IDiffDevOAuth oAuth = DiffDevOAuthFactory.getDiffDevOAuth();
    String errMsg = "授权失败";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.qrView = new WechatQRView(this);
        addContentView(this.qrView, new ViewGroup.LayoutParams(-1, -1));
        Intent intent = getIntent();
        if (intent == null) {
            this.errMsg = "请接入微信appid appkey";
            LogUtils.e("请接入微信appid appkey");
            carryParametersFinish();
        } else {
            this.appid = intent.getStringExtra(SocialConstant.APPID);
            this.appKey = intent.getStringExtra("appkey");
            auth();
        }
    }

    private void auth() {
        this.qrView.setProgressVisible(true);
        WechatHttpUtil.getInstance(this).getTicket(this.appid, this.appKey, new OnRequestCallBack() { // from class: com.social.sdk.sso.wechat.WXQRActivity.1
            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onSuccess(String str) {
                if (WXQRActivity.this.isFinishing()) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 8; i++) {
                    sb.append(random.nextInt(10));
                }
                String string = sb.toString();
                String str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                WXQRActivity.this.oAuth.auth(WXQRActivity.this.appid, Wechat.SCOPE, string, str2, EncryptUtils.getSHA(String.format("appid=%s&noncestr=%s&sdk_ticket=%s&timestamp=%s", WXQRActivity.this.appid, string, str, str2)), WXQRActivity.this);
            }

            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onError(int i, String str) {
                if (WXQRActivity.this.isFinishing()) {
                    return;
                }
                WXQRActivity.this.qrView.setProgressVisible(false);
                WXQRActivity.this.errMsg = "获取Ticket失败: " + str;
                LogUtils.i(WXQRActivity.this.errMsg);
                WXQRActivity.this.carryParametersFinish();
            }
        });
    }

    @Override // com.tencent.mm.opensdk.diffdev.OAuthListener
    public void onAuthGotQrcode(String str, byte[] bArr) {
        Bitmap bitmapDecodeFile;
        this.qrView.setProgressVisible(false);
        this.qrView.setTipVisible(true);
        LogUtils.i("onAuthGotQrcode 获取二维码成功：" + str);
        if (bArr != null) {
            LogUtils.i("读取bytes");
            bitmapDecodeFile = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } else if (TextUtils.isEmpty(str)) {
            bitmapDecodeFile = null;
        } else {
            LogUtils.i("读取文件中的图片");
            bitmapDecodeFile = BitmapFactory.decodeFile(str);
        }
        if (bitmapDecodeFile != null) {
            LogUtils.i("显示二维码");
            this.qrView.setQRCode(bitmapDecodeFile);
        }
    }

    @Override // com.tencent.mm.opensdk.diffdev.OAuthListener
    public void onQrcodeScanned() {
        LogUtils.i("onQrcodeScanned");
    }

    @Override // com.tencent.mm.opensdk.diffdev.OAuthListener
    public void onAuthFinish(OAuthErrCode oAuthErrCode, String str) {
        LogUtils.i("onAuthFinish,authCode=" + str);
        int code = oAuthErrCode.getCode();
        if (code == 0) {
            LogUtils.i("authCode;" + str);
            Intent intent = new Intent();
            intent.putExtra(SocialConstant.KEY_OAUTHERRCODE, 0);
            intent.putExtra("code", str);
            intent.putExtra(SocialConstant.APPID, this.appid);
            setResult(1, intent);
            finish();
            return;
        }
        String str2 = "用户授权失败  oAuthErrCode=" + code;
        this.errMsg = str2;
        LogUtils.i(str2);
        carryParametersFinish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.oAuth.detach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void carryParametersFinish() {
        this.qrView.setProgressVisible(false);
        Intent intent = new Intent();
        intent.putExtra(SocialConstant.KEY_OAUTHERRCODE, -1);
        intent.putExtra(SocialConstant.KEY_OAUTH_MSG, this.errMsg);
        setResult(1, intent);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.errMsg = "用户主动取消授权";
        carryParametersFinish();
    }
}
