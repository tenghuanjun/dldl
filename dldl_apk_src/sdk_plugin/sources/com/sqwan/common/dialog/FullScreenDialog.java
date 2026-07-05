package com.sqwan.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FullScreenDialog extends Dialog {
    private Context mContext;

    public FullScreenDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "FullScreenDialogStyle"));
        this.mContext = context;
    }

    public FullScreenDialog(Context context, int i) {
        super(context, SqResUtils.getStyleId(context, "FullScreenDialogStyle"));
        this.mContext = context;
    }

    public FullScreenDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog
    public void show() {
        Context context = this.mContext;
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }
}
