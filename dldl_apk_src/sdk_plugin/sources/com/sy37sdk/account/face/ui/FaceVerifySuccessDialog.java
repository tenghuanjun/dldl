package com.sy37sdk.account.face.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sq.tools.Logger;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.CutoutUtil;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.face.OnFaceVerifyListener;
import notchtools.geek.com.notchtools.NotchTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifySuccessDialog extends BaseDialog {
    private View closeView;
    private CountDownTimer countDownTimer;
    private Context mContext;
    private OnFaceVerifyListener onFaceVerifyListener;
    private View statusView;
    private TextView tvTimeTip;

    public FaceVerifySuccessDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_face_success_activity"));
        setCancelable(false);
        this.closeView = findViewById(SqResUtils.getId(this.mContext, "close"));
        this.tvTimeTip = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_time_tip"));
        this.statusView = findViewById(SqResUtils.getId(this.mContext, "status_view"));
        initStatusView();
        this.closeView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifySuccessDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifySuccessDialog.this.dismiss();
                if (FaceVerifySuccessDialog.this.onFaceVerifyListener != null) {
                    FaceVerifySuccessDialog.this.onFaceVerifyListener.onVerifySuccess();
                }
            }
        });
        CountDownTimer countDownTimer = new CountDownTimer(3000L, 1000L) { // from class: com.sy37sdk.account.face.ui.FaceVerifySuccessDialog.2
            @Override // android.os.CountDownTimer
            public void onTick(final long j) {
                if (!(FaceVerifySuccessDialog.this.mContext instanceof Activity) || ((Activity) FaceVerifySuccessDialog.this.mContext).isFinishing()) {
                    return;
                }
                ((Activity) FaceVerifySuccessDialog.this.mContext).runOnUiThread(new Runnable() { // from class: com.sy37sdk.account.face.ui.FaceVerifySuccessDialog.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FaceVerifySuccessDialog.this.tvTimeTip.setText(String.format(SqResUtils.getStringByName(FaceVerifySuccessDialog.this.getContext(), "sysq_txt_face_verify_success_back"), Long.valueOf((j / 1000) + 1)));
                    }
                });
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                FaceVerifySuccessDialog.this.dismiss();
                if (FaceVerifySuccessDialog.this.onFaceVerifyListener != null) {
                    FaceVerifySuccessDialog.this.onFaceVerifyListener.onVerifySuccess();
                }
            }
        };
        this.countDownTimer = countDownTimer;
        countDownTimer.start();
    }

    private void initStatusView() {
        int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(getWindow());
        int notchHeight = NotchTools.getFullScreenTools().getNotchHeight(getWindow());
        boolean zHasNotchScreen = CutoutUtil.hasNotchScreen((Activity) this.mContext);
        Logger.info("statusHeight=" + statusHeight + " notchHeight=" + notchHeight + " 是否刘海屏：" + zHasNotchScreen, new Object[0]);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.statusView.getLayoutParams();
        if (zHasNotchScreen) {
            layoutParams.height = statusHeight;
        } else {
            layoutParams.height = 0;
        }
        this.statusView.setLayoutParams(layoutParams);
    }

    public void setOnFaceVerifyListener(OnFaceVerifyListener onFaceVerifyListener) {
        this.onFaceVerifyListener = onFaceVerifyListener;
    }
}
