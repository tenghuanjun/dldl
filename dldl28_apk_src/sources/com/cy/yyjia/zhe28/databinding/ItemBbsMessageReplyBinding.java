package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsMessageReplyBinding extends ViewDataBinding {

    @Bindable
    protected boolean mChild;

    @Bindable
    protected BbsBean mData;
    public final TextView tvReply;

    public abstract void setChild(boolean child);

    public abstract void setData(BbsBean data);

    protected ItemBbsMessageReplyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvReply) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvReply = tvReply;
    }

    public boolean getChild() {
        return this.mChild;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ItemBbsMessageReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsMessageReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_message_reply, root, attachToRoot, component);
    }

    public static ItemBbsMessageReplyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageReplyBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsMessageReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_message_reply, null, false, component);
    }

    public static ItemBbsMessageReplyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsMessageReplyBinding bind(View view, Object component) {
        return (ItemBbsMessageReplyBinding) bind(component, view, R.layout.item_bbs_message_reply);
    }
}
