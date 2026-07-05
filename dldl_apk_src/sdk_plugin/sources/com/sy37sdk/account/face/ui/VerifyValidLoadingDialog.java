package com.sy37sdk.account.face.ui;

import android.content.Context;
import android.os.Bundle;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class VerifyValidLoadingDialog extends BaseDialog {
    private Context mContext;

    public VerifyValidLoadingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_face_valid_dialog"));
    }
}
