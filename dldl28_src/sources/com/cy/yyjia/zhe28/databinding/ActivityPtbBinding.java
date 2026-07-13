package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityPtbBinding extends ViewDataBinding {
    public final EditText et;
    public final ImageView ivTag;
    public final ShapeView ivView1;
    public final ShapeLinearLayout ll1;
    public final ShapeLinearLayout ll2;
    public final ShapeLinearLayout ll3;
    public final ShapeLinearLayout ll4;
    public final ShapeLinearLayout ll5;
    public final ShapeLinearLayout ll6;

    @Bindable
    protected boolean mAlipay;

    @Bindable
    protected int mPosition;
    public final Navigation navigation;
    public final ShapeTextView tvAlipay;
    public final ShapeTextView tvGo;
    public final TextView tvPtb;
    public final TextView tvTag1;
    public final TextView tvUsername;
    public final ShapeTextView tvWx;

    public abstract void setAlipay(boolean alipay);

    public abstract void setPosition(int position);

    protected ActivityPtbBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ImageView ivTag, ShapeView ivView1, ShapeLinearLayout ll1, ShapeLinearLayout ll2, ShapeLinearLayout ll3, ShapeLinearLayout ll4, ShapeLinearLayout ll5, ShapeLinearLayout ll6, Navigation navigation, ShapeTextView tvAlipay, ShapeTextView tvGo, TextView tvPtb, TextView tvTag1, TextView tvUsername, ShapeTextView tvWx) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.ivTag = ivTag;
        this.ivView1 = ivView1;
        this.ll1 = ll1;
        this.ll2 = ll2;
        this.ll3 = ll3;
        this.ll4 = ll4;
        this.ll5 = ll5;
        this.ll6 = ll6;
        this.navigation = navigation;
        this.tvAlipay = tvAlipay;
        this.tvGo = tvGo;
        this.tvPtb = tvPtb;
        this.tvTag1 = tvTag1;
        this.tvUsername = tvUsername;
        this.tvWx = tvWx;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean getAlipay() {
        return this.mAlipay;
    }

    public static ActivityPtbBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPtbBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_ptb, root, attachToRoot, component);
    }

    public static ActivityPtbBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPtbBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_ptb, null, false, component);
    }

    public static ActivityPtbBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbBinding bind(View view, Object component) {
        return (ActivityPtbBinding) bind(component, view, R.layout.activity_ptb);
    }
}
