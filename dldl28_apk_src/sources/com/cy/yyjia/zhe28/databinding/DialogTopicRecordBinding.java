package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogTopicRecordBinding extends ViewDataBinding {
    public final ImageView bg;

    @Bindable
    protected TopicDetailBean mData;
    public final RecyclerView rv;

    public abstract void setData(TopicDetailBean data);

    protected DialogTopicRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.rv = rv;
    }

    public TopicDetailBean getData() {
        return this.mData;
    }

    public static DialogTopicRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogTopicRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_topic_record, root, attachToRoot, component);
    }

    public static DialogTopicRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogTopicRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_topic_record, null, false, component);
    }

    public static DialogTopicRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTopicRecordBinding bind(View view, Object component) {
        return (DialogTopicRecordBinding) bind(component, view, R.layout.dialog_topic_record);
    }
}
