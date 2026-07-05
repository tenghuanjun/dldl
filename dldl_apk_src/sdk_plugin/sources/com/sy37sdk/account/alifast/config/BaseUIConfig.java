package com.sy37sdk.account.alifast.config;

import android.app.Activity;
import android.content.Context;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.sq.sdk.tool.util.DisplayUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseUIConfig {
    protected OnClickFastLoginListener listener;
    public Activity mActivity;
    public PhoneNumberAuthHelper mAuthHelper;
    public Context mContext;
    public int mScreenHeightDp;
    public int mScreenWidthDp;

    public interface OnClickFastLoginListener {
        void clickAccountLogin();

        void clickBack();

        void clickClose();

        void clickOtherLogin();

        void clickWechatLogin();
    }

    public abstract void configAuthPage();

    public void onResume() {
    }

    public static BaseUIConfig init(int i, Activity activity, PhoneNumberAuthHelper phoneNumberAuthHelper) {
        if (i != 7) {
            return null;
        }
        return new DialogConfig(activity, phoneNumberAuthHelper);
    }

    public BaseUIConfig(Activity activity, PhoneNumberAuthHelper phoneNumberAuthHelper) {
        this.mActivity = activity;
        this.mContext = activity.getApplicationContext();
        this.mAuthHelper = phoneNumberAuthHelper;
    }

    protected void updateScreenSize(int i) {
        int iPx2dip = DisplayUtil.px2dip(this.mContext, DisplayUtil.getScreenHeight(r0));
        int iPx2dip2 = DisplayUtil.px2dip(this.mContext, DisplayUtil.getScreenWidth(r1));
        int rotation = this.mActivity.getWindowManager().getDefaultDisplay().getRotation();
        if (i == 3) {
            i = this.mActivity.getRequestedOrientation();
        }
        if (i == 0 || i == 6 || i == 11) {
            rotation = 1;
        } else if (i == 1 || i == 7 || i == 12) {
            rotation = 2;
        }
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    if (rotation != 3) {
                        return;
                    }
                }
            }
            this.mScreenWidthDp = iPx2dip;
            this.mScreenHeightDp = iPx2dip2;
            return;
        }
        this.mScreenWidthDp = iPx2dip2;
        this.mScreenHeightDp = iPx2dip;
    }

    public void setOnClickFastLoginListener(OnClickFastLoginListener onClickFastLoginListener) {
        this.listener = onClickFastLoginListener;
    }
}
