package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsMessageOfficialBinding extends ViewDataBinding {

    @Bindable
    protected BbsBean mData;

    public abstract void setData(BbsBean data);

    protected ItemBbsMessageOfficialBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ItemBbsMessageOfficialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageOfficialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsMessageOfficialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_message_official, root, attachToRoot, component);
    }

    public static ItemBbsMessageOfficialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageOfficialBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsMessageOfficialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_message_official, null, false, component);
    }

    public static ItemBbsMessageOfficialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageOfficialBinding bind(View view, Object component) {
        return (ItemBbsMessageOfficialBinding) bind(component, view, R.layout.item_bbs_message_official);
    }
}
