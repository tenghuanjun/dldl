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
public abstract class FragmentHomeSchedule2Binding extends ViewDataBinding {

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;

    @Bindable
    protected String mTime;
    public final RecyclerView rv;
    public final RecyclerView rvType;
    public final SmartRefreshLayout srl;
    public final ShapeTextView tvScreen;
    public final ShapeTextView tvTime;
    public final ShapeTextView tvType;

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    public abstract void setTime(String time);

    protected FragmentHomeSchedule2Binding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, RecyclerView rvType, SmartRefreshLayout srl, ShapeTextView tvScreen, ShapeTextView tvTime, ShapeTextView tvType) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.rvType = rvType;
        this.srl = srl;
        this.tvScreen = tvScreen;
        this.tvTime = tvTime;
        this.tvType = tvType;
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

    public static FragmentHomeSchedule2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeSchedule2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeSchedule2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_schedule2, root, attachToRoot, component);
    }

    public static FragmentHomeSchedule2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeSchedule2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeSchedule2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_schedule2, null, false, component);
    }

    public static FragmentHomeSchedule2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeSchedule2Binding bind(View view, Object component) {
        return (FragmentHomeSchedule2Binding) bind(component, view, R.layout.fragment_home_schedule2);
    }
}
