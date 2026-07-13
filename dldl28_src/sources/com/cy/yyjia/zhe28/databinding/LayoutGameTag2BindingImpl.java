package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutGameTag2BindingImpl extends LayoutGameTag2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeTextView mboundView1;
    private final ShapeTextView mboundView2;
    private final ShapeTextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public LayoutGameTag2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private LayoutGameTag2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView2;
        shapeTextView2.setTag(null);
        ShapeTextView shapeTextView3 = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView3;
        shapeTextView3.setTag(null);
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
        setData((List) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutGameTag2Binding
    public void setData(List<String> Data) {
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
        long j2;
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String str2;
        String str3;
        int size;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        List<String> list = this.mData;
        long j3 = j & 3;
        if (j3 != 0) {
            if (list != null) {
                str = list.get(0);
                size = list.size();
            } else {
                str = null;
                size = 0;
            }
            boolean z5 = size > 1;
            boolean z6 = size < 2;
            boolean z7 = size < 3;
            z4 = size > 2;
            if (j3 != 0) {
                j = z5 ? j | 8 : j | 4;
            }
            if ((j & 3) == 0) {
                j2 = 32;
            } else if (z4) {
                j2 = 32;
                j |= 32;
            } else {
                j2 = 32;
                j |= 16;
            }
            z3 = z7;
            z2 = z5;
            z = z6;
        } else {
            j2 = 32;
            str = null;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        String str4 = ((j & j2) == 0 || list == null) ? null : list.get(2);
        String str5 = ((8 & j) == 0 || list == null) ? null : list.get(1);
        long j4 = j & 3;
        if (j4 != 0) {
            if (!z2) {
                str5 = "";
            }
            str3 = str5;
            str2 = z4 ? str4 : "";
        } else {
            str2 = null;
            str3 = null;
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
            DataBindingHelper.setViewGone(this.mboundView2, z);
            TextViewBindingAdapter.setText(this.mboundView2, str3);
            DataBindingHelper.setViewGone(this.mboundView3, z3);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
        }
    }
}
