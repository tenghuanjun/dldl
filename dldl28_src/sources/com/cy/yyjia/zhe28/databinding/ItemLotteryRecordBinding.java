package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.LotteryRecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemLotteryRecordBinding extends ViewDataBinding {

    @Bindable
    protected LotteryRecordBean mData;

    public abstract void setData(LotteryRecordBean data);

    protected ItemLotteryRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LotteryRecordBean getData() {
        return this.mData;
    }

    public static ItemLotteryRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemLotteryRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_record, root, attachToRoot, component);
    }

    public static ItemLotteryRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemLotteryRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_lottery_record, null, false, component);
    }

    public static ItemLotteryRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLotteryRecordBinding bind(View view, Object component) {
        return (ItemLotteryRecordBinding) bind(component, view, R.layout.item_lottery_record);
    }
}
