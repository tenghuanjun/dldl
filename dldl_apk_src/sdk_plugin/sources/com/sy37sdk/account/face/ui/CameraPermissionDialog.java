package com.sy37sdk.account.face.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CameraPermissionDialog extends BaseDialog {
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

    public CameraPermissionDialog(Context context) {
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
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_camera_permission_tip"));
        this.tvCancel = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_cancel"));
        this.tvSure = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_sure"));
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.CameraPermissionDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CameraPermissionDialog.this.mCancelListener != null) {
                    CameraPermissionDialog.this.mCancelListener.onCancel();
                }
                CameraPermissionDialog.this.dismiss();
            }
        });
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.CameraPermissionDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CameraPermissionDialog.this.mConfirmListener != null) {
                    CameraPermissionDialog.this.mConfirmListener.onConfirm();
                }
                CameraPermissionDialog.this.dismiss();
                CameraPermissionDialog cameraPermissionDialog = CameraPermissionDialog.this;
                cameraPermissionDialog.goSetting((Activity) cameraPermissionDialog.mContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goSetting(Activity activity) {
        activity.startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + this.mContext.getPackageName())), PermissionHelper.SETTING_REQUEST_CODE);
    }
}
