package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameCommentSubBinding extends ViewDataBinding {

    @Bindable
    protected CommentBean mData;

    public abstract void setData(CommentBean data);

    protected ItemGameCommentSubBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CommentBean getData() {
        return this.mData;
    }

    public static ItemGameCommentSubBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentSubBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameCommentSubBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment_sub, root, attachToRoot, component);
    }

    public static ItemGameCommentSubBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentSubBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameCommentSubBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment_sub, null, false, component);
    }

    public static ItemGameCommentSubBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentSubBinding bind(View view, Object component) {
        return (ItemGameCommentSubBinding) bind(component, view, R.layout.item_game_comment_sub);
    }
}
