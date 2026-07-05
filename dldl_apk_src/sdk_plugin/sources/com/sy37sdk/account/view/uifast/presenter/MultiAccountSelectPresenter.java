package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.view.View;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.view.uifast.view.IMultiSelectView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MultiAccountSelectPresenter extends BaseAccountPagerPresenter<IMultiSelectView> implements IMultiAccountSelectPresenter {
    public MultiAccountSelectPresenter(Context context, IMultiSelectView iMultiSelectView) {
        super(context, iMultiSelectView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IMultiAccountSelectPresenter
    public void login(String str, String str2, String str3, AccountLogic.AccountListener accountListener) {
        if ("account".equals(str)) {
            AccountLogic.getInstance(getView().getContext()).accountLogin(str2, str3, accountListener);
        } else if ("phone_pwd".equals(str)) {
            AccountLogic.getInstance(getView().getContext()).phoneLoginPwd(str2, str3, accountListener);
        } else {
            accountListener.onFailure(-1, "check_account_list 接口返回错误");
        }
    }
}
