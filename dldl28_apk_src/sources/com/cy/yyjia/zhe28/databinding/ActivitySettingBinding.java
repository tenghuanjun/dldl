package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySettingBinding extends ViewDataBinding {
    public final ImageView ivSwitchApk;
    public final TextView llAbout;
    public final LinearLayout llCancellation;
    public final LinearLayout llParents;
    public final LinearLayout llSdk;
    public final LinearLayout llUpdate;

    @Bindable
    protected UserBean mData;
    public final RelativeLayout rlUser;
    public final TextView tvCache;
    public final TextView tvDownload;
    public final TextView tvDownloadManager;
    public final TextView tvFeedback;
    public final TextView tvLogout;
    public final TextView tvSafe;

    public abstract void setData(UserBean data);

    protected ActivitySettingBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivSwitchApk, TextView llAbout, LinearLayout llCancellation, LinearLayout llParents, LinearLayout llSdk, LinearLayout llUpdate, RelativeLayout rlUser, TextView tvCache, TextView tvDownload, TextView tvDownloadManager, TextView tvFeedback, TextView tvLogout, TextView tvSafe) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivSwitchApk = ivSwitchApk;
        this.llAbout = llAbout;
        this.llCancellation = llCancellation;
        this.llParents = llParents;
        this.llSdk = llSdk;
        this.llUpdate = llUpdate;
        this.rlUser = rlUser;
        this.tvCache = tvCache;
        this.tvDownload = tvDownload;
        this.tvDownloadManager = tvDownloadManager;
        this.tvFeedback = tvFeedback;
        this.tvLogout = tvLogout;
        this.tvSafe = tvSafe;
    }

    public UserBean getData() {
        return this.mData;
    }

    public static ActivitySettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_setting, root, attachToRoot, component);
    }

    public static ActivitySettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySettingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_setting, null, false, component);
    }

    public static ActivitySettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingBinding bind(View view, Object component) {
        return (ActivitySettingBinding) bind(component, view, R.layout.activity_setting);
    }
}
