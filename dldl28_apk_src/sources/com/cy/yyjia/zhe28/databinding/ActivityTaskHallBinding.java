package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityTaskHallBinding extends ViewDataBinding {
    public final ImageView imageView;

    @Bindable
    protected TaskResult mData;
    public final Navigation navigation;
    public final RecyclerView rvTask;
    public final Space s;

    public abstract void setData(TaskResult data);

    protected ActivityTaskHallBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView, Navigation navigation, RecyclerView rvTask, Space s) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView = imageView;
        this.navigation = navigation;
        this.rvTask = rvTask;
        this.s = s;
    }

    public TaskResult getData() {
        return this.mData;
    }

    public static ActivityTaskHallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTaskHallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTaskHallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_task_hall, root, attachToRoot, component);
    }

    public static ActivityTaskHallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTaskHallBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTaskHallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_task_hall, null, false, component);
    }

    public static ActivityTaskHallBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTaskHallBinding bind(View view, Object component) {
        return (ActivityTaskHallBinding) bind(component, view, R.layout.activity_task_hall);
    }
}
