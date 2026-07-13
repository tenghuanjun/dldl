package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogNoviceWelfareBinding extends ViewDataBinding {
    public final RecyclerView rvCoupon;
    public final RecyclerView rvGift;
    public final ShapeTextView tvClose;

    protected DialogNoviceWelfareBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvCoupon, RecyclerView rvGift, ShapeTextView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvCoupon = rvCoupon;
        this.rvGift = rvGift;
        this.tvClose = tvClose;
    }

    public static DialogNoviceWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoviceWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogNoviceWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_novice_welfare, root, attachToRoot, component);
    }

    public static DialogNoviceWelfareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoviceWelfareBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogNoviceWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_novice_welfare, null, false, component);
    }

    public static DialogNoviceWelfareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoviceWelfareBinding bind(View view, Object component) {
        return (DialogNoviceWelfareBinding) bind(component, view, R.layout.dialog_novice_welfare);
    }
}
