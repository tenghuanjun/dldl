package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityFudaiIndexBinding extends ViewDataBinding {
    public final ImageView ivAdd;
    public final LinearLayout llRecord;

    @Bindable
    protected FudaiIndexBean mData;
    public final Navigation navigation;
    public final RecyclerView rv;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f441tv;
    public final TextView tvCountdown;
    public final TextView tvTime;

    public abstract void setData(FudaiIndexBean data);

    protected ActivityFudaiIndexBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAdd, LinearLayout llRecord, Navigation navigation, RecyclerView rv, TextView tv2, TextView tvCountdown, TextView tvTime) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAdd = ivAdd;
        this.llRecord = llRecord;
        this.navigation = navigation;
        this.rv = rv;
        this.f441tv = tv2;
        this.tvCountdown = tvCountdown;
        this.tvTime = tvTime;
    }

    public FudaiIndexBean getData() {
        return this.mData;
    }

    public static ActivityFudaiIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFudaiIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityFudaiIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_fudai_index, root, attachToRoot, component);
    }

    public static ActivityFudaiIndexBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFudaiIndexBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityFudaiIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_fudai_index, null, false, component);
    }

    public static ActivityFudaiIndexBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFudaiIndexBinding bind(View view, Object component) {
        return (ActivityFudaiIndexBinding) bind(component, view, R.layout.activity_fudai_index);
    }
}
