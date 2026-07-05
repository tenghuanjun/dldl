package com.sy37sdk.account.policy.view;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.live.login.LoginReportConstants;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;
import com.sy37sdk.account.policy.view.AuthBaseDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PolicyDialog extends BaseDialog {
    private final ConfirmCallback mConfirmCallback;
    private final Context mContext;

    public interface ConfirmCallback {
        void onConfirm();
    }

    public PolicyDialog(Context context, ConfirmCallback confirmCallback) {
        super(context);
        this.mContext = context;
        this.mConfirmCallback = confirmCallback;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sysq_policy_dialog", "layout"));
        setLink((TextView) findViewById(getIdByName("tv_tip", SqTrackCommonKey.id)), "为了更好地保障你的合法权益，请你先阅读并同意《用户协议》《隐私政策》，未注册的手机号将自动完成帐号注册");
        ((ImageView) findViewById(getIdByName("iv_close", SqTrackCommonKey.id))).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.policy.view.-$$Lambda$PolicyDialog$xJwzYGKpFLOp2SvkgRFmJ5JOciI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$PolicyDialog(view);
            }
        });
        ((TextView) findViewById(getIdByName("tv_sure", SqTrackCommonKey.id))).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.policy.view.PolicyDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PolicyDialog.this.mConfirmCallback != null) {
                    PolicyDialog.this.mConfirmCallback.onConfirm();
                }
                PolicyDialog.this.dismiss();
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$PolicyDialog(View view) {
        dismiss();
    }

    private void setLink(TextView textView, String str) {
        SpannableString spannableString = new SpannableString(str);
        AuthBaseDialog.URLSpanNoUnderline uRLSpanNoUnderline = new AuthBaseDialog.URLSpanNoUnderline(ActiveBeforeManager.getInstance().userProtocolInfo.userprotol, this.mContext);
        uRLSpanNoUnderline.setTextColor("#FE673A");
        AuthBaseDialog.URLSpanNoUnderline uRLSpanNoUnderline2 = new AuthBaseDialog.URLSpanNoUnderline(ActiveBeforeManager.getInstance().userProtocolInfo.privacyprotol, this.mContext);
        uRLSpanNoUnderline2.setTextColor("#FE673A");
        spannableString.setSpan(uRLSpanNoUnderline, str.indexOf("《用户协议》"), str.indexOf("《用户协议》") + 6, 33);
        spannableString.setSpan(uRLSpanNoUnderline2, str.indexOf(LoginReportConstants.UserGuide), str.indexOf(LoginReportConstants.UserGuide) + 6, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
    }
}
