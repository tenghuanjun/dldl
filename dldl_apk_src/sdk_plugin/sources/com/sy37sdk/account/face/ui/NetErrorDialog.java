package com.sy37sdk.account.face.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NetErrorDialog extends BaseDialog {
    private Context mContext;
    private OnClickNetListener mOnClickNetListener;
    private TextView tvCustomer;
    private TextView tvRetry;

    public interface OnClickNetListener {
        void clickLeft();

        void clickRight();
    }

    public NetErrorDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_net_error"));
        this.tvCustomer = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_customer"));
        this.tvRetry = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_retry"));
        this.tvCustomer.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.NetErrorDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetErrorDialog.this.dismiss();
                if (NetErrorDialog.this.mOnClickNetListener != null) {
                    NetErrorDialog.this.mOnClickNetListener.clickLeft();
                }
            }
        });
        this.tvRetry.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.NetErrorDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetErrorDialog.this.dismiss();
                if (NetErrorDialog.this.mOnClickNetListener != null) {
                    NetErrorDialog.this.mOnClickNetListener.clickRight();
                }
            }
        });
    }

    public void setOnClickNetListener(OnClickNetListener onClickNetListener) {
        this.mOnClickNetListener = onClickNetListener;
    }
}
