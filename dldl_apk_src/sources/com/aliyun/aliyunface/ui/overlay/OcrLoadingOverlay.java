package com.aliyun.aliyunface.ui.overlay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrLoadingOverlay extends FrameLayout {
    public OcrLoadingOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.ocr_section_layout_loading, this);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(2000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        ImageView imageView = (ImageView) findViewById(R.id.img_ocr_loading);
        if (imageView != null) {
            imageView.setAnimation(rotateAnimation);
        }
    }

    public void setLoadingText(String str) {
        TextView textView = (TextView) findViewById(R.id.ocr_loading_tips);
        if (textView != null) {
            textView.setText(str);
        }
    }
}
