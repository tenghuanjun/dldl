package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeFrameLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityVipBinding extends ViewDataBinding {
    public final ShapeFrameLayout btn1;
    public final ShapeFrameLayout btn2;
    public final ShapeFrameLayout btn3;
    public final ShapeFrameLayout btn4;
    public final ShapeFrameLayout btn5;
    public final ShapeFrameLayout btn6;
    public final LinearLayout llRight;

    @Bindable
    protected VipListBean mData;

    @Bindable
    protected int mPosition;

    @Bindable
    protected boolean mRight;
    public final Navigation navigation;
    public final RecyclerView rvFun;
    public final RecyclerView rvGift;
    public final RecyclerView rvRight;
    public final ShapeTextView tvGift;
    public final ViewPager2 vp;

    public abstract void setData(VipListBean data);

    public abstract void setPosition(int position);

    public abstract void setRight(boolean right);

    protected ActivityVipBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeFrameLayout btn1, ShapeFrameLayout btn2, ShapeFrameLayout btn3, ShapeFrameLayout btn4, ShapeFrameLayout btn5, ShapeFrameLayout btn6, LinearLayout llRight, Navigation navigation, RecyclerView rvFun, RecyclerView rvGift, RecyclerView rvRight, ShapeTextView tvGift, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.btn5 = btn5;
        this.btn6 = btn6;
        this.llRight = llRight;
        this.navigation = navigation;
        this.rvFun = rvFun;
        this.rvGift = rvGift;
        this.rvRight = rvRight;
        this.tvGift = tvGift;
        this.vp = vp;
    }

    public boolean getRight() {
        return this.mRight;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public VipListBean getData() {
        return this.mData;
    }

    public static ActivityVipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityVipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_vip, root, attachToRoot, component);
    }

    public static ActivityVipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityVipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_vip, null, false, component);
    }

    public static ActivityVipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipBinding bind(View view, Object component) {
        return (ActivityVipBinding) bind(component, view, R.layout.activity_vip);
    }
}
