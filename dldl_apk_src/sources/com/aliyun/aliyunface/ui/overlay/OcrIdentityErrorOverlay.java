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
public class OcrIdentityErrorOverlay extends FrameLayout {
    private OcrIdentityErrorOverlayListener ocrIdentityErrorOverlayListener;

    public interface OcrIdentityErrorOverlayListener {
        void onClose();

        void onRetry();
    }

    public OcrIdentityErrorOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ocrIdentityErrorOverlayListener = null;
        LayoutInflater.from(context).inflate(R.layout.ocr_section_layout_identity_error, this);
        ImageView imageView = (ImageView) findViewById(R.id.ocr_identity_error_page_close);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.OcrIdentityErrorOverlay.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (OcrIdentityErrorOverlay.this.ocrIdentityErrorOverlayListener != null) {
                        OcrIdentityErrorOverlay.this.setVisibility(4);
                        OcrIdentityErrorOverlay.this.ocrIdentityErrorOverlayListener.onClose();
                    }
                }
            });
        }
        View viewFindViewById = findViewById(R.id.ocr_identity_error_retry);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.OcrIdentityErrorOverlay.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (OcrIdentityErrorOverlay.this.ocrIdentityErrorOverlayListener != null) {
                        OcrIdentityErrorOverlay.this.setVisibility(4);
                        OcrIdentityErrorOverlay.this.ocrIdentityErrorOverlayListener.onRetry();
                    }
                }
            });
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ImageView imageView = (ImageView) findViewById(R.id.img_ocr_identity_take_photo_require);
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (int) (imageView.getWidth() * 0.7717996f);
            imageView.setLayoutParams(layoutParams);
        }
    }

    public void setOcrIdentityErrorOverlayListener(OcrIdentityErrorOverlayListener ocrIdentityErrorOverlayListener) {
        this.ocrIdentityErrorOverlayListener = ocrIdentityErrorOverlayListener;
    }
}
