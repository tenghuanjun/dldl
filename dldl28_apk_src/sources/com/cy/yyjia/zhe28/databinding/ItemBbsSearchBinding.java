package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.view.ExpandableTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsSearchBinding extends ViewDataBinding {

    @Bindable
    protected BbsBean mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ExpandableTextView f462tv;
    public final TextView tvPraise;
    public final TextView tvTitle;

    public abstract void setData(BbsBean data);

    protected ItemBbsSearchBinding(Object _bindingComponent, View _root, int _localFieldCount, ExpandableTextView tv2, TextView tvPraise, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f462tv = tv2;
        this.tvPraise = tvPraise;
        this.tvTitle = tvTitle;
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ItemBbsSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_search, root, attachToRoot, component);
    }

    public static ItemBbsSearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSearchBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_search, null, false, component);
    }

    public static ItemBbsSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSearchBinding bind(View view, Object component) {
        return (ItemBbsSearchBinding) bind(component, view, R.layout.item_bbs_search);
    }
}
