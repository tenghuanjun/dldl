package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class IncludeViewpagerBinding implements ViewBinding {
    public final CBLoopViewPager cbLoopViewPager;
    public final LinearLayout loPageTurningPoint;
    private final LinearLayout rootView;

    private IncludeViewpagerBinding(LinearLayout rootView, CBLoopViewPager cbLoopViewPager, LinearLayout loPageTurningPoint) {
        this.rootView = rootView;
        this.cbLoopViewPager = cbLoopViewPager;
        this.loPageTurningPoint = loPageTurningPoint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static IncludeViewpagerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static IncludeViewpagerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.include_viewpager, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static IncludeViewpagerBinding bind(View rootView) {
        int i = R.id.cbLoopViewPager;
        CBLoopViewPager cBLoopViewPager = (CBLoopViewPager) ViewBindings.findChildViewById(rootView, R.id.cbLoopViewPager);
        if (cBLoopViewPager != null) {
            i = R.id.loPageTurningPoint;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.loPageTurningPoint);
            if (linearLayout != null) {
                return new IncludeViewpagerBinding((LinearLayout) rootView, cBLoopViewPager, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
