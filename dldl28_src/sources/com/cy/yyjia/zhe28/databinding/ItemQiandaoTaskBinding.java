package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemQiandaoTaskBinding extends ViewDataBinding {
    public final ShapeTextView btn;

    @Bindable
    protected TaskBean mData;

    @Bindable
    protected boolean mHideLine;
    public final TextView tvName;

    public abstract void setData(TaskBean data);

    public abstract void setHideLine(boolean hideLine);

    protected ItemQiandaoTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.tvName = tvName;
    }

    public boolean getHideLine() {
        return this.mHideLine;
    }

    public TaskBean getData() {
        return this.mData;
    }

    public static ItemQiandaoTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemQiandaoTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_task, root, attachToRoot, component);
    }

    public static ItemQiandaoTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemQiandaoTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_task, null, false, component);
    }

    public static ItemQiandaoTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoTaskBinding bind(View view, Object component) {
        return (ItemQiandaoTaskBinding) bind(component, view, R.layout.item_qiandao_task);
    }
}
