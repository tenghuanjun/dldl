package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHomeScheduleBinding extends ViewDataBinding {
    public final LinearLayout ll;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected String mTime;
    public final RecyclerView rv;
    public final RecyclerView rvTime;
    public final SmartRefreshLayout srl;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f461tv;

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setTime(String time);

    protected FragmentHomeScheduleBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, RecyclerView rv, RecyclerView rvTime, SmartRefreshLayout srl, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.rv = rv;
        this.rvTime = rvTime;
        this.srl = srl;
        this.f461tv = tv2;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public String getTime() {
        return this.mTime;
    }

    public static FragmentHomeScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_schedule, root, attachToRoot, component);
    }

    public static FragmentHomeScheduleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeScheduleBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_schedule, null, false, component);
    }

    public static FragmentHomeScheduleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeScheduleBinding bind(View view, Object component) {
        return (FragmentHomeScheduleBinding) bind(component, view, R.layout.fragment_home_schedule);
    }
}
