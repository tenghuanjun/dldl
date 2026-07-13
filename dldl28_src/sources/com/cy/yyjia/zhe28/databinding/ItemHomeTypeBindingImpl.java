package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeTypeBindingImpl extends ItemHomeTypeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final ImageView mboundView1;
    private final ShapeTextView mboundView2;

    public ItemHomeTypeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemHomeTypeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
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
        setData((TypeBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeTypeBinding
    public void setData(TypeBean Data) {
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
        return onChangeData((TypeBean) object, fieldId);
    }

    private boolean onChangeData(TypeBean Data, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:29:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r21 = this;
            r1 = r21
            monitor-enter(r21)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L9a
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r21)     // Catch: java.lang.Throwable -> L9a
            com.cy.yyjia.zhe28.domain.TypeBean r0 = r1.mData
            r6 = 7
            long r8 = r2 & r6
            r10 = 5
            r12 = 8
            r14 = 1
            r15 = 0
            r16 = 0
            int r17 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r17 == 0) goto L4f
            if (r0 == 0) goto L23
            boolean r8 = r0.getSelected()
            goto L24
        L23:
            r8 = 0
        L24:
            long r17 = r2 & r10
            int r9 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r9 == 0) goto L4c
            if (r0 == 0) goto L35
            java.lang.String r17 = r0.getName()
            java.lang.String r0 = r0.getIcon()
            goto L38
        L35:
            r0 = r15
            r17 = r0
        L38:
            if (r0 != 0) goto L3d
            r18 = 1
            goto L3f
        L3d:
            r18 = 0
        L3f:
            if (r9 == 0) goto L49
            if (r18 == 0) goto L48
            r19 = 16
            long r2 = r2 | r19
            goto L49
        L48:
            long r2 = r2 | r12
        L49:
            r9 = r17
            goto L54
        L4c:
            r0 = r15
            r9 = r0
            goto L52
        L4f:
            r0 = r15
            r9 = r0
            r8 = 0
        L52:
            r18 = 0
        L54:
            long r12 = r12 & r2
            int r17 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r17 == 0) goto L65
            if (r0 == 0) goto L60
            int r12 = r0.length()
            goto L61
        L60:
            r12 = 0
        L61:
            if (r12 != 0) goto L65
            r12 = 1
            goto L66
        L65:
            r12 = 0
        L66:
            long r10 = r10 & r2
            int r13 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r13 == 0) goto L70
            if (r18 == 0) goto L6e
            goto L71
        L6e:
            r14 = r12
            goto L71
        L70:
            r14 = 0
        L71:
            long r2 = r2 & r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L85
            com.hjq.shape.layout.ShapeLinearLayout r2 = r1.mboundView0
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r2, r8)
            com.hjq.shape.view.ShapeTextView r2 = r1.mboundView2
            com.cy.yyjia.zhe28.util.DataBindingHelper.setBold(r2, r8)
            com.hjq.shape.view.ShapeTextView r2 = r1.mboundView2
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r2, r8)
        L85:
            if (r13 == 0) goto L99
            android.widget.ImageView r2 = r1.mboundView1
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r2, r14)
            android.widget.ImageView r2 = r1.mboundView1
            r3 = r15
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r2, r0, r15)
            com.hjq.shape.view.ShapeTextView r0 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
        L99:
            return
        L9a:
            r0 = move-exception
            monitor-exit(r21)     // Catch: java.lang.Throwable -> L9a
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemHomeTypeBindingImpl.executeBindings():void");
    }
}
