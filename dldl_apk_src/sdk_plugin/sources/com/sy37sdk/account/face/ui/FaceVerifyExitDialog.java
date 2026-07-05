package com.sy37sdk.account.face.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyExitDialog extends BaseDialog {
    private Context mContext;
    public OnClickExitListener onClickExitListener;
    private TextView tvCancel;
    private TextView tvSure;

    public interface OnClickExitListener {
        void clickExit();
    }

    public FaceVerifyExitDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_exit_face_verify"));
        this.tvCancel = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_cancel"));
        this.tvSure = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_sure"));
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyExitDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyExitDialog.this.dismiss();
            }
        });
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyExitDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyExitDialog.this.dismiss();
                ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(false);
                if (FaceVerifyExitDialog.this.onClickExitListener != null) {
                    FaceVerifyExitDialog.this.onClickExitListener.clickExit();
                }
            }
        });
    }

    public void setOnClickExitListener(OnClickExitListener onClickExitListener) {
        this.onClickExitListener = onClickExitListener;
    }
}
