package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogFreeTipBinding implements ViewBinding {
    public final TextView btn;
    private final LinearLayout rootView;
    public final TextView tvContent;
    public final TextView tvTitle;

    private DialogFreeTipBinding(LinearLayout rootView, TextView btn, TextView tvContent, TextView tvTitle) {
        this.rootView = rootView;
        this.btn = btn;
        this.tvContent = tvContent;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogFreeTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogFreeTipBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_free_tip, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogFreeTipBinding bind(View rootView) {
        int i = R.id.btn;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn);
        if (textView != null) {
            i = R.id.tv_content;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
            if (textView2 != null) {
                i = R.id.tv_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                if (textView3 != null) {
                    return new DialogFreeTipBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
