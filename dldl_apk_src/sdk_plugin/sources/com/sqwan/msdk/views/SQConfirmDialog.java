package com.sqwan.msdk.views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQConfirmDialog extends FullScreenDialog {
    private Button cancel;
    private Button confirm;
    private Context context;
    private ConfirmListener listener;
    private TextView msg_tv;
    private String text;

    public interface ConfirmListener {
        void onCancel();

        void onConfirm();
    }

    public SQConfirmDialog(Context context) {
        super(context);
    }

    public SQConfirmDialog(Context context, String str) {
        super(context);
        this.context = context;
        this.text = str;
    }

    public SQConfirmDialog(Context context, int i, String str) {
        super(context, i);
        this.context = context;
        this.text = str;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().setTheme(Util.getIdByName("Mdialog", "style", this.context.getPackageName(), this.context));
        setContentView(Util.getIdByName("sy37_confirm_dialog", "layout", this.context.getPackageName(), this.context));
        this.msg_tv = (TextView) findViewById(Util.getIdByName("message", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.cancel = (Button) findViewById(Util.getIdByName("cancel", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.confirm = (Button) findViewById(Util.getIdByName("confirm", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.msg_tv.setText(this.text);
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQConfirmDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SQConfirmDialog.this.listener != null) {
                    SQConfirmDialog.this.listener.onCancel();
                }
            }
        });
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQConfirmDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SQConfirmDialog.this.listener != null) {
                    SQConfirmDialog.this.listener.onConfirm();
                }
            }
        });
        setCanceledOnTouchOutside(false);
    }

    public void setConfirmListenr(ConfirmListener confirmListener) {
        if (confirmListener != null) {
            this.listener = confirmListener;
        }
    }
}
