package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ServiceResult;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityServiceBinding extends ViewDataBinding {
    public final ImageView ivTag;

    @Bindable
    protected ServiceResult mData;
    public final RecyclerView rv;
    public final ShapeTextView tvCopy;

    public abstract void setData(ServiceResult data);

    protected ActivityServiceBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivTag, RecyclerView rv, ShapeTextView tvCopy) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivTag = ivTag;
        this.rv = rv;
        this.tvCopy = tvCopy;
    }

    public ServiceResult getData() {
        return this.mData;
    }

    public static ActivityServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_service, root, attachToRoot, component);
    }

    public static ActivityServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_service, null, false, component);
    }

    public static ActivityServiceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityServiceBinding bind(View view, Object component) {
        return (ActivityServiceBinding) bind(component, view, R.layout.activity_service);
    }
}
