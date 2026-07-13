package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityInvite3Binding extends ViewDataBinding {
    public final FrameLayout fl1;
    public final FrameLayout fl2;
    public final ImageView ivShare;
    public final ImageView ivShare2;

    @Bindable
    protected InviteInfoBean mData;

    @Bindable
    protected int mPosition;
    public final TextView tvRecord;
    public final TextView tvRule;
    public final ShapeTextView tvWithdraw;
    public final AdapterViewFlipper vf;

    public abstract void setData(InviteInfoBean data);

    public abstract void setPosition(int position);

    protected ActivityInvite3Binding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout fl1, FrameLayout fl2, ImageView ivShare, ImageView ivShare2, TextView tvRecord, TextView tvRule, ShapeTextView tvWithdraw, AdapterViewFlipper vf) {
        super(_bindingComponent, _root, _localFieldCount);
        this.fl1 = fl1;
        this.fl2 = fl2;
        this.ivShare = ivShare;
        this.ivShare2 = ivShare2;
        this.tvRecord = tvRecord;
        this.tvRule = tvRule;
        this.tvWithdraw = tvWithdraw;
        this.vf = vf;
    }

    public InviteInfoBean getData() {
        return this.mData;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static ActivityInvite3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInvite3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityInvite3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_invite3, root, attachToRoot, component);
    }

    public static ActivityInvite3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInvite3Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityInvite3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_invite3, null, false, component);
    }

    public static ActivityInvite3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInvite3Binding bind(View view, Object component) {
        return (ActivityInvite3Binding) bind(component, view, R.layout.activity_invite3);
    }
}
