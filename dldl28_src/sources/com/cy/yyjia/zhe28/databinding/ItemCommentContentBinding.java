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
public abstract class ItemCommentContentBinding extends ViewDataBinding {

    @Bindable
    protected CommentConfig.Content mData;

    public abstract void setData(CommentConfig.Content data);

    protected ItemCommentContentBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CommentConfig.Content getData() {
        return this.mData;
    }

    public static ItemCommentContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCommentContentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_content, root, attachToRoot, component);
    }

    public static ItemCommentContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentContentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCommentContentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_content, null, false, component);
    }

    public static ItemCommentContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentContentBinding bind(View view, Object component) {
        return (ItemCommentContentBinding) bind(component, view, R.layout.item_comment_content);
    }
}
