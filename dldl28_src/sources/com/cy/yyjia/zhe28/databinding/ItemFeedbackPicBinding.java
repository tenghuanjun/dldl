package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemFeedbackPicBinding extends ViewDataBinding {
    public final ImageView iv;
    public final ImageView ivDelete;

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected ItemFeedbackPicBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, ImageView ivDelete) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.ivDelete = ivDelete;
    }

    public String getData() {
        return this.mData;
    }

    public static ItemFeedbackPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFeedbackPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemFeedbackPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_feedback_pic, root, attachToRoot, component);
    }

    public static ItemFeedbackPicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFeedbackPicBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemFeedbackPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_feedback_pic, null, false, component);
    }

    public static ItemFeedbackPicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFeedbackPicBinding bind(View view, Object component) {
        return (ItemFeedbackPicBinding) bind(component, view, R.layout.item_feedback_pic);
    }
}
