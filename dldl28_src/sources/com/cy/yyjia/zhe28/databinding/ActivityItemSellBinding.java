package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityItemSellBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final EditText et;
    public final ImageView iv;
    public final LinearLayout llGame;
    public final LinearLayout llServer;

    @Bindable
    protected ItemTradeBean mData;

    @Bindable
    protected String mDesc;
    public final Navigation navigation;

    public abstract void setData(ItemTradeBean data);

    public abstract void setDesc(String desc);

    protected ActivityItemSellBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, EditText et, ImageView iv, LinearLayout llGame, LinearLayout llServer, Navigation navigation) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.iv = iv;
        this.llGame = llGame;
        this.llServer = llServer;
        this.navigation = navigation;
    }

    public ItemTradeBean getData() {
        return this.mData;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public static ActivityItemSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityItemSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell, root, attachToRoot, component);
    }

    public static ActivityItemSellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityItemSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell, null, false, component);
    }

    public static ActivityItemSellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellBinding bind(View view, Object component) {
        return (ActivityItemSellBinding) bind(component, view, R.layout.activity_item_sell);
    }
}
