package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogCommentBinding extends ViewDataBinding {
    public final Button btn;
    public final EditText et;

    @Bindable
    protected String mText;

    public abstract void setText(String text);

    protected DialogCommentBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btn, EditText et) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
    }

    public String getText() {
        return this.mText;
    }

    public static DialogCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_comment, root, attachToRoot, component);
    }

    public static DialogCommentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCommentBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_comment, null, false, component);
    }

    public static DialogCommentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCommentBinding bind(View view, Object component) {
        return (DialogCommentBinding) bind(component, view, R.layout.dialog_comment);
    }
}
