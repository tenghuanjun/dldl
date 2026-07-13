package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDealSellBinding extends ViewDataBinding {
    public final ImageView ivIntro;
    public final ImageView ivRecord;
    public final RecyclerView rv;

    protected FragmentDealSellBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIntro, ImageView ivRecord, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIntro = ivIntro;
        this.ivRecord = ivRecord;
        this.rv = rv;
    }

    public static FragmentDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_sell, root, attachToRoot, component);
    }

    public static FragmentDealSellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealSellBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_sell, null, false, component);
    }

    public static FragmentDealSellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealSellBinding bind(View view, Object component) {
        return (FragmentDealSellBinding) bind(component, view, R.layout.fragment_deal_sell);
    }
}
