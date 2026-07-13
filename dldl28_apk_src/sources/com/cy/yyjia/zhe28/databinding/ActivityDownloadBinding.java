package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDownloadBinding extends ViewDataBinding {
    public final LinearLayout llManager;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final TextView tvAll;
    public final TextView tvDelete;

    protected ActivityDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llManager, Navigation navigation, RecyclerView rv, TextView tvAll, TextView tvDelete) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llManager = llManager;
        this.navigation = navigation;
        this.rv = rv;
        this.tvAll = tvAll;
        this.tvDelete = tvDelete;
    }

    public static ActivityDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_download, root, attachToRoot, component);
    }

    public static ActivityDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_download, null, false, component);
    }

    public static ActivityDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadBinding bind(View view, Object component) {
        return (ActivityDownloadBinding) bind(component, view, R.layout.activity_download);
    }
}
