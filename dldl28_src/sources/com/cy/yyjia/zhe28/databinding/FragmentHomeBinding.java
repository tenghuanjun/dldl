package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GroupBuyBean;
import com.cy.yyjia.zhe28.domain.HomeBean;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHomeBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final ConvenientBanner banner;
    public final LinearLayout llSearch;

    @Bindable
    protected HomeBean mData;

    @Bindable
    protected GroupBuyBean mGroup;

    @Bindable
    protected boolean mIndex;
    public final NestedScrollView nsv;
    public final RecyclerView rv;
    public final RecyclerView rv1;
    public final RecyclerView rv3;
    public final RecyclerView rvFun;
    public final RecyclerView rvGame;
    public final RecyclerView rvMore;
    public final RecyclerView rvTry;
    public final SmartRefreshLayout srl;
    public final SmartRefreshLayout srl2;
    public final TextView tvMessage;
    public final TextView tvMonthCard;
    public final ImageView tvQiandao;
    public final ShapeLinearLayout tvSearch;
    public final AdapterViewFlipper vf;

    public abstract void setData(HomeBean data);

    public abstract void setGroup(GroupBuyBean group);

    public abstract void setIndex(boolean index);

    protected FragmentHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, ConvenientBanner banner, LinearLayout llSearch, NestedScrollView nsv, RecyclerView rv, RecyclerView rv1, RecyclerView rv3, RecyclerView rvFun, RecyclerView rvGame, RecyclerView rvMore, RecyclerView rvTry, SmartRefreshLayout srl, SmartRefreshLayout srl2, TextView tvMessage, TextView tvMonthCard, ImageView tvQiandao, ShapeLinearLayout tvSearch, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.banner = banner;
        this.llSearch = llSearch;
        this.nsv = nsv;
        this.rv = rv;
        this.rv1 = rv1;
        this.rv3 = rv3;
        this.rvFun = rvFun;
        this.rvGame = rvGame;
        this.rvMore = rvMore;
        this.rvTry = rvTry;
        this.srl = srl;
        this.srl2 = srl2;
        this.tvMessage = tvMessage;
        this.tvMonthCard = tvMonthCard;
        this.tvQiandao = tvQiandao;
        this.tvSearch = tvSearch;
        this.vf = vf;
    }

    public HomeBean getData() {
        return this.mData;
    }

    public GroupBuyBean getGroup() {
        return this.mGroup;
    }

    public boolean getIndex() {
        return this.mIndex;
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, root, attachToRoot, component);
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, null, false, component);
    }

    public static FragmentHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding bind(View view, Object component) {
        return (FragmentHomeBinding) bind(component, view, R.layout.fragment_home);
    }
}
