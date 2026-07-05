package com.huya.berry;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.login.LoginReportConstants;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.EasyTimer;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUI;
import com.huya.berry.gamesdk.widgets.CommonTopBar;
import com.huya.berry.webview.WebviewApi;
import com.huya.component.login.api.ILoginModule;
import com.huya.component.login.api.LoginCallback;
import com.huya.component.login.api.LoginInterface;
import com.huya.live.service.ServiceCenter;
import com.huya.mtp.utils.StringUtils;
import com.sqwan.liveshow.huya.SqR;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LoginFragment extends BaseDialogFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static final String TAG = "HY_SYSDK_LoginFragment";
    private static final int VERTIFY_CODE = 10030;
    private CheckBox mCbRule;
    private CommonTopBar mCtbTitleBar;
    private EditText mEditPhone;
    private EditText mEditVerifyCode;
    private boolean mIsGetVerifyCode;
    private ImageView mIvDelete;
    private TextView mTvGetVerifyCode;
    private TextView mTvLogin;
    private TextView mTvLoginLiveRule;
    private TextView mTvLoginPrivateRule;
    private TextView mTvLoginRule;
    private boolean mShown = false;
    private int mReSendSmsTime = -1;
    private EasyTimer mSendSmsTimer = new EasyTimer();
    private CommonTopBar.TopBarListener mTopBarListener = new CommonTopBar.TopBarListener() { // from class: com.huya.berry.LoginFragment.1
        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickAvatar() {
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickBack() {
            LoginFragment.this.dismiss();
            Report.event(LoginReportConstants.CLICK_LOGIN_BACK);
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickClose() {
            LoginFragment.this.dismiss();
            Report.event(LoginReportConstants.CLICK_LOGIN_CLOSE);
        }
    };
    private TextWatcher mEditPhoneWatcher = new TextWatcher() { // from class: com.huya.berry.LoginFragment.2
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            LoginFragment.this.mIsGetVerifyCode = false;
            if (TextUtils.isEmpty(editable)) {
                LoginFragment.this.mIvDelete.setVisibility(8);
            } else if (editable.toString().length() > 0) {
                LoginFragment.this.mIvDelete.setVisibility(0);
            } else {
                LoginFragment.this.mIvDelete.setVisibility(8);
            }
        }
    };

    public static LoginFragment getInstance(FragmentManager fragmentManager) {
        LoginFragment loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(TAG);
        return loginFragment == null ? new LoginFragment() : loginFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.Live.DialogFullscreen"));
        ServiceCenter.instance().getService(ILoginModule.class);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        if (CommonUtil.isScreenLandScape() && SdkProperties.isVideoLandScape.get().booleanValue()) {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_login_land), viewGroup, false);
        } else {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_login), viewGroup, false);
        }
        this.mIsGetVerifyCode = false;
        return viewInflate;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCtbTitleBar = (CommonTopBar) findViewById(ResourceUtil.getIdResIDByName(SqR.id.ctb_title_bar));
        this.mEditPhone = (EditText) findViewById(ResourceUtil.getIdResIDByName(SqR.id.edit_phone));
        this.mIvDelete = (ImageView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_delete));
        this.mEditVerifyCode = (EditText) findViewById(ResourceUtil.getIdResIDByName(SqR.id.edit_verify_code));
        this.mTvGetVerifyCode = (TextView) findViewById(ResourceUtil.getIdResIDByName("tv_get_verify_code"));
        this.mCbRule = (CheckBox) findViewById(ResourceUtil.getIdResIDByName(SqR.id.cb_rule));
        this.mTvLoginRule = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_login_rule));
        this.mTvLoginPrivateRule = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_login_private_rule));
        this.mTvLoginLiveRule = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_live_rule));
        this.mTvLogin = (TextView) findViewById(ResourceUtil.getIdResIDByName("tv_login"));
        this.mIvDelete.setVisibility(8);
        this.mCtbTitleBar.setTitle("快捷登录");
        this.mCtbTitleBar.showLogo(false);
        this.mCtbTitleBar.setTopBarListener(this.mTopBarListener);
        this.mEditPhone.addTextChangedListener(this.mEditPhoneWatcher);
        this.mIvDelete.setOnClickListener(this);
        this.mTvGetVerifyCode.setOnClickListener(this);
        this.mCbRule.setOnCheckedChangeListener(this);
        this.mTvLoginRule.setOnClickListener(this);
        this.mTvLoginPrivateRule.setOnClickListener(this);
        this.mTvLoginLiveRule.setOnClickListener(this);
        this.mTvLogin.setOnClickListener(this);
    }

    public void show(FragmentManager fragmentManager) {
        if (isAdded() || this.mShown) {
            return;
        }
        this.mShown = true;
        super.show(fragmentManager, TAG);
        Report.event(LoginReportConstants.PV_LOGIN);
    }

    public boolean isShow() {
        return this.mShown;
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        super.dismissAllowingStateLoss();
        if (isAdded() && this.mShown) {
            this.mShown = false;
            this.mSendSmsTimer.stop();
        }
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.equals(this.mIvDelete)) {
            this.mEditPhone.getText().clear();
            return;
        }
        if (view.equals(this.mTvGetVerifyCode)) {
            Report.event(LoginReportConstants.CLICK_LOGIN_GETCODE);
            checkPhoneInput(this.mEditPhone.getText().toString());
            return;
        }
        if (view.equals(this.mTvLoginRule)) {
            Report.event(LoginReportConstants.CLICK_LOGIN_PROTOCOL);
            WebviewApi.openWebview(getActivity(), "用户协议", "http://www.huya.com/tg/sdk-yhxy", false);
            return;
        }
        if (view.equals(this.mTvLoginPrivateRule)) {
            Report.event(LoginReportConstants.CLICK_LOGIN_PROTOCOL);
            WebviewApi.openWebview(getActivity(), "隐私政策", "http://www.huya.com/tg/sdk-yszc", false);
            return;
        }
        if (view.equals(this.mTvLoginLiveRule)) {
            Report.event(LoginReportConstants.CLICK_LOGIN_PROTOCOL);
            WebviewApi.openWebview(getActivity(), "开播协议", "http://www.huya.com/tg/sdk-kbxy", false);
            return;
        }
        if (view.equals(this.mTvLogin)) {
            Report.event(LoginReportConstants.CLICK_LOGIN_LOGIN);
            if (this.mIsGetVerifyCode) {
                if (checkInput()) {
                    SystemUI.hideSoftInput(getActivity(), this.mEditPhone);
                    SystemUI.hideSoftInput(getActivity(), this.mEditVerifyCode);
                    this.mTvLogin.setEnabled(false);
                    ArkUtils.call(new LoginInterface.LoginPhoneSms(this.mEditPhone.getText().toString(), this.mEditVerifyCode.getText().toString()));
                    return;
                }
                return;
            }
            ArkToast.show("请获取验证码");
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.equals(this.mCbRule)) {
            this.mCbRule.setChecked(z);
            Report.event(z ? LoginReportConstants.CLICK_LOGIN_CHECKPROTOCOL : LoginReportConstants.CLICK_LOGIN_UNCHECKPROTOCOL);
        }
    }

    private void checkPhoneInput(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (checkPhone(str)) {
                ArkUtils.call(new LoginInterface.SendLoginPhoneSms(str));
                this.mReSendSmsTime = 61;
                this.mSendSmsTimer.resetAndStart(1000, new Runnable() { // from class: com.huya.berry.LoginFragment.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (LoginFragment.this.mReSendSmsTime == 0) {
                            LoginFragment.this.mSendSmsTimer.stop();
                        } else {
                            LoginFragment loginFragment = LoginFragment.this;
                            loginFragment.mReSendSmsTime--;
                        }
                        LoginFragment.this.updateGetVerifyCodeBtnText();
                    }
                });
                return;
            }
            ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_phone_format_error));
            return;
        }
        ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_input_phone));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGetVerifyCodeBtnText() {
        if (isAdded() && this.mShown) {
            int i = this.mReSendSmsTime;
            if (i == -1) {
                this.mTvGetVerifyCode.setText(ResourceUtil.getStringResIDByName(SqR.string.hyberry_send_verify_code));
                this.mTvGetVerifyCode.setEnabled(true);
                return;
            }
            if (i == 0) {
                this.mTvGetVerifyCode.setText(ResourceUtil.getStringResIDByName(SqR.string.hyberry_re_send_verify_code));
                this.mTvGetVerifyCode.setEnabled(true);
                return;
            }
            this.mTvGetVerifyCode.setText(getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_re_send_verify_code)) + "(" + this.mReSendSmsTime + ")");
            this.mTvGetVerifyCode.setEnabled(false);
        }
    }

    private boolean checkPhone(String str) {
        return Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$").matcher(str).matches();
    }

    private boolean checkInput() {
        if (!checkPhone(this.mEditPhone.getText().toString())) {
            ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_input_phone));
            return false;
        }
        if (StringUtils.isNullOrEmpty(this.mEditVerifyCode.getText().toString())) {
            ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_input_verify_code));
            return false;
        }
        if (this.mCbRule.isChecked()) {
            return true;
        }
        ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_agree_rule));
        return false;
    }

    @IASlot(executorID = 1)
    public void onRefreshSmsCode(LoginCallback.RefreshSmsCodeCallBack refreshSmsCodeCallBack) {
        if (refreshSmsCodeCallBack.event.errCode == 10030) {
            TextUtils.isEmpty(refreshSmsCodeCallBack.event.description);
            return;
        }
        if (refreshSmsCodeCallBack.event.errCode == 0) {
            ArkToast.show("发送成功");
            this.mIsGetVerifyCode = true;
            return;
        }
        String str = TextUtils.isEmpty(refreshSmsCodeCallBack.event.description) ? "获取验证码失败" : refreshSmsCodeCallBack.event.description;
        ArkToast.show(str);
        StringBuilder sb = new StringBuilder();
        sb.append("GetVerifyCode error:");
        if (TextUtils.isEmpty(refreshSmsCodeCallBack.event.description)) {
            str = AbstractJsonLexerKt.NULL;
        }
        sb.append(str);
        L.info(TAG, sb.toString());
        this.mSendSmsTimer.stop();
        this.mReSendSmsTime = -1;
        updateGetVerifyCodeBtnText();
    }

    @IASlot(executorID = 1)
    public void onLoginTimeout(LoginCallback.LoginTimeout loginTimeout) {
        Report.event(LoginReportConstants.SMSLoginFail, LoginReportConstants.SMSLoginFailDesc);
        dismiss();
        ArkToast.show("登录超时,请稍后重试");
    }

    @IASlot(executorID = 1)
    public void onLoginFinished(LoginCallback.LoginFinished loginFinished) {
        if (loginFinished.success) {
            dismiss();
            return;
        }
        this.mTvLogin.setEnabled(true);
        L.info(TAG, "onLoginFinished error:" + (!StringUtils.isNullOrEmpty(loginFinished.desc) ? loginFinished.desc : "登录失败"));
    }
}
