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
import com.cy.yyjia.zhe28.domain.GiftDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGiftDetailBinding extends ViewDataBinding {
    public final ImageView gameIcon;

    @Bindable
    protected GiftDetailBean mData;
    public final LinearLayout tag;

    public abstract void setData(GiftDetailBean data);

    protected ActivityGiftDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gameIcon, LinearLayout tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gameIcon = gameIcon;
        this.tag = tag;
    }

    public GiftDetailBean getData() {
        return this.mData;
    }

    public static ActivityGiftDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGiftDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGiftDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gift_detail, root, attachToRoot, component);
    }

    public static ActivityGiftDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGiftDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGiftDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gift_detail, null, false, component);
    }

    public static ActivityGiftDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGiftDetailBinding bind(View view, Object component) {
        return (ActivityGiftDetailBinding) bind(component, view, R.layout.activity_gift_detail);
    }
}
