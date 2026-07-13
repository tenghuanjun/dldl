package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameIntro4Binding extends ViewDataBinding {
    public final View bg;
    public final RecyclerView rv;

    protected ItemGameIntro4Binding(Object _bindingComponent, View _root, int _localFieldCount, View bg, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.rv = rv;
    }

    public static ItemGameIntro4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameIntro4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro4, root, attachToRoot, component);
    }

    public static ItemGameIntro4Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro4Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameIntro4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro4, null, false, component);
    }

    public static ItemGameIntro4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro4Binding bind(View view, Object component) {
        return (ItemGameIntro4Binding) bind(component, view, R.layout.item_game_intro4);
    }
}
