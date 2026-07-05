package com.sy37sdk.account.view.uifast.view;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.PopListHelper;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.AccountLoginPresenter;
import com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginView extends BaseSwitchView implements IAccountLoginView {
    public static final int LOGIN_TYPE = 0;
    public static final int REGISTER_TYPE = 1;
    private View accountRow;
    private CheckBox cbClause;
    private View contentLayout;
    private EditText etAccount;
    private EditText etPwd;
    private View functionView;
    private boolean isFillLoginAccount;
    private boolean isFillRegAccount;
    private boolean isShowPwd;
    private ImageView ivPwdToggle;
    private int mType;
    private PopListHelper popListHelper;
    private IAccountLoginPresenter presenter;
    private View privacyView;
    private ImageView selectAccountView;
    private TextView tvClause;
    private TextView tvForgetPwd;
    private TextView tvLogin;
    private TextView tvPolicy;
    private TextView tvReg;
    private String uRegName;
    private String uRegPwd;
    private String uname;
    private String upwd;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_account";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return SqTrackBtn.SqTrackBtnExt.ACCOUNT_LOGIN;
    }

    public AccountLoginView(Context context, ILoginDialog iLoginDialog) {
        super(context);
        this.isShowPwd = false;
        this.loginDialog = iLoginDialog;
        this.presenter = new AccountLoginPresenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        View viewByName = getViewByName("content_layout");
        this.contentLayout = viewByName;
        viewByName.setBackgroundResource(LoginSkinHelper.getAccountLoginBackgroundResId(getContext()));
        this.accountRow = getViewByName("ll_account_row");
        this.etAccount = (EditText) getViewByName("et_account");
        this.etPwd = (EditText) getViewByName("et_pwd");
        this.ivPwdToggle = (ImageView) getViewByName("iv_password_hide");
        this.selectAccountView = (ImageView) getViewByName("iv_select_account");
        TextView textView = (TextView) getViewByName("tv_login");
        this.tvLogin = textView;
        textView.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
        this.tvLogin.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        TextView textView2 = (TextView) getViewByName("tv_register");
        this.tvReg = textView2;
        textView2.setTextColor(LoginSkinHelper.getPrimaryTextColor(getContext()));
        TextView textView3 = (TextView) getViewByName("tv_forgot_pwd");
        this.tvForgetPwd = textView3;
        textView3.setTextColor(LoginSkinHelper.getPrimaryTextColor(getContext()));
        this.functionView = getViewByName("function_view");
        this.privacyView = getViewByName("ll_privacy");
        this.cbClause = (CheckBox) getViewByName("cb_clause");
        this.tvClause = (TextView) getViewByName("tv_clause");
        this.tvPolicy = (TextView) getViewByName("tv_policy");
        updateLoginButtonState();
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.ivPwdToggle.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountLoginView.this.togglePassword();
            }
        });
        this.selectAccountView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountLoginView.this.popListHelper == null || AccountLoginView.this.popListHelper.getAccountList() == null || AccountLoginView.this.popListHelper.getAccountList().size() < 1) {
                    return;
                }
                if (!AccountLoginView.this.popListHelper.isShowing()) {
                    AccountLoginView.this.selectAccountView.setImageResource(AccountLoginView.this.getIdByName("sysq_ic_account_list_up", "drawable"));
                    AccountLoginView.this.popListHelper.showAccountList(AccountLoginView.this.accountRow);
                } else {
                    AccountLoginView.this.selectAccountView.setImageResource(AccountLoginView.this.getIdByName("sysq_ic_account_list_down", "drawable"));
                    AccountLoginView.this.popListHelper.hideAccountList();
                }
            }
        });
        this.tvLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountLoginView.this.isQuickClick()) {
                    return;
                }
                if (AccountLoginView.this.mType == 0) {
                    AccountLoginView.this.presenter.login(AccountLoginView.this.etAccount.getText().toString(), AccountLoginView.this.etPwd.getText().toString());
                } else {
                    AccountLoginView.this.presenter.accountRegister(AccountLoginView.this.etAccount.getText().toString(), AccountLoginView.this.etPwd.getText().toString());
                }
            }
        });
        this.tvReg.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.accountRegister, SqTrackBtn.SqTrackBtnExt.accountRegister);
                AccountLoginView.this.toggleUI(1);
            }
        });
        this.tvForgetPwd.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountLoginView.this.presenter.forgotPassword();
            }
        });
        this.cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AccountLoginView.this.presenter.clauseClick(z);
            }
        });
        this.tvClause.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountLoginView.this.presenter.toClausePage();
            }
        });
        this.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountLoginView.this.presenter.toPolicy();
            }
        });
        this.etAccount.addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.9
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                AccountLoginView.this.updateLoginButtonState();
            }
        });
        this.etPwd.addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.10
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                AccountLoginView.this.updateLoginButtonState();
            }
        });
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    protected void onBack() {
        if (this.mType == 1) {
            toggleUI(0);
        } else {
            super.onBack();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAccountList() {
        PopListHelper popListHelper = new PopListHelper();
        this.popListHelper = popListHelper;
        popListHelper.initAccountList(getContext(), AccountUtil.getUserInfoFilterByAccount(getContext()), this.accountRow.getWidth(), 42, new PopListHelper.ItemClickListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.11
            @Override // com.sy37sdk.account.view.PopListHelper.ItemClickListener
            public void onSelect(UserInfo userInfo) {
                if (!TextUtils.isEmpty(userInfo.getAlias())) {
                    AccountLoginView.this.etAccount.setText(userInfo.getAlias());
                } else {
                    AccountLoginView.this.etAccount.setText(userInfo.getUname());
                }
                AccountLoginView.this.etPwd.setText(userInfo.getUpwd());
            }

            @Override // com.sy37sdk.account.view.PopListHelper.ItemClickListener
            public void onDelete(UserInfo userInfo) {
                AccountLoginView.this.presenter.deleteUser(userInfo);
                String alias = userInfo.getAlias();
                String uname = userInfo.getUname();
                String string = AccountLoginView.this.etAccount.getText().toString();
                if ((TextUtils.isEmpty(alias) || !alias.contentEquals(string)) && (TextUtils.isEmpty(uname) || !uname.contentEquals(string))) {
                    return;
                }
                AccountLoginView.this.etAccount.setText("");
                AccountLoginView.this.etPwd.setText("");
            }
        }, new PopupWindow.OnDismissListener() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.12
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (AccountLoginView.this.popListHelper != null) {
                    AccountLoginView.this.selectAccountView.setImageResource(SqResUtils.getDrawableId(AccountLoginView.this.getContext(), "sysq_ic_account_list_down"));
                }
            }
        });
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        if (i != 6) {
            this.isFillLoginAccount = false;
            this.isFillRegAccount = false;
            post(new Runnable() { // from class: com.sy37sdk.account.view.uifast.view.AccountLoginView.13
                @Override // java.lang.Runnable
                public void run() {
                    AccountLoginView.this.initAccountList();
                }
            });
            this.presenter.initData();
        }
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void enableLoginBtn(boolean z) {
        this.tvLogin.setEnabled(z);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void loginSuccess(Map<String, String> map) {
        this.loginDialog.loginSuccess(map);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void setAutoAccount(String str, String str2) {
        this.etAccount.setText(str);
        this.etPwd.setText(str2);
        updateLoginButtonState();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void accountRegSuccess(Map<String, String> map) {
        this.loginDialog.accountRegSuccess(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePassword() {
        String string = this.etPwd.getText().toString();
        if (this.isShowPwd) {
            this.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.ivPwdToggle.setImageResource(getIdByName("sysq_ic_pwd_hide", "drawable"));
        } else {
            this.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this.ivPwdToggle.setImageResource(getIdByName("sysq_ic_pwd_show", "drawable"));
        }
        this.isShowPwd = !this.isShowPwd;
        this.etPwd.setText(string);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void toggleUI(int i) {
        this.mType = i;
        String string = this.etAccount.getText().toString();
        String string2 = this.etPwd.getText().toString();
        this.privacyView.setVisibility(0);
        int i2 = this.mType;
        if (i2 == 0) {
            this.uRegName = string;
            this.uRegPwd = string2;
            this.selectAccountView.setVisibility(0);
            this.functionView.setVisibility(0);
            this.tvLogin.setText(SqResUtils.getStringByName(getContext(), "sysq_login"));
            this.etAccount.setPadding(DisplayUtil.dip2px(getContext(), 12.0f), 0, 0, 0);
            if (this.isFillLoginAccount) {
                setAutoAccount(this.uname, this.upwd);
            } else {
                this.presenter.autoAccount();
                this.isFillLoginAccount = true;
            }
            setTitleView(SqTrackBtn.SqTrackBtnExt.ACCOUNT_LOGIN);
            PageExposureTrackManager.track("view05", SqTrackPage.SqTrackViewName.ACCOUNT_LOGIN);
            return;
        }
        if (i2 == 1) {
            this.uname = string;
            this.upwd = string2;
            this.selectAccountView.setVisibility(8);
            this.functionView.setVisibility(8);
            this.tvLogin.setText(SqResUtils.getStringByName(getContext(), "sysq_register"));
            this.etAccount.setPadding(DisplayUtil.dip2px(getContext(), 12.0f), 0, DisplayUtil.dip2px(getContext(), 12.0f), 0);
            if (this.isFillRegAccount) {
                setAutoAccount(this.uRegName, this.uRegPwd);
            } else {
                this.isFillRegAccount = true;
                this.presenter.autoRegister(false);
            }
            setTitleView("账号注册");
            PageExposureTrackManager.track("view06", SqTrackPage.SqTrackViewName.ACCOUNT_REGISTER);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void regEntrance(boolean z) {
        this.tvReg.setVisibility(z ? 0 : 8);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void checkedClause() {
        this.cbClause.setChecked(true);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IAccountLoginView
    public void selectMultiAccount(JSONArray jSONArray, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("account_list", jSONArray.toString());
        bundle.putString("login_uname", str);
        bundle.putString("login_pwd", str2);
        this.loginDialog.onSwitch(6, bundle);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    protected void closeLoginDialog() {
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.closeRegisterPage, SqTrackBtn.SqTrackBtnExt.closeRegisterPage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLoginButtonState() {
        boolean z = (TextUtils.isEmpty(this.etAccount.getText().toString().trim()) || TextUtils.isEmpty(this.etPwd.getText().toString().trim())) ? false : true;
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
}
