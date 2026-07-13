package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentTopicDetailBinding extends ViewDataBinding {
    public final ImageView bgLottery;
    public final ImageView bgPic;
    public final ImageView bgVideo;
    public final ImageView bgVideo2;
    public final ImageView btnRecord;
    public final ImageView btnRule;
    public final ImageView iv;
    public final ImageView ivCoupon;
    public final ImageView ivDownload;
    public final ImageView ivDownload2;

    @Bindable
    protected TopicDetailBean mData;

    @Bindable
    protected View.OnClickListener mOnClick;
    public final RecyclerView rvLottery;
    public final RecyclerView rvPic;
    public final RecyclerView rvTask;
    public final AdapterViewFlipper vf;

    public abstract void setData(TopicDetailBean data);

    public abstract void setOnClick(View.OnClickListener onClick);

    protected FragmentTopicDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bgLottery, ImageView bgPic, ImageView bgVideo, ImageView bgVideo2, ImageView btnRecord, ImageView btnRule, ImageView iv, ImageView ivCoupon, ImageView ivDownload, ImageView ivDownload2, RecyclerView rvLottery, RecyclerView rvPic, RecyclerView rvTask, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bgLottery = bgLottery;
        this.bgPic = bgPic;
        this.bgVideo = bgVideo;
        this.bgVideo2 = bgVideo2;
        this.btnRecord = btnRecord;
        this.btnRule = btnRule;
        this.iv = iv;
        this.ivCoupon = ivCoupon;
        this.ivDownload = ivDownload;
        this.ivDownload2 = ivDownload2;
        this.rvLottery = rvLottery;
        this.rvPic = rvPic;
        this.rvTask = rvTask;
        this.vf = vf;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public TopicDetailBean getData() {
        return this.mData;
    }

    public static FragmentTopicDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTopicDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentTopicDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_topic_detail, root, attachToRoot, component);
    }

    public static FragmentTopicDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTopicDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentTopicDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_topic_detail, null, false, component);
    }

    public static FragmentTopicDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTopicDetailBinding bind(View view, Object component) {
        return (FragmentTopicDetailBinding) bind(component, view, R.layout.fragment_topic_detail);
    }
}
