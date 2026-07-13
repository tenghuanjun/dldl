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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogPinBinding extends ViewDataBinding {
    public final EditText edit;

    @Bindable
    protected String mData;

    @Bindable
    protected String mTip;

    @Bindable
    protected String mTitle;
    public final ShapeTextView tvGo;
    public final ShapeTextView tvPassword1;
    public final ShapeTextView tvPassword2;
    public final ShapeTextView tvPassword3;
    public final ShapeTextView tvPassword4;
    public final TextView tvTag1;
    public final TextView tvTag3;

    public abstract void setData(String data);

    public abstract void setTip(String tip);

    public abstract void setTitle(String title);

    protected DialogPinBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText edit, ShapeTextView tvGo, ShapeTextView tvPassword1, ShapeTextView tvPassword2, ShapeTextView tvPassword3, ShapeTextView tvPassword4, TextView tvTag1, TextView tvTag3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.edit = edit;
        this.tvGo = tvGo;
        this.tvPassword1 = tvPassword1;
        this.tvPassword2 = tvPassword2;
        this.tvPassword3 = tvPassword3;
        this.tvPassword4 = tvPassword4;
        this.tvTag1 = tvTag1;
        this.tvTag3 = tvTag3;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTip() {
        return this.mTip;
    }

    public String getData() {
        return this.mData;
    }

    public static DialogPinBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPinBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPinBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pin, root, attachToRoot, component);
    }

    public static DialogPinBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPinBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPinBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pin, null, false, component);
    }

    public static DialogPinBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPinBinding bind(View view, Object component) {
        return (DialogPinBinding) bind(component, view, R.layout.dialog_pin);
    }
}
