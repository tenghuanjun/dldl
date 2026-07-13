package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityPinBinding extends ViewDataBinding {

    @Bindable
    protected String mBtn;

    @Bindable
    protected String mHint;

    @Bindable
    protected String mPassword;

    @Bindable
    protected String mTip;
    public final Navigation navigation;
    public final ShapeTextView tvGo;

    public abstract void setBtn(String btn);

    public abstract void setHint(String hint);

    public abstract void setPassword(String password);

    public abstract void setTip(String tip);

    protected ActivityPinBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.tvGo = tvGo;
    }

    public String getTip() {
        return this.mTip;
    }

    public String getHint() {
        return this.mHint;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public String getBtn() {
        return this.mBtn;
    }

    public static ActivityPinBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPinBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPinBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_pin, root, attachToRoot, component);
    }

    public static ActivityPinBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPinBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPinBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_pin, null, false, component);
    }

    public static ActivityPinBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPinBinding bind(View view, Object component) {
        return (ActivityPinBinding) bind(component, view, R.layout.activity_pin);
    }
}
