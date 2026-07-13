package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public class DialogTopicRecordBindingImpl extends DialogTopicRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv, 2);
    }

    public DialogTopicRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private DialogTopicRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (RecyclerView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.bg.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
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
        setData((TopicDetailBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogTopicRecordBinding
    public void setData(TopicDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.mDirtyFlags     // Catch: java.lang.Throwable -> L2d
            r2 = 0
            r7.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L2d
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L2d
            com.cy.yyjia.zhe28.domain.TopicDetailBean r4 = r7.mData
            r5 = 3
            long r0 = r0 & r5
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L21
            if (r4 == 0) goto L19
            com.cy.yyjia.zhe28.domain.TopicDetailBean$Common r0 = r4.getCommon()
            goto L1a
        L19:
            r0 = r5
        L1a:
            if (r0 == 0) goto L21
            java.lang.String r0 = r0.getReward_log_bg()
            goto L22
        L21:
            r0 = r5
        L22:
            if (r6 == 0) goto L2c
            android.widget.ImageView r1 = r7.bg
            r2 = r5
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r1, r0, r5)
        L2c:
            return
        L2d:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L2d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.DialogTopicRecordBindingImpl.executeBindings():void");
    }
}
