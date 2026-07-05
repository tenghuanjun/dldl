package com.sy37sdk.account.policy.view;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserHistorySearchDialog extends AuthBaseDialog {
    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getContainerLayout() {
        return "sy37_layout_history_search_dialog";
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getTitle() {
        return "查询历史账号";
    }

    public UserHistorySearchDialog(Context context) {
        super(context);
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void doEngine() {
        setCanceledOnTouchOutside(false);
        this.tvCancel.setText("不同意");
        this.tvCancel.setSelected(false);
        this.tvOk.setText("同意");
    }
}
