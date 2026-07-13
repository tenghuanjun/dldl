package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentMessageBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMessageUserBinding extends ViewDataBinding {
    public final ImageView imageView2;

    @Bindable
    protected CommentMessageBean mData;
    public final ImageView userIcon;
    public final TextView userName;

    public abstract void setData(CommentMessageBean data);

    protected ItemMessageUserBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView2, ImageView userIcon, TextView userName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView2 = imageView2;
        this.userIcon = userIcon;
        this.userName = userName;
    }

    public CommentMessageBean getData() {
        return this.mData;
    }

    public static ItemMessageUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMessageUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_user, root, attachToRoot, component);
    }

    public static ItemMessageUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageUserBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMessageUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_user, null, false, component);
    }

    public static ItemMessageUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageUserBinding bind(View view, Object component) {
        return (ItemMessageUserBinding) bind(component, view, R.layout.item_message_user);
    }
}
