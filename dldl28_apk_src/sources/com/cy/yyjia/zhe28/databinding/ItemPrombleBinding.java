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
import com.cy.yyjia.zhe28.domain.FeedbackRecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemPrombleBinding extends ViewDataBinding {
    public final ImageView ivTag1;

    @Bindable
    protected FeedbackRecordBean mData;
    public final RecyclerView rv;
    public final TextView tvDelete;

    public abstract void setData(FeedbackRecordBean data);

    protected ItemPrombleBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivTag1, RecyclerView rv, TextView tvDelete) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivTag1 = ivTag1;
        this.rv = rv;
        this.tvDelete = tvDelete;
    }

    public FeedbackRecordBean getData() {
        return this.mData;
    }

    public static ItemPrombleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPrombleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPrombleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_promble, root, attachToRoot, component);
    }

    public static ItemPrombleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPrombleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPrombleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_promble, null, false, component);
    }

    public static ItemPrombleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPrombleBinding bind(View view, Object component) {
        return (ItemPrombleBinding) bind(component, view, R.layout.item_promble);
    }
}
