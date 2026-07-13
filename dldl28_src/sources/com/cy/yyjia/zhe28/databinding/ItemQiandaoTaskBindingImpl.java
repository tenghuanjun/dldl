package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemQiandaoTaskBindingImpl extends ItemQiandaoTaskBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView2;
    private final View mboundView4;

    public ItemQiandaoTaskBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemQiandaoTaskBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[3], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        View view = (View) bindings[4];
        this.mboundView4 = view;
        view.setTag(null);
        this.tvName.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (38 == variableId) {
            setHideLine(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((TaskBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemQiandaoTaskBinding
    public void setHideLine(boolean HideLine) {
        this.mHideLine = HideLine;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(38);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemQiandaoTaskBinding
    public void setData(TaskBean Data) {
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
        return onChangeData((TaskBean) object, fieldId);
    }

    private boolean onChangeData(TaskBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 28) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L97
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L97
            monitor-exit(r19)     // Catch: java.lang.Throwable -> L97
            boolean r0 = r1.mHideLine
            com.cy.yyjia.zhe28.domain.TaskBean r6 = r1.mData
            r7 = 10
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r7 = 13
            long r7 = r7 & r2
            r10 = 9
            r12 = 0
            r13 = 0
            int r14 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r14 == 0) goto L65
            if (r6 == 0) goto L25
            java.lang.String r7 = r6.getDone()
            goto L26
        L25:
            r7 = r12
        L26:
            if (r7 == 0) goto L35
            java.lang.String r8 = "yes"
            boolean r8 = r7.equals(r8)
            java.lang.String r15 = "not_receive"
            boolean r7 = r7.equals(r15)
            goto L37
        L35:
            r7 = 0
            r8 = 0
        L37:
            r8 = r8 ^ 1
            long r15 = r2 & r10
            int r17 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r17 == 0) goto L62
            if (r6 == 0) goto L54
            java.lang.String r12 = r6.getBtnText()
            java.lang.String r13 = r6.getCreditText()
            java.lang.String r6 = r6.getName()
            r18 = r13
            r13 = r6
            r6 = r12
            r12 = r18
            goto L56
        L54:
            r6 = r12
            r13 = r6
        L56:
            java.lang.String r15 = ""
            boolean r15 = r15.equals(r12)
            r18 = r12
            r12 = r6
            r6 = r18
            goto L6a
        L62:
            r6 = r12
            r13 = r6
            goto L69
        L65:
            r6 = r12
            r13 = r6
            r7 = 0
            r8 = 0
        L69:
            r15 = 0
        L6a:
            if (r14 == 0) goto L76
            com.hjq.shape.view.ShapeTextView r14 = r1.btn
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r14, r7)
            com.hjq.shape.view.ShapeTextView r7 = r1.btn
            r7.setEnabled(r8)
        L76:
            long r2 = r2 & r10
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 == 0) goto L8f
            com.hjq.shape.view.ShapeTextView r2 = r1.btn
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r12)
            android.widget.TextView r2 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r6)
            android.widget.TextView r2 = r1.mboundView2
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r2, r15)
            android.widget.TextView r2 = r1.tvName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r13)
        L8f:
            if (r9 == 0) goto L96
            android.view.View r2 = r1.mboundView4
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r2, r0)
        L96:
            return
        L97:
            r0 = move-exception
            monitor-exit(r19)     // Catch: java.lang.Throwable -> L97
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemQiandaoTaskBindingImpl.executeBindings():void");
    }
}
