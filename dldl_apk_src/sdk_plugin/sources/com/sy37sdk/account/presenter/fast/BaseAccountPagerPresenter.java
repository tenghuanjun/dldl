package com.sy37sdk.account.presenter.fast;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mvp.IView;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.view.base.view.IAccountPresenter;
import com.sy37sdk.account.view.base.view.IPageSwitchView;
import com.sy37sdk.account.view.uifast.switcher.IPageSwitcher;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseAccountPagerPresenter<K extends IView> extends IAccountPresenter<K> {
    private IPageSwitcher mAccountPageSwitcher;
    protected ILoginListener mLoginListener;

    public abstract View getView();

    public void loginMobileWithCode(String str, String str2) {
    }

    public void loginMobileWithPwd(String str, String str2) {
    }

    public BaseAccountPagerPresenter(Context context, K k) {
        super(context, k);
    }

    public void setAccountPageSwitcher(IPageSwitcher iPageSwitcher) {
        this.mAccountPageSwitcher = iPageSwitcher;
    }

    public void setLoginListener(ILoginListener iLoginListener) {
        this.mLoginListener = iLoginListener;
    }

    public void onSwitch(int i) {
        IPageSwitcher iPageSwitcher = this.mAccountPageSwitcher;
        if (iPageSwitcher != null) {
            iPageSwitcher.onSwitch(i);
        }
    }

    public void onSwitched(int i, int i2, Bundle bundle) {
        ((IPageSwitchView) this.mView).onSwitched(i, i2, bundle);
    }

    public void onBackPressed() {
        ((IPageSwitchView) this.mView).onBackPressed();
    }

    public void loginAccount(String str, String str2, final AccountLogic.AccountListener accountListener) {
        AccountLogic.getInstance(this.context).accountLogin(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter.1
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                AccountLogic.AccountListener accountListener2 = accountListener;
                if (accountListener2 != null) {
                    accountListener2.onSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                ToastUtil.showToast(BaseAccountPagerPresenter.this.context, str3);
                AccountLogic.AccountListener accountListener2 = accountListener;
                if (accountListener2 != null) {
                    accountListener2.onFailure(i, str3);
                }
            }
        });
    }

    public void forgotPassword() {
        String lowerCase = AppUtils.getLocaleLanguage().toLowerCase();
        String appName = AppUtils.getAppName(this.context);
        String username = AccountCache.getUsername(this.context);
        StringBuilder sb = new StringBuilder();
        if (UrlConstant.FORGET_PWD.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        if (!"".equals(lowerCase)) {
            sb.append("locale=" + lowerCase);
        }
        if (!"".equals(appName)) {
            sb.append("&gn=" + URLEncoder.encode(appName));
        }
        if (!"".equals(username)) {
            sb.append("&uname=" + username);
        }
        LogUtil.i(sb.toString());
        AppUtils.toSQWebUrl(this.context, UrlConstant.FORGET_PWD + sb.toString(), SqTrackBtn.SqTrackBtnExt.forgetPwd);
    }
}
