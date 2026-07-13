package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameCouponBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final LinearLayout ll;

    @Bindable
    protected CouponBean mData;

    public abstract void setData(CouponBean data);

    protected ItemGameCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, LinearLayout ll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.ll = ll;
    }

    public CouponBean getData() {
        return this.mData;
    }

    public static ItemGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_coupon, root, attachToRoot, component);
    }

    public static ItemGameCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_coupon, null, false, component);
    }

    public static ItemGameCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCouponBinding bind(View view, Object component) {
        return (ItemGameCouponBinding) bind(component, view, R.layout.item_game_coupon);
    }
}
