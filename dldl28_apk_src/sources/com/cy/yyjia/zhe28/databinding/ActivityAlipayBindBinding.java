package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityAlipayBindBinding extends ViewDataBinding {
    public final CountdownView btnCode;

    @Bindable
    protected String mAccount;

    @Bindable
    protected String mCode;

    @Bindable
    protected UserBean mData;

    @Bindable
    protected String mName;
    public final ShapeTextView tvGo;

    public abstract void setAccount(String account);

    public abstract void setCode(String code);

    public abstract void setData(UserBean data);

    public abstract void setName(String name);

    protected ActivityAlipayBindBinding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.tvGo = tvGo;
    }

    public UserBean getData() {
        return this.mData;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getAccount() {
        return this.mAccount;
    }

    public String getName() {
        return this.mName;
    }

    public static ActivityAlipayBindBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAlipayBindBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAlipayBindBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_alipay_bind, root, attachToRoot, component);
    }

    public static ActivityAlipayBindBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAlipayBindBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAlipayBindBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_alipay_bind, null, false, component);
    }

    public static ActivityAlipayBindBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAlipayBindBinding bind(View view, Object component) {
        return (ActivityAlipayBindBinding) bind(component, view, R.layout.activity_alipay_bind);
    }
}
