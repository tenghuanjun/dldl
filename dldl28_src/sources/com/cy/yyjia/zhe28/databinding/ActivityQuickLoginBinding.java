package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityQuickLoginBinding extends ViewDataBinding {
    public final ImageView ivGame;
    public final Navigation navigation;
    public final ShapeTextView tvLogin;
    public final TextView tvLogout;

    protected ActivityQuickLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivGame, Navigation navigation, ShapeTextView tvLogin, TextView tvLogout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivGame = ivGame;
        this.navigation = navigation;
        this.tvLogin = tvLogin;
        this.tvLogout = tvLogout;
    }

    public static ActivityQuickLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQuickLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityQuickLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_quick_login, root, attachToRoot, component);
    }

    public static ActivityQuickLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQuickLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityQuickLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_quick_login, null, false, component);
    }

    public static ActivityQuickLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQuickLoginBinding bind(View view, Object component) {
        return (ActivityQuickLoginBinding) bind(component, view, R.layout.activity_quick_login);
    }
}
