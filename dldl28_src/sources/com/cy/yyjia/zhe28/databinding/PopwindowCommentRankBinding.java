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
public final class PopwindowCommentRankBinding implements ViewBinding {
    private final LinearLayout rootView;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f476tv;
    public final TextView tvNumber;
    public final TextView tvTime;

    private PopwindowCommentRankBinding(LinearLayout rootView, TextView tv2, TextView tvNumber, TextView tvTime) {
        this.rootView = rootView;
        this.f476tv = tv2;
        this.tvNumber = tvNumber;
        this.tvTime = tvTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopwindowCommentRankBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopwindowCommentRankBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.popwindow_comment_rank, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopwindowCommentRankBinding bind(View rootView) {
        int i = R.id.f438tv;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.f438tv);
        if (textView != null) {
            i = R.id.tv_number;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_number);
            if (textView2 != null) {
                i = R.id.tv_time;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_time);
                if (textView3 != null) {
                    return new PopwindowCommentRankBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
