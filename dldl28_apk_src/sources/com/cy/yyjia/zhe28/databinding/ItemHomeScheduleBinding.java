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
import com.cy.yyjia.zhe28.domain.NewGameBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeScheduleBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected NewGameBean mData;

    @Bindable
    protected int mPosition;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f467tv;
    public final ShapeTextView tv2;
    public final ShapeLinearLayout tvXc;

    public abstract void setData(NewGameBean data);

    public abstract void setPosition(int position);

    protected ItemHomeScheduleBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, TextView tv2, ShapeTextView tv22, ShapeLinearLayout tvXc) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.f467tv = tv2;
        this.tv2 = tv22;
        this.tvXc = tvXc;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public NewGameBean getData() {
        return this.mData;
    }

    public static ItemHomeScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule, root, attachToRoot, component);
    }

    public static ItemHomeScheduleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule, null, false, component);
    }

    public static ItemHomeScheduleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleBinding bind(View view, Object component) {
        return (ItemHomeScheduleBinding) bind(component, view, R.layout.item_home_schedule);
    }
}
