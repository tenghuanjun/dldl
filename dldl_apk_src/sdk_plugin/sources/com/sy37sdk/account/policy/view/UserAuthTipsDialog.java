package com.sy37sdk.account.policy.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserAuthTipsDialog extends AuthBaseDialog {
    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getContainerLayout() {
        return "sy37_layout_auth_tips";
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getTitle() {
        return "温馨提示";
    }

    public UserAuthTipsDialog(Context context) {
        super(context);
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void doEngine() {
        TextView textView = (TextView) findViewById(findId("tvProtol"));
        String string = textView.getText().toString();
        SpannableString link = setLink(textView, string);
        link.setSpan(new ForegroundColorSpan(Color.parseColor("#EC808D")), string.indexOf("取消授权并退出游戏"), string.indexOf("取消授权并退出游戏") + 9, 33);
        textView.setText(link);
        this.tvCancel.setText("取消授权");
        this.tvCancel.setSelected(false);
        this.tvOk.setText("我再想想");
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void onClickCancel() {
        exit();
    }
}
