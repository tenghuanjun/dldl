package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMonthCardBinding extends ViewDataBinding {
    public final ImageView ivBack;

    @Bindable
    protected CardModuleBean mData;

    @Bindable
    protected UserBean mUser;
    public final RelativeLayout rlTitle;
    public final RecyclerView rv;
    public final RecyclerView rvTitle;
    public final LinearLayout tvBuy;
    public final ShapeTextView tvGain;
    public final TextView tvGame;
    public final TextView tvNickname;
    public final TextView tvRecord;

    public abstract void setData(CardModuleBean data);

    public abstract void setUser(UserBean user);

    protected ActivityMonthCardBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivBack, RelativeLayout rlTitle, RecyclerView rv, RecyclerView rvTitle, LinearLayout tvBuy, ShapeTextView tvGain, TextView tvGame, TextView tvNickname, TextView tvRecord) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivBack = ivBack;
        this.rlTitle = rlTitle;
        this.rv = rv;
        this.rvTitle = rvTitle;
        this.tvBuy = tvBuy;
        this.tvGain = tvGain;
        this.tvGame = tvGame;
        this.tvNickname = tvNickname;
        this.tvRecord = tvRecord;
    }

    public UserBean getUser() {
        return this.mUser;
    }

    public CardModuleBean getData() {
        return this.mData;
    }

    public static ActivityMonthCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMonthCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_month_card, root, attachToRoot, component);
    }

    public static ActivityMonthCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthCardBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMonthCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_month_card, null, false, component);
    }

    public static ActivityMonthCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthCardBinding bind(View view, Object component) {
        return (ActivityMonthCardBinding) bind(component, view, R.layout.activity_month_card);
    }
}
