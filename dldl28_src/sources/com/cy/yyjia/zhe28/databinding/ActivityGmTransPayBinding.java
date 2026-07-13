package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGmTransPayBinding extends ViewDataBinding {
    public final ShapeTextView btn;

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

    @Bindable
    protected String mUsername;
    public final TextView tvPrice;

    public abstract void setGame(GMGameBean game);

    public abstract void setOldGame(GMGameBean oldGame);

    public abstract void setOldRole(GMRoleBean oldRole);

    public abstract void setOldTitle(GMTitleBean oldTitle);

    public abstract void setRole(GMRoleBean role);

    public abstract void setTitle(GMTitleBean title);

    public abstract void setUsername(String username);

    protected ActivityGmTransPayBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, TextView tvPrice) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.tvPrice = tvPrice;
    }

    public String getUsername() {
        return this.mUsername;
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

    public GMTitleBean getTitle() {
        return this.mTitle;
    }

    public GMTitleBean getOldTitle() {
        return this.mOldTitle;
    }

    public static ActivityGmTransPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGmTransPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm_trans_pay, root, attachToRoot, component);
    }

    public static ActivityGmTransPayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransPayBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGmTransPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_gm_trans_pay, null, false, component);
    }

    public static ActivityGmTransPayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGmTransPayBinding bind(View view, Object component) {
        return (ActivityGmTransPayBinding) bind(component, view, R.layout.activity_gm_trans_pay);
    }
}
