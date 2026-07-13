package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.view.AutoHeightViewPager;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.model.Progress;
import eightbitlab.com.blurview.BlurView;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGameDetailBinding extends ViewDataBinding {
    public final AppBarLayout appBar;
    public final ImageView bg;
    public final ImageView btnComment;
    public final BlurView bv;
    public final ConstraintLayout cl;
    public final MagicIndicator indicator;
    public final LinearLayout ll;
    public final LinearLayout ll2;

    @Bindable
    protected GameDetailBean mData;

    @Bindable
    protected boolean mFold;

    @Bindable
    protected int mPosition;

    @Bindable
    protected Progress mProgress;
    public final Navigation navigation;
    public final ProgressBar pb;
    public final RelativeLayout rlDownload;
    public final TabLayout tab;
    public final Toolbar toolbar;
    public final ShapeTextView tv0;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tv3;
    public final ShapeLinearLayout tvGm;
    public final ShapeTextView tvOpen;
    public final ShapeTextView tvOrder;
    public final ShapeLinearLayout tvPlay;
    public final ShapeLinearLayout tvYun;
    public final ViewPager vp;
    public final AutoHeightViewPager vp2;

    public abstract void setData(GameDetailBean data);

    public abstract void setFold(boolean fold);

    public abstract void setPosition(int position);

    public abstract void setProgress(Progress progress);

    protected ActivityGameDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout appBar, ImageView bg, ImageView btnComment, BlurView bv, ConstraintLayout cl, MagicIndicator indicator, LinearLayout ll, LinearLayout ll2, Navigation navigation, ProgressBar pb, RelativeLayout rlDownload, TabLayout tab, Toolbar toolbar, ShapeTextView tv0, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tv3, ShapeLinearLayout tvGm, ShapeTextView tvOpen, ShapeTextView tvOrder, ShapeLinearLayout tvPlay, ShapeLinearLayout tvYun, ViewPager vp, AutoHeightViewPager vp2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appBar = appBar;
        this.bg = bg;
        this.btnComment = btnComment;
        this.bv = bv;
        this.cl = cl;
        this.indicator = indicator;
        this.ll = ll;
        this.ll2 = ll2;
        this.navigation = navigation;
        this.pb = pb;
        this.rlDownload = rlDownload;
        this.tab = tab;
        this.toolbar = toolbar;
        this.tv0 = tv0;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tvGm = tvGm;
        this.tvOpen = tvOpen;
        this.tvOrder = tvOrder;
        this.tvPlay = tvPlay;
        this.tvYun = tvYun;
        this.vp = vp;
        this.vp2 = vp2;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean getFold() {
        return this.mFold;
    }

    public GameDetailBean getData() {
        return this.mData;
    }

    public Progress getProgress() {
        return this.mProgress;
    }

    public static ActivityGameDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGameDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_detail, root, attachToRoot, component);
    }

    public static ActivityGameDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGameDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_detail, null, false, component);
    }

    public static ActivityGameDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameDetailBinding bind(View view, Object component) {
        return (ActivityGameDetailBinding) bind(component, view, R.layout.activity_game_detail);
    }
}
