package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealOffsetBinding extends ViewDataBinding {
    public final ShapeTextView tvGo;

    protected DialogDealOffsetBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvGo = tvGo;
    }

    public static DialogDealOffsetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealOffsetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealOffsetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_offset, root, attachToRoot, component);
    }

    public static DialogDealOffsetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealOffsetBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealOffsetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_offset, null, false, component);
    }

    public static DialogDealOffsetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealOffsetBinding bind(View view, Object component) {
        return (DialogDealOffsetBinding) bind(component, view, R.layout.dialog_deal_offset);
    }
}
