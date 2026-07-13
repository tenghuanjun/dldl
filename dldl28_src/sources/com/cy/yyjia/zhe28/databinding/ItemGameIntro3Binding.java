package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBannerBean;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameIntro3Binding extends ViewDataBinding {

    @Bindable
    protected GameBannerBean mData;
    public final WancmsStandardPlayer player;

    public abstract void setData(GameBannerBean data);

    protected ItemGameIntro3Binding(Object _bindingComponent, View _root, int _localFieldCount, WancmsStandardPlayer player) {
        super(_bindingComponent, _root, _localFieldCount);
        this.player = player;
    }

    public GameBannerBean getData() {
        return this.mData;
    }

    public static ItemGameIntro3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameIntro3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro3, root, attachToRoot, component);
    }

    public static ItemGameIntro3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro3Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameIntro3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro3, null, false, component);
    }

    public static ItemGameIntro3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro3Binding bind(View view, Object component) {
        return (ItemGameIntro3Binding) bind(component, view, R.layout.item_game_intro3);
    }
}
