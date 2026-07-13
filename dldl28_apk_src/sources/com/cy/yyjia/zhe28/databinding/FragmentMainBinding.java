package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentMainBinding extends ViewDataBinding {
    public final View bg;
    public final ImageView ivQiandao;
    public final ImageView ivSearch;
    public final LinearLayout ll;

    @Bindable
    protected boolean mWhite;
    public final RelativeLayout rl;
    public final RecyclerView rv;
    public final ViewPager2 vp;

    public abstract void setWhite(boolean white);

    protected FragmentMainBinding(Object _bindingComponent, View _root, int _localFieldCount, View bg, ImageView ivQiandao, ImageView ivSearch, LinearLayout ll, RelativeLayout rl, RecyclerView rv, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.ivQiandao = ivQiandao;
        this.ivSearch = ivSearch;
        this.ll = ll;
        this.rl = rl;
        this.rv = rv;
        this.vp = vp;
    }

    public boolean getWhite() {
        return this.mWhite;
    }

    public static FragmentMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_main, root, attachToRoot, component);
    }

    public static FragmentMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMainBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_main, null, false, component);
    }

    public static FragmentMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMainBinding bind(View view, Object component) {
        return (FragmentMainBinding) bind(component, view, R.layout.fragment_main);
    }
}
