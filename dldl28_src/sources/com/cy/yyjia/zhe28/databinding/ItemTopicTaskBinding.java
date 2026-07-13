package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTopicTaskBinding extends ViewDataBinding {
    public final ImageView bg;

    @Bindable
    protected TopicDetailBean.Task mConfig;

    @Bindable
    protected TopicDetailBean.TaskList mData;

    public abstract void setConfig(TopicDetailBean.Task config);

    public abstract void setData(TopicDetailBean.TaskList data);

    protected ItemTopicTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
    }

    public TopicDetailBean.Task getConfig() {
        return this.mConfig;
    }

    public TopicDetailBean.TaskList getData() {
        return this.mData;
    }

    public static ItemTopicTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTopicTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_task, root, attachToRoot, component);
    }

    public static ItemTopicTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTopicTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_task, null, false, component);
    }

    public static ItemTopicTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicTaskBinding bind(View view, Object component) {
        return (ItemTopicTaskBinding) bind(component, view, R.layout.item_topic_task);
    }
}
