package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UpdateBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogUpdateBinding extends ViewDataBinding {
    public final RelativeLayout btnDownload;

    @Bindable
    protected int mCurrent;

    @Bindable
    protected UpdateBean mData;

    @Bindable
    protected int mMax;

    @Bindable
    protected String mNumber;
    public final ProgressBar pb;
    public final TextView tvContent;
    public final TextView tvPb;
    public final TextView tvService;

    public abstract void setCurrent(int current);

    public abstract void setData(UpdateBean data);

    public abstract void setMax(int max);

    public abstract void setNumber(String number);

    protected DialogUpdateBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout btnDownload, ProgressBar pb, TextView tvContent, TextView tvPb, TextView tvService) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnDownload = btnDownload;
        this.pb = pb;
        this.tvContent = tvContent;
        this.tvPb = tvPb;
        this.tvService = tvService;
    }

    public int getMax() {
        return this.mMax;
    }

    public int getCurrent() {
        return this.mCurrent;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public UpdateBean getData() {
        return this.mData;
    }

    public static DialogUpdateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogUpdateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_update, root, attachToRoot, component);
    }

    public static DialogUpdateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogUpdateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_update, null, false, component);
    }

    public static DialogUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding bind(View view, Object component) {
        return (DialogUpdateBinding) bind(component, view, R.layout.dialog_update);
    }
}
