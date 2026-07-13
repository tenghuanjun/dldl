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
public abstract class ItemLotteryGiftResultBinding extends ViewDataBinding {

    @Bindable
    protected LotteryGiftBean mData;

    public abstract void setData(LotteryGiftBean data);

    protected ItemLotteryGiftResultBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LotteryGiftBean getData() {
        return this.mData;
    }

    public static ItemLotteryGiftResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemLotteryGiftResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_gift_result, root, attachToRoot, component);
    }

    public static ItemLotteryGiftResultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftResultBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemLotteryGiftResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_gift_result, null, false, component);
    }

    public static ItemLotteryGiftResultBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryGiftResultBinding bind(View view, Object component) {
        return (ItemLotteryGiftResultBinding) bind(component, view, R.layout.item_lottery_gift_result);
    }
}
