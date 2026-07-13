package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBbsDetailBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final TextView btn;
    public final CollapsingToolbarLayout ctl;

    @Bindable
    protected BbsBean mData;

    @Bindable
    protected String mText;
    public final LayoutGameNameBinding name;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final RecyclerView rvPic;
    public final TextView tvPraise;
    public final TextView tvSort;

    public abstract void setData(BbsBean data);

    public abstract void setText(String text);

    protected ActivityBbsDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, TextView btn, CollapsingToolbarLayout ctl, LayoutGameNameBinding name, Navigation navigation, RecyclerView rv, RecyclerView rvPic, TextView tvPraise, TextView tvSort) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.btn = btn;
        this.ctl = ctl;
        this.name = name;
        this.navigation = navigation;
        this.rv = rv;
        this.rvPic = rvPic;
        this.tvPraise = tvPraise;
        this.tvSort = tvSort;
    }

    public String getText() {
        return this.mText;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ActivityBbsDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBbsDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_detail, root, attachToRoot, component);
    }

    public static ActivityBbsDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBbsDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_detail, null, false, component);
    }

    public static ActivityBbsDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetailBinding bind(View view, Object component) {
        return (ActivityBbsDetailBinding) bind(component, view, R.layout.activity_bbs_detail);
    }
}
