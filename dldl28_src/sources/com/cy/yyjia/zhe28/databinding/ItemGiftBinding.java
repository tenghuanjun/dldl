package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GiftBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGiftBinding extends ViewDataBinding {
    public final TextView btn;
    public final ImageView gameIcon;
    public final LinearLayout giftName;

    @Bindable
    protected GiftBean mData;

    @Bindable
    protected boolean mVip;
    public final TextView textView;
    public final TextView textView2;

    public abstract void setData(GiftBean data);

    public abstract void setVip(boolean vip);

    protected ItemGiftBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, ImageView gameIcon, LinearLayout giftName, TextView textView, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.gameIcon = gameIcon;
        this.giftName = giftName;
        this.textView = textView;
        this.textView2 = textView2;
    }

    public boolean getVip() {
        return this.mVip;
    }

    public GiftBean getData() {
        return this.mData;
    }

    public static ItemGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gift, root, attachToRoot, component);
    }

    public static ItemGiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gift, null, false, component);
    }

    public static ItemGiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGiftBinding bind(View view, Object component) {
        return (ItemGiftBinding) bind(component, view, R.layout.item_gift);
    }
}
