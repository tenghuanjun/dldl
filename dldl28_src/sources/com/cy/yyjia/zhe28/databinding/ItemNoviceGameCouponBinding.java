package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemNoviceGameCouponBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected NoviceGameBean.CouponBean mData;

    public abstract void setData(NoviceGameBean.CouponBean data);

    protected ItemNoviceGameCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public NoviceGameBean.CouponBean getData() {
        return this.mData;
    }

    public static ItemNoviceGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemNoviceGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_novice_game_coupon, root, attachToRoot, component);
    }

    public static ItemNoviceGameCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemNoviceGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_novice_game_coupon, null, false, component);
    }

    public static ItemNoviceGameCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameCouponBinding bind(View view, Object component) {
        return (ItemNoviceGameCouponBinding) bind(component, view, R.layout.item_novice_game_coupon);
    }
}
