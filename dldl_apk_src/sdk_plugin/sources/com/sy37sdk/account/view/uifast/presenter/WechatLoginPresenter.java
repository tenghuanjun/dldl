package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sq.tools.Logger;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.policy.view.PolicyDialog;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.view.ui.WechatRegSuccessDialog;
import com.sy37sdk.account.view.uifast.view.IWechatLoginView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WechatLoginPresenter extends BaseAccountPagerPresenter<IWechatLoginView> implements IWechatPresenter {
    private boolean clauseStatus;

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatPresenter
    public void wechatRegister() {
    }

    public WechatLoginPresenter(Context context, IWechatLoginView iWechatLoginView) {
        super(context, iWechatLoginView);
        this.clauseStatus = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatPresenter
    public void clauseClick(boolean z) {
        this.clauseStatus = z;
        Logger.info("clauseClick " + z, new Object[0]);
        if (z) {
            SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.agreement, SqTrackBtn.SqTrackBtnExt.agreement);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatPresenter
    public void toClausePage() {
        UAgreeManager.getInstance().showUserProtocol(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatPresenter
    public void toPolicy() {
        UAgreeManager.getInstance().showPolicy(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IWechatPresenter
    public void wechatLogin() {
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$WechatLoginPresenter$_81KoMU3r9ExJEPFvKTXj-kZFFI
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$wechatLogin$0$WechatLoginPresenter();
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IWechatLoginView) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.wechat, SqTrackBtn.SqTrackBtnExt.wechat);
        LoginTractionManager.trackInvoke("3", "8");
        AccountLogic.getInstance(this.context).wechatLogin(new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.WechatLoginPresenter.1
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(final Map<String, String> map) {
                LoginTractionManager.track("8", map);
                if (WechatLoginPresenter.this.mView != null) {
                    ((IWechatLoginView) WechatLoginPresenter.this.mView).hideLoading();
                }
                if (!TextUtils.isEmpty(map.get("pwd"))) {
                    if (WechatLoginPresenter.this.mView != null) {
                        ((IWechatLoginView) WechatLoginPresenter.this.mView).showRegDialog(map, new WechatRegSuccessDialog.IWechatRegListener() { // from class: com.sy37sdk.account.view.uifast.presenter.WechatLoginPresenter.1.1
                            @Override // com.sy37sdk.account.view.ui.WechatRegSuccessDialog.IWechatRegListener
                            public void onSuccess() {
                                if (WechatLoginPresenter.this.mView != null) {
                                    ((IWechatLoginView) WechatLoginPresenter.this.mView).loginSuccess(map);
                                }
                            }
                        });
                    }
                } else if (WechatLoginPresenter.this.mView != null) {
                    ((IWechatLoginView) WechatLoginPresenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str) {
                if (WechatLoginPresenter.this.mView != null) {
                    ((IWechatLoginView) WechatLoginPresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(WechatLoginPresenter.this.context, "登录失败，请稍后再试");
                LoginTractionManager.trackFail("3", "8", i + "", str);
            }
        });
    }

    public /* synthetic */ void lambda$wechatLogin$0$WechatLoginPresenter() {
        this.clauseStatus = true;
        ((IWechatLoginView) this.mView).checkedClause();
        wechatLogin();
    }
}
