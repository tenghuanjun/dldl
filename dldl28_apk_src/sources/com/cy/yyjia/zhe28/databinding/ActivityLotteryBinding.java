package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.LotteryInfoBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityLotteryBinding extends ViewDataBinding {
    public final ShapeTextView btn1;
    public final ShapeTextView btn10;

    @Bindable
    protected LotteryInfoBean mData;

    @Bindable
    protected int mType;
    public final RecyclerView rv;
    public final RecyclerView rvBottom;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tvPrize;
    public final TextView tvRecord;
    public final TextView tvRule;
    public final ShapeTextView tvShop;
    public final AdapterViewFlipper vf;

    public abstract void setData(LotteryInfoBean data);

    public abstract void setType(int type);

    protected ActivityLotteryBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn1, ShapeTextView btn10, RecyclerView rv, RecyclerView rvBottom, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tvPrize, TextView tvRecord, TextView tvRule, ShapeTextView tvShop, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn1 = btn1;
        this.btn10 = btn10;
        this.rv = rv;
        this.rvBottom = rvBottom;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tvPrize = tvPrize;
        this.tvRecord = tvRecord;
        this.tvRule = tvRule;
        this.tvShop = tvShop;
        this.vf = vf;
    }

    public int getType() {
        return this.mType;
    }

    public LotteryInfoBean getData() {
        return this.mData;
    }

    public static ActivityLotteryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLotteryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lottery, root, attachToRoot, component);
    }

    public static ActivityLotteryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLotteryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lottery, null, false, component);
    }

    public static ActivityLotteryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryBinding bind(View view, Object component) {
        return (ActivityLotteryBinding) bind(component, view, R.layout.activity_lottery);
    }
}
