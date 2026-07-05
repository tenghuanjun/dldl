package com.sy37sdk.account.face.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.face.FaceVerifyManager;
import com.sy37sdk.account.face.data.FaceVerifyData;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyWarningDialog extends BaseDialog {
    private final SQResultListener authResultListener;
    private ImageView ivHelp;
    private Context mContext;
    private TextView tvCancel;
    private TextView tvSure;

    public FaceVerifyWarningDialog(Context context, SQResultListener sQResultListener) {
        super(context);
        this.mContext = context;
        this.authResultListener = sQResultListener;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_face_warning_dialog"));
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.face, SqTrackPage.SqTrackViewName.face);
        initView();
        initEvent();
    }

    private void initView() {
        this.ivHelp = (ImageView) findViewById(getIdByName("iv_help", SqTrackCommonKey.id));
        this.tvCancel = (TextView) findViewById(getIdByName("tv_cancel", SqTrackCommonKey.id));
        this.tvSure = (TextView) findViewById(getIdByName("tv_sure", SqTrackCommonKey.id));
    }

    private void initEvent() {
        this.ivHelp.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyWarningDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppUtils.toSQWebUrl(FaceVerifyWarningDialog.this.mContext, UrlConstant.FACE_VERIFY_HELP_PAGE, "帮助");
            }
        });
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyWarningDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyData faceVerifyData = FaceVerifyManager.getInstance(FaceVerifyWarningDialog.this.mContext).getFaceVerifyData();
                if (faceVerifyData == null) {
                    return;
                }
                if (FaceVerifyWarningDialog.this.mContext instanceof Activity) {
                    Intent intent = new Intent(FaceVerifyWarningDialog.this.mContext, (Class<?>) FaceVerifyConfirmActivity.class);
                    intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_USER_NAME, faceVerifyData.getName());
                    intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_USER_CARD, faceVerifyData.getIdCardName());
                    intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_TIP, faceVerifyData.getVerifyTip());
                    ((Activity) FaceVerifyWarningDialog.this.mContext).startActivityForResult(intent, 10000);
                }
                FaceVerifyWarningDialog.this.dismiss();
            }
        });
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyWarningDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_FACE_TIP);
                ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(false);
                FaceVerifyWarningDialog.this.dismiss();
                if (FaceVerifyWarningDialog.this.authResultListener != null) {
                    FaceVerifyWarningDialog.this.authResultListener.onFailture(203, "取消人脸识别，登录失败");
                }
            }
        });
    }
}
