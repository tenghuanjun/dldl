package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BannerBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutBannerHomeBinding extends ViewDataBinding {
    public final ShapeLinearLayout ll;
    public final LinearLayout llDiscount;

    @Bindable
    protected BannerBean mData;
    public final LinearLayout name;
    public final ShapeLinearLayout other;
    public final ShapeTextView tag;

    public abstract void setData(BannerBean data);

    protected LayoutBannerHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeLinearLayout ll, LinearLayout llDiscount, LinearLayout name, ShapeLinearLayout other, ShapeTextView tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.llDiscount = llDiscount;
        this.name = name;
        this.other = other;
        this.tag = tag;
    }

    public BannerBean getData() {
        return this.mData;
    }

    public static LayoutBannerHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutBannerHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_banner_home, root, attachToRoot, component);
    }

    public static LayoutBannerHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutBannerHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_banner_home, null, false, component);
    }

    public static LayoutBannerHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerHomeBinding bind(View view, Object component) {
        return (LayoutBannerHomeBinding) bind(component, view, R.layout.layout_banner_home);
    }
}
