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
import com.cy.yyjia.zhe28.domain.GameReportBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGameReportBinding extends ViewDataBinding {
    public final ImageView ivDelete;
    public final ImageView ivVideo;

    @Bindable
    protected GameReportBean mData;
    public final RecyclerView rvPic;

    public abstract void setData(GameReportBean data);

    protected ActivityGameReportBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivDelete, ImageView ivVideo, RecyclerView rvPic) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivDelete = ivDelete;
        this.ivVideo = ivVideo;
        this.rvPic = rvPic;
    }

    public GameReportBean getData() {
        return this.mData;
    }

    public static ActivityGameReportBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReportBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGameReportBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_report, root, attachToRoot, component);
    }

    public static ActivityGameReportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReportBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGameReportBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_report, null, false, component);
    }

    public static ActivityGameReportBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReportBinding bind(View view, Object component) {
        return (ActivityGameReportBinding) bind(component, view, R.layout.activity_game_report);
    }
}
