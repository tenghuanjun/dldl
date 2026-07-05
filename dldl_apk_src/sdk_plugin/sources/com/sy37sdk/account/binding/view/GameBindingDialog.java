package com.sy37sdk.account.binding.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class GameBindingDialog extends BaseDialog {
    private GameBindingDialogCallback mCallback;
    private final Context mContext;
    private final String mTips;

    public interface GameBindingDialogCallback {
        void onCancel();

        void onConfirm();
    }

    public GameBindingDialog(Context context, String str) {
        super(context);
        this.mCallback = null;
        this.mContext = context;
        this.mTips = str;
    }

    public void setGameBindingDialogCallback(GameBindingDialogCallback gameBindingDialogCallback) {
        this.mCallback = gameBindingDialogCallback;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sysq_binding_dialog", "layout"));
        ((TextView) findViewById(getIdByName("tv_tip", SqTrackCommonKey.id))).setText(this.mTips);
        TextView textView = (TextView) findViewById(getIdByName("tv_cancel", SqTrackCommonKey.id));
        TextView textView2 = (TextView) findViewById(getIdByName("tv_sure", SqTrackCommonKey.id));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.binding.view.GameBindingDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameBindingDialog.this.dismiss();
                if (GameBindingDialog.this.mCallback != null) {
                    GameBindingDialog.this.mCallback.onCancel();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.binding.view.GameBindingDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GameBindingDialog.this.mCallback != null) {
                    GameBindingDialog.this.mCallback.onConfirm();
                }
            }
        });
    }
}
