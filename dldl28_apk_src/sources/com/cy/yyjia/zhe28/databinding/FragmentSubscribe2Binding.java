package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
public abstract class FragmentSubscribe2Binding extends ViewDataBinding {

    @Bindable
    protected CardModuleBean mData;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected UserBean mUser;
    public final RecyclerView rv;
    public final RecyclerView rvTitle;
    public final LinearLayout tvBuy;
    public final ShapeTextView tvGain;
    public final TextView tvGame;
    public final TextView tvNickname;

    public abstract void setData(CardModuleBean data);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setUser(UserBean user);

    protected FragmentSubscribe2Binding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, RecyclerView rvTitle, LinearLayout tvBuy, ShapeTextView tvGain, TextView tvGame, TextView tvNickname) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.rvTitle = rvTitle;
        this.tvBuy = tvBuy;
        this.tvGain = tvGain;
        this.tvGame = tvGame;
        this.tvNickname = tvNickname;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public UserBean getUser() {
        return this.mUser;
    }

    public CardModuleBean getData() {
        return this.mData;
    }

    public static FragmentSubscribe2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubscribe2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSubscribe2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_subscribe2, root, attachToRoot, component);
    }

    public static FragmentSubscribe2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubscribe2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSubscribe2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_subscribe2, null, false, component);
    }

    public static FragmentSubscribe2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubscribe2Binding bind(View view, Object component) {
        return (FragmentSubscribe2Binding) bind(component, view, R.layout.fragment_subscribe2);
    }
}
