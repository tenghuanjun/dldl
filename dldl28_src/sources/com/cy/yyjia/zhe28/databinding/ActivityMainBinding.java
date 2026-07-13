package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMainBinding extends ViewDataBinding {
    public final ImageView ivService;
    public final LinearLayout llHome;
    public final LinearLayout llTab;

    @Bindable
    protected int mPosition;
    public final AppCompatTextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvUnread;
    public final ViewPager2 vp;

    public abstract void setPosition(int position);

    protected ActivityMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivService, LinearLayout llHome, LinearLayout llTab, AppCompatTextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5, TextView tvUnread, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivService = ivService;
        this.llHome = llHome;
        this.llTab = llTab;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvUnread = tvUnread;
        this.vp = vp;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main, root, attachToRoot, component);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main, null, false, component);
    }

    public static ActivityMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding bind(View view, Object component) {
        return (ActivityMainBinding) bind(component, view, R.layout.activity_main);
    }
}
