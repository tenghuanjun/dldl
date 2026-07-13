package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.VipGiftBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemVipGiftBindingImpl extends ItemVipGiftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;

    public ItemVipGiftBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemVipGiftBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
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
        setData((VipGiftBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemVipGiftBinding
    public void setData(VipGiftBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r8 = this;
            monitor-enter(r8)
            long r0 = r8.mDirtyFlags     // Catch: java.lang.Throwable -> L3a
            r2 = 0
            r8.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L3a
            com.cy.yyjia.zhe28.domain.VipGiftBean r4 = r8.mData
            r5 = 7
            long r0 = r0 & r5
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L2c
            if (r4 == 0) goto L19
            com.cy.yyjia.zhe28.domain.GameBean r0 = r4.getGame()
            goto L1a
        L19:
            r0 = r5
        L1a:
            r1 = 0
            r8.updateRegistration(r1, r0)
            if (r0 == 0) goto L2c
            java.lang.String r5 = r0.getName()
            java.lang.String r0 = r0.getIcon()
            r7 = r5
            r5 = r0
            r0 = r7
            goto L2d
        L2c:
            r0 = r5
        L2d:
            if (r6 == 0) goto L39
            android.widget.ImageView r1 = r8.mboundView1
            com.cy.yyjia.zhe28.util.DataBindingHelper.setGameIcon(r1, r5)
            android.widget.TextView r1 = r8.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r1, r0)
        L39:
            return
        L3a:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L3a
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemVipGiftBindingImpl.executeBindings():void");
    }
}
