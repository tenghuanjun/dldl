package com.sy37sdk.account.view.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.track.SqTrackAction;
import com.sqwan.common.track.SqTrackActionManager;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.SDKError;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.presenter.IAutoLoginPresenter;
import com.sy37sdk.account.view.IAutoLoginDialog;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AutoLoginDialog extends BaseDialog implements IAutoLoginDialog, DialogInterface.OnCancelListener {
    private View auto_login_layout;
    private IAutoLoginDialog.IChangeAccountListener changeAccountListener;
    private ILoginListener loginListener;
    private IAutoLoginPresenter presenter;
    private TextView tvAccount;
    private TextView tvChangeAccount;
    private String uname;

    public AutoLoginDialog(Context context, String str, ILoginListener iLoginListener) {
        super(context);
        this.loginListener = iLoginListener;
        this.uname = str;
        setOnCancelListener(this);
        getWindow().setDimAmount(0.0f);
    }

    public void setPresenter(IAutoLoginPresenter iAutoLoginPresenter) {
        this.presenter = iAutoLoginPresenter;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sy37_account_change_simple", "layout"));
        initView();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.presenter.setAutoLoginShow(true);
        this.presenter.autoLogin();
    }

    private void initView() {
        int iDip2px;
        this.auto_login_layout = findViewById(getIdByName("auto_login_layout", SqTrackCommonKey.id));
        this.tvChangeAccount = (TextView) findViewById(getIdByName("tv_changeAccount", SqTrackCommonKey.id));
        this.tvAccount = (TextView) findViewById(getIdByName("text_account", SqTrackCommonKey.id));
        int i = this.mContext.getResources().getConfiguration().orientation;
        int screenWidth = DisplayUtil.getScreenWidth(this.mContext);
        if (i == 2) {
            iDip2px = screenWidth / 2;
        } else {
            iDip2px = screenWidth - (DisplayUtil.dip2px(this.mContext, 28.0f) * 2);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.auto_login_layout.getLayoutParams();
        layoutParams.width = iDip2px;
        this.auto_login_layout.setLayoutParams(layoutParams);
        this.tvChangeAccount.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.ui.AutoLoginDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_AUTO_LOGIN);
                SqTrackActionManager.getInstance().trackAction(SqTrackAction.CLICK_CHANGE_ACCOUNT_IN_AUTO_LOGIN_VIEW);
                AutoLoginDialog.this.switchAccount();
            }
        });
        this.tvAccount.setText(this.uname);
        showAnimation();
    }

    @Override // com.sy37sdk.account.view.IAutoLoginDialog
    public void autoLoginSuccess(Map<String, String> map) {
        dismiss();
        if (this.loginListener != null) {
            SqTrackActionManager.getInstance().trackAction(SqTrackAction.AUTO_LOGIN_SUCCESS, true);
            this.loginListener.onSuccess(map);
        }
    }

    private void showAnimation() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.auto_login_layout, "translationY", -200.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.auto_login_layout, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
    }

    @Override // com.sy37sdk.account.view.IAutoLoginDialog
    public void autoLoginFail(int i, String str) {
        ToastUtil.showToast(getContext(), str);
        dismiss();
        IAutoLoginDialog.IChangeAccountListener iChangeAccountListener = this.changeAccountListener;
        if (iChangeAccountListener != null) {
            iChangeAccountListener.changeAccount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchAccount() {
        this.presenter.setAutoLoginShow(false);
        dismiss();
        IAutoLoginDialog.IChangeAccountListener iChangeAccountListener = this.changeAccountListener;
        if (iChangeAccountListener != null) {
            iChangeAccountListener.changeAccount();
        }
    }

    @Override // com.sy37sdk.account.view.IAutoLoginDialog
    public void setChangeAccountListener(IAutoLoginDialog.IChangeAccountListener iChangeAccountListener) {
        this.changeAccountListener = iChangeAccountListener;
    }

    @Override // com.sy37sdk.account.view.IAutoLoginDialog
    public void closeDialog() {
        dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.presenter.detachView();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        ILoginListener iLoginListener = this.loginListener;
        if (iLoginListener != null) {
            iLoginListener.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, SDKError.ACCOUNT_LOGIN_CANCEL.message);
        }
    }
}
