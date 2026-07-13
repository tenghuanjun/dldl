package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SearchHistory;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSearchHistoryBinding extends ViewDataBinding {

    @Bindable
    protected SearchHistory mData;

    public abstract void setData(SearchHistory data);

    protected ItemSearchHistoryBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public SearchHistory getData() {
        return this.mData;
    }

    public static ItemSearchHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSearchHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_history, root, attachToRoot, component);
    }

    public static ItemSearchHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHistoryBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSearchHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_history, null, false, component);
    }

    public static ItemSearchHistoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHistoryBinding bind(View view, Object component) {
        return (ItemSearchHistoryBinding) bind(component, view, R.layout.item_search_history);
    }
}
