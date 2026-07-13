package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.lzy.okgo.model.Progress;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityYunPlayBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final TextView llFloat;

    @Bindable
    protected GameDetailBean mData;

    @Bindable
    protected YunIndexBean mIndex;

    @Bindable
    protected String mPin;

    @Bindable
    protected Progress mProgress;

    @Bindable
    protected boolean mShowMenu;

    @Bindable
    protected boolean mVisitor;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f445tv;
    public final TextView tvBack;
    public final TextView tvDownload;
    public final TextView tvFold;
    public final TextView tvLevel;
    public final TextView tvMore;
    public final TextView tvPin;
    public final TextView tvRestart;
    public final TextView tvService;
    public final TextView tvYunDevice;

    public abstract void setData(GameDetailBean data);

    public abstract void setIndex(YunIndexBean index);

    public abstract void setPin(String pin);

    public abstract void setProgress(Progress progress);

    public abstract void setShowMenu(boolean showMenu);

    public abstract void setVisitor(boolean visitor);

    protected ActivityYunPlayBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, TextView llFloat, TextView tv2, TextView tvBack, TextView tvDownload, TextView tvFold, TextView tvLevel, TextView tvMore, TextView tvPin, TextView tvRestart, TextView tvService, TextView tvYunDevice) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.llFloat = llFloat;
        this.f445tv = tv2;
        this.tvBack = tvBack;
        this.tvDownload = tvDownload;
        this.tvFold = tvFold;
        this.tvLevel = tvLevel;
        this.tvMore = tvMore;
        this.tvPin = tvPin;
        this.tvRestart = tvRestart;
        this.tvService = tvService;
        this.tvYunDevice = tvYunDevice;
    }

    public boolean getVisitor() {
        return this.mVisitor;
    }

    public String getPin() {
        return this.mPin;
    }

    public boolean getShowMenu() {
        return this.mShowMenu;
    }

    public GameDetailBean getData() {
        return this.mData;
    }

    public YunIndexBean getIndex() {
        return this.mIndex;
    }

    public Progress getProgress() {
        return this.mProgress;
    }

    public static ActivityYunPlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunPlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityYunPlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_yun_play, root, attachToRoot, component);
    }

    public static ActivityYunPlayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunPlayBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityYunPlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_yun_play, null, false, component);
    }

    public static ActivityYunPlayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunPlayBinding bind(View view, Object component) {
        return (ActivityYunPlayBinding) bind(component, view, R.layout.activity_yun_play);
    }
}
