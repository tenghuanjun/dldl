package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealBuyNoticeBinding extends ViewDataBinding {
    public final TextView title;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f448tv;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;
    public final TextView tvTag1;
    public final TextView tvTopTag1;
    public final TextView tvTopTag2;
    public final TextView tvTopTag3;
    public final TextView tvTopTag4;

    protected DialogDealBuyNoticeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView title, TextView tv2, ShapeTextView tvCancel, ShapeTextView tvGo, TextView tvTag1, TextView tvTopTag1, TextView tvTopTag2, TextView tvTopTag3, TextView tvTopTag4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.title = title;
        this.f448tv = tv2;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
        this.tvTag1 = tvTag1;
        this.tvTopTag1 = tvTopTag1;
        this.tvTopTag2 = tvTopTag2;
        this.tvTopTag3 = tvTopTag3;
        this.tvTopTag4 = tvTopTag4;
    }

    public static DialogDealBuyNoticeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealBuyNoticeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealBuyNoticeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_buy_notice, root, attachToRoot, component);
    }

    public static DialogDealBuyNoticeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealBuyNoticeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealBuyNoticeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_buy_notice, null, false, component);
    }

    public static DialogDealBuyNoticeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealBuyNoticeBinding bind(View view, Object component) {
        return (DialogDealBuyNoticeBinding) bind(component, view, R.layout.dialog_deal_buy_notice);
    }
}
