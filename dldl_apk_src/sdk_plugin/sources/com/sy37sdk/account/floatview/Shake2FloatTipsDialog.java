package com.sy37sdk.account.floatview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.social.sdk.common.util.DensityUtil;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.util.SpanUtil;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Shake2FloatTipsDialog extends Dialog {
    public Shake2FloatTipsDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "CustomDialog"));
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sy37_dialog_shake2floattips"));
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = DisplayUtil.dip2px(getContext(), 280.0f);
            window.setAttributes(attributes);
        }
        View decorView = window != null ? window.getDecorView() : null;
        if (decorView != null) {
            decorView.setSystemUiVisibility(5894);
        }
        final LinearLayout linearLayout = (LinearLayout) findViewById(SqResUtils.getId(getContext(), "ll_float_shake_status"));
        int iDip2px = DensityUtil.dip2px(getContext(), 15.0f);
        ((TextView) findViewById(SqResUtils.getId(getContext(), "tv_tips"))).setText(SpanUtil.concat(SpanUtil.getFontString("摇1秒", iDip2px, Color.parseColor("#FFA100"), true), SpanUtil.getFontString(" 显示悬浮球", iDip2px, -16777216, true)));
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.Shake2FloatTipsDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                linearLayout.setSelected(!r2.isSelected());
            }
        });
        findViewById(SqResUtils.getId(getContext(), "btnOk")).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.Shake2FloatTipsDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewUtils.setShankeTips(Shake2FloatTipsDialog.this.getContext(), !linearLayout.isSelected());
                Shake2FloatTipsDialog.this.dismiss();
            }
        });
    }
}
