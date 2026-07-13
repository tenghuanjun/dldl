package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.NoviceDataBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityNoviceWelfareBinding extends ViewDataBinding {
    public final ImageView bg;
    public final ImageView ivGo;

    @Bindable
    protected NoviceDataBean mData;
    public final Navigation navigation;
    public final TextView tvGame;
    public final TextView tvGo;
    public final TextView tvRecord;
    public final TextView tvRule;
    public final TextView tvShow;

    public abstract void setData(NoviceDataBean data);

    protected ActivityNoviceWelfareBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg, ImageView ivGo, Navigation navigation, TextView tvGame, TextView tvGo, TextView tvRecord, TextView tvRule, TextView tvShow) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.ivGo = ivGo;
        this.navigation = navigation;
        this.tvGame = tvGame;
        this.tvGo = tvGo;
        this.tvRecord = tvRecord;
        this.tvRule = tvRule;
        this.tvShow = tvShow;
    }

    public NoviceDataBean getData() {
        return this.mData;
    }

    public static ActivityNoviceWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityNoviceWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_novice_welfare, root, attachToRoot, component);
    }

    public static ActivityNoviceWelfareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceWelfareBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityNoviceWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_novice_welfare, null, false, component);
    }

    public static ActivityNoviceWelfareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNoviceWelfareBinding bind(View view, Object component) {
        return (ActivityNoviceWelfareBinding) bind(component, view, R.layout.activity_novice_welfare);
    }
}
