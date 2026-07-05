package com.sqwan.msdk.views;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQSplashDialogBlackStyle extends Dialog {
    private int bgColor;
    private Context mcontext;

    public SQSplashDialogBlackStyle(Context context) {
        super(context, R.style.Theme.Black.NoTitleBar.Fullscreen);
        this.bgColor = R.color.black;
        this.mcontext = context;
    }

    public void setBgColor(int i) {
        this.bgColor = i;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LinearLayout linearLayout = new LinearLayout(this.mcontext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(this.bgColor);
        setContentView(linearLayout);
    }
}
