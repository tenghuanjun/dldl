package com.aliyun.aliyunface.ui.overlay;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CommAlertOverlay extends FrameLayout {
    private CommAlertOverlayListener commAlertOverlayListener;
    private boolean hasCancel;
    private Context mContext;

    public interface CommAlertOverlayListener {
        void onCancel();

        void onConfirm();
    }

    public CommAlertOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.commAlertOverlayListener = null;
        this.hasCancel = true;
        this.mContext = context;
        LayoutInflater.from(context).inflate(getLayout("comm_alert_layout"), this);
        View viewFindViewById = findViewById(getId("comm_alert_cancel"));
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CommAlertOverlay.this.commAlertOverlayListener != null) {
                        CommAlertOverlay.this.commAlertOverlayListener.onCancel();
                        CommAlertOverlay.this.setVisibility(4);
                    }
                }
            });
        }
        View viewFindViewById2 = findViewById(getId("comm_alert_confirm"));
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CommAlertOverlay.this.commAlertOverlayListener != null) {
                        CommAlertOverlay.this.commAlertOverlayListener.onConfirm();
                        CommAlertOverlay.this.setVisibility(4);
                    }
                }
            });
        }
        View viewFindViewById3 = findViewById(getId("comm_alert_confirm1"));
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CommAlertOverlay.this.commAlertOverlayListener != null) {
                        CommAlertOverlay.this.commAlertOverlayListener.onConfirm();
                        CommAlertOverlay.this.setVisibility(4);
                    }
                }
            });
        }
    }

    private int getIdByType(String str, String str2) {
        return getResources().getIdentifier(str, str2, this.mContext.getPackageName());
    }

    private int getId(String str) {
        return getIdByType(str, "id");
    }

    private int getDimension(String str) {
        return getIdByType(str, "dimen");
    }

    private int getLayout(String str) {
        return getIdByType(str, "layout");
    }

    private int getString(String str) {
        return getIdByType(str, "string");
    }

    public void setTitleText(String str, boolean z) {
        TextView textView = (TextView) findViewById(getId("comm_alert_title_text"));
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setMessageText(String str, boolean z) {
        TextView textView = (TextView) findViewById(getId("comm_alert_message_text"));
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setCancelText(String str, boolean z) {
        TextView textView = (TextView) findViewById(getId("comm_alert_cancel"));
        if (textView != null) {
            if (z) {
                textView.setText(Html.fromHtml(str));
            } else {
                textView.setText(str);
            }
        }
    }

    public void setConfirmText(String str, boolean z) {
        if (this.hasCancel) {
            TextView textView = (TextView) findViewById(getId("comm_alert_confirm"));
            if (textView != null) {
                if (z) {
                    textView.setText(Html.fromHtml(str));
                    return;
                } else {
                    textView.setText(str);
                    return;
                }
            }
            return;
        }
        TextView textView2 = (TextView) findViewById(getId("comm_alert_confirm1"));
        if (textView2 != null) {
            if (z) {
                textView2.setText(Html.fromHtml(str));
            } else {
                textView2.setText(str);
            }
        }
    }

    public void setButtonType(boolean z) {
        View viewFindViewById = findViewById(getId("comm_alert_button_1"));
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z ? 4 : 0);
        }
        View viewFindViewById2 = findViewById(getId("comm_alert_button_2"));
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(z ? 0 : 4);
        }
        this.hasCancel = z;
    }

    public void setCommAlertOverlayListener(CommAlertOverlayListener commAlertOverlayListener) {
        this.commAlertOverlayListener = commAlertOverlayListener;
    }
}
