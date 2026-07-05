package com.sqwan.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonProgressDialog extends Dialog {
    private Context context;
    private String mMessage;
    TextView textView;

    public CommonProgressDialog(Context context) {
        this(context, SqResUtils.getStyleId(context, "progressDialog"));
    }

    public CommonProgressDialog(Context context, int i) {
        super(context, i);
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(SqResUtils.getLayoutId(this.context, "sy37_progress_dialog_simple"), (ViewGroup) null);
        this.textView = (TextView) viewInflate.findViewById(SqResUtils.getId(this.context, "msg"));
        setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
    }

    public void setMessage(String str) {
        TextView textView;
        this.mMessage = str;
        if (TextUtils.isEmpty(str) || (textView = this.textView) == null) {
            return;
        }
        textView.setText(this.mMessage);
    }
}
