package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTopicBannerBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected ItemTopicBannerBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public String getData() {
        return this.mData;
    }

    public static ItemTopicBannerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicBannerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTopicBannerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_banner, root, attachToRoot, component);
    }

    public static ItemTopicBannerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicBannerBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTopicBannerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_banner, null, false, component);
    }

    public static ItemTopicBannerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicBannerBinding bind(View view, Object component) {
        return (ItemTopicBannerBinding) bind(component, view, R.layout.item_topic_banner);
    }
}
