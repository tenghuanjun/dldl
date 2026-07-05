package com.aliyun.aliyunface.ui.overlay;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrIdentityNetErrorOverlay extends FrameLayout {
    private OcrIdentityNetErrorListener ocrIdentityNetErrorListener;

    public interface OcrIdentityNetErrorListener {
        void onExit();

        void onRetry();
    }

    public OcrIdentityNetErrorOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ocrIdentityNetErrorListener = null;
        LayoutInflater.from(context).inflate(R.layout.ocr_section_layout_identity_net_error, this);
        View viewFindViewById = findViewById(R.id.ocr_alert_exit_identity);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (OcrIdentityNetErrorOverlay.this.ocrIdentityNetErrorListener != null) {
                        OcrIdentityNetErrorOverlay.this.setVisibility(4);
                        OcrIdentityNetErrorOverlay.this.ocrIdentityNetErrorListener.onExit();
                    }
                }
            });
        }
        View viewFindViewById2 = findViewById(R.id.ocr_alert_retry_identitiy);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (OcrIdentityNetErrorOverlay.this.ocrIdentityNetErrorListener != null) {
                        OcrIdentityNetErrorOverlay.this.setVisibility(4);
                        OcrIdentityNetErrorOverlay.this.ocrIdentityNetErrorListener.onRetry();
                    }
                }
            });
        }
    }

    public void setOnNetworkErrorListener(OcrIdentityNetErrorListener ocrIdentityNetErrorListener) {
        this.ocrIdentityNetErrorListener = ocrIdentityNetErrorListener;
    }

    public void setTitleText(String str, boolean z) {
        TextView textView = (TextView) findViewById(R.id.ocr_identity_error_title);
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setMessageText(String str, boolean z) {
        TextView textView = (TextView) findViewById(R.id.ocr_identity_error_message);
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setCancelText(String str, boolean z) {
        TextView textView = (TextView) findViewById(R.id.ocr_alert_exit_identity);
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setConfirmText(String str, boolean z) {
        TextView textView = (TextView) findViewById(R.id.ocr_alert_retry_identitiy);
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }
}
