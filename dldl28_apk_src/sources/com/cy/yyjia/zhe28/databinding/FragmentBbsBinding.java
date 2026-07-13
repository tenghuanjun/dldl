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
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentBbsBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final ConvenientBanner banner;
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
    public final RecyclerView rv;
    public final RecyclerView rvBbs;
    public final RecyclerView rvTop;
    public final TabLayout tab;
    public final Toolbar toolbar;
    public final TextView tvName;
    public final ImageView tvSearch;
    public final ImageView tvSign;
    public final TextView tvViews;

    public abstract void setData(BbsDetailBean data);

    public abstract void setFold(float fold);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentBbsBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, ConvenientBanner banner, ImageView ivEdit, ImageView ivRefresh, RecyclerView rv, RecyclerView rvBbs, RecyclerView rvTop, TabLayout tab, Toolbar toolbar, TextView tvName, ImageView tvSearch, ImageView tvSign, TextView tvViews) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.banner = banner;
        this.ivEdit = ivEdit;
        this.ivRefresh = ivRefresh;
        this.rv = rv;
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

    public static FragmentBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs, root, attachToRoot, component);
    }

    public static FragmentBbsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs, null, false, component);
    }

    public static FragmentBbsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsBinding bind(View view, Object component) {
        return (FragmentBbsBinding) bind(component, view, R.layout.fragment_bbs);
    }
}
