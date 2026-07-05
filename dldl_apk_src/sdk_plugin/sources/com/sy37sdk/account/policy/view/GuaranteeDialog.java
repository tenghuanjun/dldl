package com.sy37sdk.account.policy.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class GuaranteeDialog extends BaseDialog {
    private String tip;
    private TextView tvSure;
    private TextView tvTip;

    public GuaranteeDialog(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sysq_guarantee_dialog", "layout"));
        initView();
    }

    private void initView() {
        this.tvTip = (TextView) findViewById(getIdByName("tv_tip", SqTrackCommonKey.id));
        this.tvSure = (TextView) findViewById(getIdByName("tv_sure", SqTrackCommonKey.id));
        this.tvTip.setText(TextUtils.isEmpty(this.tip) ? "" : this.tip);
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.policy.view.GuaranteeDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GuaranteeDialog.this.dismiss();
                ((Activity) GuaranteeDialog.this.mContext).finish();
                System.exit(0);
            }
        });
    }

    public void setTip(String str) {
        this.tip = str;
    }
}
