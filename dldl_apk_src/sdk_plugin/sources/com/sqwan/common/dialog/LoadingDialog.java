package com.sqwan.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ViewUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoadingDialog extends FullScreenDialog {
    private CharSequence mMessage;
    public TextView textView;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int i) {
        super(context, i);
    }

    protected LoadingDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sy37_progress_dialog", "layout"));
        this.textView = (TextView) findViewById(getIdByName("msg", SqTrackCommonKey.id));
    }

    protected int getIdByName(String str, String str2) {
        try {
            return SqResUtils.getIdByName(str, str2, getContext());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void show() {
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }

    public void setMessage(CharSequence charSequence) {
        TextView textView;
        this.mMessage = charSequence;
        if (TextUtils.isEmpty(charSequence) || (textView = this.textView) == null) {
            return;
        }
        textView.setText(this.mMessage);
    }

    public void hideMessage() {
        TextView textView = this.textView;
        if (textView != null) {
            ViewUtils.gone(textView);
        }
    }
}
