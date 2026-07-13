package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.model.Progress;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDownloadBinding extends ViewDataBinding {

    @Bindable
    protected AppInfo mData;

    @Bindable
    protected boolean mManager;

    @Bindable
    protected Progress mProgress;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeTextView f463tv;

    public abstract void setData(AppInfo data);

    public abstract void setManager(boolean manager);

    public abstract void setProgress(Progress progress);

    protected ItemDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f463tv = tv2;
    }

    public boolean getManager() {
        return this.mManager;
    }

    public AppInfo getData() {
        return this.mData;
    }

    public Progress getProgress() {
        return this.mProgress;
    }

    public static ItemDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_download, root, attachToRoot, component);
    }

    public static ItemDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_download, null, false, component);
    }

    public static ItemDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDownloadBinding bind(View view, Object component) {
        return (ItemDownloadBinding) bind(component, view, R.layout.item_download);
    }
}
