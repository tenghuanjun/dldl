package com.jiguang.h5;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.jiguang.dldl.sy37.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PermissionDialog extends Dialog {
    private String desc;
    private Context mContext;
    private TextView tvDesc;

    public PermissionDialog(Context context, String str) {
        super(context, R.style.FullScreenDialogStyle);
        this.mContext = context;
        this.desc = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.permission_dialog);
        setCancelable(false);
        this.tvDesc = (TextView) findViewById(R.id.tv_desc);
        this.tvDesc.setText(this.desc);
    }
}
