package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHallGameBinding extends ViewDataBinding {
    public final RecyclerView rvGame;
    public final RecyclerView rvType;
    public final SmartRefreshLayout srl;

    protected FragmentHallGameBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvGame, RecyclerView rvType, SmartRefreshLayout srl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvGame = rvGame;
        this.rvType = rvType;
        this.srl = srl;
    }

    public static FragmentHallGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHallGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_hall_game, root, attachToRoot, component);
    }

    public static FragmentHallGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallGameBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHallGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_hall_game, null, false, component);
    }

    public static FragmentHallGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallGameBinding bind(View view, Object component) {
        return (FragmentHallGameBinding) bind(component, view, R.layout.fragment_hall_game);
    }
}
