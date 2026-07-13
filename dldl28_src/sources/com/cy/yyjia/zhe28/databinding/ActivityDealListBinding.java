package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FilterBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDealListBinding extends ViewDataBinding {
    public final ConstraintLayout clJianlou;
    public final EditText et;
    public final FrameLayout flFilter3;
    public final ImageView ivTag;

    @Bindable
    protected int mFilter;

    @Bindable
    protected int mJianlou;

    @Bindable
    protected String mKeyword;

    @Bindable
    protected String mMax;

    @Bindable
    protected String mMin;

    @Bindable
    protected FilterBean mOrder;

    @Bindable
    protected String mServer;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final RecyclerView rvFilter;
    public final SmartRefreshLayout srl;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeLinearLayout f440tv;
    public final TextView tvOrder;
    public final TextView tvPrice;
    public final ShapeTextView tvReset;
    public final ShapeTextView tvSearch;
    public final TextView tvServer;

    public abstract void setFilter(int filter);

    public abstract void setJianlou(int jianlou);

    public abstract void setKeyword(String keyword);

    public abstract void setMax(String max);

    public abstract void setMin(String min);

    public abstract void setOrder(FilterBean order);

    public abstract void setServer(String server);

    protected ActivityDealListBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clJianlou, EditText et, FrameLayout flFilter3, ImageView ivTag, Navigation navigation, RecyclerView rv, RecyclerView rvFilter, SmartRefreshLayout srl, ShapeLinearLayout tv2, TextView tvOrder, TextView tvPrice, ShapeTextView tvReset, ShapeTextView tvSearch, TextView tvServer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clJianlou = clJianlou;
        this.et = et;
        this.flFilter3 = flFilter3;
        this.ivTag = ivTag;
        this.navigation = navigation;
        this.rv = rv;
        this.rvFilter = rvFilter;
        this.srl = srl;
        this.f440tv = tv2;
        this.tvOrder = tvOrder;
        this.tvPrice = tvPrice;
        this.tvReset = tvReset;
        this.tvSearch = tvSearch;
        this.tvServer = tvServer;
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public String getServer() {
        return this.mServer;
    }

    public int getFilter() {
        return this.mFilter;
    }

    public String getMin() {
        return this.mMin;
    }

    public String getMax() {
        return this.mMax;
    }

    public FilterBean getOrder() {
        return this.mOrder;
    }

    public int getJianlou() {
        return this.mJianlou;
    }

    public static ActivityDealListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDealListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_list, root, attachToRoot, component);
    }

    public static ActivityDealListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealListBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDealListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_list, null, false, component);
    }

    public static ActivityDealListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealListBinding bind(View view, Object component) {
        return (ActivityDealListBinding) bind(component, view, R.layout.activity_deal_list);
    }
}
