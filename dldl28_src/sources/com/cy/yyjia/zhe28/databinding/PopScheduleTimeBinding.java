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
public final class PopScheduleTimeBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;

    private PopScheduleTimeBinding(LinearLayout rootView, TextView tv1, TextView tv2, TextView tv3) {
        this.rootView = rootView;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopScheduleTimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopScheduleTimeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.pop_schedule_time, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopScheduleTimeBinding bind(View rootView) {
        int i = R.id.tv1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv1);
        if (textView != null) {
            i = R.id.tv2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv2);
            if (textView2 != null) {
                i = R.id.tv3;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv3);
                if (textView3 != null) {
                    return new PopScheduleTimeBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
