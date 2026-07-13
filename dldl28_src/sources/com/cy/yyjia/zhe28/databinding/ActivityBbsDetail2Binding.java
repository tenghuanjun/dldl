package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBbsDetail2Binding extends ViewDataBinding {
    public final TextView btn;
    public final ImageView ivActivity;

    @Bindable
    protected ChampionshipBean mActivity;

    @Bindable
    protected BbsBean mData;

    @Bindable
    protected String mText;
    public final LayoutGameNameBinding name;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final RecyclerView rvPic;
    public final TextView tvPraise;
    public final TextView tvSort;
    public final WebView wv;

    public abstract void setActivity(ChampionshipBean activity);

    public abstract void setData(BbsBean data);

    public abstract void setText(String text);

    protected ActivityBbsDetail2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, ImageView ivActivity, LayoutGameNameBinding name, Navigation navigation, RecyclerView rv, RecyclerView rvPic, TextView tvPraise, TextView tvSort, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.ivActivity = ivActivity;
        this.name = name;
        this.navigation = navigation;
        this.rv = rv;
        this.rvPic = rvPic;
        this.tvPraise = tvPraise;
        this.tvSort = tvSort;
        this.wv = wv;
    }

    public String getText() {
        return this.mText;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public ChampionshipBean getActivity() {
        return this.mActivity;
    }

    public static ActivityBbsDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetail2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBbsDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_detail2, root, attachToRoot, component);
    }

    public static ActivityBbsDetail2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetail2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBbsDetail2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_detail2, null, false, component);
    }

    public static ActivityBbsDetail2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsDetail2Binding bind(View view, Object component) {
        return (ActivityBbsDetail2Binding) bind(component, view, R.layout.activity_bbs_detail2);
    }
}
