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
import com.cy.yyjia.zhe28.domain.DickerMessageBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMessageDickerBinding extends ViewDataBinding {

    @Bindable
    protected DickerMessageBean mData;
    public final ImageView userIcon;
    public final TextView userName;

    public abstract void setData(DickerMessageBean data);

    protected ItemMessageDickerBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView userIcon, TextView userName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.userIcon = userIcon;
        this.userName = userName;
    }

    public DickerMessageBean getData() {
        return this.mData;
    }

    public static ItemMessageDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMessageDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_dicker, root, attachToRoot, component);
    }

    public static ItemMessageDickerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageDickerBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMessageDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_dicker, null, false, component);
    }

    public static ItemMessageDickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageDickerBinding bind(View view, Object component) {
        return (ItemMessageDickerBinding) bind(component, view, R.layout.item_message_dicker);
    }
}
