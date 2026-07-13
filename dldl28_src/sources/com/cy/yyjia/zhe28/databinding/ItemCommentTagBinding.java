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
public abstract class ItemCommentTagBinding extends ViewDataBinding {

    @Bindable
    protected CommentConfig.Tag mData;

    public abstract void setData(CommentConfig.Tag data);

    protected ItemCommentTagBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CommentConfig.Tag getData() {
        return this.mData;
    }

    public static ItemCommentTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCommentTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_tag, root, attachToRoot, component);
    }

    public static ItemCommentTagBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentTagBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCommentTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_comment_tag, null, false, component);
    }

    public static ItemCommentTagBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCommentTagBinding bind(View view, Object component) {
        return (ItemCommentTagBinding) bind(component, view, R.layout.item_comment_tag);
    }
}
