package com.aliyun.aliyunface.ui.overlay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrPhotoRequireOverlay extends FrameLayout {
    public OcrPhotoRequireOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.ocr_section_take_photo_require, this);
        ImageView imageView = (ImageView) findViewById(R.id.ocr_take_photo_require_close);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.OcrPhotoRequireOverlay.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrPhotoRequireOverlay.this.setVisibility(4);
                }
            });
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ImageView imageView = (ImageView) findViewById(R.id.img_ocr_take_photo_require);
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (int) (imageView.getWidth() * 0.7717996f);
            imageView.setLayoutParams(layoutParams);
        }
    }
}
