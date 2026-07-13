package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDealBinding extends ViewDataBinding {
    public final ShapeLinearLayout btnRecord;

    @Bindable
    protected UserBean mData;

    @Bindable
    protected View.OnClickListener mOnClick;
    public final RecyclerView rv;
    public final TextView tvMoney;
    public final ShapeTextView tvWithdrew;

    public abstract void setData(UserBean data);

    public abstract void setOnClick(View.OnClickListener onClick);

    protected FragmentDealBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeLinearLayout btnRecord, RecyclerView rv, TextView tvMoney, ShapeTextView tvWithdrew) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnRecord = btnRecord;
        this.rv = rv;
        this.tvMoney = tvMoney;
        this.tvWithdrew = tvWithdrew;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public UserBean getData() {
        return this.mData;
    }

    public static FragmentDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal, root, attachToRoot, component);
    }

    public static FragmentDealBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal, null, false, component);
    }

    public static FragmentDealBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealBinding bind(View view, Object component) {
        return (FragmentDealBinding) bind(component, view, R.layout.fragment_deal);
    }
}
