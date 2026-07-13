package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeVideoGameBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;
    public final WancmsStandardPlayer player;

    public abstract void setData(GameBean data);

    protected ItemHomeVideoGameBinding(Object _bindingComponent, View _root, int _localFieldCount, WancmsStandardPlayer player) {
        super(_bindingComponent, _root, _localFieldCount);
        this.player = player;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeVideoGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideoGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeVideoGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_video_game, root, attachToRoot, component);
    }

    public static ItemHomeVideoGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideoGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeVideoGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_video_game, null, false, component);
    }

    public static ItemHomeVideoGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideoGameBinding bind(View view, Object component) {
        return (ItemHomeVideoGameBinding) bind(component, view, R.layout.item_home_video_game);
    }
}
