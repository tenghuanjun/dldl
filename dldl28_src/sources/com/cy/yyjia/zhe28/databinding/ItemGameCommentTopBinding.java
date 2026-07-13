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
public abstract class ItemGameCommentTopBinding extends ViewDataBinding {

    @Bindable
    protected CommentBean mData;

    public abstract void setData(CommentBean data);

    protected ItemGameCommentTopBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CommentBean getData() {
        return this.mData;
    }

    public static ItemGameCommentTopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentTopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameCommentTopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment_top, root, attachToRoot, component);
    }

    public static ItemGameCommentTopBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentTopBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameCommentTopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment_top, null, false, component);
    }

    public static ItemGameCommentTopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentTopBinding bind(View view, Object component) {
        return (ItemGameCommentTopBinding) bind(component, view, R.layout.item_game_comment_top);
    }
}
