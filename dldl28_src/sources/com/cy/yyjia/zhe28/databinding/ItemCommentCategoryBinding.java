package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentConfig;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemCommentCategoryBinding extends ViewDataBinding {

    @Bindable
    protected CommentConfig.Tag mData;

    public abstract void setData(CommentConfig.Tag data);

    protected ItemCommentCategoryBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CommentConfig.Tag getData() {
        return this.mData;
    }

    public static ItemCommentCategoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentCategoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCommentCategoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_category, root, attachToRoot, component);
    }

    public static ItemCommentCategoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentCategoryBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCommentCategoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_category, null, false, component);
    }

    public static ItemCommentCategoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentCategoryBinding bind(View view, Object component) {
        return (ItemCommentCategoryBinding) bind(component, view, R.layout.item_comment_category);
    }
}
