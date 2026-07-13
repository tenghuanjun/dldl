package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDealDetailBinding extends ViewDataBinding {
    public final ShapeConstraintLayout clTag;
    public final ImageView gameIcon;
    public final TextView gameName;
    public final LinearLayout llDicker;

    @Bindable
    protected DealBean mData;

    @Bindable
    protected boolean mShowDickerPrice;
    public final Navigation reTag;
    public final LinearLayout reTag1;
    public final RecyclerView rv;
    public final ShapeTextView tvBuy;
    public final TextView tvDicker;
    public final ShapeTextView tvDicker1;
    public final ShapeTextView tvDicker2;
    public final TextView tvDickerPrice;
    public final TextView tvDickerPrice1;
    public final ShapeTextView tvGame;
    public final TextView tvTag1;
    public final TextView tvTag2;
    public final TextView tvTag3;
    public final TextView tvTag4;
    public final TextView tvTag5;
    public final TextView tvTag6;

    public abstract void setData(DealBean data);

    public abstract void setShowDickerPrice(boolean showDickerPrice);

    protected ActivityDealDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeConstraintLayout clTag, ImageView gameIcon, TextView gameName, LinearLayout llDicker, Navigation reTag, LinearLayout reTag1, RecyclerView rv, ShapeTextView tvBuy, TextView tvDicker, ShapeTextView tvDicker1, ShapeTextView tvDicker2, TextView tvDickerPrice, TextView tvDickerPrice1, ShapeTextView tvGame, TextView tvTag1, TextView tvTag2, TextView tvTag3, TextView tvTag4, TextView tvTag5, TextView tvTag6) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clTag = clTag;
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.llDicker = llDicker;
        this.reTag = reTag;
        this.reTag1 = reTag1;
        this.rv = rv;
        this.tvBuy = tvBuy;
        this.tvDicker = tvDicker;
        this.tvDicker1 = tvDicker1;
        this.tvDicker2 = tvDicker2;
        this.tvDickerPrice = tvDickerPrice;
        this.tvDickerPrice1 = tvDickerPrice1;
        this.tvGame = tvGame;
        this.tvTag1 = tvTag1;
        this.tvTag2 = tvTag2;
        this.tvTag3 = tvTag3;
        this.tvTag4 = tvTag4;
        this.tvTag5 = tvTag5;
        this.tvTag6 = tvTag6;
    }

    public DealBean getData() {
        return this.mData;
    }

    public boolean getShowDickerPrice() {
        return this.mShowDickerPrice;
    }

    public static ActivityDealDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDealDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_detail, root, attachToRoot, component);
    }

    public static ActivityDealDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDealDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_detail, null, false, component);
    }

    public static ActivityDealDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealDetailBinding bind(View view, Object component) {
        return (ActivityDealDetailBinding) bind(component, view, R.layout.activity_deal_detail);
    }
}
