package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivitySplashBindingImpl extends ActivitySplashBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_skip, 4);
    }

    public ActivitySplashBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivitySplashBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (ShapeTextView) bindings[2], (ShapeTextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.iv.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        this.tvGo.setTag(null);
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
        if (44 != variableId) {
            return false;
        }
        setInfo((UpdateBean.BaseInfoBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySplashBinding
    public void setInfo(UpdateBean.BaseInfoBean Info) {
        this.mInfo = Info;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(44);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e A[PHI: r6
  0x001e: PHI (r6v1 boolean) = (r6v0 boolean), (r6v3 boolean) binds: [B:6:0x0011, B:9:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r8 = this;
            monitor-enter(r8)
            long r0 = r8.mDirtyFlags     // Catch: java.lang.Throwable -> L34
            r2 = 0
            r8.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L34
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L34
            com.cy.yyjia.zhe28.domain.UpdateBean$BaseInfoBean r4 = r8.mInfo
            r5 = 3
            long r0 = r0 & r5
            r5 = 0
            r6 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L1e
            if (r4 != 0) goto L17
            r0 = 1
            r6 = 1
        L17:
            if (r4 == 0) goto L1e
            java.lang.String r0 = r4.getPic()
            goto L1f
        L1e:
            r0 = r5
        L1f:
            if (r7 == 0) goto L33
            android.widget.ImageView r1 = r8.iv
            r2 = r5
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r1, r0, r5)
            android.widget.ImageView r0 = r8.mboundView3
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r6)
            com.hjq.shape.view.ShapeTextView r0 = r8.tvGo
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r6)
        L33:
            return
        L34:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L34
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivitySplashBindingImpl.executeBindings():void");
    }
}
