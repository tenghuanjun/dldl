package com.sqwan.common.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "Mdialog"));
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
    }

    protected BaseDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.getDecorView().setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog
    public void show() {
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }
}
