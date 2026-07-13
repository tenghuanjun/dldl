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
import com.cy.yyjia.zhe28.domain.BbsBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBbsReplyBinding extends ViewDataBinding {
    public final TextView btn;
    public final EditText et;

    @Bindable
    protected BbsBean mData;

    public abstract void setData(BbsBean data);

    protected DialogBbsReplyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, EditText et) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static DialogBbsReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsReplyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBbsReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_reply, root, attachToRoot, component);
    }

    public static DialogBbsReplyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsReplyBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogBbsReplyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_reply, null, false, component);
    }

    public static DialogBbsReplyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsReplyBinding bind(View view, Object component) {
        return (DialogBbsReplyBinding) bind(component, view, R.layout.dialog_bbs_reply);
    }
}
