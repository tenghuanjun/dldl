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
public final class DialogDealDoloBinding implements ViewBinding {
    public final LinearLayout btnBuy;
    public final LinearLayout btnRecycle;
    public final ImageView ivClose;
    private final LinearLayout rootView;
    public final TextView tvRule;

    private DialogDealDoloBinding(LinearLayout rootView, LinearLayout btnBuy, LinearLayout btnRecycle, ImageView ivClose, TextView tvRule) {
        this.rootView = rootView;
        this.btnBuy = btnBuy;
        this.btnRecycle = btnRecycle;
        this.ivClose = ivClose;
        this.tvRule = tvRule;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogDealDoloBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDealDoloBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_deal_dolo, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDealDoloBinding bind(View rootView) {
        int i = R.id.btn_buy;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_buy);
        if (linearLayout != null) {
            i = R.id.btn_recycle;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_recycle);
            if (linearLayout2 != null) {
                i = R.id.iv_close;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_close);
                if (imageView != null) {
                    i = R.id.tv_rule;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_rule);
                    if (textView != null) {
                        return new DialogDealDoloBinding((LinearLayout) rootView, linearLayout, linearLayout2, imageView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
