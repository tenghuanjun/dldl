package com.sy37sdk.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class ProgressDialog extends FullScreenDialog {
    private Context context;
    private CharSequence mMessage;
    TextView textView;

    public ProgressDialog(Context context) {
        this(context, Util.getIdByName("progressDialog", "style", context.getPackageName(), context));
        this.context = context;
    }

    ProgressDialog(Context context, int i) {
        super(context, i);
        this.context = context;
    }

    ProgressDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.context = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        View viewInflate;
        super.onCreate(bundle);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (Util.getIsSpecialSDK(this.context)) {
            viewInflate = layoutInflater.inflate(Util.getIdByName("sy37_progress_dialog_simple", "layout", this.context.getPackageName(), this.context), (ViewGroup) null);
            this.textView = (TextView) viewInflate.findViewById(Util.getIdByName("msg", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        } else {
            viewInflate = layoutInflater.inflate(Util.getIdByName("sy37_progress_dialog", "layout", this.context.getPackageName(), this.context), (ViewGroup) null);
            this.textView = (TextView) viewInflate.findViewById(Util.getIdByName("msg", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        }
        setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
    }

    public static ProgressDialog show(Context context, CharSequence charSequence) {
        return show(context, charSequence, false);
    }

    public static ProgressDialog show(Context context, int i) {
        return show(context, context.getResources().getString(i), false);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, boolean z) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(charSequence);
        progressDialog.setCancelable(z);
        progressDialog.show();
        return progressDialog;
    }

    public void setMessage(CharSequence charSequence) {
        TextView textView;
        this.mMessage = charSequence;
        if (TextUtils.isEmpty(charSequence) || (textView = this.textView) == null) {
            return;
        }
        textView.setText(this.mMessage);
    }
}
