package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfareEventReplyBinding extends ViewDataBinding {

    @Bindable
    protected CommentBean mData;
    public final ShapeTextView tvPraise;

    public abstract void setData(CommentBean data);

    protected ItemWelfareEventReplyBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvPraise) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvPraise = tvPraise;
    }

    public CommentBean getData() {
        return this.mData;
    }

    public static ItemWelfareEventReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareEventReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfareEventReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_event_reply, root, attachToRoot, component);
    }

    public static ItemWelfareEventReplyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareEventReplyBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfareEventReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_event_reply, null, false, component);
    }

    public static ItemWelfareEventReplyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareEventReplyBinding bind(View view, Object component) {
        return (ItemWelfareEventReplyBinding) bind(component, view, R.layout.item_welfare_event_reply);
    }
}
