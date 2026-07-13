package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogVipRight2Binding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected VipListBean.RightBean mData;

    public abstract void setData(VipListBean.RightBean data);

    protected DialogVipRight2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public VipListBean.RightBean getData() {
        return this.mData;
    }

    public static DialogVipRight2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipRight2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogVipRight2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_right2, root, attachToRoot, component);
    }

    public static DialogVipRight2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipRight2Binding inflate(LayoutInflater inflater, Object component) {
        return (DialogVipRight2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_right2, null, false, component);
    }

    public static DialogVipRight2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipRight2Binding bind(View view, Object component) {
        return (DialogVipRight2Binding) bind(component, view, R.layout.dialog_vip_right2);
    }
}
