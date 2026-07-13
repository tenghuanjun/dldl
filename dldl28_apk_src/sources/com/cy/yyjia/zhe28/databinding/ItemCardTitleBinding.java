package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CardInfoBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemCardTitleBinding extends ViewDataBinding {

    @Bindable
    protected CardInfoBean mData;

    public abstract void setData(CardInfoBean data);

    protected ItemCardTitleBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CardInfoBean getData() {
        return this.mData;
    }

    public static ItemCardTitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardTitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCardTitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_title, root, attachToRoot, component);
    }

    public static ItemCardTitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardTitleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCardTitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_title, null, false, component);
    }

    public static ItemCardTitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardTitleBinding bind(View view, Object component) {
        return (ItemCardTitleBinding) bind(component, view, R.layout.item_card_title);
    }
}
