package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameEventDetailBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityEventDetailBinding extends ViewDataBinding {
    public final ConsecutiveScrollerLayout csl;

    @Bindable
    protected GameEventDetailBean mData;
    public final Navigation navigation;
    public final WebView wv;
    public final WebView wv2;

    public abstract void setData(GameEventDetailBean data);

    protected ActivityEventDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ConsecutiveScrollerLayout csl, Navigation navigation, WebView wv, WebView wv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.csl = csl;
        this.navigation = navigation;
        this.wv = wv;
        this.wv2 = wv2;
    }

    public GameEventDetailBean getData() {
        return this.mData;
    }

    public static ActivityEventDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEventDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityEventDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_event_detail, root, attachToRoot, component);
    }

    public static ActivityEventDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEventDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityEventDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_event_detail, null, false, component);
    }

    public static ActivityEventDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEventDetailBinding bind(View view, Object component) {
        return (ActivityEventDetailBinding) bind(component, view, R.layout.activity_event_detail);
    }
}
