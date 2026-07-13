package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogInviteRecordBinding extends ViewDataBinding {
    public final RecyclerView rv;
    public final ImageView tvClose;

    protected DialogInviteRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, ImageView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvClose = tvClose;
    }

    public static DialogInviteRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogInviteRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite_record, root, attachToRoot, component);
    }

    public static DialogInviteRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogInviteRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite_record, null, false, component);
    }

    public static DialogInviteRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteRecordBinding bind(View view, Object component) {
        return (DialogInviteRecordBinding) bind(component, view, R.layout.dialog_invite_record);
    }
}
