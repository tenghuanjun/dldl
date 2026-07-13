package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentSanbao648Binding extends ViewDataBinding {
    public final LinearLayout ll;

    @Bindable
    protected boolean mGift;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final RecyclerView rvGame;
    public final RecyclerView rvMy;
    public final RecyclerView rvType;
    public final TextView tv1;
    public final TextView tv2;
    public final ShapeTextView tvCoupon;
    public final ShapeTextView tvGift;
    public final ShapeTextView tvRule;
    public final AdapterViewFlipper vf;

    public abstract void setGift(boolean gift);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentSanbao648Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, RecyclerView rvGame, RecyclerView rvMy, RecyclerView rvType, TextView tv1, TextView tv2, ShapeTextView tvCoupon, ShapeTextView tvGift, ShapeTextView tvRule, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.rvGame = rvGame;
        this.rvMy = rvMy;
        this.rvType = rvType;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tvCoupon = tvCoupon;
        this.tvGift = tvGift;
        this.tvRule = tvRule;
        this.vf = vf;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean getGift() {
        return this.mGift;
    }

    public static FragmentSanbao648Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSanbao648Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSanbao648Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_sanbao648, root, attachToRoot, component);
    }

    public static FragmentSanbao648Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSanbao648Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSanbao648Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_sanbao648, null, false, component);
    }

    public static FragmentSanbao648Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSanbao648Binding bind(View view, Object component) {
        return (FragmentSanbao648Binding) bind(component, view, R.layout.fragment_sanbao648);
    }
}
