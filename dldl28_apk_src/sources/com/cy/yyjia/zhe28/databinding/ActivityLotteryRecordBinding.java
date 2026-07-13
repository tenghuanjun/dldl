package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityLotteryRecordBinding extends ViewDataBinding {

    @Bindable
    protected int mType;
    public final RecyclerView rv;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;

    public abstract void setType(int type);

    protected ActivityLotteryRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, ShapeTextView tv1, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public int getType() {
        return this.mType;
    }

    public static ActivityLotteryRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLotteryRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lottery_record, root, attachToRoot, component);
    }

    public static ActivityLotteryRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLotteryRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lottery_record, null, false, component);
    }

    public static ActivityLotteryRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLotteryRecordBinding bind(View view, Object component) {
        return (ActivityLotteryRecordBinding) bind(component, view, R.layout.activity_lottery_record);
    }
}
