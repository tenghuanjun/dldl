package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sq.tools.Logger;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.policy.view.PolicyDialog;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.view.ui.WechatRegSuccessDialog;
import com.sy37sdk.account.view.uifast.view.IWechatLoginV2View;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
@Deprecated
public class WechatLoginV2Presenter extends BaseAccountPagerPresenter<IWechatLoginV2View> implements IWechatV2Presenter {
    private boolean clauseStatus;

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void wechatRegister() {
    }

    public WechatLoginV2Presenter(Context context, IWechatLoginV2View iWechatLoginV2View) {
        super(context, iWechatLoginV2View);
        this.clauseStatus = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void clauseClick(boolean z) {
        this.clauseStatus = z;
        Logger.info("clauseClick " + z, new Object[0]);
        if (z) {
            SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.agreement, SqTrackBtn.SqTrackBtnExt.agreement);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void toClausePage() {
        UAgreeManager.getInstance().showUserProtocol(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void obtainVerifyCode() {
        String phone = ((IWechatLoginV2View) this.mView).getPhone();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(this.context, "请输入手机号");
            return;
        }
        if (!AppUtils.isMobileNO(phone)) {
            ToastUtil.showToast(this.context, "请填写正确的手机号");
            return;
        }
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$WechatLoginV2Presenter$AcRU1meSZYaXAs-PdWvZWqeB6DA
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$obtainVerifyCode$0$WechatLoginV2Presenter();
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IWechatLoginV2View) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.getPhoneCode, SqTrackBtn.SqTrackBtnExt.getPhoneCode);
        AccountLogic.getInstance(this.context).sendPhoneCode(phone, new AccountLogic.VerifyCodeListener() { // from class: com.sy37sdk.account.view.uifast.presenter.WechatLoginV2Presenter.1
            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onSuccess() {
                if (WechatLoginV2Presenter.this.mView != null) {
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).hideLoading();
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).startVerifyCodeView();
                }
                ToastUtil.showToast(WechatLoginV2Presenter.this.context, "请求已发送，请注意查收短信");
            }

            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onFailure(int i, String str) {
                ToastUtil.showToast(WechatLoginV2Presenter.this.context, str);
                if (WechatLoginV2Presenter.this.mView != null) {
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).hideLoading();
                }
                if (i == -77710 || WechatLoginV2Presenter.this.mView == null) {
                    return;
                }
                ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).startVerifyCodeView();
            }
        });
    }

    public /* synthetic */ void lambda$obtainVerifyCode$0$WechatLoginV2Presenter() {
        this.clauseStatus = true;
        ((IWechatLoginV2View) this.mView).checkedClause();
        obtainVerifyCode();
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void toPolicy() {
        UAgreeManager.getInstance().showPolicy(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatV2Presenter
    public void wechatLogin() {
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$WechatLoginV2Presenter$uPupW48Z363Z7TfC72nvyscx5Ag
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$wechatLogin$1$WechatLoginV2Presenter();
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IWechatLoginV2View) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.wechat, SqTrackBtn.SqTrackBtnExt.wechat);
        LoginTractionManager.trackInvoke("3", "8");
        AccountLogic.getInstance(this.context).wechatLogin(new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.WechatLoginV2Presenter.2
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(final Map<String, String> map) {
                LoginTractionManager.track("8", map);
                if (WechatLoginV2Presenter.this.mView != null) {
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).hideLoading();
                }
                if (!TextUtils.isEmpty(map.get("pwd"))) {
                    if (WechatLoginV2Presenter.this.mView != null) {
                        ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).showRegDialog(map, new WechatRegSuccessDialog.IWechatRegListener() { // from class: com.sy37sdk.account.view.uifast.presenter.WechatLoginV2Presenter.2.1
                            @Override // com.sy37sdk.account.view.ui.WechatRegSuccessDialog.IWechatRegListener
                            public void onSuccess() {
                                if (WechatLoginV2Presenter.this.mView != null) {
                                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).loginSuccess(map);
                                }
                            }
                        });
                    }
                } else if (WechatLoginV2Presenter.this.mView != null) {
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str) {
                if (WechatLoginV2Presenter.this.mView != null) {
                    ((IWechatLoginV2View) WechatLoginV2Presenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(WechatLoginV2Presenter.this.context, "登录失败，请稍后再试");
                LoginTractionManager.trackFail("3", "8", i + "", str);
            }
        });
    }

    public /* synthetic */ void lambda$wechatLogin$1$WechatLoginV2Presenter() {
        ((IWechatLoginV2View) this.mView).checkedClause();
        this.clauseStatus = true;
        wechatLogin();
    }
}
