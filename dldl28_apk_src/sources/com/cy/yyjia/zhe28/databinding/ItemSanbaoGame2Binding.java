package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbaoGame2Binding extends ViewDataBinding {
    public final LinearLayout desc;

    @Bindable
    protected GameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final TextView tvBtn;

    public abstract void setData(GameBean data);

    protected ItemSanbaoGame2Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutGameNameBinding name, LinearLayout tag, TextView tvBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.name = name;
        this.tag = tag;
        this.tvBtn = tvBtn;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemSanbaoGame2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGame2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbaoGame2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_game2, root, attachToRoot, component);
    }

    public static ItemSanbaoGame2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGame2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbaoGame2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_game2, null, false, component);
    }

    public static ItemSanbaoGame2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGame2Binding bind(View view, Object component) {
        return (ItemSanbaoGame2Binding) bind(component, view, R.layout.item_sanbao_game2);
    }
}
