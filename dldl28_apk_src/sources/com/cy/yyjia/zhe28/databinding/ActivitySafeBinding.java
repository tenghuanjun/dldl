package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySafeBinding extends ViewDataBinding {
    public final LinearLayout llAddress;
    public final LinearLayout llAli;
    public final LinearLayout llAuth;
    public final LinearLayout llBirthday;
    public final LinearLayout llCancellation;
    public final LinearLayout llPassword;
    public final LinearLayout llPhone;
    public final LinearLayout llPin;

    @Bindable
    protected UserBean mData;

    public abstract void setData(UserBean data);

    protected ActivitySafeBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llAddress, LinearLayout llAli, LinearLayout llAuth, LinearLayout llBirthday, LinearLayout llCancellation, LinearLayout llPassword, LinearLayout llPhone, LinearLayout llPin) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llAddress = llAddress;
        this.llAli = llAli;
        this.llAuth = llAuth;
        this.llBirthday = llBirthday;
        this.llCancellation = llCancellation;
        this.llPassword = llPassword;
        this.llPhone = llPhone;
        this.llPin = llPin;
    }

    public UserBean getData() {
        return this.mData;
    }

    public static ActivitySafeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySafeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySafeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_safe, root, attachToRoot, component);
    }

    public static ActivitySafeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySafeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySafeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_safe, null, false, component);
    }

    public static ActivitySafeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySafeBinding bind(View view, Object component) {
        return (ActivitySafeBinding) bind(component, view, R.layout.activity_safe);
    }
}
