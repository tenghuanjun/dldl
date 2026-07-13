package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogInviteGameBinding extends ViewDataBinding {

    @Bindable
    protected String mText;
    public final RecyclerView rv;
    public final ImageView tvClose;

    public abstract void setText(String text);

    protected DialogInviteGameBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, ImageView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvClose = tvClose;
    }

    public String getText() {
        return this.mText;
    }

    public static DialogInviteGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogInviteGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite_game, root, attachToRoot, component);
    }

    public static DialogInviteGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteGameBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogInviteGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invite_game, null, false, component);
    }

    public static DialogInviteGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInviteGameBinding bind(View view, Object component) {
        return (DialogInviteGameBinding) bind(component, view, R.layout.dialog_invite_game);
    }
}
