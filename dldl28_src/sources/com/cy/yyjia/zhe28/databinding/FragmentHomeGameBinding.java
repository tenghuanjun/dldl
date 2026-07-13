package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHomeGameBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final LinearLayout llSearch;
    public final RecyclerView rvGame;
    public final RecyclerView rvType;
    public final SmartRefreshLayout srl;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f458tv;
    public final ImageView tvQiandao;
    public final ShapeLinearLayout tvSearch;
    public final TextView tvThunt;
    public final AdapterViewFlipper vf;

    protected FragmentHomeGameBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, LinearLayout llSearch, RecyclerView rvGame, RecyclerView rvType, SmartRefreshLayout srl, TextView tv2, ImageView tvQiandao, ShapeLinearLayout tvSearch, TextView tvThunt, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.llSearch = llSearch;
        this.rvGame = rvGame;
        this.rvType = rvType;
        this.srl = srl;
        this.f458tv = tv2;
        this.tvQiandao = tvQiandao;
        this.tvSearch = tvSearch;
        this.tvThunt = tvThunt;
        this.vf = vf;
    }

    public static FragmentHomeGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_game, root, attachToRoot, component);
    }

    public static FragmentHomeGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeGameBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_game, null, false, component);
    }

    public static FragmentHomeGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeGameBinding bind(View view, Object component) {
        return (FragmentHomeGameBinding) bind(component, view, R.layout.fragment_home_game);
    }
}
