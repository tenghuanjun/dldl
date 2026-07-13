package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemCardRewardRecordBinding extends ViewDataBinding {

    @Bindable
    protected boolean mCard;

    @Bindable
    protected RecordBean mData;

    public abstract void setCard(boolean card);

    public abstract void setData(RecordBean data);

    protected ItemCardRewardRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RecordBean getData() {
        return this.mData;
    }

    public boolean getCard() {
        return this.mCard;
    }

    public static ItemCardRewardRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardRewardRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCardRewardRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_reward_record, root, attachToRoot, component);
    }

    public static ItemCardRewardRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardRewardRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCardRewardRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_card_reward_record, null, false, component);
    }

    public static ItemCardRewardRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCardRewardRecordBinding bind(View view, Object component) {
        return (ItemCardRewardRecordBinding) bind(component, view, R.layout.item_card_reward_record);
    }
}
