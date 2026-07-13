package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentVipRightBindingImpl extends FragmentVipRightBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final ProgressBar mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bg, 5);
        sparseIntArray.put(R.id.tvb, 6);
        sparseIntArray.put(R.id.tv_current, 7);
    }

    public FragmentVipRightBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private FragmentVipRightBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (ImageView) bindings[7], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[3];
        this.mboundView3 = progressBar;
        progressBar.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (79 == variableId) {
            setProgress(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((VipListBean.ListBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentVipRightBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentVipRightBinding
    public void setProgress(int Progress) {
        this.mProgress = Progress;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(79);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentVipRightBinding
    public void setData(VipListBean.ListBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String pic;
        String desc;
        String str;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mProgress;
        VipListBean.ListBean listBean = this.mData;
        long j2 = 14 & j;
        if (j2 != 0) {
            long j3 = j & 12;
            if (j3 == 0 || listBean == null) {
                pic = null;
                desc = null;
            } else {
                pic = listBean.getPic();
                desc = listBean.getDesc();
            }
            int growth_alue = listBean != null ? listBean.getGrowth_alue() : 0;
            i = i >= growth_alue ? 1 : 0;
            if (j3 != 0) {
                str = ("成长值达到" + growth_alue) + "可升级";
            } else {
                str = null;
            }
            int i2 = i;
            i = growth_alue;
            z = i2;
        } else {
            pic = null;
            desc = null;
            str = null;
            z = 0;
        }
        if ((12 & j) != 0) {
            DataBindingHelper.setImg(this.mboundView1, pic, null);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            this.mboundView3.setMax(i);
            TextViewBindingAdapter.setText(this.mboundView4, desc);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z);
        }
        if ((j & 10) != 0) {
            this.mboundView3.setProgress(i);
        }
    }
}
