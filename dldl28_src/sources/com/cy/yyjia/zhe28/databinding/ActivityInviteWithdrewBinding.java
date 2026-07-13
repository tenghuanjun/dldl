package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityInviteWithdrewBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final EditText et;

    @Bindable
    protected InviteInfoBean mData;

    @Bindable
    protected String mNumber;
    public final Navigation navigation;
    public final TextView tvAll;
    public final TextView tvRule;

    public abstract void setData(InviteInfoBean data);

    public abstract void setNumber(String number);

    protected ActivityInviteWithdrewBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, EditText et, Navigation navigation, TextView tvAll, TextView tvRule) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.navigation = navigation;
        this.tvAll = tvAll;
        this.tvRule = tvRule;
    }

    public InviteInfoBean getData() {
        return this.mData;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public static ActivityInviteWithdrewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteWithdrewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityInviteWithdrewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_invite_withdrew, root, attachToRoot, component);
    }

    public static ActivityInviteWithdrewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteWithdrewBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityInviteWithdrewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_invite_withdrew, null, false, component);
    }

    public static ActivityInviteWithdrewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteWithdrewBinding bind(View view, Object component) {
        return (ActivityInviteWithdrewBinding) bind(component, view, R.layout.activity_invite_withdrew);
    }
}
