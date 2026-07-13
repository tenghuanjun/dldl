package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.MessageBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMessageOfficialBinding extends ViewDataBinding {
    public final ImageView ivTag1;

    @Bindable
    protected MessageBean mData;

    public abstract void setData(MessageBean data);

    protected ItemMessageOfficialBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivTag1) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivTag1 = ivTag1;
    }

    public MessageBean getData() {
        return this.mData;
    }

    public static ItemMessageOfficialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageOfficialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMessageOfficialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_official, root, attachToRoot, component);
    }

    public static ItemMessageOfficialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageOfficialBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMessageOfficialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_official, null, false, component);
    }

    public static ItemMessageOfficialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageOfficialBinding bind(View view, Object component) {
        return (ItemMessageOfficialBinding) bind(component, view, R.layout.item_message_official);
    }
}
