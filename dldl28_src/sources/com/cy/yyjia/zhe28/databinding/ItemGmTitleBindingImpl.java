package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGmTitleBindingImpl extends ItemGmTitleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final ShapeTextView mboundView2;

    public ItemGmTitleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemGmTitleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
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
        setData((GMTitleBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGmTitleBinding
    public void setData(GMTitleBean Data) {
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
        return onChangeData((GMTitleBean) object, fieldId);
    }

    private boolean onChangeData(GMTitleBean Data, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L87
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L87
            monitor-exit(r18)     // Catch: java.lang.Throwable -> L87
            com.cy.yyjia.zhe28.domain.GMTitleBean r0 = r1.mData
            r6 = 7
            long r8 = r2 & r6
            r10 = 5
            r12 = 0
            r13 = 0
            int r14 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r14 == 0) goto L60
            long r8 = r2 & r10
            int r14 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r14 == 0) goto L4e
            if (r0 == 0) goto L29
            java.lang.String r12 = r0.getTier_name()
            int r8 = r0.getHas_access()
            goto L2a
        L29:
            r8 = 0
        L2a:
            r9 = 1
            if (r8 != 0) goto L2f
            r15 = 1
            goto L30
        L2f:
            r15 = 0
        L30:
            if (r8 != r9) goto L33
            goto L34
        L33:
            r9 = 0
        L34:
            if (r14 == 0) goto L3f
            if (r9 == 0) goto L3b
            r16 = 80
            goto L3d
        L3b:
            r16 = 40
        L3d:
            long r2 = r2 | r16
        L3f:
            if (r9 == 0) goto L44
            java.lang.String r8 = "已解锁"
            goto L46
        L44:
            java.lang.String r8 = "立即\n解锁"
        L46:
            if (r9 == 0) goto L4b
            java.lang.String r9 = "已解锁"
            goto L51
        L4b:
            java.lang.String r9 = "未解锁"
            goto L51
        L4e:
            r8 = r12
            r9 = r8
            r15 = 0
        L51:
            if (r0 == 0) goto L5c
            boolean r13 = r0.getSelected()
            r0 = r12
            r12 = r8
            r8 = r13
            r13 = r15
            goto L63
        L5c:
            r0 = r12
            r13 = r15
            r12 = r8
            goto L62
        L60:
            r0 = r12
            r9 = r0
        L62:
            r8 = 0
        L63:
            long r10 = r10 & r2
            int r14 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r14 == 0) goto L7c
            com.hjq.shape.view.ShapeTextView r10 = r1.btn
            r10.setEnabled(r13)
            com.hjq.shape.view.ShapeTextView r10 = r1.btn
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r12)
            android.widget.TextView r10 = r1.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r0)
            com.hjq.shape.view.ShapeTextView r0 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
        L7c:
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L86
            com.hjq.shape.layout.ShapeLinearLayout r0 = r1.mboundView0
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r0, r8)
        L86:
            return
        L87:
            r0 = move-exception
            monitor-exit(r18)     // Catch: java.lang.Throwable -> L87
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemGmTitleBindingImpl.executeBindings():void");
    }
}
