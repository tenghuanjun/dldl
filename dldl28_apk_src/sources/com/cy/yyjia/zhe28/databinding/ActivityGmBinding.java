package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMItemBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeImageView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGmBinding extends ViewDataBinding {
    public final TextView btn;
    public final TextView btn1;
    public final TextView btn2;
    public final TextView btn3;
    public final TextView btn4;
    public final TextView btn5;
    public final TextView btn6;
    public final TextView btn7;
    public final FrameLayout fl;
    public final ShapeImageView iv;
    public final ShapeLinearLayout ll;
    public final LinearLayout llItem;
    public final LinearLayout llRoles;

    @Bindable
    protected String mCount;

    @Bindable
    protected GMGameBean mGame;

    @Bindable
    protected GMItemBean mItem;

    @Bindable
    protected GMRoleBean mRole;

    @Bindable
    protected GMTitleBean mTitle;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final ShapeTextView tvTrans;

    public abstract void setCount(String count);

    public abstract void setGame(GMGameBean game);

    public abstract void setItem(GMItemBean item);

    public abstract void setRole(GMRoleBean role);

    public abstract void setTitle(GMTitleBean title);

    protected ActivityGmBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, TextView btn1, TextView btn2, TextView btn3, TextView btn4, TextView btn5, TextView btn6, TextView btn7, FrameLayout fl, ShapeImageView iv, ShapeLinearLayout ll, LinearLayout llItem, LinearLayout llRoles, Navigation navigation, RecyclerView rv, ShapeTextView tvTrans) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.btn5 = btn5;
        this.btn6 = btn6;
        this.btn7 = btn7;
        this.fl = fl;
        this.iv = iv;
        this.ll = ll;
        this.llItem = llItem;
        this.llRoles = llRoles;
        this.navigation = navigation;
        this.rv = rv;
        this.tvTrans = tvTrans;
    }

    public String getCount() {
        return this.mCount;
    }

    public GMGameBean getGame() {
        return this.mGame;
    }

    public GMRoleBean getRole() {
        return this.mRole;
    }

    public GMTitleBean getTitle() {
        return this.mTitle;
    }

    public GMItemBean getItem() {
        return this.mItem;
    }

    public static ActivityGmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm, root, attachToRoot, component);
    }

    public static ActivityGmBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm, null, false, component);
    }

    public static ActivityGmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmBinding bind(View view, Object component) {
        return (ActivityGmBinding) bind(component, view, R.layout.activity_gm);
    }
}
