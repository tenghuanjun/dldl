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
import com.cy.yyjia.zhe28.domain.GameBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentItemTradeBinding extends ViewDataBinding {
    public final LinearLayout llFilter;

    @Bindable
    protected GameBean mGame;

    @Bindable
    protected String mServer;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final TextView tvServer;
    public final TextView tvService;

    public abstract void setGame(GameBean game);

    public abstract void setServer(String server);

    protected FragmentItemTradeBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llFilter, RecyclerView rv, SmartRefreshLayout srl, TextView tvServer, TextView tvService) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llFilter = llFilter;
        this.rv = rv;
        this.srl = srl;
        this.tvServer = tvServer;
        this.tvService = tvService;
    }

    public GameBean getGame() {
        return this.mGame;
    }

    public String getServer() {
        return this.mServer;
    }

    public static FragmentItemTradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentItemTradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentItemTradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_item_trade, root, attachToRoot, component);
    }

    public static FragmentItemTradeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentItemTradeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentItemTradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_item_trade, null, false, component);
    }

    public static FragmentItemTradeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentItemTradeBinding bind(View view, Object component) {
        return (FragmentItemTradeBinding) bind(component, view, R.layout.fragment_item_trade);
    }
}
