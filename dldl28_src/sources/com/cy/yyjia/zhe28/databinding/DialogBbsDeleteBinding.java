package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogBbsDeleteBinding implements ViewBinding {
    private final ShapeLinearLayout rootView;
    public final TextView tv1;
    public final TextView tv2;

    private DialogBbsDeleteBinding(ShapeLinearLayout rootView, TextView tv1, TextView tv2) {
        this.rootView = rootView;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogBbsDeleteBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogBbsDeleteBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_bbs_delete, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogBbsDeleteBinding bind(View rootView) {
        int i = R.id.tv1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv1);
        if (textView != null) {
            i = R.id.tv2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv2);
            if (textView2 != null) {
                return new DialogBbsDeleteBinding((ShapeLinearLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
