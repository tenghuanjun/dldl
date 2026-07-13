package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.LotteryGiftBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemLotteryGiftBinding extends ViewDataBinding {

    @Bindable
    protected LotteryGiftBean mData;

    public abstract void setData(LotteryGiftBean data);

    protected ItemLotteryGiftBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LotteryGiftBean getData() {
        return this.mData;
    }

    public static ItemLotteryGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemLotteryGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_gift, root, attachToRoot, component);
    }

    public static ItemLotteryGiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemLotteryGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_gift, null, false, component);
    }

    public static ItemLotteryGiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftBinding bind(View view, Object component) {
        return (ItemLotteryGiftBinding) bind(component, view, R.layout.item_lottery_gift);
    }
}
