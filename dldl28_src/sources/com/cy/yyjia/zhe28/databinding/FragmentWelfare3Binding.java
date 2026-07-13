package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BossServerBean;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.cy.yyjia.zhe28.domain.MonthlyTaskBean;
import com.cy.yyjia.zhe28.domain.MonthlyTaskNavBean;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WelfareBean3;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentWelfare3Binding extends ViewDataBinding {
    public final ConvenientBanner banner;
    public final ShapeTextView btnReceive1;
    public final ShapeTextView btnReceive2;
    public final ShapeTextView btnReceive3;
    public final TextView btnTask1;
    public final TextView btnTask12;
    public final TextView btnTask13;
    public final TextView btnTask2;
    public final TextView btnTask22;
    public final ImageView clMonth;
    public final ImageView clSqk;
    public final ConstraintLayout clUser;
    public final ImageView ivBbs;
    public final ImageView ivInvite;
    public final ImageView ivUser;

    @Bindable
    protected BossServerBean mBoss;

    @Bindable
    protected WelfareBean3 mData;

    @Bindable
    protected DailyCouponBean mData3;

    @Bindable
    protected MonthlyTaskBean mMonth;

    @Bindable
    protected MonthlyTaskNavBean mNav;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected QiandaoBean mQiandao;

    @Bindable
    protected int mTask1;

    @Bindable
    protected int mTask2;

    @Bindable
    protected UserBean mUser;
    public final ProgressBar pb;
    public final RecyclerView rv1;
    public final RecyclerView rv2;
    public final RecyclerView rv3;
    public final RecyclerView rv4;
    public final RecyclerView rv5;
    public final ShapeTextView tvBaofu;
    public final ShapeTextView tvBoss;
    public final ShapeTextView tvDailyCoupon;
    public final ShapeTextView tvDailyTask;
    public final TextView tvExp;
    public final ShapeTextView tvMonthlyTask;
    public final TextView tvName;
    public final AdapterViewFlipper vf;

    public abstract void setBoss(BossServerBean boss);

    public abstract void setData(WelfareBean3 data);

    public abstract void setData3(DailyCouponBean data3);

    public abstract void setMonth(MonthlyTaskBean month);

    public abstract void setNav(MonthlyTaskNavBean nav);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setQiandao(QiandaoBean qiandao);

    public abstract void setTask1(int task1);

    public abstract void setTask2(int task2);

    public abstract void setUser(UserBean user);

    protected FragmentWelfare3Binding(Object _bindingComponent, View _root, int _localFieldCount, ConvenientBanner banner, ShapeTextView btnReceive1, ShapeTextView btnReceive2, ShapeTextView btnReceive3, TextView btnTask1, TextView btnTask12, TextView btnTask13, TextView btnTask2, TextView btnTask22, ImageView clMonth, ImageView clSqk, ConstraintLayout clUser, ImageView ivBbs, ImageView ivInvite, ImageView ivUser, ProgressBar pb, RecyclerView rv1, RecyclerView rv2, RecyclerView rv3, RecyclerView rv4, RecyclerView rv5, ShapeTextView tvBaofu, ShapeTextView tvBoss, ShapeTextView tvDailyCoupon, ShapeTextView tvDailyTask, TextView tvExp, ShapeTextView tvMonthlyTask, TextView tvName, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.banner = banner;
        this.btnReceive1 = btnReceive1;
        this.btnReceive2 = btnReceive2;
        this.btnReceive3 = btnReceive3;
        this.btnTask1 = btnTask1;
        this.btnTask12 = btnTask12;
        this.btnTask13 = btnTask13;
        this.btnTask2 = btnTask2;
        this.btnTask22 = btnTask22;
        this.clMonth = clMonth;
        this.clSqk = clSqk;
        this.clUser = clUser;
        this.ivBbs = ivBbs;
        this.ivInvite = ivInvite;
        this.ivUser = ivUser;
        this.pb = pb;
        this.rv1 = rv1;
        this.rv2 = rv2;
        this.rv3 = rv3;
        this.rv4 = rv4;
        this.rv5 = rv5;
        this.tvBaofu = tvBaofu;
        this.tvBoss = tvBoss;
        this.tvDailyCoupon = tvDailyCoupon;
        this.tvDailyTask = tvDailyTask;
        this.tvExp = tvExp;
        this.tvMonthlyTask = tvMonthlyTask;
        this.tvName = tvName;
        this.vf = vf;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public UserBean getUser() {
        return this.mUser;
    }

    public WelfareBean3 getData() {
        return this.mData;
    }

    public QiandaoBean getQiandao() {
        return this.mQiandao;
    }

    public MonthlyTaskNavBean getNav() {
        return this.mNav;
    }

    public MonthlyTaskBean getMonth() {
        return this.mMonth;
    }

    public int getTask1() {
        return this.mTask1;
    }

    public int getTask2() {
        return this.mTask2;
    }

    public DailyCouponBean getData3() {
        return this.mData3;
    }

    public BossServerBean getBoss() {
        return this.mBoss;
    }

    public static FragmentWelfare3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWelfare3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_welfare3, root, attachToRoot, component);
    }

    public static FragmentWelfare3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare3Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWelfare3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_welfare3, null, false, component);
    }

    public static FragmentWelfare3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare3Binding bind(View view, Object component) {
        return (FragmentWelfare3Binding) bind(component, view, R.layout.fragment_welfare3);
    }
}
