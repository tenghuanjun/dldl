package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentVipRightBinding extends ViewDataBinding {
    public final ImageView bg;

    @Bindable
    protected VipListBean.ListBean mData;

    @Bindable
    protected int mPosition;

    @Bindable
    protected int mProgress;
    public final ImageView tvCurrent;
    public final TextView tvb;

    public abstract void setData(VipListBean.ListBean data);

    public abstract void setPosition(int position);

    public abstract void setProgress(int progress);

    protected FragmentVipRightBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg, ImageView tvCurrent, TextView tvb) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.tvCurrent = tvCurrent;
        this.tvb = tvb;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public VipListBean.ListBean getData() {
        return this.mData;
    }

    public static FragmentVipRightBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipRightBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVipRightBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_vip_right, root, attachToRoot, component);
    }

    public static FragmentVipRightBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipRightBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVipRightBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_vip_right, null, false, component);
    }

    public static FragmentVipRightBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipRightBinding bind(View view, Object component) {
        return (FragmentVipRightBinding) bind(component, view, R.layout.fragment_vip_right);
    }
}
