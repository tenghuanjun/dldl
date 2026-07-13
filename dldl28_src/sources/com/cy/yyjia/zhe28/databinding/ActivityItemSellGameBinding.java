package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityItemSellGameBinding extends ViewDataBinding {
    public final ShapeEditText et;

    @Bindable
    protected String mText;
    public final Navigation navigation;
    public final RecyclerView rv;

    public abstract void setText(String text);

    protected ActivityItemSellGameBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeEditText et, Navigation navigation, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.navigation = navigation;
        this.rv = rv;
    }

    public String getText() {
        return this.mText;
    }

    public static ActivityItemSellGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityItemSellGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell_game, root, attachToRoot, component);
    }

    public static ActivityItemSellGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityItemSellGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell_game, null, false, component);
    }

    public static ActivityItemSellGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellGameBinding bind(View view, Object component) {
        return (ActivityItemSellGameBinding) bind(component, view, R.layout.activity_item_sell_game);
    }
}
