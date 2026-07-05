package com.sqwan.msdk.api.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.InitBean;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.order.SQPay;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class _SQwan extends Platform {
    private final SQPay mSQPay;

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onWindowFocusChanged(boolean z) {
    }

    public _SQwan(Context context, InitBean initBean, SQResultListener sQResultListener) {
        super(context, initBean, sQResultListener);
        this.mSQPay = new SQPay();
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void init(Context context) {
        this.mSQPay.init(context);
        super.init(context);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    protected void initPlatform() {
        initSQ(false);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    protected void loginPlatform(SQResultListener sQResultListener) {
        if (sQResultListener != null) {
            loginSQ(sQResultListener);
        }
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, SQResultListener sQResultListener) {
        this.isNeedInputMoney = true;
        super.pay(context, str, str2, str3, str4, str5, str6, str7, str8, i, f, i2, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    protected void payPlatform(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, String str9, String str10, SQResultListener sQResultListener) {
        SQLog.e("不支持payPlatform");
        if (sQResultListener != null) {
            sQResultListener.onFailture(SqPayError.ERROR_SDK_UNSUPPORTED, "不支持(10086)");
        }
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    protected IPay getPlatformPay() {
        return this.mSQPay;
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void changeAccount(Context context, SQResultListener sQResultListener) {
        LogUtil.w("37切换账号");
        super.changeAccount(context, sQResultListener);
        changeAccountSQ(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void logout(Context context, SQResultListener sQResultListener) {
        LogUtil.w("37退出框");
        super.logout(context, sQResultListener);
        logoutSQ(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        LogUtil.w("37设置悬浮窗切换账号监听");
        super.setSwitchAccountListener(sQResultListener);
        setSwitchAccountListenerSQ(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        LogUtil.w("37设置返回游戏登录界面监听");
        super.setBackToGameLoginListener(sQResultListener);
        setBackToGameListenerSQ(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        LogUtil.w("37设置悬浮球截图监听");
        super.setScreenshotListener(iScreenshotListener);
        setScreenshotListenerSQ(iScreenshotListener);
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void onResume() {
        super.onResume();
        if (sq != null) {
            sq.onResume();
        }
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void onPause() {
        super.onPause();
        if (sq != null) {
            sq.onPause();
        }
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void onStop() {
        super.onStop();
        if (sq != null) {
            sq.onStop();
        }
    }

    @Override // com.sqwan.msdk.api.sdk.Platform
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void showUAgreement(Context context) {
        if (sq != null) {
            sq.showUAgreement(context);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (sq != null) {
            sq.onConfigurationChanged(configuration);
        }
    }
}
