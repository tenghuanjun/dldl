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
import com.cy.yyjia.zhe28.domain.NoviceGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbao648Binding extends ViewDataBinding {
    public final TextView btn;
    public final LinearLayout desc;

    @Bindable
    protected NoviceGameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;

    public abstract void setData(NoviceGameBean data);

    protected ItemSanbao648Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, LinearLayout desc, LayoutGameNameBinding name, LinearLayout tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.desc = desc;
        this.name = name;
        this.tag = tag;
    }

    public NoviceGameBean getData() {
        return this.mData;
    }

    public static ItemSanbao648Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbao648Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648, root, attachToRoot, component);
    }

    public static ItemSanbao648Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbao648Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648, null, false, component);
    }

    public static ItemSanbao648Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648Binding bind(View view, Object component) {
        return (ItemSanbao648Binding) bind(component, view, R.layout.item_sanbao_648);
    }
}
