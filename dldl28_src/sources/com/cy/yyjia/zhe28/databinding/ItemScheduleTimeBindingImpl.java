package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.ScheduleTimeBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemScheduleTimeBindingImpl extends ItemScheduleTimeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ShapeView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    public ItemScheduleTimeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemScheduleTimeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ShapeView shapeView = (ShapeView) bindings[1];
        this.mboundView1 = shapeView;
        shapeView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        setData((ScheduleTimeBean.Day) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemScheduleTimeBinding
    public void setData(ScheduleTimeBean.Day Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((ScheduleTimeBean.Day) object, fieldId);
    }

    private boolean onChangeData(ScheduleTimeBean.Day Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        boolean z;
        boolean zIsToday;
        boolean zIsTomorrow;
        boolean z2;
        boolean z3;
        boolean z4;
        String day;
        boolean z5;
        boolean z6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ScheduleTimeBean.Day day2 = this.mData;
        String week = null;
        if ((j & 7) != 0) {
            long j2 = j & 5;
            if (j2 != 0) {
                if (day2 != null) {
                    zIsToday = day2.isToday();
                    week = day2.getWeek();
                    zIsTomorrow = day2.isTomorrow();
                    day = day2.getDay();
                } else {
                    day = null;
                    zIsToday = false;
                    zIsTomorrow = false;
                }
                if (j2 != 0) {
                    j |= zIsToday ? 16L : 8L;
                }
                z5 = !zIsToday;
                z6 = !zIsTomorrow;
            } else {
                day = null;
                zIsToday = false;
                zIsTomorrow = false;
                z5 = false;
                z6 = false;
            }
            z = !(day2 != null ? day2.getSelected() : false);
            z2 = z5;
            str = day;
            z3 = z6;
        } else {
            str = null;
            z = false;
            zIsToday = false;
            zIsTomorrow = false;
            z2 = false;
            z3 = false;
        }
        long j3 = 5 & j;
        if (j3 != 0) {
            z4 = zIsToday ? true : zIsTomorrow;
        } else {
            z4 = false;
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z4);
            TextViewBindingAdapter.setText(this.mboundView2, week);
            DataBindingHelper.setViewGone(this.mboundView3, z4);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            DataBindingHelper.setViewGone(this.mboundView4, z2);
            DataBindingHelper.setViewGone(this.mboundView5, z3);
        }
    }
}
