package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfareTask3Binding extends ViewDataBinding {
    public final ShapeTextView btn;

    @Bindable
    protected DailyCouponBean.Tier mData;

    @Bindable
    protected boolean mHideLine;
    public final TextView tvName;

    public abstract void setData(DailyCouponBean.Tier data);

    public abstract void setHideLine(boolean hideLine);

    protected ItemWelfareTask3Binding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.tvName = tvName;
    }

    public boolean getHideLine() {
        return this.mHideLine;
    }

    public DailyCouponBean.Tier getData() {
        return this.mData;
    }

    public static ItemWelfareTask3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareTask3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfareTask3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_task3, root, attachToRoot, component);
    }

    public static ItemWelfareTask3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareTask3Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfareTask3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_task3, null, false, component);
    }

    public static ItemWelfareTask3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareTask3Binding bind(View view, Object component) {
        return (ItemWelfareTask3Binding) bind(component, view, R.layout.item_welfare_task3);
    }
}
