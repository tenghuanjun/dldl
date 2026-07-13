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
import com.cy.yyjia.zhe28.domain.SanbaoBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbaoGameBinding extends ViewDataBinding {
    public final LinearLayout desc;

    @Bindable
    protected String mBtn;

    @Bindable
    protected SanbaoBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final TextView tvBtn;

    public abstract void setBtn(String btn);

    public abstract void setData(SanbaoBean data);

    protected ItemSanbaoGameBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutGameNameBinding name, LinearLayout tag, TextView tvBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.name = name;
        this.tag = tag;
        this.tvBtn = tvBtn;
    }

    public String getBtn() {
        return this.mBtn;
    }

    public SanbaoBean getData() {
        return this.mData;
    }

    public static ItemSanbaoGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbaoGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_game, root, attachToRoot, component);
    }

    public static ItemSanbaoGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbaoGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_game, null, false, component);
    }

    public static ItemSanbaoGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoGameBinding bind(View view, Object component) {
        return (ItemSanbaoGameBinding) bind(component, view, R.layout.item_sanbao_game);
    }
}
