package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityNoviceTaskBinding extends ViewDataBinding {
    public final ImageView imageView;
    public final LinearLayout linearLayout;
    public final LinearLayout linearLayout2;
    public final RecyclerView list;

    @Bindable
    protected TaskResult mData;
    public final Navigation navigation;
    public final LinearLayout textView;

    public abstract void setData(TaskResult data);

    protected ActivityNoviceTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView list, Navigation navigation, LinearLayout textView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView = imageView;
        this.linearLayout = linearLayout;
        this.linearLayout2 = linearLayout2;
        this.list = list;
        this.navigation = navigation;
        this.textView = textView;
    }

    public TaskResult getData() {
        return this.mData;
    }

    public static ActivityNoviceTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityNoviceTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_novice_task, root, attachToRoot, component);
    }

    public static ActivityNoviceTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityNoviceTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_novice_task, null, false, component);
    }

    public static ActivityNoviceTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceTaskBinding bind(View view, Object component) {
        return (ActivityNoviceTaskBinding) bind(component, view, R.layout.activity_novice_task);
    }
}
