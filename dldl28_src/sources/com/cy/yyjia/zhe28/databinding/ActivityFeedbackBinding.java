package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeRelativeLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityFeedbackBinding extends ViewDataBinding {

    @Bindable
    protected String mDesc;

    @Bindable
    protected int mType;
    public final Navigation navigation;
    public final ShapeRelativeLayout reTag3;
    public final RecyclerView rvPic;
    public final GridLayout rvType;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tv3;
    public final ShapeTextView tv4;
    public final ShapeTextView tv5;

    public abstract void setDesc(String desc);

    public abstract void setType(int type);

    protected ActivityFeedbackBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, ShapeRelativeLayout reTag3, RecyclerView rvPic, GridLayout rvType, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tv3, ShapeTextView tv4, ShapeTextView tv5) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.reTag3 = reTag3;
        this.rvPic = rvPic;
        this.rvType = rvType;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
    }

    public int getType() {
        return this.mType;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityFeedbackBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_feedback, root, attachToRoot, component);
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityFeedbackBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_feedback, null, false, component);
    }

    public static ActivityFeedbackBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding bind(View view, Object component) {
        return (ActivityFeedbackBinding) bind(component, view, R.layout.activity_feedback);
    }
}
