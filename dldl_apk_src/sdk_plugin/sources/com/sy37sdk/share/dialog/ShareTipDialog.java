package com.sy37sdk.share.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareTipDialog extends Dialog {
    public ShareTipDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "CustomDialog"));
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sy37_dialog_share_fail"));
        setCanceledOnTouchOutside(false);
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = defaultDisplay.getWidth();
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(SqResUtils.getStyleId(getContext(), "dialogWindowAnim"));
        findViewById(SqResUtils.getId(getContext(), "tv_tips_ok")).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareTipDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareTipDialog.this.dismiss();
            }
        });
    }
}
