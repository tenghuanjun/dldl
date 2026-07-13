package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentMainBindingImpl extends FragmentMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bg, 3);
        sparseIntArray.put(R.id.rv, 4);
        sparseIntArray.put(R.id.iv_qiandao, 5);
        sparseIntArray.put(R.id.vp, 6);
    }

    public FragmentMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private FragmentMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (View) bindings[3], (ImageView) bindings[5], (ImageView) bindings[2], (LinearLayout) bindings[1], (RelativeLayout) bindings[0], (RecyclerView) bindings[4], (ViewPager2) bindings[6]);
        this.mDirtyFlags = -1L;
        this.ivSearch.setTag(null);
        this.ll.setTag(null);
        this.rl.setTag(null);
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
        if (125 != variableId) {
            return false;
        }
        setWhite(((Boolean) variable).booleanValue());
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentMainBinding
    public void setWhite(boolean White) {
        this.mWhite = White;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(125);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int colorFromResource;
        ImageView imageView;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mWhite;
        long j2 = j & 3;
        if (j2 != 0) {
            if (j2 != 0) {
                j |= z ? 8L : 4L;
            }
            if (z) {
                imageView = this.ivSearch;
                i = R.color.colorWhite;
            } else {
                imageView = this.ivSearch;
                i = R.color.color_text_1;
            }
            colorFromResource = getColorFromResource(imageView, i);
        } else {
            colorFromResource = 0;
        }
        if ((3 & j) != 0 && getBuildSdkInt() >= 21) {
            this.ivSearch.setImageTintList(Converters.convertColorToColorStateList(colorFromResource));
        }
        if ((j & 2) != 0) {
            DataBindingHelper.setFitWindow(this.ll, true);
        }
    }
}
