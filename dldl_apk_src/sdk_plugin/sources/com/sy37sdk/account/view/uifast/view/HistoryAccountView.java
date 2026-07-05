package com.sy37sdk.account.view.uifast.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.CheckClassUtils;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.alifast.AccountLoginManager;
import com.sy37sdk.account.alifast.FastBooleanResultListener;
import com.sy37sdk.account.alifast.FastLoginManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.PopListHelper;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter;
import com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class HistoryAccountView extends BaseSwitchView implements IHistoryAccountView {
    private View accountRow;
    private CheckBox cbClause;
    private View contentLayout;
    private ImageView ivAccountType;
    private ILoginListener loginListener;
    private Context mContext;
    private UserInfo mUserInfo;
    private PopListHelper popListHelper;
    private IHistoryAccountPresenter presenter;
    private ImageView selectAccountView;
    private TextView tvAccount;
    private TextView tvClause;
    private TextView tvForgetPwd;
    private TextView tvLogin;
    private TextView tvOtherLogin;
    private TextView tvPolicy;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_history_account";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return SqTrackBtn.SqTrackBtnExt.login;
    }

    public HistoryAccountView(Context context, ILoginDialog iLoginDialog, ILoginListener iLoginListener) {
        super(context);
        this.mContext = context;
        this.loginListener = iLoginListener;
        this.loginDialog = iLoginDialog;
        this.presenter = new HistoryAccountPresenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        View viewByName = getViewByName("content_layout");
        this.contentLayout = viewByName;
        viewByName.setBackgroundResource(LoginSkinHelper.getHistoryLoginBackgroundResId(getContext()));
        this.tvAccount = (TextView) getViewByName("tv_account");
        TextView textView = (TextView) getViewByName("tv_login");
        this.tvLogin = textView;
        textView.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
        this.tvLogin.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        TextView textView2 = (TextView) getViewByName("tv_other_login");
        this.tvOtherLogin = textView2;
        textView2.setTextColor(LoginSkinHelper.getPrimaryTextColor(getContext()));
        this.ivAccountType = (ImageView) getViewByName("iv_account_type");
        this.selectAccountView = (ImageView) getViewByName("iv_select_account");
        this.accountRow = getViewByName("ll_account_row");
        TextView textView3 = (TextView) getViewByName("tv_forget_pwd");
        this.tvForgetPwd = textView3;
        textView3.setTextColor(LoginSkinHelper.getPrimaryTextColor(getContext()));
        this.cbClause = (CheckBox) getViewByName("cb_clause");
        this.tvClause = (TextView) getViewByName("tv_clause");
        this.tvPolicy = (TextView) getViewByName("tv_policy");
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        post(new Runnable() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.1
            @Override // java.lang.Runnable
            public void run() {
                HistoryAccountView.this.initAccountList();
            }
        });
        this.presenter.initData();
        hideSoftInput();
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.history_account, SqTrackPage.SqTrackViewName.HISTORY_ACCOUNT);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.tvOtherLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HistoryAccountView.this.isLoginAuthActivityExist()) {
                    FastLoginManager.getInstance(HistoryAccountView.this.mContext).getFastEnv(new FastBooleanResultListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.2.1
                        @Override // com.sy37sdk.account.alifast.FastBooleanResultListener
                        public void callback(boolean z) {
                            if (!z) {
                                HistoryAccountView.this.loginDialog.onSwitch(0, null);
                                return;
                            }
                            AccountUtil.setToFastLogin(true);
                            AccountUtil.setCanFastBack(true);
                            HistoryAccountView.this.loginDialog.dismissAccountDialog();
                            AccountLoginManager.getInstance(HistoryAccountView.this.mContext).login(HistoryAccountView.this.loginListener);
                        }
                    });
                } else {
                    HistoryAccountView.this.loginDialog.onSwitch(0, null);
                }
            }
        });
        this.accountRow.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HistoryAccountView.this.popListHelper == null || HistoryAccountView.this.popListHelper.getAccountList() == null || HistoryAccountView.this.popListHelper.getAccountList().size() < 1) {
                    return;
                }
                if (!HistoryAccountView.this.popListHelper.isShowing()) {
                    HistoryAccountView.this.selectAccountView.setImageResource(HistoryAccountView.this.getIdByName("sysq_ic_account_list_up", "drawable"));
                    HistoryAccountView.this.popListHelper.showAccountList(HistoryAccountView.this.accountRow);
                } else {
                    HistoryAccountView.this.selectAccountView.setImageResource(HistoryAccountView.this.getIdByName("sysq_ic_account_list_down", "drawable"));
                    HistoryAccountView.this.popListHelper.hideAccountList();
                }
            }
        });
        this.tvLogin.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HistoryAccountView.this.isQuickClick()) {
                    return;
                }
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.history_account_login, SqTrackBtn.SqTrackBtnExt.history_account_login);
                HistoryAccountView.this.presenter.login(HistoryAccountView.this.mUserInfo);
            }
        });
        this.tvForgetPwd.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HistoryAccountView.this.presenter.forgetPassword();
            }
        });
        this.tvClause.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HistoryAccountView.this.presenter.toClausePage();
            }
        });
        this.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HistoryAccountView.this.presenter.toPolicy();
            }
        });
        this.cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HistoryAccountView.this.presenter.clauseClick(z);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void setAccount(UserInfo userInfo) {
        this.mUserInfo = userInfo;
        if (userInfo == null) {
            this.loginDialog.onSwitch(0, null);
        } else {
            selectUserInfo(userInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAccountList() {
        PopListHelper popListHelper = new PopListHelper();
        this.popListHelper = popListHelper;
        popListHelper.initAccountList(getContext(), AccountUtil.getAllUserInfo(getContext()), this.accountRow.getWidth(), 42, new PopListHelper.ItemClickListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.9
            @Override // com.sy37sdk.account.view.PopListHelper.ItemClickListener
            public void onSelect(UserInfo userInfo) {
                HistoryAccountView.this.mUserInfo = userInfo;
                HistoryAccountView.this.selectUserInfo(userInfo);
            }

            @Override // com.sy37sdk.account.view.PopListHelper.ItemClickListener
            public void onDelete(UserInfo userInfo) {
                HistoryAccountView.this.presenter.deleteUser(userInfo);
                String alias = userInfo.getAlias();
                String uname = userInfo.getUname();
                String uname2 = HistoryAccountView.this.mUserInfo == null ? "" : HistoryAccountView.this.mUserInfo.getUname();
                if ((TextUtils.isEmpty(alias) || !alias.contentEquals(uname2)) && (TextUtils.isEmpty(uname) || !uname.contentEquals(uname2))) {
                    return;
                }
                HistoryAccountView.this.tvAccount.setText("");
            }
        }, new PopupWindow.OnDismissListener() { // from class: com.sy37sdk.account.view.uifast.view.HistoryAccountView.10
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (HistoryAccountView.this.popListHelper != null) {
                    HistoryAccountView.this.selectAccountView.setImageResource(SqResUtils.getDrawableId(HistoryAccountView.this.getContext(), "sysq_ic_account_list_down"));
                }
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void onSwitch(int i) {
        this.loginDialog.onSwitch(i, null);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void enableLoginBtn(boolean z) {
        this.tvLogin.setEnabled(z);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void loginSuccess(Map<String, String> map) {
        this.loginDialog.loginSuccess(map);
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public String getPhone() {
        UserInfo userInfo = this.mUserInfo;
        return (userInfo == null || TextUtils.isEmpty(userInfo.getMobile())) ? "" : this.mUserInfo.getMobile();
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void startView(int i, Bundle bundle) {
        if (i != 1 || EntranceManager.getInstance().isAccountLoginEntrance()) {
            this.loginDialog.onSwitch(i, bundle);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.view.IHistoryAccountView
    public void checkedClause() {
        this.cbClause.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectUserInfo(UserInfo userInfo) {
        String loginType = userInfo.getLoginType();
        if ("2".equals(loginType)) {
            this.ivAccountType.setImageResource(SqResUtils.getDrawableId(getContext(), "sysq_item_account_phone"));
            this.tvAccount.setText(userInfo.getMobile());
        } else if ("3".equals(loginType)) {
            this.ivAccountType.setImageResource(SqResUtils.getDrawableId(getContext(), "sysq_item_account_wechat"));
            this.tvAccount.setText(userInfo.getUname());
        } else {
            this.ivAccountType.setImageResource(SqResUtils.getDrawableId(getContext(), "sysq_ic_account"));
            this.tvAccount.setText(!TextUtils.isEmpty(userInfo.getAlias()) ? userInfo.getAlias() : userInfo.getUname());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoginAuthActivityExist() {
        return CheckClassUtils.classExist("com.mobile.auth.gatewayauth.LoginAuthActivity");
    }
}
