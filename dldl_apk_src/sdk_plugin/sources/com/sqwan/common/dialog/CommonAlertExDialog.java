package com.sqwan.common.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.SpanUtil;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ViewUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonAlertExDialog extends CommonAlertDialog {
    public CommonAlertExDialog(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.dialog.CommonAlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.tvTitle.setGravity(17);
        ViewGroup.LayoutParams layoutParams = this.tvTitle.getLayoutParams();
        layoutParams.width = -1;
        this.tvTitle.setLayoutParams(layoutParams);
        if (TextUtils.isEmpty(this.mNegativeButtonText)) {
            ViewUtils.gone(this.ViewDivider);
            ViewUtils.gone(this.tvCancel);
        }
        if (TextUtils.isEmpty(this.mPositiveButtonText)) {
            ViewUtils.gone(this.ViewDivider);
            ViewUtils.gone(this.tvEnsure);
        }
        this.tvTitle.setText(SpanUtil.concat(SpanUtil.getFontString(((Object) this.title) + "\n\n", DensityUtil.dip2px(this.mContext, 18.0f), Color.parseColor("#ff333333"), true), SpanUtil.getFontString(this.message.toString(), DensityUtil.dip2px(this.mContext, 16.0f), Color.parseColor("#ff333333"), false)));
    }

    @Override // android.app.Dialog
    public void show() {
        if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isFinishing()) {
            return;
        }
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }
}
