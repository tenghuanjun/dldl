package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySanbaoBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final ImageView ivSearch;

    @Bindable
    protected int mPosition;

    @Bindable
    protected SanbaoRuleBean.Topic2 mTopic;
    public final RecyclerView rv;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final ShapeTextView tvRule;

    public abstract void setPosition(int position);

    public abstract void setTopic(SanbaoRuleBean.Topic2 topic);

    protected ActivitySanbaoBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, ImageView ivSearch, RecyclerView rv, TextView tv1, TextView tv2, TextView tv3, ShapeTextView tvRule) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.ivSearch = ivSearch;
        this.rv = rv;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tvRule = tvRule;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public SanbaoRuleBean.Topic2 getTopic() {
        return this.mTopic;
    }

    public static ActivitySanbaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySanbaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySanbaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sanbao, root, attachToRoot, component);
    }

    public static ActivitySanbaoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySanbaoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySanbaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sanbao, null, false, component);
    }

    public static ActivitySanbaoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySanbaoBinding bind(View view, Object component) {
        return (ActivitySanbaoBinding) bind(component, view, R.layout.activity_sanbao);
    }
}
