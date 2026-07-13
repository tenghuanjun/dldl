package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySearchBinding extends ViewDataBinding {
    public final EditText et;
    public final ImageView ivClear;
    public final LinearLayout llEmpty;
    public final LinearLayout llGone;
    public final LinearLayout llInput;

    @Bindable
    protected boolean mSearch;
    public final Navigation navigation;
    public final RelativeLayout rlHistory;
    public final RecyclerView rv;
    public final RecyclerView rvEmpty;
    public final RecyclerView rvHistory;
    public final RecyclerView rvHot;
    public final RecyclerView rvType;
    public final TextView tvApply;
    public final TextView tvClean;
    public final TextView tvEmpty;
    public final TextView tvSearch;

    public abstract void setSearch(boolean search);

    protected ActivitySearchBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ImageView ivClear, LinearLayout llEmpty, LinearLayout llGone, LinearLayout llInput, Navigation navigation, RelativeLayout rlHistory, RecyclerView rv, RecyclerView rvEmpty, RecyclerView rvHistory, RecyclerView rvHot, RecyclerView rvType, TextView tvApply, TextView tvClean, TextView tvEmpty, TextView tvSearch) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.ivClear = ivClear;
        this.llEmpty = llEmpty;
        this.llGone = llGone;
        this.llInput = llInput;
        this.navigation = navigation;
        this.rlHistory = rlHistory;
        this.rv = rv;
        this.rvEmpty = rvEmpty;
        this.rvHistory = rvHistory;
        this.rvHot = rvHot;
        this.rvType = rvType;
        this.tvApply = tvApply;
        this.tvClean = tvClean;
        this.tvEmpty = tvEmpty;
        this.tvSearch = tvSearch;
    }

    public boolean getSearch() {
        return this.mSearch;
    }

    public static ActivitySearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_search, root, attachToRoot, component);
    }

    public static ActivitySearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_search, null, false, component);
    }

    public static ActivitySearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding bind(View view, Object component) {
        return (ActivitySearchBinding) bind(component, view, R.layout.activity_search);
    }
}
