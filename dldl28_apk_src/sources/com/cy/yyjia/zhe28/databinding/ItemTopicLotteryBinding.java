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
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTopicLotteryBinding extends ViewDataBinding {
    public final ImageView bg;

    @Bindable
    protected TopicDetailBean.Prize mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f471tv;

    public abstract void setData(TopicDetailBean.Prize data);

    protected ItemTopicLotteryBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bg, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
        this.f471tv = tv2;
    }

    public TopicDetailBean.Prize getData() {
        return this.mData;
    }

    public static ItemTopicLotteryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicLotteryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTopicLotteryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_lottery, root, attachToRoot, component);
    }

    public static ItemTopicLotteryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicLotteryBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTopicLotteryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_topic_lottery, null, false, component);
    }

    public static ItemTopicLotteryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTopicLotteryBinding bind(View view, Object component) {
        return (ItemTopicLotteryBinding) bind(component, view, R.layout.item_topic_lottery);
    }
}
