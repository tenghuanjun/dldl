package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityRecycleBinding extends ViewDataBinding {
    public final ImageView ivRecord;

    @Bindable
    protected String mName;

    @Bindable
    protected int mStep;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final TextView tvRecycle;
    public final ShapeTextView tvSearch;

    public abstract void setName(String name);

    public abstract void setStep(int step);

    protected ActivityRecycleBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivRecord, Navigation navigation, RecyclerView rv, TextView tvRecycle, ShapeTextView tvSearch) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivRecord = ivRecord;
        this.navigation = navigation;
        this.rv = rv;
        this.tvRecycle = tvRecycle;
        this.tvSearch = tvSearch;
    }

    public int getStep() {
        return this.mStep;
    }

    public String getName() {
        return this.mName;
    }

    public static ActivityRecycleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecycleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRecycleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_recycle, root, attachToRoot, component);
    }

    public static ActivityRecycleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecycleBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRecycleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_recycle, null, false, component);
    }

    public static ActivityRecycleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRecycleBinding bind(View view, Object component) {
        return (ActivityRecycleBinding) bind(component, view, R.layout.activity_recycle);
    }
}
