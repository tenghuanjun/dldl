package com.sy37sdk.account.alifast.config;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.duowan.live.login.LoginReportConstants;
import com.mobile.auth.gatewayauth.AuthRegisterViewConfig;
import com.mobile.auth.gatewayauth.AuthRegisterXmlConfig;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.ui.AbstractPnsViewDelegate;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.uagree.UAgreeCacheHelper;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.LoginSkinHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DialogConfig extends BaseUIConfig {
    private static final String TAG = "DialogConfig";

    public DialogConfig(Activity activity, PhoneNumberAuthHelper phoneNumberAuthHelper) {
        super(activity, phoneNumberAuthHelper);
    }

    @Override // com.sy37sdk.account.alifast.config.BaseUIConfig
    public void configAuthPage() {
        this.mAuthHelper.removeAuthRegisterXmlConfig();
        this.mAuthHelper.removeAuthRegisterViewConfig();
        int orientation = AppUtils.getOrientation();
        LogUtil.i("DialogConfig  " + orientation);
        int i = orientation == 2 ? 6 : 7;
        if (Build.VERSION.SDK_INT == 26) {
            i = 3;
        }
        updateScreenSize(i);
        boolean zSupportWxEntrance = EntranceManager.getInstance().supportWxEntrance(this.mContext);
        LogUtil.i(TAG, "WeChat support: " + zSupportWxEntrance);
        boolean zIsAccountLoginEntrance = EntranceManager.getInstance().isAccountLoginEntrance();
        int i2 = 270;
        if (zSupportWxEntrance) {
            if (zIsAccountLoginEntrance) {
                i2 = 310;
            }
        } else if (!zIsAccountLoginEntrance) {
            i2 = 260;
        }
        if (zSupportWxEntrance) {
            if (zIsAccountLoginEntrance) {
                this.mAuthHelper.addAuthRegistViewConfig("other_phone_login_btn", new AuthRegisterViewConfig.Builder().setView(initOtherPhoneLoginButton()).setRootViewId(0).build());
                this.mAuthHelper.addAuthRegistViewConfig("wechat_account_container", new AuthRegisterViewConfig.Builder().setView(initWechatAccountContainer(zIsAccountLoginEntrance)).setRootViewId(0).build());
            } else {
                this.mAuthHelper.addAuthRegistViewConfig("other_wechat_container", new AuthRegisterViewConfig.Builder().setView(initOtherWechatContainer()).setRootViewId(0).build());
            }
        } else if (zIsAccountLoginEntrance) {
            this.mAuthHelper.addAuthRegistViewConfig("buttons_container", new AuthRegisterViewConfig.Builder().setView(initHorizontalButtonsContainer()).setRootViewId(0).build());
        } else {
            this.mAuthHelper.addAuthRegistViewConfig("other_phone_login_btn", new AuthRegisterViewConfig.Builder().setView(initOtherPhoneLoginButton()).setRootViewId(0).build());
        }
        this.mAuthHelper.addAuthRegisterXmlConfig(new AuthRegisterXmlConfig.Builder().setLayout(SqResUtils.getLayoutId(this.mContext, "sysq_custom_port_dialog_action_bar"), new AbstractPnsViewDelegate() { // from class: com.sy37sdk.account.alifast.config.DialogConfig.1
            public void onViewCreated(View view) {
                LogUtil.i(DialogConfig.TAG, "addAuthRegisterXmlConfig onViewCreated: ");
                ((ImageView) findViewById(SqResUtils.getId(DialogConfig.this.mContext, "btn_close"))).setImageResource(LoginSkinHelper.getCloseIconResId(DialogConfig.this.mContext));
                findViewById(SqResUtils.getId(DialogConfig.this.mContext, "view_close")).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.DialogConfig.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.closeLoginPage, SqTrackBtn.SqTrackBtnExt.closeLoginPage);
                        DialogConfig.this.mAuthHelper.quitLoginPage();
                        if (DialogConfig.this.listener != null) {
                            DialogConfig.this.listener.clickClose();
                        }
                    }
                });
                ImageView imageView = (ImageView) findViewById(SqResUtils.getId(DialogConfig.this.mContext, "btn_back"));
                imageView.setImageResource(LoginSkinHelper.getBackIconResId(DialogConfig.this.mContext));
                if (!AccountUtil.checkCanFastBack()) {
                    imageView.setVisibility(4);
                }
                findViewById(SqResUtils.getId(DialogConfig.this.mContext, "view_back")).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.DialogConfig.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (AccountUtil.checkCanFastBack()) {
                            DialogConfig.this.mAuthHelper.quitLoginPage();
                            if (DialogConfig.this.listener != null) {
                                DialogConfig.this.listener.clickBack();
                            }
                        }
                    }
                });
                TextView textView = (TextView) findViewById(SqResUtils.getId(DialogConfig.this.mContext, "tv_title"));
                ImageView imageView2 = (ImageView) findViewById(SqResUtils.getId(DialogConfig.this.mContext, "iv_logo"));
                if (MultiSdkManager.getInstance().isScut3()) {
                    imageView2.setVisibility(8);
                    textView.setVisibility(0);
                } else if (ConfigManager.getInstance(getContext()).isSplashSDK() || ConfigManager.getInstance(getContext()).isLessFunction()) {
                    imageView2.setVisibility(8);
                    textView.setVisibility(0);
                } else {
                    imageView2.setImageResource(SqResUtils.getDrawableId(DialogConfig.this.mContext, MultiConfigManager.getInstance().isSqUnion() ? "sysq_ic_logo_union" : "sysq_ic_logo"));
                    imageView2.setVisibility(0);
                    textView.setVisibility(8);
                }
            }
        }).build());
        this.mAuthHelper.setAuthUIConfig(new AuthUIConfig.Builder().setAppPrivacyOne("《用户协议》", UAgreeCacheHelper.getUrlProtocol(this.mContext) + "&isAgree=true").setAppPrivacyTwo(LoginReportConstants.UserGuide, UAgreeCacheHelper.getUrlPolicy(this.mContext) + "&isAgree=true").setAppPrivacyColor(-7829368, SqResUtils.getColorByName(this.mContext, "sysq_dialog_login_text_accent")).setPrivacyConectTexts(new String[]{"及"}).setProtocolGravity(3).setPrivacyTextSizeDp(12).setPrivacyOperatorIndex(2).setPrivacyState(false).setPrivacyOffsetY_B(10).setPrivacyOffsetX(-14).setNavHidden(true).setDialogBottom(false).setProtocolAction(AppUtils.getPackageName(this.mContext) + ".protocolWeb").setLogoHidden(true).setSloganHidden(true).setAuthPageActIn("in_activity", "out_activity").setAuthPageActOut("in_activity", "out_activity").setNumFieldOffsetY(12).setNumberSizeDp(22).setNumberColor(-16777216).setLogBtnWidth(280).setLogBtnHeight(42).setLogBtnMarginLeftAndRight(20).setLogBtnTextSizeDp(16).setNavReturnImgDrawable(this.mContext.getDrawable(LoginSkinHelper.getCloseIconResId(this.mContext))).setLogBtnText("本机号码一键登录").setLogBtnTextColor(-1).setLogBtnBackgroundPath(LoginSkinHelper.getFastLoginBtnBackgroundResString(this.mContext)).setLogBtnOffsetY(55).setPageBackgroundPath(LoginSkinHelper.getPhoneLoginBackgroundResString(this.mContext)).setVendorPrivacyPrefix("《").setVendorPrivacySuffix("》").setCheckboxHidden(false).setCheckedImgDrawable(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_privacy_check_box"))).setDialogWidth(320).setDialogHeight(i2).setScreenOrientation(i).setLogBtnToastHidden(true).create());
    }

    private View initWechatAccountContainer(boolean z) {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 32.0f));
        layoutParams2.addRule(14, -1);
        layoutParams2.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), DisplayUtil.dip2px(this.mContext, 160.0f), DisplayUtil.dip2px(this.mContext, 20.0f), 0);
        linearLayout.setLayoutParams(layoutParams2);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        if (z) {
            LinearLayout linearLayoutCreatePasswordLoginButton = createPasswordLoginButton();
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
            layoutParams3.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
            linearLayoutCreatePasswordLoginButton.setLayoutParams(layoutParams3);
            linearLayout.addView(linearLayoutCreatePasswordLoginButton);
        }
        LinearLayout linearLayoutCreateWechatLoginButton = createWechatLoginButton();
        if (z) {
            layoutParams = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
            layoutParams.setMarginStart(DisplayUtil.dip2px(this.mActivity, 5.0f));
        } else {
            layoutParams = new LinearLayout.LayoutParams((DisplayUtil.dip2px(this.mActivity, 320.0f) - DisplayUtil.dip2px(this.mActivity, 40.0f)) / 2, DisplayUtil.dip2px(this.mActivity, 32.0f));
        }
        linearLayoutCreateWechatLoginButton.setLayoutParams(layoutParams);
        linearLayout.addView(linearLayoutCreateWechatLoginButton);
        return linearLayout;
    }

    private View initOtherWechatContainer() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 32.0f));
        layoutParams.addRule(14, -1);
        layoutParams.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), DisplayUtil.dip2px(this.mContext, 115.0f), DisplayUtil.dip2px(this.mContext, 20.0f), 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        LinearLayout linearLayoutCreateOtherPhoneLoginForWechatButton = createOtherPhoneLoginForWechatButton();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
        layoutParams2.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        linearLayoutCreateOtherPhoneLoginForWechatButton.setLayoutParams(layoutParams2);
        linearLayout.addView(linearLayoutCreateOtherPhoneLoginForWechatButton);
        LinearLayout linearLayoutCreateWechatLoginButton = createWechatLoginButton();
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
        layoutParams3.setMarginStart(DisplayUtil.dip2px(this.mActivity, 5.0f));
        linearLayoutCreateWechatLoginButton.setLayoutParams(layoutParams3);
        linearLayout.addView(linearLayoutCreateWechatLoginButton);
        return linearLayout;
    }

    private View initOtherPhoneLoginButton() {
        int iDip2px;
        boolean zSupportWxEntrance = EntranceManager.getInstance().supportWxEntrance(this.mContext);
        boolean zIsAccountLoginEntrance = EntranceManager.getInstance().isAccountLoginEntrance();
        if (!zSupportWxEntrance && !zIsAccountLoginEntrance) {
            LinearLayout linearLayout = new LinearLayout(this.mActivity);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 40.0f));
            layoutParams.addRule(14, -1);
            layoutParams.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), DisplayUtil.dip2px(this.mContext, 110.0f), DisplayUtil.dip2px(this.mContext, 20.0f), 0);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            LinearLayout linearLayoutCreateOtherPhoneLoginButtonNoIcon = createOtherPhoneLoginButtonNoIcon();
            linearLayoutCreateOtherPhoneLoginButtonNoIcon.setLayoutParams(new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 40.0f), 0.5f));
            linearLayout.addView(linearLayoutCreateOtherPhoneLoginButtonNoIcon);
            return linearLayout;
        }
        LinearLayout linearLayout2 = new LinearLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 40.0f));
        layoutParams2.addRule(14, -1);
        if (zSupportWxEntrance) {
            iDip2px = DisplayUtil.dip2px(this.mContext, 105.0f);
        } else {
            iDip2px = DisplayUtil.dip2px(this.mContext, 110.0f);
        }
        layoutParams2.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), iDip2px, DisplayUtil.dip2px(this.mContext, 20.0f), 0);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(17);
        if (!zSupportWxEntrance && zIsAccountLoginEntrance) {
            linearLayout2.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_secondary_btn_bg"), null));
        } else {
            linearLayout2.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_single_btn_bg"), null));
        }
        if (!zSupportWxEntrance && zIsAccountLoginEntrance) {
            ImageView imageView = new ImageView(this.mActivity);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 12.0f), DisplayUtil.dip2px(this.mActivity, 13.0f));
            layoutParams3.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
            imageView.setLayoutParams(layoutParams3);
            imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_ic_account_new"));
            linearLayout2.addView(imageView);
        }
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText("其他手机登录");
        textView.setTextSize(1, zSupportWxEntrance ? 16 : 12);
        textView.setTextColor(zSupportWxEntrance ? -16777216 : LoginSkinHelper.getFastLoginPrimaryTextColor(this.mContext));
        linearLayout2.addView(textView);
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$UqLzDxJkzJeehL_GubI_wOdVmqo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initOtherPhoneLoginButton$0$DialogConfig(view);
            }
        });
        return linearLayout2;
    }

    public /* synthetic */ void lambda$initOtherPhoneLoginButton$0$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickOtherLogin();
        }
    }

    private View initWechatLoginButton() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 40.0f));
        layoutParams.addRule(14, -1);
        layoutParams.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), DisplayUtil.dip2px(this.mContext, 155.0f), DisplayUtil.dip2px(this.mContext, 20.0f), 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_wechat_btn_bg"), null));
        ImageView imageView = new ImageView(this.mActivity);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 15.0f), DisplayUtil.dip2px(this.mActivity, 12.0f));
        layoutParams2.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        imageView.setLayoutParams(layoutParams2);
        imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_ic_wechat_login"));
        linearLayout.addView(imageView);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText(SqTrackBtn.SqTrackBtnExt.wechat);
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(-1);
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$CTxKytuUaekJ7oFIfpYcUQZeUeA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initWechatLoginButton$1$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$initWechatLoginButton$1$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickWechatLogin();
        }
    }

    private View initHorizontalButtonsContainer() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DisplayUtil.dip2px(this.mActivity, 32.0f));
        layoutParams.addRule(14, -1);
        layoutParams.setMargins(DisplayUtil.dip2px(this.mContext, 20.0f), DisplayUtil.dip2px(this.mContext, 115.0f), DisplayUtil.dip2px(this.mContext, 20.0f), 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        LinearLayout linearLayoutCreateOtherPhoneLoginButton = createOtherPhoneLoginButton();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
        layoutParams2.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        linearLayoutCreateOtherPhoneLoginButton.setLayoutParams(layoutParams2);
        linearLayout.addView(linearLayoutCreateOtherPhoneLoginButton);
        if (EntranceManager.getInstance().isAccountLoginEntrance()) {
            LinearLayout linearLayoutCreatePasswordLoginButton = createPasswordLoginButton();
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, DisplayUtil.dip2px(this.mActivity, 32.0f), 1.0f);
            layoutParams3.setMarginStart(DisplayUtil.dip2px(this.mActivity, 5.0f));
            linearLayoutCreatePasswordLoginButton.setLayoutParams(layoutParams3);
            linearLayout.addView(linearLayoutCreatePasswordLoginButton);
        }
        return linearLayout;
    }

    private LinearLayout createOtherPhoneLoginButton() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_secondary_btn_bg"), null));
        ImageView imageView = new ImageView(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 12.0f), DisplayUtil.dip2px(this.mActivity, 13.0f));
        layoutParams.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_item_account_phone_shanyan"));
        linearLayout.addView(imageView);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText("其他手机号登录");
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(LoginSkinHelper.getFastLoginPrimaryTextColor(this.mContext));
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$n3a-Wc-J999c2V5bRn2DjJGlw2g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createOtherPhoneLoginButton$2$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$createOtherPhoneLoginButton$2$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickOtherLogin();
        }
    }

    private LinearLayout createOtherPhoneLoginButtonNoIcon() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_single_btn_bg"), null));
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText("其他手机登录");
        textView.setTextSize(1, 16.0f);
        textView.setTextColor(-16777216);
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$ep31Ch0BftgIxrbajgeUcMBYL_g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createOtherPhoneLoginButtonNoIcon$3$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$createOtherPhoneLoginButtonNoIcon$3$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickOtherLogin();
        }
    }

    private LinearLayout createOtherPhoneLoginForWechatButton() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_secondary_btn_bg"), null));
        ImageView imageView = new ImageView(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 12.0f), DisplayUtil.dip2px(this.mActivity, 13.0f));
        layoutParams.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_item_account_phone_shanyan"));
        linearLayout.addView(imageView);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText("其他手机登录");
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(LoginSkinHelper.getFastLoginPrimaryTextColor(this.mContext));
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$VSzTAWHhCLQFzxJt7BYSnbFekIg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createOtherPhoneLoginForWechatButton$4$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$createOtherPhoneLoginForWechatButton$4$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickOtherLogin();
        }
    }

    private LinearLayout createPasswordLoginButton() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_secondary_btn_bg"), null));
        ImageView imageView = new ImageView(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 12.0f), DisplayUtil.dip2px(this.mActivity, 13.0f));
        layoutParams.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_ic_account_new"));
        linearLayout.addView(imageView);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText("账号密码登录");
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(LoginSkinHelper.getFastLoginPrimaryTextColor(this.mContext));
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$doEDBRnqGg5HWVMX550po3bYX_8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createPasswordLoginButton$5$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$createPasswordLoginButton$5$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickAccountLogin();
        }
    }

    private LinearLayout createWechatLoginButton() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setBackground(this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, "sysq_dialog_login_wechat_btn_bg"), null));
        ImageView imageView = new ImageView(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dip2px(this.mActivity, 15.0f), DisplayUtil.dip2px(this.mActivity, 12.0f));
        layoutParams.setMarginEnd(DisplayUtil.dip2px(this.mActivity, 5.0f));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, "sysq_ic_wechat_login"));
        linearLayout.addView(imageView);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setText(SqTrackBtn.SqTrackBtnExt.wechat);
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(-1);
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.alifast.config.-$$Lambda$DialogConfig$hnGwDVYfZ9ozpCfxdAfcKPUjFTA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createWechatLoginButton$6$DialogConfig(view);
            }
        });
        return linearLayout;
    }

    public /* synthetic */ void lambda$createWechatLoginButton$6$DialogConfig(View view) {
        if (this.listener != null) {
            this.listener.clickWechatLogin();
        }
    }
}
