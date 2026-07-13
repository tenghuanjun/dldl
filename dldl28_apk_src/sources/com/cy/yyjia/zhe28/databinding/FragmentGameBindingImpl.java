package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentGameBindingImpl extends FragmentGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_type, 2);
        sparseIntArray.put(R.id.srl, 3);
        sparseIntArray.put(R.id.rv_game, 4);
    }

    public FragmentGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private FragmentGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[4], (RecyclerView) bindings[2], (SmartRefreshLayout) bindings[3]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
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
        if (6 != variableId) {
            return false;
        }
        setBanner((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameBinding
    public void setBanner(String Banner) {
        this.mBanner = Banner;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mBanner;
        long j2 = j & 3;
        boolean z2 = false;
        if (j2 != 0) {
            z = str == null;
            if (j2 != 0) {
                j = z ? j | 8 : j | 4;
            }
        } else {
            z = false;
        }
        boolean zEquals = ((4 & j) == 0 || str == null) ? false : str.equals("");
        long j3 = j & 3;
        if (j3 != 0) {
            z2 = z ? true : zEquals;
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setImg(this.mboundView1, str, null);
        }
    }
}
