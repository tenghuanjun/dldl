package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeRelativeLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogCancellationTipsBinding implements ViewBinding {
    private final ShapeRelativeLayout rootView;
    public final TextView tvCancel;
    public final ShapeTextView tvGo;

    private DialogCancellationTipsBinding(ShapeRelativeLayout rootView, TextView tvCancel, ShapeTextView tvGo) {
        this.rootView = rootView;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeRelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogCancellationTipsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogCancellationTipsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_cancellation_tips, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCancellationTipsBinding bind(View rootView) {
        int i = R.id.tv_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
        if (textView != null) {
            i = R.id.tv_go;
            ShapeTextView shapeTextView = (ShapeTextView) ViewBindings.findChildViewById(rootView, R.id.tv_go);
            if (shapeTextView != null) {
                return new DialogCancellationTipsBinding((ShapeRelativeLayout) rootView, textView, shapeTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
