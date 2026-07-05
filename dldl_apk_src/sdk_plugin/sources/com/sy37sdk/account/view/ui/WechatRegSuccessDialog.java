package com.sy37sdk.account.view.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WechatRegSuccessDialog extends BaseDialog {
    private View close;
    private String pwd;
    private View rootView;
    private TextView tvAccount;
    private TextView tvEnterGame;
    private TextView tvPassword;
    private String uname;
    private IWechatRegListener wechatRegListener;

    public interface IWechatRegListener {
        void onSuccess();
    }

    public WechatRegSuccessDialog(Context context, String str, String str2) {
        super(context);
        this.uname = str;
        this.pwd = str2;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().setTheme(getIdByName("Mdialog", "style"));
        View viewInflate = LayoutInflater.from(getContext()).inflate(getIdByName("sysq_wechat_reg_success", "layout"), (ViewGroup) null);
        this.rootView = viewInflate;
        setContentView(viewInflate);
        initView();
    }

    private void initView() {
        this.tvAccount = (TextView) findViewById(getIdByName("tv_account", SqTrackCommonKey.id));
        this.tvPassword = (TextView) findViewById(getIdByName("tv_password", SqTrackCommonKey.id));
        this.tvEnterGame = (TextView) findViewById(getIdByName("tv_enter_game", SqTrackCommonKey.id));
        this.close = findViewById(getIdByName("view_close", SqTrackCommonKey.id));
        initAction();
        initData();
        setCanceledOnTouchOutside(false);
    }

    private void initAction() {
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sy37sdk.account.view.ui.WechatRegSuccessDialog.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (WechatRegSuccessDialog.this.wechatRegListener != null) {
                    WechatRegSuccessDialog.this.wechatRegListener.onSuccess();
                }
            }
        });
        this.tvEnterGame.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.ui.WechatRegSuccessDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatRegSuccessDialog.this.dismiss();
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.ui.WechatRegSuccessDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WechatRegSuccessDialog.this.dismiss();
            }
        });
    }

    public void initData() {
        this.tvAccount.setText(this.uname);
        this.tvPassword.setText(this.pwd);
    }

    public void setWechatRegListener(IWechatRegListener iWechatRegListener) {
        this.wechatRegListener = iWechatRegListener;
    }
}
