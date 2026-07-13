package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemFudaiIndexBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected FudaiIndexBean.List mData;

    @Bindable
    protected boolean mWo;

    public abstract void setData(FudaiIndexBean.List data);

    public abstract void setWo(boolean wo);

    protected ItemFudaiIndexBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public boolean getWo() {
        return this.mWo;
    }

    public FudaiIndexBean.List getData() {
        return this.mData;
    }

    public static ItemFudaiIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFudaiIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemFudaiIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fudai_index, root, attachToRoot, component);
    }

    public static ItemFudaiIndexBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFudaiIndexBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemFudaiIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fudai_index, null, false, component);
    }

    public static ItemFudaiIndexBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFudaiIndexBinding bind(View view, Object component) {
        return (ItemFudaiIndexBinding) bind(component, view, R.layout.item_fudai_index);
    }
}
