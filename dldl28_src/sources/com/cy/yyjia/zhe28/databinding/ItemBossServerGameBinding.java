package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBossServerGameBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected GameBean mData;

    public abstract void setData(GameBean data);

    protected ItemBossServerGameBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemBossServerGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServerGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBossServerGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_boss_server_game, root, attachToRoot, component);
    }

    public static ItemBossServerGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServerGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBossServerGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_boss_server_game, null, false, component);
    }

    public static ItemBossServerGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServerGameBinding bind(View view, Object component) {
        return (ItemBossServerGameBinding) bind(component, view, R.layout.item_boss_server_game);
    }
}
