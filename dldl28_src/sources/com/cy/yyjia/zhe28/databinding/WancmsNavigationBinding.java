package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class WancmsNavigationBinding implements ViewBinding {
    public final ConstraintLayout bg;
    public final ImageView ivBack;
    public final ImageView ivMore;
    public final TextView navigationTitle;
    private final ConstraintLayout rootView;
    public final TextView tvMore;

    private WancmsNavigationBinding(ConstraintLayout rootView, ConstraintLayout bg, ImageView ivBack, ImageView ivMore, TextView navigationTitle, TextView tvMore) {
        this.rootView = rootView;
        this.bg = bg;
        this.ivBack = ivBack;
        this.ivMore = ivMore;
        this.navigationTitle = navigationTitle;
        this.tvMore = tvMore;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static WancmsNavigationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WancmsNavigationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.wancms_navigation, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WancmsNavigationBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
        if (imageView != null) {
            i = R.id.iv_more;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_more);
            if (imageView2 != null) {
                i = R.id.navigation_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.navigation_title);
                if (textView != null) {
                    i = R.id.tv_more;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_more);
                    if (textView2 != null) {
                        return new WancmsNavigationBinding(constraintLayout, constraintLayout, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
