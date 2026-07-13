package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMyGameBinding extends ViewDataBinding {
    public final LinearLayout desc;
    public final ShapeTextView discount;
    public final LayoutGameIconBinding icon;
    public final ImageView ivDelete;

    @Bindable
    protected GameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final LinearLayout tag2;

    public abstract void setData(GameBean data);

    protected ItemMyGameBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, ShapeTextView discount, LayoutGameIconBinding icon, ImageView ivDelete, LayoutGameNameBinding name, LinearLayout tag, LinearLayout tag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.icon = icon;
        this.ivDelete = ivDelete;
        this.name = name;
        this.tag = tag;
        this.tag2 = tag2;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemMyGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMyGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_game, root, attachToRoot, component);
    }

    public static ItemMyGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMyGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_game, null, false, component);
    }

    public static ItemMyGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGameBinding bind(View view, Object component) {
        return (ItemMyGameBinding) bind(component, view, R.layout.item_my_game);
    }
}
