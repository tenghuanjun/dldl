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
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.layout.ShapeRelativeLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDealSellInfoBinding extends ViewDataBinding {
    public final EditText etDesc;
    public final EditText etPrice;
    public final ImageView gameIcon;
    public final ImageView ivMore;
    public final ImageView ivMore2;
    public final ShapeLinearLayout linTag1;
    public final ShapeLinearLayout linTag2;
    public final ShapeLinearLayout linTag3;
    public final LinearLayout ll;

    @Bindable
    protected DealBean mData;
    public final Navigation reTag;
    public final RelativeLayout reTag2;
    public final ShapeRelativeLayout reTag3;
    public final TextView roleName;
    public final RecyclerView rv;
    public final View tagView;
    public final TextView tvDicker;
    public final TextView tvLimit;
    public final TextView tvTag1;
    public final TextView tvTag2;
    public final TextView tvTag3;
    public final TextView tvTag4;
    public final TextView tvTag5;
    public final TextView tvTag6;
    public final TextView tvTag7;
    public final TextView tvTag8;
    public final TextView tvTag9;
    public final View viewTag;
    public final View viewTag1;
    public final View viewTag2;

    public abstract void setData(DealBean data);

    protected ActivityDealSellInfoBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText etDesc, EditText etPrice, ImageView gameIcon, ImageView ivMore, ImageView ivMore2, ShapeLinearLayout linTag1, ShapeLinearLayout linTag2, ShapeLinearLayout linTag3, LinearLayout ll, Navigation reTag, RelativeLayout reTag2, ShapeRelativeLayout reTag3, TextView roleName, RecyclerView rv, View tagView, TextView tvDicker, TextView tvLimit, TextView tvTag1, TextView tvTag2, TextView tvTag3, TextView tvTag4, TextView tvTag5, TextView tvTag6, TextView tvTag7, TextView tvTag8, TextView tvTag9, View viewTag, View viewTag1, View viewTag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.etDesc = etDesc;
        this.etPrice = etPrice;
        this.gameIcon = gameIcon;
        this.ivMore = ivMore;
        this.ivMore2 = ivMore2;
        this.linTag1 = linTag1;
        this.linTag2 = linTag2;
        this.linTag3 = linTag3;
        this.ll = ll;
        this.reTag = reTag;
        this.reTag2 = reTag2;
        this.reTag3 = reTag3;
        this.roleName = roleName;
        this.rv = rv;
        this.tagView = tagView;
        this.tvDicker = tvDicker;
        this.tvLimit = tvLimit;
        this.tvTag1 = tvTag1;
        this.tvTag2 = tvTag2;
        this.tvTag3 = tvTag3;
        this.tvTag4 = tvTag4;
        this.tvTag5 = tvTag5;
        this.tvTag6 = tvTag6;
        this.tvTag7 = tvTag7;
        this.tvTag8 = tvTag8;
        this.tvTag9 = tvTag9;
        this.viewTag = viewTag;
        this.viewTag1 = viewTag1;
        this.viewTag2 = viewTag2;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static ActivityDealSellInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDealSellInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_sell_info, root, attachToRoot, component);
    }

    public static ActivityDealSellInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellInfoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDealSellInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_sell_info, null, false, component);
    }

    public static ActivityDealSellInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellInfoBinding bind(View view, Object component) {
        return (ActivityDealSellInfoBinding) bind(component, view, R.layout.activity_deal_sell_info);
    }
}
