package com.sy37sdk.account.face.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.base.L;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.face.OnFaceVerifyListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyFailDialog extends BaseDialog {
    private Context mContext;
    private OnFaceVerifyListener onFaceVerifyListener;
    private TextView tvCustomer;
    private TextView tvQuit;
    private TextView tvRetry;

    public FaceVerifyFailDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_face_fail_activity"));
        setCancelable(false);
        this.tvRetry = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_retry"));
        this.tvQuit = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_quit_verify"));
        this.tvCustomer = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_customer"));
        this.tvRetry.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyFailDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyFailDialog.this.dismiss();
            }
        });
        this.tvQuit.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyFailDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_FACE_FAIL);
                ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(false);
                if (FaceVerifyFailDialog.this.onFaceVerifyListener != null) {
                    FaceVerifyFailDialog.this.onFaceVerifyListener.onVerifyFail("退出了人脸认证");
                }
            }
        });
        this.tvCustomer.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyFailDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppUtils.toSQWebUrl(L.getActivity(), UrlConstant.FACE_VERIFY_CUSTOMER_PAGE, "客服");
            }
        });
    }

    public void setOnFaceVerifyListener(OnFaceVerifyListener onFaceVerifyListener) {
        this.onFaceVerifyListener = onFaceVerifyListener;
    }
}
