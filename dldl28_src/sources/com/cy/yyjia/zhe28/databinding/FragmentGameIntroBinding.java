package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentGameIntroBinding extends ViewDataBinding {
    public final ShapeLinearLayout btn648;
    public final ShapeTextView btnCoupon;
    public final LinearLayout btnEvent;
    public final ShapeTextView btnGift;
    public final ShapeTextView btnIntro;
    public final Button btnReserve;
    public final LayoutDiscountBinding discount;
    public final ImageView ivBbs;
    public final ImageView ivFreeClose;
    public final ImageView ivTask;
    public final ImageView ivTopic;

    @Bindable
    protected GameDetailBean mData;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected boolean mShowFree;

    @Bindable
    protected boolean mVip;
    public final NestedScrollView nsv;
    public final RecyclerView rv;
    public final RecyclerView rvTag;
    public final RecyclerView rvUpdate;
    public final TextView tvEvent;
    public final TextView tvHour;
    public final TextView tvKaiju;
    public final TextView tvLottery;
    public final ShapeLinearLayout tvService;
    public final TextView tvTask;
    public final ShapeTextView tvType1;
    public final ShapeTextView tvType2;
    public final ShapeTextView tvType3;
    public final TextView tvUpdate;
    public final AdapterViewFlipper vf;

    public abstract void setData(GameDetailBean data);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setShowFree(boolean showFree);

    public abstract void setVip(boolean vip);

    protected FragmentGameIntroBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeLinearLayout btn648, ShapeTextView btnCoupon, LinearLayout btnEvent, ShapeTextView btnGift, ShapeTextView btnIntro, Button btnReserve, LayoutDiscountBinding discount, ImageView ivBbs, ImageView ivFreeClose, ImageView ivTask, ImageView ivTopic, NestedScrollView nsv, RecyclerView rv, RecyclerView rvTag, RecyclerView rvUpdate, TextView tvEvent, TextView tvHour, TextView tvKaiju, TextView tvLottery, ShapeLinearLayout tvService, TextView tvTask, ShapeTextView tvType1, ShapeTextView tvType2, ShapeTextView tvType3, TextView tvUpdate, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn648 = btn648;
        this.btnCoupon = btnCoupon;
        this.btnEvent = btnEvent;
        this.btnGift = btnGift;
        this.btnIntro = btnIntro;
        this.btnReserve = btnReserve;
        this.discount = discount;
        this.ivBbs = ivBbs;
        this.ivFreeClose = ivFreeClose;
        this.ivTask = ivTask;
        this.ivTopic = ivTopic;
        this.nsv = nsv;
        this.rv = rv;
        this.rvTag = rvTag;
        this.rvUpdate = rvUpdate;
        this.tvEvent = tvEvent;
        this.tvHour = tvHour;
        this.tvKaiju = tvKaiju;
        this.tvLottery = tvLottery;
        this.tvService = tvService;
        this.tvTask = tvTask;
        this.tvType1 = tvType1;
        this.tvType2 = tvType2;
        this.tvType3 = tvType3;
        this.tvUpdate = tvUpdate;
        this.vf = vf;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public boolean getVip() {
        return this.mVip;
    }

    public GameDetailBean getData() {
        return this.mData;
    }

    public boolean getShowFree() {
        return this.mShowFree;
    }

    public static FragmentGameIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGameIntroBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_intro, root, attachToRoot, component);
    }

    public static FragmentGameIntroBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameIntroBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGameIntroBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_intro, null, false, component);
    }

    public static FragmentGameIntroBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameIntroBinding bind(View view, Object component) {
        return (FragmentGameIntroBinding) bind(component, view, R.layout.fragment_game_intro);
    }
}
