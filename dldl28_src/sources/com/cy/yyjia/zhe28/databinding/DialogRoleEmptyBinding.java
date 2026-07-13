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
public final class DialogRoleEmptyBinding implements ViewBinding {
    public final TextView btn;
    public final TextView dismiss;
    private final ShapeLinearLayout rootView;
    public final TextView title;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f452tv;

    private DialogRoleEmptyBinding(ShapeLinearLayout rootView, TextView btn, TextView dismiss, TextView title, TextView tv2) {
        this.rootView = rootView;
        this.btn = btn;
        this.dismiss = dismiss;
        this.title = title;
        this.f452tv = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogRoleEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogRoleEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_role_empty, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogRoleEmptyBinding bind(View rootView) {
        int i = R.id.btn;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn);
        if (textView != null) {
            i = R.id.dismiss;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.dismiss);
            if (textView2 != null) {
                i = R.id.title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                if (textView3 != null) {
                    i = R.id.f438tv;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.f438tv);
                    if (textView4 != null) {
                        return new DialogRoleEmptyBinding((ShapeLinearLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
