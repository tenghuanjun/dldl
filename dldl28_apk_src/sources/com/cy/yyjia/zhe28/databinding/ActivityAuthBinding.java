package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityAuthBinding extends ViewDataBinding {
    public final ShapeLinearLayout linTag1;
    public final ShapeLinearLayout linTag2;

    @Bindable
    protected String mCode;

    @Bindable
    protected String mName;
    public final Navigation reTag;
    public final RelativeLayout reTag1;
    public final ShapeTextView tvGo;
    public final TextView tvTag1;
    public final TextView tvTag2;

    public abstract void setCode(String code);

    public abstract void setName(String name);

    protected ActivityAuthBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeLinearLayout linTag1, ShapeLinearLayout linTag2, Navigation reTag, RelativeLayout reTag1, ShapeTextView tvGo, TextView tvTag1, TextView tvTag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linTag1 = linTag1;
        this.linTag2 = linTag2;
        this.reTag = reTag;
        this.reTag1 = reTag1;
        this.tvGo = tvGo;
        this.tvTag1 = tvTag1;
        this.tvTag2 = tvTag2;
    }

    public String getName() {
        return this.mName;
    }

    public String getCode() {
        return this.mCode;
    }

    public static ActivityAuthBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAuthBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAuthBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_auth, root, attachToRoot, component);
    }

    public static ActivityAuthBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAuthBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAuthBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_auth, null, false, component);
    }

    public static ActivityAuthBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAuthBinding bind(View view, Object component) {
        return (ActivityAuthBinding) bind(component, view, R.layout.activity_auth);
    }
}
