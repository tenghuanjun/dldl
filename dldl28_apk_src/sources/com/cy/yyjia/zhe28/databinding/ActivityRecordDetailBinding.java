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
import com.cy.yyjia.zhe28.domain.RecordBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityRecordDetailBinding extends ViewDataBinding {
    public final ImageView icon;

    @Bindable
    protected RecordBean mData;
    public final TextView name;
    public final Navigation navigation;
    public final TextView number;
    public final TextView orderDetailes;
    public final TextView orderId;
    public final TextView orderNumber;
    public final TextView orderPayType;
    public final TextView orderTime;

    public abstract void setData(RecordBean data);

    protected ActivityRecordDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView icon, TextView name, Navigation navigation, TextView number, TextView orderDetailes, TextView orderId, TextView orderNumber, TextView orderPayType, TextView orderTime) {
        super(_bindingComponent, _root, _localFieldCount);
        this.icon = icon;
        this.name = name;
        this.navigation = navigation;
        this.number = number;
        this.orderDetailes = orderDetailes;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderPayType = orderPayType;
        this.orderTime = orderTime;
    }

    public RecordBean getData() {
        return this.mData;
    }

    public static ActivityRecordDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecordDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRecordDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_record_detail, root, attachToRoot, component);
    }

    public static ActivityRecordDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecordDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRecordDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_record_detail, null, false, component);
    }

    public static ActivityRecordDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecordDetailBinding bind(View view, Object component) {
        return (ActivityRecordDetailBinding) bind(component, view, R.layout.activity_record_detail);
    }
}
