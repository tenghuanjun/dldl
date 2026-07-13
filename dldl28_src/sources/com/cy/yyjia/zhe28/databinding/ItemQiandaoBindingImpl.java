package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemQiandaoBindingImpl extends ItemQiandaoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemQiandaoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemQiandaoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((QiandaoBean.Day) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemQiandaoBinding
    public void setData(QiandaoBean.Day Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        QiandaoBean.Day day = this.mData;
        long j2 = j & 3;
        String status = null;
        if (j2 != 0) {
            if (day != null) {
                String week = day.getWeek();
                status = day.getStatus();
                str = week;
            } else {
                str = null;
            }
            zEquals = status != null ? status.equals("end") : false;
            z = !zEquals;
            status = str;
        } else {
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, zEquals);
            DataBindingHelper.setViewGone(this.mboundView2, z);
            DataBindingHelper.setViewGone(this.mboundView3, zEquals);
            TextViewBindingAdapter.setText(this.mboundView3, status);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            TextViewBindingAdapter.setText(this.mboundView4, status);
            DataBindingHelper.setViewGone(this.mboundView5, zEquals);
            DataBindingHelper.setViewGone(this.mboundView6, z);
        }
    }
}
