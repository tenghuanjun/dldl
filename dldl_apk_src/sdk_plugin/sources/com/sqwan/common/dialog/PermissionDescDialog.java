package com.sqwan.common.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PermissionDescDialog extends com.sqwan.common.mvp.BaseDialog {
    protected String desc;
    protected Context mContext;
    protected CharSequence title;
    protected TextView tvTitle;

    public PermissionDescDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sy37_common_blank_dialog"));
        TextView textView = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_title"));
        this.tvTitle = textView;
        textView.setText(this.desc);
    }

    public void setDesc(String str) {
        this.desc = str;
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
