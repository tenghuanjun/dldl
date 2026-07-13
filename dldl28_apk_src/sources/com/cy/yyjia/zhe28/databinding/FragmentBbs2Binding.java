package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentBbs2Binding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final ImageView ivEdit;
    public final ImageView ivRefresh;

    @Bindable
    protected BbsDetailBean mData;

    @Bindable
    protected float mFold;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final RecyclerView rvBbs;
    public final RecyclerView rvTop;
    public final TabLayout tab;
    public final Toolbar toolbar;
    public final TextView tvName;
    public final ImageView tvSearch;
    public final TextView tvSign;
    public final TextView tvViews;

    public abstract void setData(BbsDetailBean data);

    public abstract void setFold(float fold);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentBbs2Binding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, ImageView ivEdit, ImageView ivRefresh, RecyclerView rvBbs, RecyclerView rvTop, TabLayout tab, Toolbar toolbar, TextView tvName, ImageView tvSearch, TextView tvSign, TextView tvViews) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.ivEdit = ivEdit;
        this.ivRefresh = ivRefresh;
        this.rvBbs = rvBbs;
        this.rvTop = rvTop;
        this.tab = tab;
        this.toolbar = toolbar;
        this.tvName = tvName;
        this.tvSearch = tvSearch;
        this.tvSign = tvSign;
        this.tvViews = tvViews;
    }

    public float getFold() {
        return this.mFold;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public BbsDetailBean getData() {
        return this.mData;
    }

    public static FragmentBbs2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbs2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentBbs2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs2, root, attachToRoot, component);
    }

    public static FragmentBbs2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbs2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentBbs2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs2, null, false, component);
    }

    public static FragmentBbs2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbs2Binding bind(View view, Object component) {
        return (FragmentBbs2Binding) bind(component, view, R.layout.fragment_bbs2);
    }
}
