package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameCouponBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGameCouponBinding extends ViewDataBinding {
    public final ShapeTextView btnAll;
    public final TextView btnChange;
    public final FrameLayout btnMonth;
    public final FrameLayout btnSqk;

    @Bindable
    protected GameCouponBean mData;
    public final RecyclerView rv;
    public final TextView tvAccount;

    public abstract void setData(GameCouponBean data);

    protected ActivityGameCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btnAll, TextView btnChange, FrameLayout btnMonth, FrameLayout btnSqk, RecyclerView rv, TextView tvAccount) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnAll = btnAll;
        this.btnChange = btnChange;
        this.btnMonth = btnMonth;
        this.btnSqk = btnSqk;
        this.rv = rv;
        this.tvAccount = tvAccount;
    }

    public GameCouponBean getData() {
        return this.mData;
    }

    public static ActivityGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_coupon, root, attachToRoot, component);
    }

    public static ActivityGameCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGameCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_coupon, null, false, component);
    }

    public static ActivityGameCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameCouponBinding bind(View view, Object component) {
        return (ActivityGameCouponBinding) bind(component, view, R.layout.activity_game_coupon);
    }
}
