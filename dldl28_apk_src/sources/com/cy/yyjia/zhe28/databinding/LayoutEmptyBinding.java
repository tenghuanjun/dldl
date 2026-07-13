package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class LayoutEmptyBinding implements ViewBinding {
    public final ImageView iv;
    private final LinearLayout rootView;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f472tv;

    private LayoutEmptyBinding(LinearLayout rootView, ImageView iv, TextView tv2) {
        this.rootView = rootView;
        this.iv = iv;
        this.f472tv = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_empty, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutEmptyBinding bind(View rootView) {
        int i = R.id.iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv);
        if (imageView != null) {
            i = R.id.f438tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.f438tv);
            if (textView != null) {
                return new LayoutEmptyBinding((LinearLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
