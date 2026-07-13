package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemVipRightBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected VipListBean.RightBean mData;

    public abstract void setData(VipListBean.RightBean data);

    protected ItemVipRightBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public VipListBean.RightBean getData() {
        return this.mData;
    }

    public static ItemVipRightBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipRightBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipRightBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_right, root, attachToRoot, component);
    }

    public static ItemVipRightBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipRightBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipRightBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_right, null, false, component);
    }

    public static ItemVipRightBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipRightBinding bind(View view, Object component) {
        return (ItemVipRightBinding) bind(component, view, R.layout.item_vip_right);
    }
}
