package com.sqwan.common.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioPermissionDialog extends com.sqwan.common.mvp.BaseDialog {
    private CancelListener mCancelListener;
    private ConfirmListener mConfirmListener;
    private Context mContext;
    private TextView tvCancel;
    private TextView tvSure;

    public interface CancelListener {
        void onCancel();
    }

    public interface ConfirmListener {
        void onConfirm();
    }

    public AudioPermissionDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setCancelListener(CancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }

    public void setConfirmListener(ConfirmListener confirmListener) {
        this.mConfirmListener = confirmListener;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_audio_permission_tip"));
        this.tvCancel = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_cancel"));
        this.tvSure = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_sure"));
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.AudioPermissionDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AudioPermissionDialog.this.mCancelListener != null) {
                    AudioPermissionDialog.this.mCancelListener.onCancel();
                }
                AudioPermissionDialog.this.dismiss();
            }
        });
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.AudioPermissionDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AudioPermissionDialog.this.mConfirmListener != null) {
                    AudioPermissionDialog.this.mConfirmListener.onConfirm();
                }
                AudioPermissionDialog.this.dismiss();
                AudioPermissionDialog audioPermissionDialog = AudioPermissionDialog.this;
                audioPermissionDialog.goSetting((Activity) audioPermissionDialog.mContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goSetting(Activity activity) {
        activity.startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + this.mContext.getPackageName())), 2025);
    }
}
