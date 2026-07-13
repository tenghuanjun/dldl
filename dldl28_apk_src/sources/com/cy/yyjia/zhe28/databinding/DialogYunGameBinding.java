package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogYunGameBinding extends ViewDataBinding {
    public final EditText et;
    public final ImageView ivClose;

    @Bindable
    protected String mGame;
    public final RecyclerView rv;
    public final TextView tvSearch;

    public abstract void setGame(String game);

    protected DialogYunGameBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ImageView ivClose, RecyclerView rv, TextView tvSearch) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.ivClose = ivClose;
        this.rv = rv;
        this.tvSearch = tvSearch;
    }

    public String getGame() {
        return this.mGame;
    }

    public static DialogYunGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogYunGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_yun_game, root, attachToRoot, component);
    }

    public static DialogYunGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunGameBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogYunGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_yun_game, null, false, component);
    }

    public static DialogYunGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunGameBinding bind(View view, Object component) {
        return (DialogYunGameBinding) bind(component, view, R.layout.dialog_yun_game);
    }
}
