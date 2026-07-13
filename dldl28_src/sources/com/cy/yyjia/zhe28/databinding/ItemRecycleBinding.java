package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecycleListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemRecycleBinding extends ViewDataBinding {
    public final LinearLayout llBox;

    @Bindable
    protected RecycleListBean mData;

    @Bindable
    protected int mMode;
    public final RecyclerView rv;
    public final TextView tvShow;

    public abstract void setData(RecycleListBean data);

    public abstract void setMode(int mode);

    protected ItemRecycleBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llBox, RecyclerView rv, TextView tvShow) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llBox = llBox;
        this.rv = rv;
        this.tvShow = tvShow;
    }

    public int getMode() {
        return this.mMode;
    }

    public RecycleListBean getData() {
        return this.mData;
    }

    public static ItemRecycleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemRecycleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle, root, attachToRoot, component);
    }

    public static ItemRecycleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemRecycleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle, null, false, component);
    }

    public static ItemRecycleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleBinding bind(View view, Object component) {
        return (ItemRecycleBinding) bind(component, view, R.layout.item_recycle);
    }
}
