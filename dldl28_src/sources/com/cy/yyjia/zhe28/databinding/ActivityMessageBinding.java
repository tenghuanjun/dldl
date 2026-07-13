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
import com.cy.yyjia.zhe28.domain.UnreadBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMessageBinding extends ViewDataBinding {
    public final ImageView ivClean;

    @Bindable
    protected UnreadBean mData;

    @Bindable
    protected int mDicker;

    @Bindable
    protected int mType;
    public final RecyclerView rv;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tv3;

    public abstract void setData(UnreadBean data);

    public abstract void setDicker(int dicker);

    public abstract void setType(int type);

    protected ActivityMessageBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClean, RecyclerView rv, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tv3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClean = ivClean;
        this.rv = rv;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
    }

    public UnreadBean getData() {
        return this.mData;
    }

    public int getDicker() {
        return this.mDicker;
    }

    public int getType() {
        return this.mType;
    }

    public static ActivityMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_message, root, attachToRoot, component);
    }

    public static ActivityMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMessageBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_message, null, false, component);
    }

    public static ActivityMessageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMessageBinding bind(View view, Object component) {
        return (ActivityMessageBinding) bind(component, view, R.layout.activity_message);
    }
}
