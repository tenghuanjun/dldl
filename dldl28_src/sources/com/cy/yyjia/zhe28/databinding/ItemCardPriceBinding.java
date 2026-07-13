package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CardInfoBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemCardPriceBinding extends ViewDataBinding {
    public final LinearLayout ll1;

    @Bindable
    protected CardInfoBean.Price mData;

    public abstract void setData(CardInfoBean.Price data);

    protected ItemCardPriceBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll1) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll1 = ll1;
    }

    public CardInfoBean.Price getData() {
        return this.mData;
    }

    public static ItemCardPriceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardPriceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCardPriceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_price, root, attachToRoot, component);
    }

    public static ItemCardPriceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardPriceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCardPriceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_price, null, false, component);
    }

    public static ItemCardPriceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardPriceBinding bind(View view, Object component) {
        return (ItemCardPriceBinding) bind(component, view, R.layout.item_card_price);
    }
}
