package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.WelfareEventDetailBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityWelfareEventDetailBinding extends ViewDataBinding {
    public final TextView btnSend;
    public final EditText et;

    @Bindable
    protected WelfareEventDetailBean mData;

    @Bindable
    protected String mText;
    public final Navigation navigation;
    public final RecyclerView rv;

    public abstract void setData(WelfareEventDetailBean data);

    public abstract void setText(String text);

    protected ActivityWelfareEventDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btnSend, EditText et, Navigation navigation, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnSend = btnSend;
        this.et = et;
        this.navigation = navigation;
        this.rv = rv;
    }

    public WelfareEventDetailBean getData() {
        return this.mData;
    }

    public String getText() {
        return this.mText;
    }

    public static ActivityWelfareEventDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWelfareEventDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityWelfareEventDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_welfare_event_detail, root, attachToRoot, component);
    }

    public static ActivityWelfareEventDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWelfareEventDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityWelfareEventDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_welfare_event_detail, null, false, component);
    }

    public static ActivityWelfareEventDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWelfareEventDetailBinding bind(View view, Object component) {
        return (ActivityWelfareEventDetailBinding) bind(component, view, R.layout.activity_welfare_event_detail);
    }
}
