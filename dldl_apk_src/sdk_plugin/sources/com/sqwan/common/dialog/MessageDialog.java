package com.sqwan.common.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MessageDialog extends com.sqwan.common.mvp.BaseDialog implements View.OnClickListener {
    private TextView mCancelView;
    private boolean mClickDismiss;
    private TextView mConfirmView;
    private OnListener mListener;
    private TextView mMessageView;
    private TextView mTitleView;

    public interface OnListener {

        /* JADX INFO: renamed from: com.sqwan.common.dialog.MessageDialog$OnListener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onCancel(OnListener onListener, com.sqwan.common.mvp.BaseDialog baseDialog) {
            }
        }

        void onCancel(com.sqwan.common.mvp.BaseDialog baseDialog);

        void onConfirm(com.sqwan.common.mvp.BaseDialog baseDialog);
    }

    public MessageDialog(Context context) {
        super(context);
        this.mClickDismiss = true;
        setContentView(SqResUtils.getLayoutId(context, "sy37_dialog_message"));
        this.mConfirmView = (TextView) findViewById(SqResUtils.getId(context, "tv_confirm"));
        this.mCancelView = (TextView) findViewById(SqResUtils.getId(context, "tv_cancel"));
        this.mTitleView = (TextView) findViewById(SqResUtils.getId(context, "tv_title"));
        this.mMessageView = (TextView) findViewById(SqResUtils.getId(context, "tv_message"));
        this.mConfirmView.setOnClickListener(this);
        this.mCancelView.setOnClickListener(this);
    }

    public MessageDialog setDialogTitle(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mTitleView.setText(charSequence);
        this.mTitleView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public MessageDialog setDialogMessage(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mMessageView.setText(charSequence);
        this.mMessageView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public MessageDialog setDialogConfirm(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mConfirmView.setText(charSequence);
        this.mConfirmView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public MessageDialog setDialogCancel(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mCancelView.setText(charSequence);
        this.mCancelView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public MessageDialog setDialogListener(OnListener onListener) {
        this.mListener = onListener;
        return this;
    }

    public MessageDialog setClickDismiss(boolean z) {
        this.mClickDismiss = z;
        return this;
    }

    public void performClickDismiss() {
        if (this.mClickDismiss) {
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mConfirmView) {
            performClickDismiss();
            OnListener onListener = this.mListener;
            if (onListener == null) {
                return;
            }
            onListener.onConfirm(this);
            return;
        }
        if (view == this.mCancelView) {
            performClickDismiss();
            OnListener onListener2 = this.mListener;
            if (onListener2 == null) {
                return;
            }
            onListener2.onCancel(this);
        }
    }
}
