package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentGameBinding extends ViewDataBinding {

    @Bindable
    protected String mBanner;
    public final RecyclerView rvGame;
    public final RecyclerView rvType;
    public final SmartRefreshLayout srl;

    public abstract void setBanner(String banner);

    protected FragmentGameBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvGame, RecyclerView rvType, SmartRefreshLayout srl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvGame = rvGame;
        this.rvType = rvType;
        this.srl = srl;
    }

    public String getBanner() {
        return this.mBanner;
    }

    public static FragmentGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game, root, attachToRoot, component);
    }

    public static FragmentGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game, null, false, component);
    }

    public static FragmentGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameBinding bind(View view, Object component) {
        return (FragmentGameBinding) bind(component, view, R.layout.fragment_game);
    }
}
