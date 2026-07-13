package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealDickerBinding extends ViewDataBinding {
    public final ShapeTextView cancel;

    @Bindable
    protected String mPrice;
    public final ShapeTextView sure;

    public abstract void setPrice(String price);

    protected DialogDealDickerBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView cancel, ShapeTextView sure) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cancel = cancel;
        this.sure = sure;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public static DialogDealDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_dicker, root, attachToRoot, component);
    }

    public static DialogDealDickerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDickerBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_dicker, null, false, component);
    }

    public static DialogDealDickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDickerBinding bind(View view, Object component) {
        return (DialogDealDickerBinding) bind(component, view, R.layout.dialog_deal_dicker);
    }
}
