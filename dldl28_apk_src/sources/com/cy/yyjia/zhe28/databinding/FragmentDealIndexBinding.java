package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDealIndexBinding extends ViewDataBinding {
    public final AppBarLayout abl;
    public final EditText et;
    public final ImageView ivRule;
    public final ImageView ivSearch;
    public final LinearLayout llSearch;

    @Bindable
    protected boolean mFolder;

    @Bindable
    protected String mKeyword;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final RecyclerView rv;
    public final TextView tvFilter;
    public final ShapeLinearLayout tvSearch;
    public final ViewPager2 vp2;

    public abstract void setFolder(boolean folder);

    public abstract void setKeyword(String keyword);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentDealIndexBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout abl, EditText et, ImageView ivRule, ImageView ivSearch, LinearLayout llSearch, RecyclerView rv, TextView tvFilter, ShapeLinearLayout tvSearch, ViewPager2 vp2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.abl = abl;
        this.et = et;
        this.ivRule = ivRule;
        this.ivSearch = ivSearch;
        this.llSearch = llSearch;
        this.rv = rv;
        this.tvFilter = tvFilter;
        this.tvSearch = tvSearch;
        this.vp2 = vp2;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public String getKeyword() {
        return this.mKeyword;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean getFolder() {
        return this.mFolder;
    }

    public static FragmentDealIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDealIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_index, root, attachToRoot, component);
    }

    public static FragmentDealIndexBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealIndexBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDealIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_index, null, false, component);
    }

    public static FragmentDealIndexBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealIndexBinding bind(View view, Object component) {
        return (FragmentDealIndexBinding) bind(component, view, R.layout.fragment_deal_index);
    }
}
