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
import com.cy.yyjia.zhe28.domain.ProblemBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityServiceDetailBinding extends ViewDataBinding {

    @Bindable
    protected ProblemBean mData;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final TextView tvContent;

    public abstract void setData(ProblemBean data);

    protected ActivityServiceDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, RecyclerView rv, TextView tvContent) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.rv = rv;
        this.tvContent = tvContent;
    }

    public ProblemBean getData() {
        return this.mData;
    }

    public static ActivityServiceDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityServiceDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_service_detail, root, attachToRoot, component);
    }

    public static ActivityServiceDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityServiceDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_service_detail, null, false, component);
    }

    public static ActivityServiceDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceDetailBinding bind(View view, Object component) {
        return (ActivityServiceDetailBinding) bind(component, view, R.layout.activity_service_detail);
    }
}
