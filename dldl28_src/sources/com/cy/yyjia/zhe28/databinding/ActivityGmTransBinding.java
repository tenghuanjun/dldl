package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGmTransBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final FrameLayout fl;
    public final LinearLayout llGame;
    public final LinearLayout llRoles;

    @Bindable
    protected GMGameBean mGame;

    @Bindable
    protected GMGameBean mOldGame;

    @Bindable
    protected GMRoleBean mOldRole;

    @Bindable
    protected GMTitleBean mOldTitle;

    @Bindable
    protected GMRoleBean mRole;

    @Bindable
    protected GMTitleBean mTitle;
    public final Navigation navigation;
    public final RecyclerView rv;

    public abstract void setGame(GMGameBean game);

    public abstract void setOldGame(GMGameBean oldGame);

    public abstract void setOldRole(GMRoleBean oldRole);

    public abstract void setOldTitle(GMTitleBean oldTitle);

    public abstract void setRole(GMRoleBean role);

    public abstract void setTitle(GMTitleBean title);

    protected ActivityGmTransBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, FrameLayout fl, LinearLayout llGame, LinearLayout llRoles, Navigation navigation, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.fl = fl;
        this.llGame = llGame;
        this.llRoles = llRoles;
        this.navigation = navigation;
        this.rv = rv;
    }

    public GMGameBean getOldGame() {
        return this.mOldGame;
    }

    public GMGameBean getGame() {
        return this.mGame;
    }

    public GMRoleBean getOldRole() {
        return this.mOldRole;
    }

    public GMRoleBean getRole() {
        return this.mRole;
    }

    public GMTitleBean getOldTitle() {
        return this.mOldTitle;
    }

    public GMTitleBean getTitle() {
        return this.mTitle;
    }

    public static ActivityGmTransBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGmTransBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm_trans, root, attachToRoot, component);
    }

    public static ActivityGmTransBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGmTransBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm_trans, null, false, component);
    }

    public static ActivityGmTransBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransBinding bind(View view, Object component) {
        return (ActivityGmTransBinding) bind(component, view, R.layout.activity_gm_trans);
    }
}
