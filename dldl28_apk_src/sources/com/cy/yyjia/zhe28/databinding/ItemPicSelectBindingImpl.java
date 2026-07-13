package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: loaded from: classes2.dex */
public class ItemPicSelectBindingImpl extends ItemPicSelectBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final CardView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemPicSelectBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemPicSelectBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (ImageView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.iv.setTag(null);
        this.ivDelete.setTag(null);
        CardView cardView = (CardView) bindings[0];
        this.mboundView0 = cardView;
        cardView.setTag(null);
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
        setData((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemPicSelectBinding
    public void setData(String Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r15 = this;
            monitor-enter(r15)
            long r0 = r15.mDirtyFlags     // Catch: java.lang.Throwable -> L50
            r2 = 0
            r15.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L50
            monitor-exit(r15)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = r15.mData
            r5 = 3
            long r7 = r0 & r5
            r9 = 4
            r11 = 1
            r12 = 0
            int r13 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r13 == 0) goto L25
            if (r4 != 0) goto L1a
            r7 = 1
            goto L1b
        L1a:
            r7 = 0
        L1b:
            if (r13 == 0) goto L26
            if (r7 == 0) goto L23
            r13 = 8
            long r0 = r0 | r13
            goto L26
        L23:
            long r0 = r0 | r9
            goto L26
        L25:
            r7 = 0
        L26:
            long r8 = r0 & r9
            int r10 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r10 == 0) goto L38
            if (r4 == 0) goto L33
            int r8 = r4.length()
            goto L34
        L33:
            r8 = 0
        L34:
            if (r8 != 0) goto L38
            r8 = 1
            goto L39
        L38:
            r8 = 0
        L39:
            long r0 = r0 & r5
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L43
            if (r7 == 0) goto L41
            goto L42
        L41:
            r11 = r8
        L42:
            r12 = r11
        L43:
            if (r5 == 0) goto L4f
            android.widget.ImageView r0 = r15.iv
            com.cy.yyjia.zhe28.util.DataBindingHelper.selectPic(r0, r4)
            android.widget.ImageView r0 = r15.ivDelete
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r12)
        L4f:
            return
        L50:
            r0 = move-exception
            monitor-exit(r15)     // Catch: java.lang.Throwable -> L50
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemPicSelectBindingImpl.executeBindings():void");
    }
}
