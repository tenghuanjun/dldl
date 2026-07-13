package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityLoginBinding extends ViewDataBinding {
    public final CountdownView btnCode;
    public final LinearLayout llConfirm;

    @Bindable
    protected boolean mCheck;

    @Bindable
    protected String mPassword;

    @Bindable
    protected boolean mPhone;

    @Bindable
    protected String mUsername;

    @Bindable
    protected String mYzm;
    public final Navigation navigation;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f442tv;
    public final TextView tvChange;
    public final TextView tvFast;
    public final TextView tvForget;
    public final TextView tvLogin;
    public final TextView tvPrivacy;
    public final TextView tvTag;
    public final TextView tvUser;
    public final TextView tvWx;

    public abstract void setCheck(boolean check);

    public abstract void setPassword(String password);

    public abstract void setPhone(boolean phone);

    public abstract void setUsername(String username);

    public abstract void setYzm(String yzm);

    protected ActivityLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, LinearLayout llConfirm, Navigation navigation, TextView tv2, TextView tvChange, TextView tvFast, TextView tvForget, TextView tvLogin, TextView tvPrivacy, TextView tvTag, TextView tvUser, TextView tvWx) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.llConfirm = llConfirm;
        this.navigation = navigation;
        this.f442tv = tv2;
        this.tvChange = tvChange;
        this.tvFast = tvFast;
        this.tvForget = tvForget;
        this.tvLogin = tvLogin;
        this.tvPrivacy = tvPrivacy;
        this.tvTag = tvTag;
        this.tvUser = tvUser;
        this.tvWx = tvWx;
    }

    public boolean getPhone() {
        return this.mPhone;
    }

    public boolean getCheck() {
        return this.mCheck;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public String getYzm() {
        return this.mYzm;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, root, attachToRoot, component);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, null, false, component);
    }

    public static ActivityLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding bind(View view, Object component) {
        return (ActivityLoginBinding) bind(component, view, R.layout.activity_login);
    }
}
