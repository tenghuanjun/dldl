package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicBroadcastBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTopicRecordBinding extends ViewDataBinding {

    @Bindable
    protected TopicBroadcastBean mData;

    public abstract void setData(TopicBroadcastBean data);

    protected ItemTopicRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TopicBroadcastBean getData() {
        return this.mData;
    }

    public static ItemTopicRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTopicRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_record, root, attachToRoot, component);
    }

    public static ItemTopicRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTopicRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_record, null, false, component);
    }

    public static ItemTopicRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicRecordBinding bind(View view, Object component) {
        return (ItemTopicRecordBinding) bind(component, view, R.layout.item_topic_record);
    }
}
