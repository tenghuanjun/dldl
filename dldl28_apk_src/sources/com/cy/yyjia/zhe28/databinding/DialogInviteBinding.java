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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogInviteBinding extends ViewDataBinding {
    public final LinearLayout ll;

    @Bindable
    protected String mData;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final ShapeTextView tvClose;

    public abstract void setData(String data);

    protected DialogInviteBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, TextView tv1, TextView tv2, TextView tv3, TextView tv4, ShapeTextView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tvClose = tvClose;
    }

    public String getData() {
        return this.mData;
    }

    public static DialogInviteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogInviteBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite, root, attachToRoot, component);
    }

    public static DialogInviteBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogInviteBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite, null, false, component);
    }

    public static DialogInviteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteBinding bind(View view, Object component) {
        return (DialogInviteBinding) bind(component, view, R.layout.dialog_invite);
    }
}
