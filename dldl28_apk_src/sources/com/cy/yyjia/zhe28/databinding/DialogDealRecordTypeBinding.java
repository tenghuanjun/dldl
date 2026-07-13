package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealRecordTypeBinding extends ViewDataBinding {
    public final RecyclerView rv;

    protected DialogDealRecordTypeBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public static DialogDealRecordTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealRecordTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealRecordTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_record_type, root, attachToRoot, component);
    }

    public static DialogDealRecordTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealRecordTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealRecordTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_record_type, null, false, component);
    }

    public static DialogDealRecordTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealRecordTypeBinding bind(View view, Object component) {
        return (DialogDealRecordTypeBinding) bind(component, view, R.layout.dialog_deal_record_type);
    }
}
