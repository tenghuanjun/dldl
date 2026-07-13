package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHomeGameBindingImpl extends FragmentHomeGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 1);
        sparseIntArray.put(R.id.ll_search, 2);
        sparseIntArray.put(R.id.tv_search, 3);
        sparseIntArray.put(R.id.vf, 4);
        sparseIntArray.put(R.id.tv_thunt, 5);
        sparseIntArray.put(R.id.tv_qiandao, 6);
        sparseIntArray.put(R.id.rv_type, 7);
        sparseIntArray.put(R.id.f438tv, 8);
        sparseIntArray.put(R.id.srl, 9);
        sparseIntArray.put(R.id.rv_game, 10);
    }

    public FragmentHomeGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentHomeGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppBarLayout) bindings[1], (LinearLayout) bindings[2], (RecyclerView) bindings[10], (RecyclerView) bindings[7], (SmartRefreshLayout) bindings[9], (TextView) bindings[8], (ImageView) bindings[6], (ShapeLinearLayout) bindings[3], (TextView) bindings[5], (AdapterViewFlipper) bindings[4]);
        this.mDirtyFlags = -1L;
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
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
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
