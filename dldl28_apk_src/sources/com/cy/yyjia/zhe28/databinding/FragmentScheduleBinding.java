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
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentScheduleBinding extends ViewDataBinding {

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;

    @Bindable
    protected String mTime;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tv3;

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    public abstract void setTime(String time);

    protected FragmentScheduleBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, SmartRefreshLayout srl, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tv3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.srl = srl;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String getTime() {
        return this.mTime;
    }

    public static FragmentScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_schedule, root, attachToRoot, component);
    }

    public static FragmentScheduleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentScheduleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_schedule, null, false, component);
    }

    public static FragmentScheduleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScheduleBinding bind(View view, Object component) {
        return (FragmentScheduleBinding) bind(component, view, R.layout.fragment_schedule);
    }
}
