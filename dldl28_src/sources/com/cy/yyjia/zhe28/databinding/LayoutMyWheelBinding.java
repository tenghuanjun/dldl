package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public final class LayoutMyWheelBinding implements ViewBinding {
    public final WheelView day;
    public final WheelView hour;
    public final ImageView iv;
    public final WheelView min;
    public final WheelView month;
    private final ShapeLinearLayout rootView;
    public final RelativeLayout rvTopbar;
    public final WheelView second;
    public final LinearLayout timepicker;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f474tv;
    public final TextView tvTitle;
    public final WheelView year;

    private LayoutMyWheelBinding(ShapeLinearLayout rootView, WheelView day, WheelView hour, ImageView iv, WheelView min, WheelView month, RelativeLayout rvTopbar, WheelView second, LinearLayout timepicker, TextView tv2, TextView tvTitle, WheelView year) {
        this.rootView = rootView;
        this.day = day;
        this.hour = hour;
        this.iv = iv;
        this.min = min;
        this.month = month;
        this.rvTopbar = rvTopbar;
        this.second = second;
        this.timepicker = timepicker;
        this.f474tv = tv2;
        this.tvTitle = tvTitle;
        this.year = year;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMyWheelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMyWheelBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_my_wheel, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMyWheelBinding bind(View rootView) {
        int i = R.id.day;
        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.day);
        if (wheelView != null) {
            i = R.id.hour;
            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.hour);
            if (wheelView2 != null) {
                i = R.id.iv;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv);
                if (imageView != null) {
                    i = R.id.min;
                    WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.min);
                    if (wheelView3 != null) {
                        i = R.id.month;
                        WheelView wheelView4 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.month);
                        if (wheelView4 != null) {
                            i = R.id.rv_topbar;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.rv_topbar);
                            if (relativeLayout != null) {
                                i = R.id.second;
                                WheelView wheelView5 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.second);
                                if (wheelView5 != null) {
                                    i = R.id.timepicker;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.timepicker);
                                    if (linearLayout != null) {
                                        i = R.id.f438tv;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.f438tv);
                                        if (textView != null) {
                                            i = R.id.tvTitle;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvTitle);
                                            if (textView2 != null) {
                                                i = R.id.year;
                                                WheelView wheelView6 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.year);
                                                if (wheelView6 != null) {
                                                    return new LayoutMyWheelBinding((ShapeLinearLayout) rootView, wheelView, wheelView2, imageView, wheelView3, wheelView4, relativeLayout, wheelView5, linearLayout, textView, textView2, wheelView6);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
