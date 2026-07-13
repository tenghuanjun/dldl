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
public final class ItemCommentPhoto1Binding implements ViewBinding {
    public final ImageView iv;
    public final ImageView ivDelete;
    private final ConstraintLayout rootView;

    private ItemCommentPhoto1Binding(ConstraintLayout rootView, ImageView iv, ImageView ivDelete) {
        this.rootView = rootView;
        this.iv = iv;
        this.ivDelete = ivDelete;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCommentPhoto1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCommentPhoto1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_comment_photo1, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCommentPhoto1Binding bind(View rootView) {
        int i = R.id.iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv);
        if (imageView != null) {
            i = R.id.iv_delete;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_delete);
            if (imageView2 != null) {
                return new ItemCommentPhoto1Binding((ConstraintLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
