package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealIndexBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityWithdrewBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final EditText et;
    public final LinearLayout llPtb;
    public final LinearLayout llZfb;

    @Bindable
    protected DealIndexBean mData;

    @Bindable
    protected String mNumber;

    @Bindable
    protected boolean mPtb;
    public final Navigation navigation;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f444tv;
    public final TextView tvAlipay;
    public final TextView tvAll;

    public abstract void setData(DealIndexBean data);

    public abstract void setNumber(String number);

    public abstract void setPtb(boolean ptb);

    protected ActivityWithdrewBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, EditText et, LinearLayout llPtb, LinearLayout llZfb, Navigation navigation, TextView tv2, TextView tvAlipay, TextView tvAll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.llPtb = llPtb;
        this.llZfb = llZfb;
        this.navigation = navigation;
        this.f444tv = tv2;
        this.tvAlipay = tvAlipay;
        this.tvAll = tvAll;
    }

    public DealIndexBean getData() {
        return this.mData;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public boolean getPtb() {
        return this.mPtb;
    }

    public static ActivityWithdrewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWithdrewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityWithdrewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_withdrew, root, attachToRoot, component);
    }

    public static ActivityWithdrewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWithdrewBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityWithdrewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_withdrew, null, false, component);
    }

    public static ActivityWithdrewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWithdrewBinding bind(View view, Object component) {
        return (ActivityWithdrewBinding) bind(component, view, R.layout.activity_withdrew);
    }
}
