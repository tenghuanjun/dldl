package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskResult;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogQiandaoTaskBinding extends ViewDataBinding {

    @Bindable
    protected TaskResult mData;

    @Bindable
    protected int mType;
    public final RecyclerView rv1;

    public abstract void setData(TaskResult data);

    public abstract void setType(int type);

    protected DialogQiandaoTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv1) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv1 = rv1;
    }

    public int getType() {
        return this.mType;
    }

    public TaskResult getData() {
        return this.mData;
    }

    public static DialogQiandaoTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogQiandaoTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogQiandaoTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_qiandao_task, root, attachToRoot, component);
    }

    public static DialogQiandaoTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogQiandaoTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogQiandaoTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_qiandao_task, null, false, component);
    }

    public static DialogQiandaoTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogQiandaoTaskBinding bind(View view, Object component) {
        return (DialogQiandaoTaskBinding) bind(component, view, R.layout.dialog_qiandao_task);
    }
}
