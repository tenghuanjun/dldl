package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogYunExitBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ShapeTextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvNo;

    private DialogYunExitBinding(LinearLayout rootView, ShapeTextView tvCancel, TextView tvConfirm, TextView tvNo) {
        this.rootView = rootView;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvNo = tvNo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogYunExitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogYunExitBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_yun_exit, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogYunExitBinding bind(View rootView) {
        int i = R.id.tv_cancel;
        ShapeTextView shapeTextView = (ShapeTextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
        if (shapeTextView != null) {
            i = R.id.tv_confirm;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm);
            if (textView != null) {
                i = R.id.tv_no;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no);
                if (textView2 != null) {
                    return new DialogYunExitBinding((LinearLayout) rootView, shapeTextView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
