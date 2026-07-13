package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameToolBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameToolBinding extends ViewDataBinding {
    public final CardView cv;
    public final ImageView iv;

    @Bindable
    protected GameToolBean mData;

    public abstract void setData(GameToolBean data);

    protected ItemGameToolBinding(Object _bindingComponent, View _root, int _localFieldCount, CardView cv, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cv = cv;
        this.iv = iv;
    }

    public GameToolBean getData() {
        return this.mData;
    }

    public static ItemGameToolBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameToolBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameToolBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_tool, root, attachToRoot, component);
    }

    public static ItemGameToolBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameToolBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameToolBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_tool, null, false, component);
    }

    public static ItemGameToolBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameToolBinding bind(View view, Object component) {
        return (ItemGameToolBinding) bind(component, view, R.layout.item_game_tool);
    }
}
