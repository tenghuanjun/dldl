package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogWithdraw2Binding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final EditText et;
    public final AppCompatImageView ivClose;

    @Bindable
    protected String mBind;

    @Bindable
    protected InviteInfoBean mData;

    @Bindable
    protected int mType;
    public final TextView tvAll;
    public final TextView tvBind;

    public abstract void setBind(String bind);

    public abstract void setData(InviteInfoBean data);

    public abstract void setType(int type);

    protected DialogWithdraw2Binding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, EditText et, AppCompatImageView ivClose, TextView tvAll, TextView tvBind) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.ivClose = ivClose;
        this.tvAll = tvAll;
        this.tvBind = tvBind;
    }

    public int getType() {
        return this.mType;
    }

    public String getBind() {
        return this.mBind;
    }

    public InviteInfoBean getData() {
        return this.mData;
    }

    public static DialogWithdraw2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdraw2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWithdraw2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_withdraw2, root, attachToRoot, component);
    }

    public static DialogWithdraw2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdraw2Binding inflate(LayoutInflater inflater, Object component) {
        return (DialogWithdraw2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_withdraw2, null, false, component);
    }

    public static DialogWithdraw2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdraw2Binding bind(View view, Object component) {
        return (DialogWithdraw2Binding) bind(component, view, R.layout.dialog_withdraw2);
    }
}
