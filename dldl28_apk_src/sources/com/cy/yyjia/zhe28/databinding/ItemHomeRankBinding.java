package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeRankBinding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;
    public final LinearLayout ll;

    @Bindable
    protected GameBean mData;

    @Bindable
    protected int mPosition;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final LinearLayout tag2;

    public abstract void setData(GameBean data);

    public abstract void setPosition(int position);

    protected ItemHomeRankBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LinearLayout ll, LayoutGameNameBinding name, LinearLayout tag, LinearLayout tag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.ll = ll;
        this.name = name;
        this.tag = tag;
        this.tag2 = tag2;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeRankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeRankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeRankBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_rank, root, attachToRoot, component);
    }

    public static ItemHomeRankBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeRankBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeRankBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_rank, null, false, component);
    }

    public static ItemHomeRankBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeRankBinding bind(View view, Object component) {
        return (ItemHomeRankBinding) bind(component, view, R.layout.item_home_rank);
    }
}
