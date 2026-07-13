package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogTopicRuleBinding extends ViewDataBinding {
    public final ImageView bg;

    @Bindable
    protected TopicDetailBean mData;
    public final WebView wv;

    public abstract void setData(TopicDetailBean data);

    protected DialogTopicRuleBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.wv = wv;
    }

    public TopicDetailBean getData() {
        return this.mData;
    }

    public static DialogTopicRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogTopicRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_topic_rule, root, attachToRoot, component);
    }

    public static DialogTopicRuleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRuleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogTopicRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_topic_rule, null, false, component);
    }

    public static DialogTopicRuleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRuleBinding bind(View view, Object component) {
        return (DialogTopicRuleBinding) bind(component, view, R.layout.dialog_topic_rule);
    }
}
