package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class A1ItemDealPhoto2Binding implements ViewBinding {
    public final ImageView iv;
    private final ConstraintLayout rootView;

    private A1ItemDealPhoto2Binding(ConstraintLayout rootView, ImageView iv) {
        this.rootView = rootView;
        this.iv = iv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static A1ItemDealPhoto2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static A1ItemDealPhoto2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.a1_item_deal_photo2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static A1ItemDealPhoto2Binding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv);
        if (imageView != null) {
            return new A1ItemDealPhoto2Binding((ConstraintLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.iv)));
    }
}
