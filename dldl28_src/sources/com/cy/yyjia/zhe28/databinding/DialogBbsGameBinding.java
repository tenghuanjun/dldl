package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBbsGameBinding extends ViewDataBinding {

    @Bindable
    protected String mGame;
    public final RecyclerView rv;
    public final TextView tvClose;
    public final TextView tvTitle;

    public abstract void setGame(String game);

    protected DialogBbsGameBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, TextView tvClose, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvClose = tvClose;
        this.tvTitle = tvTitle;
    }

    public String getGame() {
        return this.mGame;
    }

    public static DialogBbsGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBbsGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_game, root, attachToRoot, component);
    }

    public static DialogBbsGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsGameBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogBbsGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bbs_game, null, false, component);
    }

    public static DialogBbsGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBbsGameBinding bind(View view, Object component) {
        return (DialogBbsGameBinding) bind(component, view, R.layout.dialog_bbs_game);
    }
}
