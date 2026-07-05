package com.sy37sdk.order.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayConfirmDialog extends BaseDialog {
    private View headView;
    private View.OnClickListener mNegativeButtonListener;
    private String mNegativeButtonText;
    private View.OnClickListener mPositiveButtonListener;
    private String mPositiveButtonText;
    private String title;

    public PayConfirmDialog(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sysq_pay_tip_dialog"));
        FrameLayout frameLayout = (FrameLayout) findViewById(getIdByName("fl_head_container", SqTrackCommonKey.id));
        TextView textView = (TextView) findViewById(getIdByName("tv_content", SqTrackCommonKey.id));
        TextView textView2 = (TextView) findViewById(getIdByName("tv_confirm", SqTrackCommonKey.id));
        TextView textView3 = (TextView) findViewById(getIdByName("tv_cancel", SqTrackCommonKey.id));
        if (this.headView != null) {
            frameLayout.removeAllViews();
            frameLayout.addView(this.headView, new FrameLayout.LayoutParams(-1, -1));
        }
        textView.setText(this.title);
        textView2.setText(this.mPositiveButtonText);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.view.PayConfirmDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PayConfirmDialog.this.mPositiveButtonListener != null) {
                    PayConfirmDialog.this.mPositiveButtonListener.onClick(view);
                }
                PayConfirmDialog.this.dismiss();
            }
        });
        textView3.setText(this.mNegativeButtonText);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.view.PayConfirmDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PayConfirmDialog.this.mNegativeButtonListener != null) {
                    PayConfirmDialog.this.mNegativeButtonListener.onClick(view);
                }
                PayConfirmDialog.this.dismiss();
            }
        });
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setNegativeButtonText(String str) {
        this.mNegativeButtonText = str;
    }

    public void setPositiveButtonListener(View.OnClickListener onClickListener) {
        this.mPositiveButtonListener = onClickListener;
    }

    public void setNegativeButtonListener(View.OnClickListener onClickListener) {
        this.mNegativeButtonListener = onClickListener;
    }

    public void setPositiveButtonText(String str) {
        this.mPositiveButtonText = str;
    }

    public void setHeadView(View view) {
        this.headView = view;
    }

    public static final class Builder {
        private View headView;
        private boolean mCancelable;
        private Context mContext;
        private View.OnClickListener mNegativeButtonListener;
        private String mNegativeButtonText;
        private View.OnClickListener mPositiveButtonListener;
        private String mPositiveButtonText;
        private String title;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setHeadView(View view) {
            this.headView = view;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setPositiveButton(String str, View.OnClickListener onClickListener) {
            this.mPositiveButtonText = str;
            this.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(String str, View.OnClickListener onClickListener) {
            this.mNegativeButtonText = str;
            this.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public void show() {
            PayConfirmDialog payConfirmDialog = new PayConfirmDialog(this.mContext);
            payConfirmDialog.setTitle(this.title);
            payConfirmDialog.setNegativeButtonText(this.mNegativeButtonText);
            payConfirmDialog.setNegativeButtonListener(this.mNegativeButtonListener);
            payConfirmDialog.setPositiveButtonListener(this.mPositiveButtonListener);
            payConfirmDialog.setPositiveButtonText(this.mPositiveButtonText);
            payConfirmDialog.setCancelable(this.mCancelable);
            payConfirmDialog.setHeadView(this.headView);
            payConfirmDialog.show();
        }
    }
}
