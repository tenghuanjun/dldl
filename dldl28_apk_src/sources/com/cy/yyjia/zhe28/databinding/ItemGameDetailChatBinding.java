package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailChatBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameDetailChatBinding extends ViewDataBinding {

    @Bindable
    protected GameDetailChatBean mData;

    public abstract void setData(GameDetailChatBean data);

    protected ItemGameDetailChatBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameDetailChatBean getData() {
        return this.mData;
    }

    public static ItemGameDetailChatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailChatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameDetailChatBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_chat, root, attachToRoot, component);
    }

    public static ItemGameDetailChatBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailChatBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameDetailChatBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_chat, null, false, component);
    }

    public static ItemGameDetailChatBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailChatBinding bind(View view, Object component) {
        return (ItemGameDetailChatBinding) bind(component, view, R.layout.item_game_detail_chat);
    }
}
