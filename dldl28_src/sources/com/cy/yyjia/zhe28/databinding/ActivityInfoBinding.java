package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityInfoBinding extends ViewDataBinding {
    public final LinearLayout llAvatar;
    public final LinearLayout llNickname;

    @Bindable
    protected UserBean mData;
    public final RadioButton rb1;
    public final RadioButton rb2;
    public final RadioButton rb3;
    public final RadioGroup rg;

    public abstract void setData(UserBean data);

    protected ActivityInfoBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llAvatar, LinearLayout llNickname, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioGroup rg) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llAvatar = llAvatar;
        this.llNickname = llNickname;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rg = rg;
    }

    public UserBean getData() {
        return this.mData;
    }

    public static ActivityInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_info, root, attachToRoot, component);
    }

    public static ActivityInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInfoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_info, null, false, component);
    }

    public static ActivityInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInfoBinding bind(View view, Object component) {
        return (ActivityInfoBinding) bind(component, view, R.layout.activity_info);
    }
}
