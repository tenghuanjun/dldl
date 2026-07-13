package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemYunGameBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;
    public final ShapeTextView tvGo;

    public abstract void setData(GameBean data);

    protected ItemYunGameBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvGo = tvGo;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemYunGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemYunGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_game, root, attachToRoot, component);
    }

    public static ItemYunGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemYunGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_yun_game, null, false, component);
    }

    public static ItemYunGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYunGameBinding bind(View view, Object component) {
        return (ItemYunGameBinding) bind(component, view, R.layout.item_yun_game);
    }
}
