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
public abstract class DialogNewGameTypeBinding extends ViewDataBinding {
    public final ImageView ivClose;
    public final RecyclerView rv;

    protected DialogNewGameTypeBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.rv = rv;
    }

    public static DialogNewGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNewGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogNewGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_new_game_type, root, attachToRoot, component);
    }

    public static DialogNewGameTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNewGameTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogNewGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_new_game_type, null, false, component);
    }

    public static DialogNewGameTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNewGameTypeBinding bind(View view, Object component) {
        return (DialogNewGameTypeBinding) bind(component, view, R.layout.dialog_new_game_type);
    }
}
