package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.TaskResult;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityQiandaoBinding extends ViewDataBinding {
    public final LinearLayout flDaily;
    public final LinearLayout flGrowup;
    public final RecyclerView list;
    public final TextView llTask;

    @Bindable
    protected QiandaoBean mData;

    @Bindable
    protected TaskResult mTask;
    public final RecyclerView rv;
    public final RecyclerView rvNav;
    public final ImageView sign;
    public final TextView tvRecord;
    public final TextView tvRule;
    public final ImageView tvYhq;
    public final AdapterViewFlipper vf;

    public abstract void setData(QiandaoBean data);

    public abstract void setTask(TaskResult task);

    protected ActivityQiandaoBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout flDaily, LinearLayout flGrowup, RecyclerView list, TextView llTask, RecyclerView rv, RecyclerView rvNav, ImageView sign, TextView tvRecord, TextView tvRule, ImageView tvYhq, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flDaily = flDaily;
        this.flGrowup = flGrowup;
        this.list = list;
        this.llTask = llTask;
        this.rv = rv;
        this.rvNav = rvNav;
        this.sign = sign;
        this.tvRecord = tvRecord;
        this.tvRule = tvRule;
        this.tvYhq = tvYhq;
        this.vf = vf;
    }

    public QiandaoBean getData() {
        return this.mData;
    }

    public TaskResult getTask() {
        return this.mTask;
    }

    public static ActivityQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_qiandao, root, attachToRoot, component);
    }

    public static ActivityQiandaoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_qiandao, null, false, component);
    }

    public static ActivityQiandaoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoBinding bind(View view, Object component) {
        return (ActivityQiandaoBinding) bind(component, view, R.layout.activity_qiandao);
    }
}
