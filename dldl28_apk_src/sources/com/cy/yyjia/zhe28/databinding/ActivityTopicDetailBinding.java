package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityTopicDetailBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final ConstraintLayout cl;
    public final Navigation navigation;

    protected ActivityTopicDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, ConstraintLayout cl, Navigation navigation) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.cl = cl;
        this.navigation = navigation;
    }

    public static ActivityTopicDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTopicDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTopicDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_topic_detail, root, attachToRoot, component);
    }

    public static ActivityTopicDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTopicDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTopicDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_topic_detail, null, false, component);
    }

    public static ActivityTopicDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTopicDetailBinding bind(View view, Object component) {
        return (ActivityTopicDetailBinding) bind(component, view, R.layout.activity_topic_detail);
    }
}
