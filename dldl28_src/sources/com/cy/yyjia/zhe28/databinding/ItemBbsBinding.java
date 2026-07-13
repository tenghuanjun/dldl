package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsBinding extends ViewDataBinding {
    public final ImageView ivDelete;

    @Bindable
    protected boolean mChild;

    @Bindable
    protected BbsBean mData;
    public final RecyclerView rv;
    public final TextView tvPraise;

    public abstract void setChild(boolean child);

    public abstract void setData(BbsBean data);

    protected ItemBbsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivDelete, RecyclerView rv, TextView tvPraise) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivDelete = ivDelete;
        this.rv = rv;
        this.tvPraise = tvPraise;
    }

    public boolean getChild() {
        return this.mChild;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ItemBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs, root, attachToRoot, component);
    }

    public static ItemBbsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs, null, false, component);
    }

    public static ItemBbsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsBinding bind(View view, Object component) {
        return (ItemBbsBinding) bind(component, view, R.layout.item_bbs);
    }
}
