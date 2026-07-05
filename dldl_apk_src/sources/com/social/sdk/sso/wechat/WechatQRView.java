package com.social.sdk.sso.wechat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.social.sdk.common.util.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class WechatQRView extends RelativeLayout {
    private ProgressBar progressBar;
    private ImageView qr;
    private TextView tip;

    public WechatQRView(Context context) {
        this(context, null);
    }

    public WechatQRView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WechatQRView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        layoutParams.setMargins(DensityUtil.dip2px(getContext(), 50.0f), 0, DensityUtil.dip2px(getContext(), 50.0f), 0);
        addView(linearLayout, layoutParams);
        this.qr = new ImageView(getContext());
        linearLayout.addView(this.qr, new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(getContext(), 200.0f)));
        TextView textView = new TextView(getContext());
        this.tip = textView;
        textView.setText("请用手机微信扫描二维码登录");
        this.tip.setTextSize(18.0f);
        this.tip.setGravity(17);
        this.tip.setTextColor(Color.parseColor("#0080FF"));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, DensityUtil.dip2px(getContext(), 10.0f), 0, 0);
        this.tip.setVisibility(8);
        linearLayout.addView(this.tip, layoutParams2);
        this.progressBar = new ProgressBar(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        this.tip.setVisibility(8);
        addView(this.progressBar, layoutParams3);
        this.qr.post(new Runnable() { // from class: com.social.sdk.sso.wechat.WechatQRView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup.LayoutParams layoutParams4 = WechatQRView.this.qr.getLayoutParams();
                layoutParams4.height = WechatQRView.this.qr.getWidth();
                WechatQRView.this.qr.setLayoutParams(layoutParams4);
            }
        });
    }

    public void setQRCode(Bitmap bitmap) {
        ImageView imageView = this.qr;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setTipVisible(boolean z) {
        TextView textView = this.tip;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    public void setProgressVisible(boolean z) {
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(z ? 0 : 8);
        }
    }
}
