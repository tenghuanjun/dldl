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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityRegisterBinding extends ViewDataBinding {
    public final CountdownView btnCode;

    @Bindable
    protected String mCode;

    @Bindable
    protected String mIdcard;

    @Bindable
    protected String mName;

    @Bindable
    protected String mPassword;

    @Bindable
    protected String mPassword2;

    @Bindable
    protected String mPhone;
    public final TextView tvFinish;
    public final ShapeTextView tvRegister;

    public abstract void setCode(String code);

    public abstract void setIdcard(String idcard);

    public abstract void setName(String name);

    public abstract void setPassword(String password);

    public abstract void setPassword2(String password2);

    public abstract void setPhone(String phone);

    protected ActivityRegisterBinding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, TextView tvFinish, ShapeTextView tvRegister) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.tvFinish = tvFinish;
        this.tvRegister = tvRegister;
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

    public String getName() {
        return this.mName;
    }

    public String getIdcard() {
        return this.mIdcard;
    }

    public static ActivityRegisterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRegisterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRegisterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_register, root, attachToRoot, component);
    }

    public static ActivityRegisterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRegisterBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRegisterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_register, null, false, component);
    }

    public static ActivityRegisterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRegisterBinding bind(View view, Object component) {
        return (ActivityRegisterBinding) bind(component, view, R.layout.activity_register);
    }
}
