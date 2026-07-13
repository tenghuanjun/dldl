package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHomeNewBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final LinearLayout ll;
    public final LinearLayout llSearch;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final RecyclerView rvType;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f460tv;
    public final ImageView tvQiandao;
    public final ShapeLinearLayout tvSearch;
    public final AdapterViewFlipper vf;
    public final ViewPager2 vp;

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentHomeNewBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, LinearLayout ll, LinearLayout llSearch, RecyclerView rvType, TextView tv2, ImageView tvQiandao, ShapeLinearLayout tvSearch, AdapterViewFlipper vf, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.ll = ll;
        this.llSearch = llSearch;
        this.rvType = rvType;
        this.f460tv = tv2;
        this.tvQiandao = tvQiandao;
        this.tvSearch = tvSearch;
        this.vf = vf;
        this.vp = vp;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public static FragmentHomeNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_new, root, attachToRoot, component);
    }

    public static FragmentHomeNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeNewBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_new, null, false, component);
    }

    public static FragmentHomeNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeNewBinding bind(View view, Object component) {
        return (FragmentHomeNewBinding) bind(component, view, R.layout.fragment_home_new);
    }
}
