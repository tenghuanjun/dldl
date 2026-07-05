package com.sy37sdk.account.policy.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.account.uagree.UAgreeManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserAuthPolicyDesDialog extends AuthBaseDialog {
    private LinearLayout llPolicy;
    private LinearLayout llPolicyUpdate;

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getContainerLayout() {
        return "sy37_layout_auth_preview";
    }

    public UserAuthPolicyDesDialog(Context context) {
        super(context);
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void doEngine() {
        this.llPolicy = (LinearLayout) findViewById(findId("llPolicy"));
        this.llPolicyUpdate = (LinearLayout) findViewById(findId("llPolicyUpdate"));
        TextView textView = (TextView) findViewById(findId("tvProtol"));
        TextView textView2 = (TextView) findViewById(findId("tvProtolUpdate"));
        setLink(textView, textView.getText().toString());
        setLink(textView2, textView2.getText().toString());
        setCanceledOnTouchOutside(false);
        this.tvCancel.setText("不同意");
        this.tvCancel.setSelected(false);
        this.tvOk.setText("同意");
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getTitle() {
        return UAgreeManager.getInstance().isFirstCheck() ? "个人信息保护指引" : "个人信息保护指引更新";
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void onClickOk() {
        super.onClickOk();
    }

    private void updatePolice() {
        if (UAgreeManager.getInstance().isFirstCheck()) {
            ViewUtils.show(this.llPolicy);
        } else {
            ViewUtils.show(this.llPolicyUpdate);
        }
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog, android.app.Dialog
    public void show() {
        super.show();
        updatePolice();
    }
}
