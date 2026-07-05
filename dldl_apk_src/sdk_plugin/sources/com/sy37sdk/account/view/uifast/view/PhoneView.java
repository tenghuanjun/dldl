package com.sy37sdk.account.view.uifast.view;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.IPhonePresenter;
import com.sy37sdk.account.view.uifast.presenter.PhonePresenter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PhoneView extends BaseSwitchView implements IPhoneView {
    private static final int CODE_LENGTH = 6;
    private static final String DISABLED_COLOR = "#B8B8B8";
    private CheckBox cbClause;
    private View content;
    private EditText etPhone;
    private EditText etVerifyCode;
    private String mMobile;
    private IPhonePresenter presenter;
    private LinearLayout tvAccountLogin;
    private TextView tvClause;
    private TextView tvLogin;
    private TextView tvPolicy;
    private TextView tvResendCode;
    private LinearLayout tvWechatLogin;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_phone";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return "手机号登录";
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void regEntrance(boolean z) {
    }

    public PhoneView(Context context, ILoginDialog iLoginDialog) {
        super(context);
        this.presenter = new PhonePresenter(context, this);
        this.loginDialog = iLoginDialog;
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        View viewByName = getViewByName("content_layout");
        this.content = viewByName;
        viewByName.setBackgroundResource(LoginSkinHelper.getPhoneLoginBackgroundResId(getContext()));
        this.etPhone = (EditText) getViewByName("et_phone");
        this.etVerifyCode = (EditText) getViewByName("et_verify_code");
        this.tvResendCode = (TextView) getViewByName("tv_resend_code");
        TextView textView = (TextView) getViewByName("tv_login");
        this.tvLogin = textView;
        textView.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
        this.tvLogin.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        this.tvClause = (TextView) getViewByName("tv_clause");
        this.tvPolicy = (TextView) getViewByName("tv_policy");
        this.tvAccountLogin = (LinearLayout) getViewByName("tv_account_login");
        TextView textView2 = (TextView) getViewByName("account_login_text");
        if (textView2 != null) {
            textView2.setTextColor(LoginSkinHelper.getPrimaryTextColor(getContext()));
        }
        this.tvWechatLogin = (LinearLayout) getViewByName("tv_wechat_login");
        boolean zSupportWxEntrance = EntranceManager.getInstance().supportWxEntrance(getContext());
        this.tvWechatLogin.setVisibility(zSupportWxEntrance ? 0 : 8);
        LinearLayout linearLayout = (LinearLayout) this.tvAccountLogin.getParent();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.tvAccountLogin.getLayoutParams();
        if (zSupportWxEntrance) {
            linearLayout.setGravity(0);
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            layoutParams.rightMargin = dpToPx(8);
        } else {
            linearLayout.setGravity(17);
            layoutParams.width = dpToPx(145);
            layoutParams.weight = 0.0f;
            layoutParams.rightMargin = 0;
        }
        this.tvAccountLogin.setLayoutParams(layoutParams);
        TextView textView3 = (TextView) getViewByName("wechat_login_text");
        if (textView3 != null) {
            textView3.setTextColor(getResources().getColor(R.color.white));
        }
        this.cbClause = (CheckBox) getViewByName("cb_clause");
        this.presenter.initData();
        updateUIState();
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.tvResendCode.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhoneView.this.presenter.obtainVerifyCode();
            }
        });
        this.tvLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = PhoneView.this.etVerifyCode.getText().toString();
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.phone_login_page_click_login, SqTrackBtn.SqTrackBtnExt.phone_login_page_click_login);
                if (TextUtils.isEmpty(string) || string.length() != 6) {
                    PhoneView.this.presenter.obtainVerifyCode();
                } else {
                    PhoneView.this.presenter.loginVerifyCode(PhoneView.this.etPhone.getText().toString(), string);
                }
            }
        });
        this.tvAccountLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.phone_login_account_login, SqTrackBtn.SqTrackBtnExt.phone_login_account_login);
                PhoneView.this.loginDialog.onSwitch(1, null);
            }
        });
        this.tvWechatLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.phone_login_page_click_wechat_login, SqTrackBtn.SqTrackBtnExt.phone_login_page_click_wechat_login);
                PhoneView.this.presenter.wechatLogin();
            }
        });
        this.tvClause.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhoneView.this.presenter.toClausePage();
            }
        });
        this.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhoneView.this.presenter.toPolicy();
            }
        });
        this.cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PhoneView.this.presenter.clauseClick(z);
            }
        });
        this.etPhone.addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.8
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                PhoneView.this.updateUIState();
            }
        });
        this.etVerifyCode.addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.uifast.view.PhoneView.9
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                PhoneView.this.updateUIState();
                String strTrim = editable.toString().trim();
                if (TextUtils.isEmpty(strTrim) || strTrim.length() != 6) {
                    return;
                }
                String strTrim2 = PhoneView.this.etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(strTrim2)) {
                    return;
                }
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.verifyPhoneCode, SqTrackBtn.SqTrackBtnExt.verifyPhoneCode);
                PhoneView.this.presenter.loginVerifyCode(strTrim2, strTrim);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public String getPhone() {
        return this.etPhone.getText().toString();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void startVerifyCodeView() {
        this.presenter.initVerifyCodeTimer();
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        if (bundle != null && !TextUtils.isEmpty(bundle.getString("mobile"))) {
            String string = bundle.getString("mobile");
            this.mMobile = string;
            EditText editText = this.etPhone;
            if (editText != null) {
                editText.setText(string);
            }
        }
        PageExposureTrackManager.track("view02", SqTrackPage.SqTrackViewName.phone_input);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void accountRegSuccess(Map<String, String> map) {
        this.loginDialog.accountRegSuccess(map);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void accountLoginEntrance(boolean z) {
        this.tvAccountLogin.setVisibility(z ? 0 : 8);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void checkedClause() {
        this.cbClause.setChecked(true);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void setResendCodeStatus(boolean z) {
        int loginTextHintColor;
        this.tvResendCode.setEnabled(z);
        this.tvResendCode.setClickable(z);
        TextView textView = this.tvResendCode;
        if (z) {
            loginTextHintColor = LoginSkinHelper.getLoginTextAccentColor(getContext());
        } else {
            loginTextHintColor = LoginSkinHelper.getLoginTextHintColor(getContext());
        }
        textView.setTextColor(loginTextHintColor);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void setResendCodeText(String str) {
        this.tvResendCode.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUIState() {
        String strTrim = this.etPhone.getText().toString().trim();
        String strTrim2 = this.etVerifyCode.getText().toString().trim();
        updateResendCodeButtonState(!TextUtils.isEmpty(strTrim));
        updateLoginButtonState((TextUtils.isEmpty(strTrim) || TextUtils.isEmpty(strTrim2)) ? false : true);
    }

    private void updateResendCodeButtonState(boolean z) {
        this.tvResendCode.setEnabled(z);
        this.tvResendCode.setClickable(z);
        if (z) {
            this.tvResendCode.setTextColor(LoginSkinHelper.getLoginTextAccentColor(getContext()));
        } else {
            this.tvResendCode.setTextColor(Color.parseColor(DISABLED_COLOR));
        }
    }

    private void updateLoginButtonState(boolean z) {
        this.tvLogin.setEnabled(z);
        this.tvLogin.setClickable(z);
        if (z) {
            this.tvLogin.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
            this.tvLogin.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        } else {
            this.tvLogin.setBackgroundResource(SqResUtils.getDrawableId(getContext(), "sysq_dialog_login_btn_disabled_bg"));
            this.tvLogin.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private int dpToPx(int i) {
        return Math.round(i * getContext().getResources().getDisplayMetrics().density);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IPhoneView
    public void startView(int i, Bundle bundle) {
        this.loginDialog.onSwitch(i, bundle);
    }
}
