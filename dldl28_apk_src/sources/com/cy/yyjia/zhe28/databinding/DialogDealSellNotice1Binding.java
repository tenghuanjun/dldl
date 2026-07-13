package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealSellNotice1Binding extends ViewDataBinding {
    public final LinearLayout linTag1;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;

    protected DialogDealSellNotice1Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout linTag1, ShapeTextView tvCancel, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linTag1 = linTag1;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    public static DialogDealSellNotice1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealSellNotice1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealSellNotice1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_sell_notice1, root, attachToRoot, component);
    }

    public static DialogDealSellNotice1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealSellNotice1Binding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealSellNotice1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_sell_notice1, null, false, component);
    }

    public static DialogDealSellNotice1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealSellNotice1Binding bind(View view, Object component) {
        return (DialogDealSellNotice1Binding) bind(component, view, R.layout.dialog_deal_sell_notice1);
    }
}
