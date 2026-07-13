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
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityItemTradeDetailBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final EditText et;
    public final ImageView ivDown;
    public final ImageView ivUp;

    @Bindable
    protected ItemTradeBean mData;

    @Bindable
    protected String mRole;

    @Bindable
    protected String mRoleId;
    public final Navigation navigation;
    public final TextView tvPrice;
    public final TextView tvRole;

    public abstract void setData(ItemTradeBean data);

    public abstract void setRole(String role);

    public abstract void setRoleId(String roleId);

    protected ActivityItemTradeDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, EditText et, ImageView ivDown, ImageView ivUp, Navigation navigation, TextView tvPrice, TextView tvRole) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.ivDown = ivDown;
        this.ivUp = ivUp;
        this.navigation = navigation;
        this.tvPrice = tvPrice;
        this.tvRole = tvRole;
    }

    public ItemTradeBean getData() {
        return this.mData;
    }

    public String getRole() {
        return this.mRole;
    }

    public String getRoleId() {
        return this.mRoleId;
    }

    public static ActivityItemTradeDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemTradeDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityItemTradeDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_trade_detail, root, attachToRoot, component);
    }

    public static ActivityItemTradeDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemTradeDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityItemTradeDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_trade_detail, null, false, component);
    }

    public static ActivityItemTradeDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemTradeDetailBinding bind(View view, Object component) {
        return (ActivityItemTradeDetailBinding) bind(component, view, R.layout.activity_item_trade_detail);
    }
}
