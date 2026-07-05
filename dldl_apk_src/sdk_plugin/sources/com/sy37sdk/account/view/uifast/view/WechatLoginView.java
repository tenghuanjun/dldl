package com.sy37sdk.account.view.uifast.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.CheckClassUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.alifast.AccountLoginManager;
import com.sy37sdk.account.alifast.FastBooleanResultListener;
import com.sy37sdk.account.alifast.FastLoginManager;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.ui.WechatRegSuccessDialog;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.IWechatPresenter;
import com.sy37sdk.account.view.uifast.presenter.WechatLoginPresenter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WechatLoginView extends BaseSwitchView implements IWechatLoginView {
    private CheckBox cbClause;
    private View chatLoginView;
    private ILoginListener loginListener;
    private Context mContext;
    private IWechatPresenter presenter;
    private TextView tvClause;
    private TextView tvPolicy;
    private TextView tv_more_login;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_wechat";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return SqTrackBtn.SqTrackBtnExt.wechat;
    }

    public WechatLoginView(Context context, ILoginDialog iLoginDialog, ILoginListener iLoginListener) {
        super(context);
        this.mContext = context;
        this.loginDialog = iLoginDialog;
        this.loginListener = iLoginListener;
        this.presenter = new WechatLoginPresenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.wechat_login, SqTrackPage.SqTrackViewName.wechat_login);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        this.tv_more_login = (TextView) getViewByName("tv_other_login");
        this.tvClause = (TextView) getViewByName("tv_clause");
        this.tvPolicy = (TextView) getViewByName("tv_policy");
        this.cbClause = (CheckBox) getViewByName("cb_clause");
        this.chatLoginView = getViewByName("rl_wechat");
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.tv_more_login.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WechatLoginView.this.isLoginAuthActivityExist()) {
                    FastLoginManager.getInstance(WechatLoginView.this.mContext).getFastEnv(new FastBooleanResultListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.1.1
                        @Override // com.sy37sdk.account.alifast.FastBooleanResultListener
                        public void callback(boolean z) {
                            if (!z) {
                                WechatLoginView.this.loginDialog.onSwitch(0, null);
                                return;
                            }
                            AccountUtil.setToFastLogin(true);
                            AccountUtil.setCanFastBack(true);
                            WechatLoginView.this.loginDialog.dismissAccountDialog();
                            AccountLoginManager.getInstance(WechatLoginView.this.mContext).login(WechatLoginView.this.loginListener);
                        }
                    });
                } else {
                    WechatLoginView.this.loginDialog.onSwitch(0, null);
                }
            }
        });
        this.tvClause.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginView.this.presenter.toClausePage();
            }
        });
        this.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginView.this.presenter.toPolicy();
            }
        });
        this.cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WechatLoginView.this.presenter.clauseClick(z);
            }
        });
        this.chatLoginView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginView.this.presenter.wechatLogin();
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginView
    public void enableLoginBtn(boolean z) {
        this.chatLoginView.setEnabled(z);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginView
    public void loginSuccess(Map<String, String> map) {
        this.loginDialog.loginSuccess(map);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginView
    public void showRegDialog(Map<String, String> map, WechatRegSuccessDialog.IWechatRegListener iWechatRegListener) {
        WechatRegSuccessDialog wechatRegSuccessDialog = new WechatRegSuccessDialog(getContext(), AccountCache.getUsername(getContext()), AccountCache.getPassword(getContext()));
        wechatRegSuccessDialog.setWechatRegListener(iWechatRegListener);
        wechatRegSuccessDialog.show();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginView
    public void checkedClause() {
        this.cbClause.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoginAuthActivityExist() {
        return CheckClassUtils.classExist("com.mobile.auth.gatewayauth.LoginAuthActivity");
    }
}
