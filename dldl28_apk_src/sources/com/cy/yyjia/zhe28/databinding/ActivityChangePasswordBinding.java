package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityChangePasswordBinding extends ViewDataBinding {
    public final CountdownView btnCode;

    @Bindable
    protected String mCode;

    @Bindable
    protected String mPassword;

    @Bindable
    protected String mPassword2;

    @Bindable
    protected String mPhone;
    public final Navigation navigation;
    public final ShapeTextView tvGo;
    public final TextView tvTitle;

    public abstract void setCode(String code);

    public abstract void setPassword(String password);

    public abstract void setPassword2(String password2);

    public abstract void setPhone(String phone);

    protected ActivityChangePasswordBinding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, Navigation navigation, ShapeTextView tvGo, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.navigation = navigation;
        this.tvGo = tvGo;
        this.tvTitle = tvTitle;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public String getPassword2() {
        return this.mPassword2;
    }

    public static ActivityChangePasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityChangePasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_change_password, root, attachToRoot, component);
    }

    public static ActivityChangePasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityChangePasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_change_password, null, false, component);
    }

    public static ActivityChangePasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding bind(View view, Object component) {
        return (ActivityChangePasswordBinding) bind(component, view, R.layout.activity_change_password);
    }
}
