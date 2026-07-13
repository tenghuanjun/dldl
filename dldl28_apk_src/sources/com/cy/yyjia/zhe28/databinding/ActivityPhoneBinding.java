package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityPhoneBinding extends ViewDataBinding {
    public final CountdownView btnCode;

    @Bindable
    protected String mBtn;

    @Bindable
    protected String mCode;

    @Bindable
    protected String mPhone;

    @Bindable
    protected String mTitle;

    @Bindable
    protected String mTitle2;
    public final Navigation navigation;
    public final ShapeTextView tvGo;

    public abstract void setBtn(String btn);

    public abstract void setCode(String code);

    public abstract void setPhone(String phone);

    public abstract void setTitle(String title);

    public abstract void setTitle2(String title2);

    protected ActivityPhoneBinding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, Navigation navigation, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.navigation = navigation;
        this.tvGo = tvGo;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTitle2() {
        return this.mTitle2;
    }

    public String getBtn() {
        return this.mBtn;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getCode() {
        return this.mCode;
    }

    public static ActivityPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPhoneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_phone, root, attachToRoot, component);
    }

    public static ActivityPhoneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPhoneBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPhoneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_phone, null, false, component);
    }

    public static ActivityPhoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPhoneBinding bind(View view, Object component) {
        return (ActivityPhoneBinding) bind(component, view, R.layout.activity_phone);
    }
}
