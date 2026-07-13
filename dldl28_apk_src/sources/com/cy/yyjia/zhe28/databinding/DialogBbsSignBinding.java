package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsSignBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBbsSignBinding extends ViewDataBinding {

    @Bindable
    protected BbsSignBean mData;
    public final RecyclerView rv;
    public final RecyclerView rv2;
    public final ShapeTextView tvSign;

    public abstract void setData(BbsSignBean data);

    protected DialogBbsSignBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, RecyclerView rv2, ShapeTextView tvSign) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.rv2 = rv2;
        this.tvSign = tvSign;
    }

    public BbsSignBean getData() {
        return this.mData;
    }

    public static DialogBbsSignBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsSignBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBbsSignBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_sign, root, attachToRoot, component);
    }

    public static DialogBbsSignBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsSignBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogBbsSignBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_sign, null, false, component);
    }

    public static DialogBbsSignBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsSignBinding bind(View view, Object component) {
        return (DialogBbsSignBinding) bind(component, view, R.layout.dialog_bbs_sign);
    }
}
