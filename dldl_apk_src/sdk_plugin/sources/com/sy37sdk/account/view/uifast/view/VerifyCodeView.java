package com.sy37sdk.account.view.uifast.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter;
import com.sy37sdk.account.view.uifast.presenter.VerifyCodePresenter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class VerifyCodeView extends BaseSwitchView implements IVerifyCodeView {
    private static final int CODE_LENGTH = 6;
    private static final int LOGIN_WAY_PWD = 0;
    private static final int LOGIN_WAY_VERIFY_CODE = 1;
    private View contentView;
    private EditText etCode;
    private EditText etPwd;
    private View inputPwdView;
    private View inputVerifyView;
    private boolean isShowPwd;
    private ImageView ivPwdStatus;
    private TextView loginHelp;
    private int mType;
    private String phoneNumber;
    private IVerifyCodePresenter presenter;
    private TextView tvLogin;
    private TextView tvLoginWay;
    private TextView tvSendCode;
    private TextView tvSendPhone;
    private TextView tvTipInput;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_verify_code";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return SqTrackBtn.SqTrackBtnExt.login;
    }

    public VerifyCodeView(Context context, ILoginDialog iLoginDialog) {
        super(context);
        this.isShowPwd = false;
        this.loginDialog = iLoginDialog;
        this.presenter = new VerifyCodePresenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        this.phoneNumber = bundle.getString("mobile");
        this.mType = 0;
        toggleLoginWay();
        this.presenter.initTimer();
        EditText editText = this.etCode;
        if (editText != null) {
            editText.setText("");
        }
        EditText editText2 = this.etPwd;
        if (editText2 != null) {
            editText2.setText("");
        }
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        this.tvSendPhone = (TextView) getViewByName("tip_send");
        this.tvTipInput = (TextView) getViewByName("tip_input_login_info");
        this.inputPwdView = getViewByName("ll_pwd");
        this.inputVerifyView = getViewByName("ll_verify_code");
        this.tvLoginWay = (TextView) getViewByName("login_way");
        this.ivPwdStatus = (ImageView) getViewByName("iv_pwd_status");
        this.etPwd = (EditText) getViewByName("et_pwd");
        this.tvSendCode = (TextView) getViewByName("send_code");
        this.etCode = (EditText) getViewByName("et_verify_code");
        TextView textView = (TextView) getViewByName("tv_login");
        this.tvLogin = textView;
        textView.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
        this.tvLogin.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        this.loginHelp = (TextView) getViewByName("login_help");
        this.contentView = getViewByName("content_layout");
        this.tvTipInput.setTextColor(LoginSkinHelper.getTipInputColor(getContext()));
        this.tvLoginWay.setTextColor(LoginSkinHelper.getLoginTextAccentColor(getContext()));
        this.loginHelp.setTextColor(LoginSkinHelper.getLoginTextAccentColor(getContext()));
        this.tvSendPhone.setTextColor(LoginSkinHelper.getLoginTextHintColor(getContext()));
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.loginHelp.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyCodeView.this.presenter.toAppeal();
            }
        });
        this.ivPwdStatus.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyCodeView.this.togglePassword();
            }
        });
        this.tvSendCode.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyCodeView.this.presenter.sendCode(VerifyCodeView.this.phoneNumber);
            }
        });
        this.tvLoginWay.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyCodeView.this.toggleLoginWay();
            }
        });
        this.etCode.addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String string = editable.toString();
                LogUtil.i("afterTextChanged  " + string);
                if (TextUtils.isEmpty(string) || string.length() != 6) {
                    return;
                }
                VerifyCodeView.this.presenter.loginVerifyCode(VerifyCodeView.this.phoneNumber, string);
            }
        });
        this.tvLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.VerifyCodeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyCodeView.this.presenter.loginPwd(VerifyCodeView.this.phoneNumber, VerifyCodeView.this.etPwd.getText().toString());
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IVerifyCodeView
    public void verifyCodeStatus(boolean z) {
        this.tvSendCode.setEnabled(z);
        this.tvSendCode.setClickable(z);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IVerifyCodeView
    public void verifyCodeText(String str) {
        this.tvSendCode.setText(str);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IVerifyCodeView
    public void loginSuccess(Map<String, String> map) {
        this.loginDialog.loginSuccess(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleLoginWay() {
        if (this.mType == 1) {
            DisplayUtil.setViewSize(this.contentView, 340, 270);
            this.contentView.setBackgroundResource(LoginSkinHelper.getPhonePwdLoginBackgroundResId(getContext()));
            this.mType = 0;
            this.tvTipInput.setText(SqResUtils.getStringByName(getContext(), "sysq_input_pwd"));
            this.tvSendPhone.setText(String.format(SqResUtils.getStringByName(getContext(), "sysq_pwd_phone"), this.phoneNumber));
            this.inputVerifyView.setVisibility(8);
            this.inputPwdView.setVisibility(0);
            this.tvLoginWay.setText(SqResUtils.getStringByName(getContext(), "sysq_login_verify"));
            showSoftInput(this.etPwd);
            PageExposureTrackManager.track("view04", SqTrackPage.SqTrackViewName.phone_pwd);
            return;
        }
        DisplayUtil.setViewSize(this.contentView, 340, 222);
        this.contentView.setBackgroundResource(LoginSkinHelper.getPhoneCodeLoginBackgroundResId(getContext()));
        this.mType = 1;
        this.tvTipInput.setText(SqResUtils.getStringByName(getContext(), "sysq_input_verify_code"));
        this.tvSendPhone.setText(String.format(SqResUtils.getStringByName(getContext(), "sysq_verify_code_phone"), this.phoneNumber));
        this.inputPwdView.setVisibility(8);
        this.inputVerifyView.setVisibility(0);
        this.tvLoginWay.setText(SqResUtils.getStringByName(getContext(), "sysq_login_pwd"));
        showSoftInput(this.etCode);
        PageExposureTrackManager.track("view03", SqTrackPage.SqTrackViewName.phone_code);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePassword() {
        String string = this.etPwd.getText().toString();
        if (this.isShowPwd) {
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.ivPwdStatus.setImageResource(getIdByName("sysq_ic_pwd_hide", "drawable"));
        } else {
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this.ivPwdStatus.setImageResource(getIdByName("sysq_ic_pwd_show", "drawable"));
        }
        this.isShowPwd = !this.isShowPwd;
        this.etPwd.setText(string);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IVerifyCodeView
    public void startView(int i, Bundle bundle) {
        this.loginDialog.onSwitch(i, bundle);
    }
}
