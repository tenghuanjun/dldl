package com.sqwan.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonAlertDialog extends Dialog {
    protected View ViewDivider;
    protected Context mContext;
    protected View.OnClickListener mNegativeButtonListener;
    protected CharSequence mNegativeButtonText;
    protected View.OnClickListener mPositiveButtonListener;
    protected CharSequence mPositiveButtonText;
    protected CharSequence message;
    protected CharSequence title;
    protected TextView tvCancel;
    protected TextView tvEnsure;
    protected TextView tvTitle;

    public CommonAlertDialog(Context context) {
        this(context, SqResUtils.getStyleId(context, "CustomDialog"));
    }

    public CommonAlertDialog(Context context, int i) {
        super(context, i);
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sy37_common_alert_dialog"));
        this.tvTitle = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_title"));
        this.tvEnsure = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_ensure"));
        this.tvCancel = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_cancel"));
        this.ViewDivider = findViewById(SqResUtils.getId(getContext(), "v_divider_h"));
        this.tvTitle.setText(this.title);
        this.tvEnsure.setText(this.mPositiveButtonText);
        this.tvEnsure.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.CommonAlertDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonAlertDialog.this.mPositiveButtonListener != null) {
                    CommonAlertDialog.this.mPositiveButtonListener.onClick(view);
                }
                CommonAlertDialog.this.dismiss();
            }
        });
        this.tvCancel.setText(this.mNegativeButtonText);
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.CommonAlertDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonAlertDialog.this.mNegativeButtonListener != null) {
                    CommonAlertDialog.this.mNegativeButtonListener.onClick(view);
                }
                CommonAlertDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.title = charSequence;
    }

    public void setMessage(CharSequence charSequence) {
        this.message = charSequence;
    }

    public void setNegativeButtonText(CharSequence charSequence) {
        this.mNegativeButtonText = charSequence;
    }

    public void setPositiveButtonListener(View.OnClickListener onClickListener) {
        this.mPositiveButtonListener = onClickListener;
    }

    public void setNegativeButtonListener(View.OnClickListener onClickListener) {
        this.mNegativeButtonListener = onClickListener;
    }

    public void setPositiveButtonText(CharSequence charSequence) {
        this.mPositiveButtonText = charSequence;
    }

    public static final class Builder {
        private boolean mCancelable;
        private boolean mCanceledOnTouchOutside;
        private Context mContext;
        private View.OnClickListener mNegativeButtonListener;
        private CharSequence mNegativeButtonText;
        private View.OnClickListener mPositiveButtonListener;
        private CharSequence mPositiveButtonText;
        private CharSequence message;
        private CharSequence title;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, View.OnClickListener onClickListener) {
            this.mPositiveButtonText = charSequence;
            this.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, View.OnClickListener onClickListener) {
            this.mNegativeButtonText = charSequence;
            this.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setmCanceledOnTouchOutside(boolean z) {
            this.mCanceledOnTouchOutside = z;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public Builder set(boolean z) {
            this.mCanceledOnTouchOutside = z;
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.message = charSequence;
            return this;
        }

        public void show() {
            CommonAlertDialog commonAlertDialog = new CommonAlertDialog(this.mContext);
            commonAlertDialog.setTitle(this.title);
            commonAlertDialog.setNegativeButtonText(this.mNegativeButtonText);
            commonAlertDialog.setNegativeButtonListener(this.mNegativeButtonListener);
            commonAlertDialog.setPositiveButtonListener(this.mPositiveButtonListener);
            commonAlertDialog.setPositiveButtonText(this.mPositiveButtonText);
            commonAlertDialog.setCancelable(this.mCancelable);
            commonAlertDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
            commonAlertDialog.show();
        }

        public Dialog showEx() {
            CommonAlertExDialog commonAlertExDialog = new CommonAlertExDialog(this.mContext);
            commonAlertExDialog.setTitle(this.title);
            commonAlertExDialog.setMessage(this.message);
            commonAlertExDialog.setNegativeButtonText(this.mNegativeButtonText);
            commonAlertExDialog.setNegativeButtonListener(this.mNegativeButtonListener);
            commonAlertExDialog.setPositiveButtonListener(this.mPositiveButtonListener);
            commonAlertExDialog.setPositiveButtonText(this.mPositiveButtonText);
            commonAlertExDialog.setCancelable(this.mCancelable);
            commonAlertExDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
            commonAlertExDialog.show();
            return commonAlertExDialog;
        }
    }
}
