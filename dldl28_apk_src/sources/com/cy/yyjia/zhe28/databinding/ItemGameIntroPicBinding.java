package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBannerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameIntroPicBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected GameBannerBean mData;

    public abstract void setData(GameBannerBean data);

    protected ItemGameIntroPicBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public GameBannerBean getData() {
        return this.mData;
    }

    public static ItemGameIntroPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntroPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameIntroPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro_pic, root, attachToRoot, component);
    }

    public static ItemGameIntroPicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntroPicBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameIntroPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro_pic, null, false, component);
    }

    public static ItemGameIntroPicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntroPicBinding bind(View view, Object component) {
        return (ItemGameIntroPicBinding) bind(component, view, R.layout.item_game_intro_pic);
    }
}
