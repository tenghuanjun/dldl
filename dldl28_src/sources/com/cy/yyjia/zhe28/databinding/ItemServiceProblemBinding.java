package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ProblemBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemServiceProblemBinding extends ViewDataBinding {

    @Bindable
    protected ProblemBean mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f470tv;

    public abstract void setData(ProblemBean data);

    protected ItemServiceProblemBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f470tv = tv2;
    }

    public ProblemBean getData() {
        return this.mData;
    }

    public static ItemServiceProblemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceProblemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemServiceProblemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_service_problem, root, attachToRoot, component);
    }

    public static ItemServiceProblemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceProblemBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemServiceProblemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_service_problem, null, false, component);
    }

    public static ItemServiceProblemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceProblemBinding bind(View view, Object component) {
        return (ItemServiceProblemBinding) bind(component, view, R.layout.item_service_problem);
    }
}
