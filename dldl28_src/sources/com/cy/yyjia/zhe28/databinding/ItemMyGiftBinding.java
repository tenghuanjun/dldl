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
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMyGiftBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final ImageView gameIcon;
    public final TextView giftName;

    @Bindable
    protected GiftBean mData;

    public abstract void setData(GiftBean data);

    protected ItemMyGiftBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, ImageView gameIcon, TextView giftName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.gameIcon = gameIcon;
        this.giftName = giftName;
    }

    public GiftBean getData() {
        return this.mData;
    }

    public static ItemMyGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMyGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_gift, root, attachToRoot, component);
    }

    public static ItemMyGiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMyGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_gift, null, false, component);
    }

    public static ItemMyGiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyGiftBinding bind(View view, Object component) {
        return (ItemMyGiftBinding) bind(component, view, R.layout.item_my_gift);
    }
}
