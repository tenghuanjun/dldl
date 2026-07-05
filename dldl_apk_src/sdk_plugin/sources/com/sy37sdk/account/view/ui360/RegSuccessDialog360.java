package com.sy37sdk.account.view.ui360;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.QrCodeInfo;
import com.sy37sdk.account.view.IRegSuccessDialog;
import com.sy37sdk.account.view.LoginSkinHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RegSuccessDialog360 extends BaseDialog implements IRegSuccessDialog {
    private View close;
    private View contentView;
    private IRegSuccessDialog.EnterGameListener enterGameListener;
    private boolean isGoInGame;
    private String pwd;
    private View rootView;
    private TextView tvAccount;
    private TextView tvEnterGame;
    private TextView tvPassword;
    private TextView tvTip;
    private TextView tvTitle;
    private String uname;

    @Override // com.sy37sdk.account.view.IRegSuccessDialog
    public void setQrCodeMessage(QrCodeInfo qrCodeInfo) {
    }

    public RegSuccessDialog360(Context context, String str, String str2) {
        super(context);
        this.isGoInGame = false;
        this.uname = str;
        this.pwd = str2;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().setTheme(getIdByName("Mdialog", "style"));
        View viewInflate = LayoutInflater.from(getContext()).inflate(getIdByName("sysq_reg_success", "layout"), (ViewGroup) null);
        this.rootView = viewInflate;
        setContentView(viewInflate);
        initView();
    }

    private void initView() {
        this.tvTitle = (TextView) findViewById(getIdByName("tv_title", SqTrackCommonKey.id));
        this.tvAccount = (TextView) findViewById(getIdByName("tv_account", SqTrackCommonKey.id));
        this.tvPassword = (TextView) findViewById(getIdByName("tv_password", SqTrackCommonKey.id));
        TextView textView = (TextView) findViewById(getIdByName("tv_enter_game", SqTrackCommonKey.id));
        this.tvEnterGame = textView;
        textView.setBackgroundResource(LoginSkinHelper.getLoginBtnBackgroundResId(getContext()));
        this.tvEnterGame.setTextColor(LoginSkinHelper.getLoginBtnTextColor(getContext()));
        this.tvTip = (TextView) findViewById(getIdByName("tv_no_qr_success", SqTrackCommonKey.id));
        this.close = findViewById(getIdByName("view_close", SqTrackCommonKey.id));
        View viewFindViewById = findViewById(getIdByName("content", SqTrackCommonKey.id));
        this.contentView = viewFindViewById;
        viewFindViewById.setBackgroundResource(LoginSkinHelper.getHistoryLoginBackgroundResId(getContext()));
        initAction();
        initData();
        setCanceledOnTouchOutside(false);
    }

    private void initAction() {
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sy37sdk.account.view.ui360.RegSuccessDialog360.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (RegSuccessDialog360.this.isGoInGame || RegSuccessDialog360.this.enterGameListener == null) {
                    return;
                }
                RegSuccessDialog360.this.enterGameListener.cancel();
            }
        });
        this.tvEnterGame.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.ui360.RegSuccessDialog360.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegSuccessDialog360.this.isGoInGame = true;
                RegSuccessDialog360.this.dismiss();
                if (RegSuccessDialog360.this.enterGameListener != null) {
                    RegSuccessDialog360.this.enterGameListener.enterGame();
                }
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.ui360.RegSuccessDialog360.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegSuccessDialog360.this.dismiss();
            }
        });
    }

    public void initData() {
        this.tvTitle.setText("注册成功");
        this.tvAccount.setText("账号：" + this.uname);
        this.tvPassword.setText("密码：" + this.pwd);
        if (AccountCache.getAutoIssave(this.mContext)) {
            saveAccountView();
        }
    }

    private void saveAccountView() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sy37sdk.account.view.ui360.RegSuccessDialog360.4
            @Override // java.lang.Runnable
            public void run() {
                if (!AppUtils.saveView(RegSuccessDialog360.this.getContext(), RegSuccessDialog360.this.rootView, "37_" + RegSuccessDialog360.this.uname + ".png")) {
                    RegSuccessDialog360.this.tvTip.setText(SqResUtils.getStringByName(RegSuccessDialog360.this.mContext, "sysq_save_account"));
                } else {
                    RegSuccessDialog360.this.tvTip.setText(SqResUtils.getStringByName(RegSuccessDialog360.this.mContext, "sysq_save_account_fail"));
                }
            }
        }, 500L);
    }

    @Override // com.sy37sdk.account.view.IRegSuccessDialog
    public void setEnterGameListener(IRegSuccessDialog.EnterGameListener enterGameListener) {
        this.enterGameListener = enterGameListener;
    }
}
