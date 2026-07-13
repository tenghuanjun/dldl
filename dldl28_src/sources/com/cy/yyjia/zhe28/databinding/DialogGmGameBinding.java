package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogGmGameBinding extends ViewDataBinding {
    public final ImageView ivClose;

    @Bindable
    protected String mText;
    public final RecyclerView rv;
    public final TextView tvSearch;

    public abstract void setText(String text);

    protected DialogGmGameBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, RecyclerView rv, TextView tvSearch) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.rv = rv;
        this.tvSearch = tvSearch;
    }

    public String getText() {
        return this.mText;
    }

    public static DialogGmGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGmGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_game, root, attachToRoot, component);
    }

    public static DialogGmGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmGameBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGmGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_game, null, false, component);
    }

    public static DialogGmGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmGameBinding bind(View view, Object component) {
        return (DialogGmGameBinding) bind(component, view, R.layout.dialog_gm_game);
    }
}
