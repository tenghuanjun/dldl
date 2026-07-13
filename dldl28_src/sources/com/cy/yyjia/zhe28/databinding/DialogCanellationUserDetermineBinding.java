package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogCanellationUserDetermineBinding implements ViewBinding {
    private final ShapeLinearLayout rootView;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;

    private DialogCanellationUserDetermineBinding(ShapeLinearLayout rootView, ShapeTextView tvCancel, ShapeTextView tvGo) {
        this.rootView = rootView;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogCanellationUserDetermineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogCanellationUserDetermineBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_canellation_user_determine, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCanellationUserDetermineBinding bind(View rootView) {
        int i = R.id.tv_cancel;
        ShapeTextView shapeTextView = (ShapeTextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
        if (shapeTextView != null) {
            i = R.id.tv_go;
            ShapeTextView shapeTextView2 = (ShapeTextView) ViewBindings.findChildViewById(rootView, R.id.tv_go);
            if (shapeTextView2 != null) {
                return new DialogCanellationUserDetermineBinding((ShapeLinearLayout) rootView, shapeTextView, shapeTextView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
