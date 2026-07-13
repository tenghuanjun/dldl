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
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHomeNewBindingImpl extends FragmentHomeNewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 1);
        sparseIntArray.put(R.id.ll_search, 2);
        sparseIntArray.put(R.id.tv_search, 3);
        sparseIntArray.put(R.id.vf, 4);
        sparseIntArray.put(R.id.tv_qiandao, 5);
        sparseIntArray.put(R.id.rv_type, 6);
        sparseIntArray.put(R.id.ll, 7);
        sparseIntArray.put(R.id.f438tv, 8);
        sparseIntArray.put(R.id.vp, 9);
    }

    public FragmentHomeNewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FragmentHomeNewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppBarLayout) bindings[1], (LinearLayout) bindings[7], (LinearLayout) bindings[2], (RecyclerView) bindings[6], (TextView) bindings[8], (ImageView) bindings[5], (ShapeLinearLayout) bindings[3], (AdapterViewFlipper) bindings[4], (ViewPager2) bindings[9]);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeNewBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeNewBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
