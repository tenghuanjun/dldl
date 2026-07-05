package com.sy37sdk.account.view.uifast.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.ui.WechatRegSuccessDialog;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter;
import com.sy37sdk.account.view.uifast.presenter.WechatLoginV2Presenter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
@Deprecated
public class WechatLoginV2View extends BaseSwitchView implements IWechatLoginV2View {
    private CheckBox cbClause;
    private View chatLoginView;
    private EditText etPhone;
    private IWechatV2Presenter presenter;
    private TextView tvAccountLogin;
    private TextView tvClause;
    private TextView tvPolicy;
    private TextView tvVerifyCode;

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public void enableLoginBtn(boolean z) {
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_wechat_v2";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return "手机号登录";
    }

    public WechatLoginV2View(Context context, ILoginDialog iLoginDialog) {
        super(context);
        this.loginDialog = iLoginDialog;
        this.presenter = new WechatLoginV2Presenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.wechat_login, SqTrackPage.SqTrackViewName.wechat_login);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        this.etPhone = (EditText) getViewByName("et_phone");
        this.tvVerifyCode = (TextView) getViewByName("tv_get_verify_code");
        this.tvAccountLogin = (TextView) getViewByName("tv_account_login");
        this.tvClause = (TextView) getViewByName("tv_clause");
        this.tvPolicy = (TextView) getViewByName("tv_policy");
        CheckBox checkBox = (CheckBox) getViewByName("cb_clause");
        this.cbClause = checkBox;
        setTouchDelegate(checkBox);
        this.chatLoginView = getViewByName("ll_wechat_login");
    }

    private void setTouchDelegate(final View view) {
        final View view2 = (View) view.getParent();
        view2.post(new Runnable() { // from class: com.sy37sdk.account.view.uifast.view.-$$Lambda$WechatLoginV2View$-nJfPc8BA-0qzlpuSirBwekcmpQ
            @Override // java.lang.Runnable
            public final void run() {
                WechatLoginV2View.lambda$setTouchDelegate$0(view, view2);
            }
        });
    }

    static /* synthetic */ void lambda$setTouchDelegate$0(View view, View view2) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        rect.top -= 300;
        rect.bottom += 300;
        rect.left -= 300;
        view2.setTouchDelegate(new TouchDelegate(rect, view));
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.tvAccountLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.accountLogin, SqTrackBtn.SqTrackBtnExt.ACCOUNT_LOGIN);
                WechatLoginV2View.this.loginDialog.onSwitch(1, null);
            }
        });
        this.tvVerifyCode.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginV2View.this.presenter.obtainVerifyCode();
            }
        });
        this.tvClause.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginV2View.this.presenter.toClausePage();
            }
        });
        this.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginV2View.this.presenter.toPolicy();
            }
        });
        this.cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WechatLoginV2View.this.presenter.clauseClick(z);
            }
        });
        this.chatLoginView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.WechatLoginV2View.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatLoginV2View.this.presenter.wechatLogin();
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public void loginSuccess(Map<String, String> map) {
        this.loginDialog.loginSuccess(map);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public void showRegDialog(Map<String, String> map, WechatRegSuccessDialog.IWechatRegListener iWechatRegListener) {
        WechatRegSuccessDialog wechatRegSuccessDialog = new WechatRegSuccessDialog(getContext(), AccountCache.getUsername(getContext()), AccountCache.getPassword(getContext()));
        wechatRegSuccessDialog.setWechatRegListener(iWechatRegListener);
        wechatRegSuccessDialog.show();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public String getPhone() {
        return this.etPhone.getText().toString();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public void startVerifyCodeView() {
        Bundle bundle = new Bundle();
        bundle.putString("mobile", this.etPhone.getText().toString());
        this.loginDialog.onSwitch(3, bundle);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IWechatLoginV2View
    public void checkedClause() {
        this.cbClause.setChecked(true);
    }
}
