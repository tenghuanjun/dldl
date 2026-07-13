package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogModifyNickBinding implements ViewBinding {
    public final ShapeEditText et;
    private final ShapeLinearLayout rootView;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f450tv;

    private DialogModifyNickBinding(ShapeLinearLayout rootView, ShapeEditText et, TextView tv2) {
        this.rootView = rootView;
        this.et = et;
        this.f450tv = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogModifyNickBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogModifyNickBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_modify_nick, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogModifyNickBinding bind(View rootView) {
        int i = R.id.et;
        ShapeEditText shapeEditText = (ShapeEditText) ViewBindings.findChildViewById(rootView, R.id.et);
        if (shapeEditText != null) {
            i = R.id.f438tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.f438tv);
            if (textView != null) {
                return new DialogModifyNickBinding((ShapeLinearLayout) rootView, shapeEditText, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
